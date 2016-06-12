<html>
<body>
	Creating the Object ...
	<jsp:useBean 
		id="myBean"
		class="com.jspiders.studentsapp.servlets.MyJavaBean"
		scope="request" />
	
	Reg. No. Value is :
	<jsp:getProperty name="myBean" property="regno" />
	<br>
	Changing the Reg. No.
	<jsp:setProperty name="myBean" property="regno" value="100" />
	 <br>
	New Reg. No. Value is :
	<jsp:getProperty name="myBean" property="regno" />
	
</body>
</html>