<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*, java.util.List"%>
  <%
  	MemberService ms = MemberService.getInstance();
  	List<MemberVo> ls = ms.listAll();
  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록</title>
</head>
<body>
	<h3>회원목록</h3>
	<table border="1">
		<% if(ls.size() == 0) {%>
		<tr>
			<td>목록이 없습니다</td>
		</tr>
		<%} else{%>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>NICKNAME</th>
		</tr>
		<% for(MemberVo vo :ls) {%>
		<tr>
			<td><%=vo.getNum()%></td>
			<td><a href="detail.jsp?num=<%=vo.getNum()%>"><%=vo.getMemberId()%></a></td>
			<td><%=vo.getNickName()%></td>
		</tr>
<%	} //for
	} //else
%>
	</table>
</body>
</html>