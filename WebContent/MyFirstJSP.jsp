<%@page import="java.util.Date"%>

<html>
<body>
	<!-- HTML Comment -->
	<%-- JSP Comment --%>
	<%
		//Java Code to Generate Current Date & Time
		Date dateRef = new Date(); 
		String currentDate = dateRef.toString();
		
		String fnm = request.getParameter("fname");
		String lnm = request.getParameter("lname");
		String contextParam 
			= application.getInitParameter("movie1");
	%>
	<h1>
		<B> Current Date & Time is : </B> 
		<font color="green"><%=currentDate%></font>
	</h1>
	<BR><BR>
	First Name : <%= fnm %>
	<BR>
	Last Name : <%= lnm %>
	<BR>
	Context Param : <%= contextParam %>
</body>
</html>