<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
<style type="text/css">
#padding{
	padding: 0px 0px 15px 15px;
}
a {
	color: #0B173B;
	font-size: 30px;
	text-decoration: none;
}
a:hover{
text-decoration:underline;
}
.border-style {
	border-radius: 90px/90px;
}
.filter-style{
	border-radius:90px/90px;
}

</style>
</head>
<body>
<body style='background-color: #01A9DB'>
<form action='${requestUri}' method='get'>

	<div style='position: absolute;margin-top:30px;margin-left:50px'>
		<%
		ArrayList<String> relateword = (ArrayList<String>)request.getAttribute("relate");
		if(relateword !=null){
			for(int i=0; i<relateword.size();i++){
			}
		}
		
		%>
		
		<p><%out.println(relateword); %><p>  <!-- print出相關關鍵字 -->
		
		</div>

	<div style='position: absolute;margin-top:190px;margin-left:50px'>
		<%
		
		String[][] orderList = (String[][]) request.getAttribute("query");
		for (int i = 0; i < orderList.length; i++) {
			String s=orderList[i][1];
			//s=s.substring(7);
		%>
		
		
		<a href='<%=s%>'><%=orderList[i][0]%> </a> <br>連結<br>
		<br>
		<%
}
%>
	</div>
	<div>
		<img src="BTS_1.png"
			style='position: absolute; width: 150px; height: 100px; left: 50%; top: 50%; margin-top: -280px; margin-left: -590px'>
	</div>
		<div>
		<input type='text' class="border-style" id="padding" name='keyword'
			style='font-size: 120%; position: absolute; left: 50%; top: 48%; margin-top: -250px; margin-left: -400px; width: 700px; height: 25px'
			placeholder = '請輸入關鍵字' value='<%=request.getParameter("keyword")%>'/>
			
		<input type='text' class="filter-style" id="padding_2" name='filter'
			style='font-size: 120%; position: absolute; left: 50.5%; top: 55%; margin-top: -250px; margin-left: -400px; width: 700px; height: 25px'
			placeholder = '請輸入過濾字' value='<%=request.getParameter("filter")%>'/>			
	</div>

</form>
</body>
</html>