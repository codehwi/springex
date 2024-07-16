<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="nav">
		<%@include file="../include/nav.jsp"%>
	</div>
	<form method="post">
		<label>제목</label><input type="text" name="title" readonly
			value="${view.title } " /><br> <label>작성자</label><input
			type="text" name="writer" readonly value="${view.writer}" /><br>
		<label>내용</label>
		<textarea readonly rows="5" cols="50" name="content">${view.content}</textarea>

		<!-- <button type="submit">작성</button> -->
	</form>
	<div>
		<a href="/board/modify?bno=${view.bno }">게시물 수정</a> <a
			href="/board/delete?bno=${view.bno }">게시물 삭제</a>
	</div>
	<div id="replyWrite">
    <h3>댓글 작성</h3>
    
    
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>

    <form action="replyWrite" method="post">
        <input type="hidden" name="bno" value="${view.bno}">
        <div>
            <label for="writer">작성자:</label> 
            <input type="text" id="writer" name="writer" required>
        </div>
        <div>
            <label for="content">내용:</label>
            <textarea id="content" name="content" rows="5" cols="50" required></textarea>
        </div>
        <div>
            <button type="submit">댓글 작성</button>
        </div>
    </form>
</div>

<div id="reply">
    <ol class="replyList">
        <c:forEach items="${repList}" var="repList">
            <li id="reply-${repList.rno}">
                <p>
                    작성자 : ${repList.writer}<br> 작성 날짜 :
                    <fmt:formatDate value="${repList.regDate}" pattern="yyyy-MM-dd" />
                </p>
                <p id="content-${repList.rno}">${repList.content}</p>
                <div>
                    <form action="deleteReply" method="post" style="display:inline;">
                        <input type="hidden" name="rno" value="${repList.rno}">
                        <input type="hidden" name="bno" value="${view.bno}">
                        <button type="submit">댓글 삭제</button>
                    </form>
                    <button type="button" onclick="showUpdateForm(${repList.rno})">댓글 수정</button>
                </div>
                <div id="updateForm-${repList.rno}" style="display: none;">
                    <form action="updateReply" method="post">
                        <input type="hidden" name="rno" value="${repList.rno}">
                        <input type="hidden" name="bno" value="${view.bno}">
                        <textarea name="content" rows="5" cols="50">${repList.content}</textarea><br>
                        <button type="submit">수정 완료</button>
                        <button type="button" onclick="hideUpdateForm(${repList.rno})">취소</button>
                    </form>
                </div>
            </li>
        </c:forEach>
    </ol>
</div>

<script>
function showUpdateForm(rno) {
    document.getElementById('content-' + rno).style.display = 'none';
    document.getElementById('updateForm-' + rno).style.display = 'block';
}

function hideUpdateForm(rno) {
    document.getElementById('content-' + rno).style.display = 'block';
    document.getElementById('updateForm-' + rno).style.display = 'none';
}
</script>







</body>
</html>