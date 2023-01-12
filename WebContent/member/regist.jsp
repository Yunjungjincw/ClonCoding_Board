<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="manager.*"%>

<!-- 처리하는 동작이 일어남  -->
<%request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="vo" class="manager.MemberVo"/>
<jsp:setProperty property="*" name="vo"/>
<%
	

	//parameter 값 = > 자바빈을 사용해서 코드 필요없음
/* 	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	String nickName = request.getParameter("nickName"); */
	
	//parameter값 저장
	/* MemberVo vo = new MemberVo(-1, memberId, memberPw, nickName); */
	
	//memberService에서 vo 값을 받아서 실행 
	MemberService memberService = MemberService.getInstance();
	
	//memberService를 이용해서 regist가 됐는지.
	if(memberService.regist(vo)){
		response.sendRedirect(request.getContextPath()+"/member/index.jsp"); //정상적으로 등록
	}else{
		response.sendRedirect(request.getContextPath()+"/member/registForm.jsp");	//등록 실패
	}


%>