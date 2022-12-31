<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.*"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<jsp:useBean id="dao" class="board.BoardDao"/>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	BoardVo vo =dao.selectOne(num);
	pageContext.setAttribute("vo",vo);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
	<p>번호:${vo.num}</p>
	<p>제목:${vo.title}</p>
	<p>작성자:${vo.writer}</p>
	<p>내용:${vo.content}</p>
	<p>등록일:${vo.regdate}</p>
	<p>조회수:${vo.cnt}</p>
</body>
</html>