<%!
	private int age = 100;
	public String name = "Rajani Kanth";

	public int getAge()	{
		return age;
	}
	
	public String getName()	{
		return name;
	}
	
	public String getName(String givenNM){
		return "Given name is - "+givenNM;
	}
%>

<html>
<body>
	<h1>JSP Tags Example</h1>
	<BR><BR>
	Name 1 : <%= name %>
	<BR>
	Name 2 : <%= getName() %>
	<BR>
	Name 3 : <%= getName("Praveen D")  %>
	<BR>
	Name 4 : <%= getName()+getName("Praveen D")  %>
	<BR>
	Age 1 : <%= age %>
	<BR>
	Age 2 : <%= getAge() %>
	
	<%
		int j=100;
		for(int i=0; i<5; i++)
		{
			
	%>
	
			<BR>
			Name : <%= name %>
			
			
	<%		
		}
	%>
	
	
	
	
	
	
</body>
</html>