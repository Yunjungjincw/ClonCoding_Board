<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*"%>
    
<%

    MemberService memberService = MemberService.getInstance();
		//service -> Dao -> deleteForm에서 parameter 값 받아서 -> remove하면 list로 else면 detail로 다시 
		//detail로 다시 보내줄땐 삭제 할려던 회원 정보를다시 보여줘야하니깐 . 뒤에 parameter 값을 붙여줘야함.
		int num = Integer.parseInt(request.getParameter("num"));
		if(memberService.remove(num)){
			response.sendRedirect(request.getContextPath()+"/member/list.jsp");
		}else{
			response.sendRedirect(request.getContextPath() + "/member/detail.jsp?num="+ num);
		}
%>
