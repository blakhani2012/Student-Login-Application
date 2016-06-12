package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncludeServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("111111111111111111");
		
		RequestDispatcher dispatcher
		 = req.getRequestDispatcher("index.html");
		dispatcher.include(req, resp);
		
		out.println("222222222222222222");
		
		RequestDispatcher dispatcher2
		 = req.getRequestDispatcher("currentDate?fname=123&lname=456");
		dispatcher2.include(req, resp);
		
		out.println("333333333333333333");
		
	}//End of doGet
	
}//End of Class
