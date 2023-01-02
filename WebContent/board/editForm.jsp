<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!--board 패키지에 있는 BoardDao 클래스에서 뭘 뭘 가져오는데, -->
<jsp:useBean id="dao" class="board.BoardDao"/>


<%
//가져온게 String이여서 Integer.parseInt올 형변환 시켜주고, int num에 할당
	int num = Integer.parseInt(request.getParameter("num"));
/* dao에 있는 selectOne 메소드에 num을 매개변수로 넣고, vo 참조 변수를 만들어. */
	BoardVo vo =dao.selectOne(num);

	pageContext.setAttribute("vo",vo);
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
</head>
<body>
<h3>수정하기</h3>
	<form action="edit.jsp" method="post">
		<input type="hidden" name="num" value="${vo.num}">
		<input type="text" name="title" value="${vo.title}" required><br>
		<input type="text" name="writer" value="${vo.writer}" required disabled><br>
		<textarea rows="4" cols="25" name="content" placeholder="내용">${vo.content}</textarea><br>
		<input type="submit" value="수정"	>
	</form>
</body>
</html>