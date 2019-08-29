<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>My Page</title>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link href="<c:url value='/css/main.css'/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/jquery-1.12.1.min.js'/>"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('userZip').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('userAddr1').value = fullRoadAddr;
            document.getElementById('userAddr1').value = data.jibunAddress;
			document.getElementById('userAddr2').value = "";
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}
</script>

<script type="text/javascript">
	$('document').ready(init);
	$.ajax({
		url: "${pageContext.request.contextPath}/user/getUserInfo",
		method: "GET",
		contentType: "application/json; charset=utf-8",
		dataType : "json",
		data: {
			"userId" : "${userId}",
		},
		success: function(data){
			$('span#infoOwner').text(data.userName);
			$('#mypageTable #name').text(data.userName);
			$('#mypageTable #birth').text(data.userBirth);
			$('#mypageTable #id').text(data.userId);
			$('#mypageTable input[name=pw]').val(data.userPassword);
			$('#mypageTable input[name=email]').val(data.userEmail);
			$('#mypageTable input[name=cellphone1]').val(0+""+data.userCellphone1);
			$('#mypageTable input[name=cellphone2]').val(data.userCellphone2);
			$('#mypageTable input[name=cellphone3]').val(data.userCellphone3);
			$('#mypageTable input[name=userZip]').val(data.userZip);
			$('#mypageTable input[name=userAddr1]').val(data.userAddr1);
			$('#mypageTable input[name=userAddr2]').val(data.userAddr2);
			$('#mypageTable #mileage').text(data.userMileage);
		},
		error : function(xhr, status, error) {
			console.log("질문이 입력되지 않았습니다.");
			console.log(xhr.status);
		}
	});
	
	function init(){
		//확인 버튼 클릭시
		$('#registerButton').click(function(){
// 			String userId, userPassword, userName, userAddr1, userEmail, userBirth, userAddr2;
// 			int userCellphone1, userCellphone2, userCellphone3, userSex, userMileage,userZip;
			$.ajax({
				url: "${pageContext.request.contextPath}/user/changeUserInfo",
				method: "GET",
				contentType: "application/json; charset=utf-8",
				dataType : "json",
				data: {
					"userId" : "${userId}",
					"userCellphone1" : $('#mypageTable input[name=cellphone1]').val(),
					"userCellphone2" : $('#mypageTable input[name=cellphone2]').val(),
					"userCellphone3" : $('#mypageTable input[name=cellphone3]').val(),
					"userEmail" : $('#mypageTable input[name=email]').val(),
					"userZip" : $('#mypageTable input[name=userZip]').val(),
					"userAddr1" : $('#mypageTable input[name=userAddr1]').val(),
					"userAddr2" : $('#mypageTable input[name=userAddr2]').val(),
				},
				success: function(data){
//		 			String userId, userPassword, userName, userAddr1, userEmail, userBirth, userAddr2;
//		 			int userCellphone1, userCellphone2, userCellphone3, userSex, userMileage,userZip;
					alert("수정이 완료되었습니다.");
					document.location = "<c:url value='/'/>";
				},
				error : function(xhr, status, error) {
					console.log("정보가 수정되지 않았습니다.");
					console.log(xhr.status);
				}
			});
		});
		
		//취소 버튼 클릭시
		$('#cancelButton').click(function(){
			document.location = "<c:url value='/'/>";
		});
		
		//비밀번호 변경하기 버튼 클릭시
		$('#changePW').click(function(){
			window.open('${pageContext.request.contextPath}/user/popupChangeUserPassword?userId='+'${userId}','_blank', 'width=730, height=750, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no' );
		}); 
	}
</script>
</head>
<body>
	<section>
		
		<div class="container">
			<div class="category-tab shop-details-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
								<li><a href="<c:url value='/user/mypage'/> " >회원정보수정</a></li>
								<li><a href="<c:url value='/user/orderList'/>" >주문조회</a></li>
							</ul>
						</div>
			</div>
			<h3>&nbsp;&nbsp;&nbsp;<span id="infoOwner" style="font-weight:bold"></span>님의 회원정보</h3>
			<table id="mypageTable"">
				<tbody>
					<tr>
						<th>이름</th>
						<td id="name"></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td id="birth"></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td id="id"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="button" name="changePW" id="changePW" class="btn btn-default" value="비밀번호 변경하기"/></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" name="email"/></td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td>
							<input type="text" name="cellphone1" size="5"/> -
							<input type="text" name="cellphone2" size="5"/> -
							<input type="text" name="cellphone3" size="5"/>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
							<input type="text" size="20px" id="userZip"  name="userZip" placeholder="우편번호"  /> &nbsp;
							<input type="button" class="btn btn-default" onclick="sample4_execDaumPostcode()"  value="주소 찾기" />
							<div style="margin-top: 4px;">
								<input type="text"  style="width: 51%" id="userAddr1"  name="userAddr1" placeholder="지번주소" />
								<input type="text"  style="width: 48%" id="userAddr2" name="userAddr2" placeholder="상세주소"/>
							</div>
							<span id="guide" style="color:#999"></span>		
						</td>
					</tr>
					<tr>
						<th>마일리지</th>
						<td id="mileage"></td>
					</tr>
				</tbody>
			</table>
			<div id="buttonArea">
				<button type="button" class="btn btn-default get" id="cancelButton" style="float: right; top: 0px; margin: 15px">취소</button>
				<button type="button" class="btn btn-default get" id="registerButton" style="float: right; top: 0px; margin: 15px">확인</button>
			</div>
		</div>
	</section>
</body>
</html>