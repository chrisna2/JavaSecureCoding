<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>비밀번호 변경</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value='/css/main.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.1.min.js'/>"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	$('document').ready(function(){
		//취소 버튼 클릭시
		$('#cancelButton').click(function(){
			window.open("about:blank","_self").close();
		});
		
		//확인 버튼 클릭시
		$('#registerButton').click(function(){
			$.ajax({
				url: "${pageContext.request.contextPath}/user/getUserPassword",
				method: "GET",
				contentType: "application/json; charset=utf-8",
				dataType: "text",
				data: {
					"userId" : "${userId}"
				},
				success: function(data){
					//기존 비밀번호 확인
					if(data != $('#mypageTable input[name=beforePW]').val()){
						alert('기존 비밀번호를 다시 입력해주세요.');
						$('#mypageTable input[name=beforePW]').val("").focus();
						return;
					}
					
					//새 비밀번호 확인
					if($('#mypageTable input[name=afterPW]').val() == $('#mypageTable input[name=checkAfterPW]').val()){
						$.ajax({
							url: "${pageContext.request.contextPath}/user/changeUserPassword",
							method: "GET",
							contentType: "application/json; charset=utf-8",
							dataType : "text",
							data: {
								"userId" : "${userId}",
								"beforePW" : $('#mypageTable input[name=beforePW]').val(),
								"afterPW" : $('#mypageTable input[name=afterPW]').val(),
								"checkAfterPW" : $('#mypageTable input[name=checkAfterPW]').val()
							},
							success: function(data){
								opener.location.reload();
								alert("수정이 완료되었습니다.");
								window.open("about:blank","_self").close();
							},
							error : function(xhr, status, error) {
								console.log(error);
								console.log(xhr.status);
							}
						});
					} else {
						alert('새 비밀번호 입력이 잘못되었습니다.');
						$('#mypageTable input[name=afterPW]').val("").focus();
						$('#mypageTable input[name=checkAfterPW]').val("");
					}
				},
				error : function(xhr, status, error) {
					console.log("기존 비밀번호를 가져오지 못했습니다.");
					console.log(xhr.status);
				}
			});
		});
	});
</script>
</head>
<body>
	<div id="PopupHeader">비밀번호 변경하기</div>
	<table id="mypageTable"">
		<tbody>
			<tr>
				<th>기존 비밀번호</th>
				<td><input type="password" name="beforePW" id="beforePW"/></td>
			</tr>
			<tr>
				<th>새 비밀번호</th>
				<td><input type="password" name="afterPW" id="afterPW"/></td>
			</tr>
			<tr>
				<th>새 비밀번호 확인</th>
				<td><input type="password" name="checkAfterPW" id="checkAfterPW"/></td>
			</tr>
		</tbody>
	</table>
	<div id="buttonArea">
		<button type="button" class="btn btn-default get" id="cancelButton" style="float: right; top: 0px; margin: 15px">취소</button>
		<button type="button" class="btn btn-default get" id="registerButton" style="float: right; top: 0px; margin: 15px">확인</button>
	</div>
</body>
</html>