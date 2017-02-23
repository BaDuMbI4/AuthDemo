<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align = "center">Your messages</h1>
<p>
<%@ page import="java.util.*" %>
<% List<String> messages = (List<String>) request.getAttribute("messages");
Iterator it = messages.iterator();
while(it.hasNext()) {
	out.print("<br>" + it.next());
}
%>
</body>
</html>