package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCookieServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		/*
		 * Get the Cookies from request
		 */
		Cookie[] receivedCookies = req.getCookies();
		
		if(receivedCookies==null)
		{
			out.println("No Cookies are present in the Request !!!");
		}else{
			out.println("Cookies are present in the Request ...");
			
			for(Cookie receivedCookie : receivedCookies)
			{
				if(receivedCookie.getName().equals("myLocation"))
				{
					receivedCookie.setMaxAge(0);
					resp.addCookie(receivedCookie);
					out.println(" Deleted the 'myLocation' Cookie ...");
					break;
				}//End of if
			}//End of for
		}//End of if-else
	}//End of doGet
}//End of Class
