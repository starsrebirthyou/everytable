<!-- <%@page import="everytable.board.service.BoardViewService"%> -->
<!-- <%@page import="com.webjjang.main.service.Execute"%> -->
<!-- <%@page import="com.webjjang.board.vo.BoardVO"%> -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>공지 수정</title>

<!-- Bootstrap / jQuery / awesome4(icon) 라이브러리 등록 : default_decorator에 등록 -->

<script type="text/javascript">
$(function(){
	$(".cancelBtn").click(function(){
		//alert("취소 버튼을 클릭했다!");
		// BOM 객체 안에 history 객체가 있다. - 이동한 url을 저장해 놓는 객체
		// history.go(1), history.back() == history.go(-1)
		history.back();  // JavaScript
	});
	
	// datepicker 적용
	$( ".datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat: "yy-mm-dd",
		dayNamesMin: [ "일", "월", "화", "수", "목", "금", "토" ],
		monthNamesShort: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ]
	});
	
	$("#startDate").datepicker("option", {
		// 최소 날짜 세팅
		"minDate" : new Date(),
		// 닫히는 이벤트가 발생되면(날짜를 선택하거나 다른 곳을 클릭하면 닫힌다). 선택한 날짜를 selectedDate 파라미터로 쓴다.
		onClose : function( selectedDate ) {
			if($(this).val() != "")  // 다른 곳을 클릭한 경우가 아니면 (날짜를 클릭한) 경우의 처리
				$( "#endDate" ).datepicker( "option", "minDate", selectedDate );  // 종료일의 최소 날짜를 바꾼다.
		}  // onClose 끝
	});  //startDate 끝
	
	$("#endDate").datepicker("option", {
		// 최소 날짜 세팅
		"minDate" : new Date(),
		onClose : function( selectedDate ) {
			if($(this).val() != "")
				$( "#startDate" ).datepicker( "option", "maxDate", selectedDate );
		}  // onClose 끝
	});  // endDate 끝
  
});
</script>

</head>
<body>
	<h2>공지 수정</h2>
	<!-- URL & Header & body(data)로 넘기는 방식 : post -- 넘어가는 데이터가 보이지 않는다. -->
	<form action="update.do" method="post">
	  <!-- 페이지 정보를 숨겨서 넘기기 -->
	  <input type="hidden" name="page" value="${param.page}">
	  <input type="hidden" name="perPageNum" value="${param.perPageNum}">
	  <input type="hidden" name="key" value="${param.key}">
	  <input type="hidden" name="word" value="${param.word}">
	  <input type="hidden" name="period" value="${param.period}">

	  <div class="mb-3 mt-3">
	    <label for="no" class="form-label">번호</label>
	    <input type="text" class="form-control" id="no" name="no" value="${vo.no}" readonly="readonly">
	  </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="title" class="form-label">제목</label>
	    <input type="text" class="form-control" id="title" placeholder="제목을 입력하세요." name="title"
	    		value="${vo.title}">
	  </div>
	  
	  <div class="mb-3 mt-3">
        <label for="content">내용</label>
        <textarea class="form-control" rows="5" id="content" placeholder="내용을 입력하세요." 
        		name="content">${vo.content}</textarea>
      </div>
      
	  <div class="mb-3 mt-3">
	    <label for="startDate" class="form-label">공지 시작일</label>
	    <input type="text" class="form-control datepicker" id="startDate" placeholder="XXXX-XX-XX"
	    		name="startDate" autocomplete="off" value="${vo.startDate}" readonly>
	  </div>
	  
	  <div class="mb-3 mt-3">
	    <label for="endDate" class="form-label">공지 종료일</label>
	    <input type="text" class="form-control datepicker" id="endDate" placeholder="XXXX-XX-XX"
	    		name="endDate" autocomplete="off" value="${vo.endDate}" readonly>
	  </div>
	  
	  <button type="submit" class="btn btn-primary">수정</button>
	  <button type="reset" class="btn btn-warning">리셋</button>
	  <button type="button" class="cancelBtn btn btn-secondary">취소</button>
	</form>
</body>
</html>