<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*"%>
<%request.setCharacterEncoding("utf-8"); %>

<!-- 톰캣이 제공해주는 기능 -->
<jsp:useBean id="modifyRequest" class="manager.ModifyRequest"/>
<jsp:setProperty property="*" name="modifyRequest"/>

<%
	/* int num = Integer.parseInt(request.getParameter("num"));
	String memberId = request.getParameter("memberId");
	String memberPwOld = request.getParameter("memberPwOld");
	String memberPwNew = request.getParameter("memberPwNew");
	String nickName = request.getParameter("nickName"); */

MemberService memberService = MemberService.getInstance(); 
	MemberVo vo = new MemberVo(
														 modifyRequest.getNum(), 
														 modifyRequest.getMemberId(), 
														 modifyRequest.getMemberPwNew(), 
														 modifyRequest.getNickName());
	
	if(memberService.edit(vo, modifyRequest.getMemberPwOld())){
		response.sendRedirect(request.getContextPath()+ "/member/detail.jsp?num="+modifyRequest.getNum());
	}else{
		response.sendRedirect(request.getContextPath()+ "/member/modifyForm.jsp?num="+modifyRequest.getNum());	//값이 설정이 안됌
	}
%>  
    
    