<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*"%>
    
<%
//list에 있는 링크로 받음
	String n = request.getParameter("num");
	int num = 0;
	if(n == null){
		response.sendRedirect("list.jsp");
	} else {
		num = Integer.parseInt(n);
		MemberService memberService = MemberService.getInstance();
		MemberVo vo = memberService.read(num);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%if(vo == null) {%>
	회원정보가 존재하지 않습니다.
<%}else{ %>
	번호: 	<%=vo.getNum() %><br>
	아이디: <%=vo.getMemberId() %><br>
	닉네임: <%=vo.getNickName() %><br>
	등록일: <%=vo.getRegdate() %><br>
	<a href="modifyForm.jsp?num=<%=vo.getNum()%>"><button>수정</button></a>
	<a href="deleteForm.jsp?num=<%=vo.getNum()%>"><button>삭제</button></a>
	<button><a href="#">수정</a></button>
<%} %>

</body>
</html>


<% }%>
 

