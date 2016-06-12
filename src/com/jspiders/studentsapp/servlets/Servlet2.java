package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet2 extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//Get the ServletContext Object
		ServletContext context = getServletContext();
		String movie1Val = context.getInitParameter("movie1");
		String movie2Val = context.getInitParameter("movie2");
		
		//Get the ServletConfig Object
		ServletConfig config = getServletConfig();
		String actress1Val = config.getInitParameter("actress1");
		String actress2Val = config.getInitParameter("actress2");
		String actor1Val = config.getInitParameter("actor1");
		String actor2Val = config.getInitParameter("actor2");
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(" Movie 1 Value : "+movie1Val);
		out.println(" Movie 2 Value : "+movie2Val);
		out.println(" Actress 1 Value : "+actress1Val);
		out.println(" Actress 2 Value : "+actress2Val);
		
	}//End of doGet
}//End of Class
