package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		/*
		 * III. In-Validate the Session ID
		 */
		HttpSession session = req.getSession(false);
		
		if(session != null)
		{
			session.invalidate();
		}
		
		out.println("<font color=\"green\">");     
		out.println("Successfully  Logged out of the Application ...");
		out.println("</font>");
		
		out.println("<BR><BR>");
		
		RequestDispatcher dispatcher
			=  req.getRequestDispatcher("Login.html");
		dispatcher.include(req, resp);
		
	}//End of doGet
}//End of Class
