<%!
	public void jspInit()
	{
		System.out.println("Inside jspInit() ...");
	}

	public void jspDestroy()
	{
		System.out.println("Inside jspDestroy() ...");
	}
%>
<html>
<body>
	<%
	System.out.println("Inside _jspService() ...");
	%>
	<h1>JSP Life Cycle Example</h1>
</body>
</html>