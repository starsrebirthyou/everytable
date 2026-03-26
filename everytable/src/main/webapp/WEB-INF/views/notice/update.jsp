<!-- <%@page import="com.webjjang.board.vo.BoardVO"%> -->
<!-- <%@page import="com.webjjang.main.service.Execute"%> -->
<!-- <%@page import="com.webjjang.board.service.BoardUpdateService"%> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 자바로 DB에 넘어오는 데이터 저장 : BoardController:list - BoardListService - BoardDAO.list(vo) -->
<%
System.out.println("update.jsp - 공지 수정 처리");
// 넘어오는 데이터 수집
// DB에 저장
// 사용자에게 데이터 받기 - 제목, 내용, 작성자, 비밀번호 - vo에 저장
// - BoardVO 생성 -> 데이터 받기 setter()와 In.getStr() 이용
//BoardVO vo = new BoardVO(); 
//vo.setNo(Long.parseLong(request.getParameter("no")));
//vo.setTitle(request.getParameter("title"));
//vo.setContent(request.getParameter("content"));
//vo.setContent(request.getParameter("startDate"));
//vo.setContent(request.getParameter("endDate"));
//vo.setWriter(request.getParameter("updateDate"));
//int result = (Integer) Execute.execute(new BoardUpdateService(), vo);

// 자동으로 글 보기로 돌아간다.
//if(result == 1) response.sendRedirect("view.jsp?no=" + vo.getNo());
%>