<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
 <title>수정</title> 
</head>
<body>

<form role="form" method="post" autocomplete="off">

 <p>
  <label for="userName">닉네임</label>
  <input type="text" id="userName" name="userName" value="${member.userName}"/>
 </p>
 <p>
  <label for="userPW">새로운 패스워드</label>
  <input type="password" id="userPW" name="userPW" />
 </p>
 <p>
   <button type="submit" id="submit" >수정</button>  
 </p>
 <p>
  <a href="/">처음으로</a>
 </p>
 
</form>
</body>
</html>