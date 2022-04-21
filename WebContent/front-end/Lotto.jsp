<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>	
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
.blue{
	background-color:lightblue;
}
</style>
</head>
<body>
<h1 class='blue'>Hello Java Web!</h1>
<h1>樂透對獎</h1>

<form action="<%=request.getContextPath()%>/test01/Lotto.do" method="POST">
<!-- <form action="com.lotto/Lotto.do" method="POST"> -->
<%-- <form action="<%=request.getContextPath()%>/front-end/Lotto.jsp" method="POST"> --%>
<input type="text" name="ch0" value="10" size="2"/> 
<input type="text" name="ch1" value="1" size="2"/> 
<input type="text" name="ch2" value="2" size="2"/> 
<input type="text" name="ch3" value="3" size="2"/> 
<input type="text" name="ch4" value="4" size="2"/> 
<input type="text" name="ch5" value="5" size="2"/>

<input type="hidden" name="ch5" value="5" size="2"/>
<input type="hidden" name="ch4" value="4" size="2"/>
<input type="hidden" name="ch3" value="3" size="2"/>
<input type="hidden" name="ch2" value="2" size="2"/>
<input type="hidden" name="ch1" value="1" size="2"/>
<input type="hidden" name="ch0" value="6" size="2"/>

<button type="submit" name="check" >確定</button>
<input type="hidden" name="action" value="check">
<br>
<!-- <h2>選取號碼</h2> -->
    <%=request.getParameter("ch0")%>,
	<%=request.getParameter("ch1")%>,
    <%=request.getParameter("ch2")%>,
    <%=request.getParameter("ch3")%>,
    <%=request.getParameter("ch4")%>,
    <%=request.getParameter("ch5")%>
</form>

<c:if test="${not empty sum}">
<h2 style="color:green">開獎號碼${ans}</h2>
<h2 style="color:green">選取號碼${chs}</h2>
<h2 style="color:green">中獎號碼:${sum}個</h2>
</c:if>
<br>
<br>

<a href="/Lotto/front-end/Search.jsp">所有兌獎紀錄</a>
<%-- 錯誤表列 Start--%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<%-- 錯誤表列 End--%>
</body>
</html>