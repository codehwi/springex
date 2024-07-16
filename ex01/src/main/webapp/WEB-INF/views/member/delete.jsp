<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>회원 탈퇴</title> 
</head>
<body>

<form role="form" method="post" autocomplete="off">
 <p>
  <label for="userID">아이디</label>
  <input type="text" id="userID" name="userID" value="${member.userID }" readonly/>
 </p>  
 <p>
  <label for="userPW">패스워드</label>
  <input type="password" id="userPW" name="userPW" />
 </p>
 <p>
   <button type="submit" id="submit" >회원탈퇴</button>  
 </p>
 <p>
  <a href="/">처음으로</a>
 </p>
 
 <c:if test="${msg==false }">
 <p>비밀번호가 잘못되었습니다</p>
 </c:if>
 
</form>
</body>
</html>