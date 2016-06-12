<html>
<body>
	1111111111111
	<BR><BR>
	
	<jsp:include page="index.html" />
	
	<BR><BR>
	2222222222222
	<BR><BR>
	
	<jsp:include page="currentDate" />
	
	<BR><BR>
	333333333333
	<BR><BR>
	
	<jsp:include page="currentDate">
		<jsp:param  name="fname" value="111"/>
		<jsp:param  name="lname" value="222"/>
	</jsp:include>
	
	<BR><BR>
	4444444444444
	<BR><BR>
	
	<jsp:include page="currentDateJsp" />
	
	<BR><BR>
	5555555555555
	<BR><BR>
	
</body>
</html>