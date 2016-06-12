package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		//Get the ServletContext Object
		ServletContext context = getServletContext();
		context.setAttribute("Contextkey", new Object());	
		
		req.setAttribute("reqKey", "ABCD");
		
		String url = "second";
		RequestDispatcher dispatcher
			=	req.getRequestDispatcher(url);
		dispatcher.forward(req, resp);
		
		/*resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("Context & Request Attributes are Set");*/
		
		
	}//End of doGet
}//End of Class
