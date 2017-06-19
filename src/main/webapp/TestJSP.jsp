<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.LocalTime"%>
<%@ page import="java.time.ZoneId"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Collections"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title>Testing JSP tech</title>
</head>

<body>
	<h5>
		Hello world!
	</h5>
	<%
		out.println("Your IP address is " + request.getRemoteAddr());
	%>
	<p>Today's date: 
		<%=
	      	LocalDate.now(ZoneId.of("Europe/Berlin")) + " " + LocalTime.now()
	    %>
	</p>
	<%="http://"%>${ header["host"] }${ pageContext.request.contextPath }<%="/intro"%><br/>
	Context' path : ${ pageContext.request.contextPath } <br/> 
	Host' name : ${ header["host"] }<br/> 
	Context' type and encoding : ${pageContext.response.contentType}<br/> 
	Response's encoding : ${pageContext.response.characterEncoding}<br/> 
	Session's ID: ${pageContext.request.session.id}<br/> 
	Session's creation time in millis : ${pageContext.request.session.creationTime}<br/> 
	Session's last accessed time : ${pageContext.request.session.lastAccessedTime}<br/> 
	Servlet's name : ${pageContext.servletConfig.servletName}<br/>  
	<p style="font-size: 0.8em">
			ArtBox set name (<i>left blank to see full list</i>):</p>
			<form  method="get" action="test">
			<input type="text" name="theme" size=40  class="loraFont">
			<input type="submit" value="Search">
			<p class="${textColor}">${message}</p><br/> 
			</form>
			
<table>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.getKey()}</td>
            <td>${product.getValue().getTheme()}</td>
            <td>${product.getValue().getAge()}</td>
            <td>${product.getValue().getCost()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>