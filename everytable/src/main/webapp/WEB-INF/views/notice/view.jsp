<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pageNav" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 글 보기</title>

<!-- Bootstrap / jQuery / awesome4(icon) 라이브러리 등록 : default_decorator에 등록 -->

<!-- 동작을 시키는 j5 : 위치와 상관없이 코딩할 수 있다. -->
<script type="text/javascript">
// jQuery :: 아래 HTML 로딩이 끝나면 실행해줘 - $() 사이에 실행할 function을 넘긴다. body가 다 로딩이 되면 function이 실행됨
$(function(){
	//alert("jQuery 영역이 실행됐다~~");  // 자바 스크립트의 팝업 열기
	$("#deleteBtn").click(function(){
		// alert("삭제 버튼 클릭");
		if (confirm("삭제하면 복구할 수 없습니다. 삭제하시겠습니까?")) location="delete.do?no=${param.no}"
				+ "&page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}"
				+ "&period=${param.period}";
	});
});
</script>
</head>
<body>
<!-- 메인 메뉴 부분 : default_decorator에 등록 -->
	<h2>공지 글 보기</h2>
	<table class="table">
		<tbody>
			<tr>
				<th>번호</th>
				<td class="no">${list.no}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${list.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${list.content}</td>
			</tr>
			<tr>
				<th>공지 기간</th>
				<td>${list.startDate} ~ ${list.endDate}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${list.writeDate}</td>
			</tr>
			<tr>
				<th>최근 수정일</th>
				<td>${list.updateDate}</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${!empty login && login.gradeNo == 9}">
		<a href="updateForm.do?no=${param.no}&page=${param.page}&perPageNum=${param.perPageNum}
			&key=${param.key}&word=${param.word}&period=${param.period}" class="btn btn-primary">수정</a>
		<a id="deleteBtn" class="btn btn-danger">삭제</a>
	</c:if>
	<a href="list.do?page=${param.page}&perPageNum=${param.perPageNum}&key=${param.key}&word=${param.word}
		&period=${param.period}" class="btn btn-warning">리스트</a>
	
</body>
</html>