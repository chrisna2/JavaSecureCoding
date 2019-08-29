<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>상품 Q&A 문의하기</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value='/css/main.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.1.min.js'/>"></script>
<script type="text/javascript">
	$('document').ready(function(){
		//취소 버튼 클릭시
		$('#cancelButton').click(function(){
			window.open("about:blank","_self").close();
		});
		
		//등록 버튼 클릭시
		$('#registerButton').click(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/qna/register",
				method: "GET",
				contentType: "application/json; charset=utf-8",
				dataType : "text",
				data: {
					"bookId" : "${bookId}",
					"userId" : "${userId}",
					"qnaTitle": $('#qnaPopupTitle').val(),
					"qnaContent": $('#qnaPopupContent').val()
				},
				success: function(data){
					opener.location.reload();
					alert("질문 등록이 완료되었습니다.");
					window.open("about:blank","_self").close();
				},
				error : function(xhr, status, error) {
					console.log("질문이 입력되지 않았습니다.");
					console.log(xhr.status);
				}
			});
		});
	})
</script>
</head>
<body id="qnaPopupBody">
	<div id="PopupHeader">상품 Q&A 문의하기</div>
	<table id="qnaPopupTable">
		<tbody>
			<tr>
				<th scope="col">제목</th>
				<td><input name="qnaPopupTitle" id="qnaPopupTitle" type="text" /></td>
			</tr>
			<tr>
				<th scope="col">내용</th>
				<td>
					<!-- <textarea rows="4" cols="50"></textarea> -->
					<textarea name="qnaPopupContent" id="qnaPopupContent" rows="10" cols="80"></textarea>
				</td>
			</tr>
		</tbody>
	</table>
	<div id="buttonArea">
		<button type="button" class="btn btn-default get" id="cancelButton"
			style="float: right; top: 0px; margin: 15px">취소</button>
		<button type="button" class="btn btn-default get" id="registerButton"
			style="float: right; top: 0px; margin: 15px">등록</button>
	</div>
</body>
</html>
