package com.jspiders.studentsapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException 
	{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		//Get the ServletContext Object
		ServletContext context = getServletContext();
		
		Object obj = context.getAttribute("Contextkey");

		if(obj==null)
		{
			out.println(" Unable to Find Context Attribute !!! ");
		}else{
			out.println(" Context Attribute is Present ... ");
			out.println(" Value : "+obj.toString());
		}
		
		String val = (String)req.getAttribute("reqKey");
		
		if(val==null){
			out.println(" Unable to Find Request Attribute !!! ");
		}else{
			out.println(" Request Attribute is Present ... ");
			out.println(" Value : "+val);
		}
		
	}//End of doGet
}//End of Class
