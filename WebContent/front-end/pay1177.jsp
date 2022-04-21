<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h2>1177pay</h2>
<form action="<%=request.getContextPath()%>/security/Security.do" method="POST">

<font>merchantnumber 商店編號</font>
<input type="text" /><br>


<font>ordernumber 訂單編號</font>
<input type="text" /><br>


<font>merchantordernumber 商店訂單編號</font>
<input type="text" /><br>

<font>amount 訂單金額</font>
<input type="text" /><br>

<font>currency 幣別</font>
<input type="text" /><br>

<font>paymenttype 此筆訂單所要使用的支付工具</font>
<input type="text" /><br>

<font>paytitle 付款說明</font>
<input type="text" /><br>

<font>paymemo 付款註記</font>
<input type="text" /><br>

<font>duedate 訂單有效期限</font>
<input type="text" /><br>

<font>id 身分證字號或統編。</font>
<input type="text" /><br>

<font>lang 頁面使用語言(多國語言)，目前支援的參數值有ch, jp, en</font>
<input type="text" /><br>

<font>payphone 消費者聯絡電話</font>
<input type="text" /><br>

<font>timestamp 時間戳記</font>
<input type="text" /><br>

<font>checksum 驗證碼</font>
<input type="text" /><br>

</form>

</body>
</html>