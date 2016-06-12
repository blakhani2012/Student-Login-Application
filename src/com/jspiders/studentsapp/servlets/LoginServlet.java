package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//1.1. Get the Form Data
		String regNum = req.getParameter("regno");
		String password = req.getParameter("pass");
		
		//1.2. Validate the Form Data
		if(regNum==null || regNum.trim().isEmpty())
		{
			out.println("<font color=\"red\">");     
			out.println("In-Valid User Name / Password");
			out.println("</font>");
			
			out.println("<BR><BR>");
			
			RequestDispatcher dispatcher
				=  req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
			
		}
		
		else if(password==null || password.trim().isEmpty()) {
			out.println("<font color=\"red\">");     
			out.println("In-Valid User Name / Password");
			out.println("</font>");
			
			out.println("<BR><BR>");
			
			RequestDispatcher dispatcher
				=  req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}
		
		else
		
		{
			
			try 
			{
				int regno = Integer.parseInt(regNum);
				//2. Authenticate the Credentials
				Connection con = null;
				PreparedStatement pstmt = null;
				Statement stmt = null;
				ResultSet rs = null;
				ResultSet rs2 = null;
				
				try 
				{
					//1. Load the Driver
					Class.forName("com.mysql.jdbc.Driver").newInstance();
					
					//2. Get the DB Connection via Driver
					String dbUrl = "jdbc:mysql://localhost:3306/BECM3?user=j2ee&password=j2ee";
					con = DriverManager.getConnection(dbUrl);
					
					//3. Issue SQL Queries via Connection
					String query = " select * from             "+  
									" students_info si,         "+
									" guardian_info gi,         "+
									" students_otherinfo soi    "+
									" where si.regno = gi.regno "+
									" and si.regno = soi.regno  "+
									" and soi.regno = ?         "+
									" and soi.password = ?      ";
					
					System.out.println("Query : "+query);
					
					pstmt = con.prepareStatement(query);
					pstmt.setInt(1, Integer.parseInt(regNum));
					pstmt.setString(2, password);
					rs = pstmt.executeQuery();
					
					//4. Process the Results returned by SQL Queries
					if(rs.next())
					{
						/*
						 * Valid Credentials;
						 * I. Create the Session 
						 * for the First Time
						 */
						HttpSession session = req.getSession(true);
						//Time in Sec
						session.setMaxInactiveInterval(1*60);
						
						System.out.println("Session ID : "+session.getId());
						
						
						//Check Admin Rights
						String isAdmin = rs.getString("soi.isadmin");
						
						RequestDispatcher header
							=  req.getRequestDispatcher("Header.html");
						header.include(req, resp);
						out.println("<BR><BR><BR>");
					
						out.println("<html>								");
						out.println("<body>                             ");
						out.println("	<table>                         ");
						out.println("		<tr bgcolor=\"pink\">      ");
						out.println("			<td>Reg. No. </td>      ");
						out.println("			<td>First Name</td>     ");
						out.println("			<td>Middle Name</td>    ");
						out.println("			<td>Last Name</td>      ");
						out.println("			<td>G First Name</td>   ");
						out.println("			<td>G Middle Name</td>  ");
						out.println("			<td>G Last Name</td>    ");
						out.println("			<td>Edit</td>  ");
						out.println("			<td>Delete</td>    ");
						out.println("		</tr>                       ");
						
						if(isAdmin.equals("Y"))
						{
							//Admin User
							String query2 = " select * from             "
											+" students_info si,         "
											+" guardian_info gi          "
											+" where si.regno = gi.regno ";
							stmt = con.createStatement();
							rs2 = stmt.executeQuery(query2);
							
							while(rs2.next())
							{
								out.println("		<tr>                        ");
								out.println("			<td>"+rs2.getInt("si.regno")+" </td>             ");
								out.println("			<td>"+rs2.getString("si.firstname")+"</td>            ");
								out.println("			<td>"+rs2.getString("si.middlename")+"</td>             ");
								out.println("			<td>"+rs2.getString("si.lastname")+"</td>            ");
								out.println("			<td>"+rs2.getString("gi.gfirstname")+"</td>   ");
								out.println("			<td>"+rs2.getString("gi.gmiddlename")+"</td>  ");
								out.println("			<td>"+rs2.getString("gi.glastname")+"</td>    ");
								
								String editUrl = "./editDelete?action=edit&regno="+rs2.getInt("si.regno");
								String deleteUrl = "./editDelete?action=delete&regno="+rs2.getInt("si.regno");
								
								String encodedEditUrl = resp.encodeURL(editUrl);
								String encodedDeleteUrl = resp.encodeURL(deleteUrl);
								
								
								out.println("			<td><a href=\""+encodedEditUrl+"\">Edit</a></td>");
								out.println("			<td><a href=\""+encodedDeleteUrl+"\">Delete</a></td>");
								out.println("		</tr>                       ");
							}//End of while
							
						}else{
							//Normal user
							out.println("		<tr>                        ");
							out.println("			<td>"+rs.getInt("si.regno")+" </td>             ");
							out.println("			<td>"+rs.getString("si.firstname")+"</td>            ");
							out.println("			<td>"+rs.getString("si.middlename")+"</td>             ");
							out.println("			<td>"+rs.getString("si.lastname")+"</td>            ");
							out.println("			<td>"+rs.getString("gi.gfirstname")+"</td>   ");
							out.println("			<td>"+rs.getString("gi.gmiddlename")+"</td>  ");
							out.println("			<td>"+rs.getString("gi.glastname")+"</td>    ");
							out.println("			<td>NA</td>");
							out.println("			<td>NA</td>");
							out.println("		</tr>                       ");
						}
						
						out.println("	</table>                        ");
						out.println("</body>                            ");
						out.println("</html>                            ");
						
						out.println("<BR><BR><BR>");
						RequestDispatcher footer
							=  req.getRequestDispatcher("Footer.html");
						footer.include(req, resp);
						
					}else{
						out.println("<font color=\"red\">");     
						out.println("In-Valid User Name / Password");
						out.println("</font>");
						
						out.println("<BR><BR>");
						
						RequestDispatcher dispatcher
							=  req.getRequestDispatcher("Login.html");
						dispatcher.include(req, resp);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally{
					//5. Close all JDBC Objects
					try 
					{
						if(con != null){
							con.close();
						}
						if(pstmt != null){
							pstmt.close();
						}
						if(rs != null){
							rs.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}//End of Authentication
			} catch (NumberFormatException e1) {
				out.println("<font color=\"red\">");     
				out.println("In-Valid User Name / Password");
				out.println("</font>");
				
				out.println("<BR><BR>");
				
				RequestDispatcher dispatcher
					=  req.getRequestDispatcher("Login.html");
				dispatcher.include(req, resp);
			}
		}//End of Validation
	}//End of doPost
}//End of Class
