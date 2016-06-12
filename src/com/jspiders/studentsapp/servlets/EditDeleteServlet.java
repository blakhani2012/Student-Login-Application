package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Driver;

public class EditDeleteServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		/*
		 * II. Validate the Session ID
		 */
		HttpSession session = req.getSession(false);
		
		
		if(session == null)
		{
			//Invalid Session; Generate Login Page
			out.println("<font color=\"red\">");     
			out.println("In-Valid Session !!! Pls Login ...");
			out.println("</font>");
			
			out.println("<BR><BR>");
			
			RequestDispatcher dispatcher
				=  req.getRequestDispatcher("Login.html");
			dispatcher.include(req, resp);
		}
		
		else
		{
			//Valid Session; Generate Proper Response
			String regno = req.getParameter("regno");
			String action = req.getParameter("action");
			
			RequestDispatcher header
				=  req.getRequestDispatcher("Header.html");
			header.include(req, resp);
			out.println("<BR><BR><BR>");
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
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
								" and soi.regno = ?         ";;
				
				System.out.println("Query : "+query);
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, Integer.parseInt(regno));
				rs = pstmt.executeQuery();
				
				//4. Process the Results returned by SQL Queries
				if(rs.next())
				{
					out.println("<html>																							");
					out.println("<body>                                                                                         ");
					out.println("	<form action=\"#\" method=\"post\">                                                         ");
					out.println("		<table>                                                                                 ");
					out.println("			<tr>                                                                                ");
					out.println("				<td>Reg No</td>                                                                 ");
					
					if(action.equals("edit")){
						out.println("				<td><input type=\"text\" name=\"regno\" value=\""+rs.getInt("si.regno")+"\" readonly=\"true\"></td>   ");
					}else{
						out.println("				<td>"+rs.getInt("si.regno")+"</td>   ");
					}
					
					
					
					out.println("			</tr>                                                                               ");
					out.println("			<tr>                                                                                ");
					out.println("				<td>First Name</td>                                                             ");
					if(action.equals("edit")){
						out.println("				<td><input type=\"text\" name=\"fnm\" value=\""+rs.getString("si.firstname")+"\"></td>                       ");
					}else{
						out.println("				<td>"+rs.getString("si.firstname")+"</td>   ");
					}
					
					out.println("			</tr>                                                                               ");
					out.println("			<tr>                                                                                ");
					out.println("				<td>Guardian First Name</td>                                                    ");
					if(action.equals("edit")){
						out.println("				<td><input type=\"text\" name=\"gfnm\" value=\""+rs.getString("gi.gfirstname")+"\"></td>                      ");
					}else{
						out.println("				<td>"+rs.getString("gi.gfirstname")+"</td>   ");
					}
					
					out.println("			</tr>                                                                               ");
					out.println("			<tr>                                                                                ");
					out.println("				<td>Password</td>                                                               ");
					if(action.equals("edit")){
						out.println("				<td><input type=\"password\" name=\"pass\" value=\""+rs.getString("soi.password")+"\"></td>                  ");
					}else{
						out.println("				<td>"+rs.getString("soi.password")+"</td>   ");
					}
					
					out.println("			</tr>                                                                               ");
					out.println("			<tr>                                                                                ");
					if(action.equals("edit")){
						out.println("				<td><input type=\"submit\" value=\"Edit\"></td>                        ");
					}else{
						out.println("				<td><input type=\"submit\" value=\"Delete\"></td>                        ");
					}
					
					out.println("				<td><input type=\"reset\"  value=\"Reset\"></td>                                ");
					out.println("			</tr>                                                                               ");
					out.println("			<input type=\"hidden\"  name=\"action\" value=\"edit\">			                    ");
					out.println("		</table>                                                                                ");
					out.println("	</form>                                                                                     ");
					out.println("</body>                                                                                        ");
					out.println("</html>                                                                                        ");
				}else{
					out.println("Unable to Find Reg. No. !!!");
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
			}//End of Outer try-catch
			
			out.println("<BR><BR><BR>");
			RequestDispatcher footer
				=  req.getRequestDispatcher("Footer.html");
			footer.include(req, resp);
		}
		
	}//End of doGet
}//End of Class
