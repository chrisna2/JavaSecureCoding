<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<style>
	tbody tr{
		text-align:center;
	}
</style>
<!-- 우편변호 API를 위한 script -->
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
                document.getElementById('newUserZip').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('newUserAddr1').value = fullRoadAddr;
                document.getElementById('newUserAddr1').value = data.jibunAddress;

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
$(function(){
	init();
	
	/*카트 금액 초기화*/
	function init(){
		var total = 0;
		$('p[name^="orderPrice"]').each(function() {
		    total += parseInt($(this).text());
		});
		$('#total').text(total);
		if(total<30000){
			$('#deliP').text(2500);
			$('#orderDeliveryCharge').val(2500);
			$('#totalP').text(total+2500);
			$('#orderTotalPrice').val(total+2500);
			
		}else {
			$('#deliP').text(0);
			$('#orderDeliveryCharge').val(0);
			$('#totalP').text(total);
			$('#orderTotalPrice').val(total);
		}
	}
	
	$("#newAddr").click(function(){
		sample4_execDaumPostcode();
	});
	
	/* 새로운 수신인 */
	$('input[type="radio"][value="option2"]').click(function(){
		$('input[id^="new"]').each(function(){
			$(this).removeAttr("disabled");
		});
		$('button[id^="new"]').removeAttr("disabled");
		$('input[id^="old"]').each(function(){
			$(this).attr("disabled", "disabled");
		});
	});
	
	/* 기존 수신인 */
	$('input[type="radio"][value="option1"]').click(function(){
		$('input[id^="old"]').each(function(){
			$(this).removeAttr("disabled");
		});
		$('button[id^="new"]').attr("disabled", "disabled");
		$('input[id^="new"]').each(function(){
			$(this).attr("disabled", "disabled");
		});
	});
	
	/* 카트 수량 더하기 */
	$(".cart_quantity_button  a.cart_quantity_up").click(function(){
		var count = parseInt($(this).next().val());
		var price = $(this).parent().parent().prev().children().text();
		$(this).next().val(count+1);
		price = price *(count+1);
		$(this).parent().parent().next().children().text(price);
		$(this).parent().parent().next().children().next().val(price);
		init();
 		return false;
		
	});
	
	/* 카트 수량 빼기 */
	$(".cart_quantity_button  a.cart_quantity_down").click(function(){
		var count = parseInt($(this).prev().val());
		if(count<=1){
			alert("1개보다 더 구매하셔야합니다.");
			return false;
		}
		var price = $(this).parent().parent().prev().children().text();
		$(this).prev().val(count-1);
		price = price *(count-1);
		$(this).parent().parent().next().children().text(price);
		$(this).parent().parent().next().children().next().val(price);
		init();
 		return false;
	});
	
	$("#checkOut").click(function(){
		if($('input[name="addr"]:checked').val()=="option2"){
			if(!checkForm()){
				return false;
			}
		}else{
			if($('input[name=orderPaymentKind]:checked').val()==null){
				alert("결제방법을 선택하세요.");
				return false;
			}
		}
		
		var form = $('form.product_cart').serializeArray().map(function(a, x) { a[x.name] = x.value; return a; }, {});
		var sentData ={}
		sentData.list= form;
		$.ajax({
			url : "/bookking/order/order",
			method: "post",
			data : JSON.stringify(sentData.list) ,
			contentType: "application/json; charset=UTF-8", 
			success : function(data) {
				alert("결제가 완료되었습니다.");
				location.href = '/bookking';
			},
			error : function(xhr,status,error){
			alert(xhr.status);
			}
		});
		
	});
	
	function checkForm() {
		if($('input[id^=newUserName]').val().trim()==""){
			alert("수신인을 입력하세요.");
			return false;
		}else if($('input[id^=newUserEmail]').val().trim()==""){
			alert("이메일을 입력하세요.");
			return false;
		}else if($('input[id^=newUserZip]').val().trim()==""){
			alert("우편번호를 입력하세요.");
			return false;
		}else if($('input[id^=newUseraddr1]').val()==""||$('input[id^=newUserAddr2]').val()==""){
			alert("주소를 정확히 입력하세요.");
			return false;
		}else if($('input[id^=newUserCellphone1]').val()==""||$('input[id^=newUserCellphone2]').val()==""||$('input[id^=newUserCellphone3]').val()==""){
			alert("휴대폰 번호를 정확히 입력하세요.");
			return false;
		}else if($('input[name=orderPaymentKind]:checked').val()==null){
			alert("결제방법을 선택하세요.");
			return false;
		}
		return true;
	}
});
</script>

<section id="cart_items">

	<div class="container">
	
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">Item</td>
						<td class="description">Book Name</td>
						<td class="price">Price</td>
						<td class="quantity">Quantity</td>
						<td class="total">Total</td>
					</tr>
				</thead>
				<form class="product_cart">
				<tbody>
				<!-- 카트에서 넘어오지 않은 경우 -->
				
				<c:if test="${empty cartList}">
					
					<tr>
						
						<td class="cart_product"><a href="<c:url value='/book/${book.bookId}'/>"><img
								src="<c:url value='/${book.bookDetailImage }'/>" alt="" width="110px"  height="110px"></a></td>
						<td class="cart_description">
						<input type="hidden" name="orderBookId" value="${book.bookId}">
							<h4>
								<a href="">${book.bookName}</a>
							</h4>
							<p>저자 : ${book.bookWriter}</p>
						</td>
						<td class="cart_price">
							<p>${book.bookPrice}</p>
						</td>
						<td class="cart_quantity">
							<div class="cart_quantity_button">
								<a class="cart_quantity_up" href=""> + </a> <input
									class="cart_quantity_input" type="text" name="orderStock"
									value="${cartStock}" autocomplete="off" size="2"> <a
									class="cart_quantity_down" href=""> - </a>
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price" name="orderPrice">${cartStock*book.bookPrice}</p>
							<input type="hidden" name="orderPrice" value="${cartStock*book.bookPrice}">
						</td>
					</tr>
					
					</c:if>
					</form>
						<!-- 카트에서 넘어온경우 -->
					<form class="product_cart">
					<c:if test="${not empty cartList}">
					<c:forEach items="${cartList}" var="book">
					<tr>
						<td class="cart_product"><a href="<c:url value='/book/${book.CART_BOOK_ID}'/>"><img
								src="<c:url value='/${book.BOOK_LIST_IMAGE }'/>" alt="" width="110px"  height="110px"></a></td>
						<td class="cart_description">
						<input type="hidden" name="orderBookId" value="${book.CART_BOOK_ID}">
							<h4>
								<a href="">${book.BOOK_NAME}</a>
							</h4>
							<p>저자 : ${book.BOOK_WRITER}</p>
						</td>
						<td class="cart_price">
							<p>${book.BOOK_PRICE}</p>
						</td>
						<td class="cart_quantity">
							<div class="cart_quantity_button">
								<a class="cart_quantity_up" href=""> + </a> <input
									class="cart_quantity_input" type="text" name="orderStock"
									value="${book.CART_STOCK}" autocomplete="off" size="2"> <a
									class="cart_quantity_down" href=""> - </a>
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price" name="orderPrice">${book.CART_STOCK*book.BOOK_PRICE }</p>
							<input type="hidden" name="orderPrice" value="${book.CART_STOCK*book.BOOK_PRICE}">
						</td>
					</tr>
					</c:forEach>
					</c:if>
					</form>
					<tr>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">
							<form class="product_cart">
							<table class="table table-condensed total-result">
								<tr>
								
									<td>Cart Sub Total</td>
									<td id="total"></td>
								</tr>
								<tr class="shipping-cost">
									<td>Shipping Cost</td>
									<td id="deliP"></td>
								</tr>
								<tr>
									<td>Total</td>
									<td>
									<span id="totalP"></span>
									<input type="hidden" id="orderDeliveryCharge" name="orderDeliveryCharge">
									<input type="hidden" id="orderTotalPrice" name="orderTotalPrice">
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
				</tbody>
					
			</table>
		</div>
		<div class="shopper-informations">
				<div class="row">
					<div class="col-sm-4">
						<div class="shopper-info">
							<p>Shopper Information</p>
								<form>
								<input type="text" id='userName' readonly="readonly" value="${userVo.userName}">
								<input type="email" id='userEmail' readonly="readonly" value="${userVo.userEmail}">
								<input type="text" id='userAddr1' readonly="readonly" value="${userVo.userAddr1}">
								<input type="text" id='userAddr2' readonly="readonly" value="${userVo.userAddr2}">
								<input type="text" id='userZip' readonly="readonly" value="${userVo.userZip}">
								<input type="text" id='userCellphone1' style="width:30%;" readonly="readonly" value="${userVo.userCellphone1}">
								<input type="text" id='userCellphone2' style="width:30%;" readonly="readonly" value="${userVo.userCellphone2}">
								<input type="text" id='userCellphone3' style="width:30%;" readonly="readonly" value="${userVo.userCellphone3}">
							<div class="radio">
							<label>
    							<input type="radio" name="addr" id="olAddr" value="option1" checked="checked">
   								 기존 정보 &nbsp;
  							</label>
							<label>
    							<input type="radio" name="addr" id="neAddr" value="option2" >
   								 새로운 수신인
  							</label>
							</div>
							</form>
						</div>
					</div>
					<div class="col-sm-4 clearfix">
						<div class="bill-to">
							<p>Old Shipping Information</p>
							<div class="shopper-info">
								<form class="product_cart">
									<input type="hidden" name="orderUserId" value="${userVo.userId}">
									<input type="text" id='oldUserName' name="orderConsingee" readonly="readonly" value="${userVo.userName}">
									<input type="email" id='oldUserEmail' name="orderEmail" readonly="readonly" value="${userVo.userEmail}">
									<input type="text" id='oldUserAddr1' name="orderAddr1" readonly="readonly" value="${userVo.userAddr1}">
									<input type="text" id='oldUserAddr2' name="orderAddr2" readonly="readonly" value="${userVo.userAddr2}">
									<input type="text" id='oldUserZip' name="orderZip" readonly="readonly" value="${userVo.userZip}">
									<input type="text" id='oldUserCellphone1' name="orderCellphone1" style="width:30%;" readonly="readonly" value="${userVo.userCellphone1}">
									<input type="text" id='oldUserCellphone2' name="orderCellphone2" style="width:30%;" readonly="readonly" value="${userVo.userCellphone2}" >
									<input type="text" id='oldUserCellphone2' name="orderCellphone3" style="width:30%;" readonly="readonly" value="${userVo.userCellphone3}">
									<input type="text" id='oldOrderMemo' name="orderMemo" placeholder="배송요청사항을 적어주세요.">
								</form>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="order-message">
							<p>New Shipping Information</p>
							<div class="shopper-info">
								<form class="product_cart">
									<input type="hidden" name="orderUserId" value="${userVo.userId}">
									<input type="text" id='newUserName' name="orderConsingee" placeholder="이름" disabled="disabled">
									<input type="email" id='newUserEmail' name="orderEmail" placeholder="yy@naver.com" disabled="disabled">
									<input type="text" id='newUserZip' name="orderZip" disabled="disabled" style="width:30%;">
									<button type="button" class="btn btn-warning" id="newAddr" style="width:60%; vertical-align: middle;" disabled="disabled">우편번호 찾기</button>
									<input type="text" id='newUserAddr1' name="orderAddr1" disabled="disabled">
									<input type="text" id='newUserAddr2' name="orderAddr2" disabled="disabled">
									<span id="guide" style="color:#999"></span>
									<input type="text" id='newUserCellphone1' name="orderCellphone1" placeholder="010" style="width:30%;" maxlength="4" disabled="disabled">
									<input type="text" id='newUserCellphone2' name="orderCellphone2" placeholder="0000" style="width:30%;" maxlength="4" min="3" disabled="disabled">
									<input type="text" id='newUserCellphone3' name="orderCellphone3" placeholder="0000" style="width:30%;" maxlength="4" min="3" disabled="disabled">
									<input type="text" id='newOrderMemo' name="orderMemo" disabled="disabled" placeholder="배송요청사항을 적어주세요.">
								</form>
							</div>
						</div>	
					</div>					
				</div>
			</div>
		<div class="col-sm-offset-8 col-sm-4" >
			<div class="col-sm-offset-5 col-sm-7 review-payment" style="float:right; text-align: right;">
				<h2>Payment Method</h2>
			</div>
			<br><br>
			<div class="radio payment-options">
			<form class="product_cart">
				<span> <label><input type="radio" name="orderPaymentKind" value="pay1"> 무통장입금</label>	</span>
			 	<span> <label><input type="radio"  name="orderPaymentKind" value="pay2"> 카드</label></span> 
				<span> <label><input type="radio"  name="orderPaymentKind" value="pay3"> 실시간 계좌이체</label> </span>
			</form>
				<button class="btn btn-success" style="margin-top:10px;" id="checkOut" disabled="disabled">결제하기</button>
				<button class="btn btn-danger"  style="margin-top:10px; margin-right: 15px;" disabled="disabled">뒤로가기</button>
			</div>
		</div>
	</div>
</section>