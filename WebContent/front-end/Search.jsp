<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="test01.model.*"%>
<%@ page import="java.util.*"%>

<%
  LottoJDBCDAO lottoJDBCDAO = new LottoJDBCDAO();
  List<LottoVO> list = lottoJDBCDAO.getALL();
  pageContext.setAttribute("list",list);	 
%>

<%-- <c:out value="${list}" /> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
<h1>所有兌獎紀錄</h1>


<table border="1">
  <tr >
    <th>中獎號碼</th>
    <th>自選號碼</th>
    <th>時間</th>
    <th>中獎結果</th>
  </tr>
<c:forEach var="lottoVO" items="${list}" >
  <tr>
    <td>${lottoVO.ans}</td>
    <td>${lottoVO.choose}</td>
    <td>${lottoVO.time}</td>
    <td>${lottoVO.result}</td>
  </tr>
  

</c:forEach>
</table>

<input type="button"name="back" value="返回"onClick="javascript:history.back()">
<%-- <c:forEach var="i" begin="1" end="5"> --%>
<%--         <c:out value="${i}" /> --%>
<%-- </c:forEach> --%>


<br>
</body>
</html>