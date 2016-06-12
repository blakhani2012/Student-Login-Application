package com.jspiders.studentsapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		/*
		 * Don't generate the Response
		 * Instead Redirect the Request
		 */
		//External URL
		//String url = "http://www.gmail.com";
		
		//Internal URL - Dynamic
		//String url = "http://localhost:8080/studentsApp/currentDate";
		//String url = "currentDate";
		
		//Internal URL - Static
		String url = "index.html";
		resp.sendRedirect(url);
		
	}//End of doGet
}//End of Class
