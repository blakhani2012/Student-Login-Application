package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFirstServlet 
extends HttpServlet
implements SingleThreadModel
{
	public static void main(String[] args) 
	{
		System.out.println("Inside Main Method");
	}
	
	public MyFirstServlet()
	{
		System.out.println("Inside Constructor ...");
	}
	
	@Override
	public void init() 
	throws ServletException 
	{
		System.out.println("Inside init() Method ...");
	}
	
	
	@Override
	protected synchronized void doGet(HttpServletRequest req, 
						 HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//int i = 100;
		
		System.out.println("Inside service() Method ...");
		//Java Code to Generate Current Date & Time
		Date dateRef = new Date();
		String currentDate = dateRef.toString();
		
		//Get the Query String Information
		String fNM = req.getParameter("fname");
		String lNM = req.getParameter("lname");
		
		//Error Simulation
		//int i = 100/0;
		
		/*String htmlRes = "<html>" 
						+"<body>" 
						+"<h1>" 
						+"<B>Current Date & Time is : </B>"
						+"<font color=\"blue\">"
						+currentDate
						+"</font>"
						+"<BR><BR>"
						+"First Name : "+fNM
						+"<BR>"
						+"Last Name : "+lNM
						+"</h1>" 
						+"</body>"
						+"</html>";*/
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		//out.println(htmlRes);
		out.println("<html>"                          );  
		out.println("<body>"                          );
		out.println("<h1>"                            );
		out.println("<B>Current Date & Time is : </B>");
		out.println("<font color=\"blue\">"           );
		out.println(currentDate);
		out.println("</font>"                         );
		out.println("<BR><BR>"                        );
		out.println("First Name : "+fNM               );
		out.println("<BR>"                            );
		out.println("Last Name : "+lNM                );
		out.println("</h1>"                           );
		
		for(int i=0; i<5; i++)
		{
			out.println("<BR>");
			out.println("First Name : "+fNM);
		}
		
		out.println("</body>"                         );
		out.println("</html>"                         );
		
	}//End of doGet
	
	
	@Override
	public void destroy() 
	{
		System.out.println("Inside destroy() Method ...");
	}
	
}//End of Class






