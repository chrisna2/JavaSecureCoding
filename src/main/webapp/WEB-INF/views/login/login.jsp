<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<script type="text/javascript" src="../js/jquery-1.12.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
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

<script>
	$(function() {

		// 	아이디중복확인
		$("#userId").keyup(function() {
			userIdDuplication();
		});
		//패스워드 확인
		$("#userPw2").keyup(function() {
			userPwDuplication();
		});
		
		//회원가입
		$("#clickBtn").click(function() {
			userValidation();
		});
		
		$("#loginBtn").click(function(){
 			loginCheck();
		});

		 
	});

	/* 유효성검사*/
	function userValidation() {
		var userName = $("#userName").val();
		var userId = $("#userId").val();
		var userPw1 = $("#userPw1").val();
		var userPw2 = $("#userPw2").val();
		var userEmail = $("#userEmail").val();
		var userZip = $("#userZip").val();
		var userAddr1 = $("#userAddr1").val();
		var userAddr2 = $("#userAddr2").val();
		var userBirth = $("#userBirth").val();
		var userSex = $("#userSex").val();
		var userCellphone1 = $("#userCellphone1").val();
		var userCellphone2 = $("#userCellphone2").val();
		var userCellphone3 = $("#userCellphone3").val();

		if (userName == "") {
			alert("이름을 입력하세요.");
			$("#userName").focus();
			return false;
		} else if (userId == "") {
			alert("아이디를 입력하세요.");
			$("#userId").focus();
			return false;
		} else if (userPw1 == "") {
			alert("패스워드를 입력하세요.");
			$("#userPw1").focus();
			return false;
		} else if (userPw2 == "") {
			alert("패스워드를 입력하세요.");
			$("#userPw2").focus();
			return false;
		}else if(userPw1 != userPw2){
			alert("패스워드를 확인해주세요.")
			$("#userPw2").focus();
			return false;
		}else if (userEmail == "") {
			alert("이메일을 입력하세요.");
			$("#userEmail").focus();
			return false;
		} else if (userZip == "" || userAddr1 == "") {
			alert("주소를 확인하세요.");
			$("#userAddr1").focus();
			return false;
		} else if (userSex == "") {
			alert("성별을 선택 하세요.");
			return false;
		} else if (userCellphone1 == "") {
			alert("전화번호를 입력 하세요.");
			$("#userCellphone1").focus();
			return false;
		} else if (userCellphone2 == "") {
			alert("전화번호를 입력 하세요.");
			$("#userCellphone2").focus();
			return false;
		} else if (userCellphone3 == "") {
			alert("전화번호를 입력 하세요.");
			$("#userCellphone3").focus();
			return false;
		}else{
			var result =  $("form[id=userForm]").serialize();
			$.ajax({
				type: "get"
				,url: "/bookking/user/userSignup"
				,contentType: "application/json; charset=utf-8"
				,dataType:"json"
				,data : result
				,success : function(data){
					if(data == 1){
						alert("회원가입완료");
							location.href="/bookking/"
					}else{
						alert("가입실패");
						return false;
					}
				}
				,error : function(xhr,status,error){
					alert(xhr.status);
				}
			});
			
		}
		
		

	}

	// 아이디 중복확인
	function userIdDuplication() {
		var userId = $("#userId").val();
		$.ajax({
			type : "GET",
			url : "userDuplication",
			dataType : "json",
			data : {
				"userId" : userId
			},
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				if (data.id == "null" ) {
					$("#duplication").html("<p>사용가능합니다.</p>");
					$("#duplication").css("color", "blue");
				} else if (data.id != "null") {
					$("#duplication").html("<p>사용할수 없는 아이디 입니다.</p>");
					$("#duplication").css("color", "red");
				}
			},
			error : function(xhr, status, error) {
				alert(xhr.status);
			}

		});
	}

	//패스워드 확인
	function userPwDuplication() {
		var userPw1 = $("#userPw1").val();
		var userPw2 = $("#userPw2").val();

		if (userPw1 == userPw2) {
			$("#pwDuplication").html("<p>일치합니다.</p>");
			$("#pwDuplication").css("color", "blue");
		} else  {
			$("#pwDuplication").html("<p>일치하지 않습니다.</p>");
			$("#pwDuplication").css("color", "red");
		}
	}
	
	//로그인 체크
	function loginCheck(){
		var loginId = $("#loginId").val(); 
		var loginPw = $("#loginPw").val();
		
		if(loginId == null || loginId == ""){
			alert("아이디를 입력하세요.");
			$("#loginId").focus();
			return false;
		}else if(loginPw == "" ){
			alert("패스워드를 입력하세요.");
			$("#loginPw").focus();
			return false;
		}else{
			$.ajax({
				type: "get"
				,url: "/bookking/user/loginAction"
				,contentType: "application/json; charset=utf-8"
				,dataType:"json"
				,data : {"loginId": loginId, "loginPw": loginPw}
				,success : function(data){
					if(data.loginId == "null" || data.loginId == ""  || data.loginId == null){
						alert("아이디 또는 패스워드가 일치하지 않습니다.");
					}else{
							location.href="/bookking/"
					}
				}
				,error : function(xhr,status,error){
					////alert(xhr.status);
					alert(xhr.responseText);
				}
			});
		}
		
// 		$("#loginActionForm").submit(); 
		
	}
</script>


</head>

<body>

<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="loginAction" method="post" id="loginActionForm">
							<input type="text"  name="loginId" id="loginId"  placeholder="아이디" />
							<input type="password" name="loginPw"   id="loginPw"  placeholder="패스워드" />
<!-- 							<button id="loginBtn" class="btn btn-default">Login</button> -->
							<input type="button" class="btn btn-default" id="loginBtn"  value="Login">
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-5">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form method="post" id="userForm" class="userForm">
							<input type="text" placeholder="UserName" style="width: 80%" id="userName"  name="userName" /> <br>
							<input type="text"    placeholder="UserId" style="width: 80%" id="userId" name="userId"/> 
							<div id="duplication"></div>
							<input type="password" placeholder="Password" style="width: 80%" id="userPw1" name="userPassword"/><br>
							<input type="password" placeholder="Password" style="width: 80%" id="userPw2" name="userPw2"/><br>
							<div id="pwDuplication"></div>
							<input type="email" placeholder="Email Address" style="width: 80%" id="userEmail" name="userEmail"/><br>
							
							<input type="text" size="20px" id="userZip"  name="userZip" placeholder="우편번호"  /> &nbsp;&nbsp;&nbsp;
							<input type="button" class="btn btn-default " onclick="sample4_execDaumPostcode()"  value="우편번호 찾기" />
							<input type="text"  style="width: 80%" id="userAddr1"  name="userAddr1" placeholder="지번주소" />
							<input type="hidden" id="sample4_jibunAddress" placeholder="지번주소"/>
							<input type="text"  style="width: 80%" placeholder="상세주소" id="userAddr2" name="userAddr2"/>				
							<span id="guide" style="color:#999"></span>
<!-- 							<input type="text" placeholder="생일" style="width: 80%"  id="userBirth" name="userBirth"/><br> -->
							
							<input type="text" placeholder="전화번호" maxlength="3" style="width: 20%"  id="userCellphone1" name="userCellphone1" value="010"/> -
							<input type="text" placeholder="전화번호" maxlength="4" style="width: 25%"  id="userCellphone2" name="userCellphone2"/> -
							<input type="text" placeholder="전화번호" maxlength="4" style="width: 25%"  id="userCellphone3" name="userCellphone3"/><br>
							<!-- 성별 남자 : 0, 여자 : 1 -->
							<input type="radio"  value="0" id="userSex" name="userSex" checked="checked">남자 
							<input type="radio"  value="1" id="userSex" name="userSex">여자<br><br>
							<input type="button" class="btn btn-default "  value="Signup" id="clickBtn">
						</form>
					</div><!--/sign up form-->
							
				</div>
			</div>
		</div>
	</section><!--/form-->
	
<!-- 우편변호 API를 위한 script -->

</body>


</html>