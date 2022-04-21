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
<h1>加密/解密網頁</h1>

<!-- AES 256 -->
<h2>AES 256</h2>
<form action="<%=request.getContextPath()%>/security/Security.do" method="POST">
<input type="text" name="aes256" value="abc" />
<input type="hidden" name="aes256" />
<!-- key: -->
<!-- <input type="text" name="aes256pass" value="uBdUx82vPHkDKb284d7NkjFoNcKWBuka"/> -->
<!-- <input type="hidden" name="aes256pass" /> -->

<button type="submit" name="aessub" >加密</button>
<input type="hidden" name="action" value="aessub">
<br>
<c:if test="${not empty aesubres}">
<font style="color:red">加密:${aesubres}</font><br>
</c:if>
</form>


<!-- 解密 -->
<form action="<%=request.getContextPath()%>/security/Security.do" method="POST">
<input type="text" name="aes256d" value="${aesubres}" />
<input type="hidden" name="aes256d" />
<!-- key: -->
<!-- <input type="text" name="aes256passd" value="uBdUx82vPHkDKb284d7NkjFoNcKWBuka"/> -->
<!-- <input type="hidden" name="aes256passd" /> -->

<button type="submit" name="aesdecrypt" >解密</button>
<input type="hidden" name="action" value="aesdecrypt">
<br>
<c:if test="${not empty aesdecrypt}">
<font style="color:red">解密:${aesdecrypt}</font><br>
</c:if>

</form>
------------------------------------------------------------------------------
<!-- MD5 Hash -->
<h2>MD5 Hash</h2>
<form action="<%=request.getContextPath()%>/security/Security.do" method="POST">
<input type="text" name="md5" value="efg" size="2"/>
<input type="hidden" name="md5" />
<button type="submit" name="md5sub" >加密</button>
<input type="hidden" name="action" value="md5sub">

<c:if test="${not empty md5sub}">
<font style="color:red">加密:${md5sub}</font>
<br>
MD5、SHA 這類演算法可計算出「雜湊值」(hash)，這與「加密」的概念不同，因為「加密」代表可以「解密」，而「雜湊值」是不可逆的，意思就是說 MD5、SHA 這類演算產生的字串無法解密及還原
</c:if>
</form>

<%-- <form action="<%=request.getContextPath()%>/security/Security.do" method="POST"> --%>
<!-- <input type="text" name="md5d" value="efg" size="2"/> -->
<!-- <input type="hidden" name="md5d" /> -->
<!-- <button type="submit" name="md5decrypt" >解密</button> -->
<!-- <input type="hidden" name="action" value="md5decrypt"> -->

<%-- <c:if test="${not empty md5decrypt}"> --%>
<%-- <font style="color:green">解密:${md5decrypt}</font> --%>
<%-- </c:if> --%>
<!-- </form> -->
------------------------------------------------------------------------------

<!-- SHA 256 -->
<h2>SHA 256</h2>
<form action="<%=request.getContextPath()%>/security/Security.do" method="POST">
<input type="text" name="sha" value="hij" size="2"/>
<input type="hidden" name="sha" />
<button type="submit" name="shasub" >加密</button>
<input type="hidden" name="action" value="shasub">


<c:if test="${not empty shasub}">
<font style="color:red">加密:${shasub}</font>
<br>
<font>解密:SHA-256是一種加密（單向）哈希函數，因此沒有直接的解碼方法。 加密哈希函數的全部目的是您無法撤消它</font>
</c:if>

</form>

------------------------------------------------------------------------------
</body>
</html>