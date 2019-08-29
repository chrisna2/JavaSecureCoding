<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<style>
	tbody tr{
		text-align:center;
	}
</style>

<script type="text/javascript">
$(function(){
	init();
	
	function init(){
	var length = "${fn:length(cartList)}";						//카트에 담긴 length
	var delivery =parseInt($("#delivery").text());			//배송비
	var totalPrice =0;					
 
 	for(var i=0;i<length;i++){
 			totalPrice += parseInt($("#totalPrice"+ i).text());
 	    }
	
	if( length !=0 && totalPrice<30000){
		$("#delivery").text(2500);
		 delivery =parseInt($("#delivery").text());
 		$("#toalP").text((totalPrice+delivery));
	}else{
		$("#delivery").text(0);
		$("#toalP").text(totalPrice);
	}
  		$("#total").text(totalPrice);
	}
	
	//상품 plus
	$("a[id^=plus]").click(function(e){
		var index= this;
 		var id= index.getAttribute('id');
 		var ind= id.substring(4);									//index 가져오기
		var count = parseInt($("#quantity"+ind).val())+1;	//상품갯수
		var price = parseInt($("#price"+ind).text());		//상품가격
		var bookId = $("#bookId"+ind).val();   

		 $("#quantity"+ind).val(count);
		
		price = price *(count);
		$("#totalPrice"+ ind).text(price); 
		$("#cartUpdate"+ind).val(bookId + "," +count);   
		
		init();																//totalP값 초기화
	});
	
	//상품 minus
	$("a[id^=minus]").click(function(){
		 var index= this;
 		var id= index.getAttribute('id');
 		var ind= id.substring(5);									//index 가져오기
		var count = parseInt($("#quantity"+ind).val());	//상품갯수
		var price = parseInt($("#price"+ind).text());		//상품가격
		var bookId = $("#bookId"+ind).val();   
 		if(count == 1){
 			alert("최소 한개이상이여야합니다.");
 			return false;
 		}
		$("#quantity"+ind).val(count-1);
 		price = price *(count-1);
 		$("#totalPrice"+ ind).text(price); 		
		$("#cartUpdate"+ind).val(bookId + "," +(count-1));   
 		
 		init();																//totalP값 초기화
	});
	
	//상품삭제
	$("a[id^=deleteCart]").click(function(){
		var result = confirm("삭제하시겠습니까?");
		var id= this.getAttribute('id');
		var ind= id.substring(10);			
		var bookId = $("#bookId"+ind).val();
		if(result){
  		$.ajax({
			type: "GET"
			,url: "deleteCart"
			,dataType:"json"
			,data :{"bookId":bookId}
			,contentType: "application/json; charset=utf-8"
			,success : function(data){
				if(data == 1){
					alert("상품이 삭제되었습니다.");
					location.href="cart";
				}
			}
			,error : function(xhr,status,error){
				alert(xhr.status);
			}
		}); 
 		}
	});
	
	
	//구매하기 버튼 클릭시 카트 update
	$("#orderBtn").click(function(){
   		var list =  JSON.stringify($("form").serializeArray());
		$.ajax({
			type: "post"
			,url: "/bookking/order/updateCart"
 			,dataType:"json"
			,contentType: "application/json; charset=utf-8"
			,data : list
			,success : function(data){
				if(data){
				var result = confirm("구매하시겠습니까?");
					if(result){
						location.href="/bookking/order/cart"
					}
				}
			}
			,error : function(xhr,status,error){
				alert(xhr.status);
			}
		});
		
		});
	
	});
</script>

<body>

	<section id="cart_items">
		<div class="container">
			<div class="breadcrumbs">
				<ol class="breadcrumb">
				  <li><a href="#">Home</a></li>
				  <li class="active">Shopping Cart</li>
				</ol>
			</div>
			<div class="table-responsive cart_info">
				
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td ></td>
							<td class="image">Item</td>
							<td class="description"></td>
							<td class="price">Price</td>
							<td class="quantity">Quantity</td>
							<td class="total">Total</td>
							<td></td>
						</tr>
					</thead>
					
					<tbody>
					<form name="cartListForm">
					<c:forEach items="${cartList }" var="cartItem" varStatus="status">
						<input type="hidden" id="bookId${status.index}"  value="${cartItem.CART_BOOK_ID}"/> 
	  					<input type="hidden" id="cartUpdate${status.index}" name="bookId" value="${cartItem.CART_BOOK_ID},${cartItem.CART_STOCK}"/> 
						<tr>
						<td></td>
							<td class="cart_product">
								<a href="/bookking/book/${cartItem.CART_BOOK_ID }"><img src="<c:url value='/${cartItem.BOOK_LIST_IMAGE }'/>" width="110px"  height="110px"></a>
							</td>
							<td class="cart_description">
								<h4><a href="/bookking/book/${cartItem.CART_BOOK_ID }">${cartItem.BOOK_NAME}</a></h4>
								<p>저  자 : ${cartItem.BOOK_WRITER}</p>
							</td>
							<td class="cart_price">
								<p id ="price${status.index}" >  ${cartItem.BOOK_PRICE} </p>
							</td>
							<td class="cart_quantity">
								<div class="cart_quantity_button">
									<a class="cart_quantity_up"  id="plus${status.index}" href="#"> + </a>
<%-- 									<input class="cart_quantity_input" type="text" name="quantity" id="quantity${status.index}"  value="${cartItem.CART_STOCK}" autocomplete="off" size="2"> --%>
									<input class="cart_quantity_input" type="text" id="quantity${status.index}"  value="${cartItem.CART_STOCK}" autocomplete="off" size="2">
									<a class="cart_quantity_down" id="minus${status.index}" href="#" > - </a>
								</div>
							</td>
							<td class="cart_total">
								<p class="cart_total_price"  id="totalPrice${status.index}">${cartItem.BOOK_PRICE * cartItem.CART_STOCK} </p>
							</td>
							<td class="cart_delete">
								<a class="cart_quantity_delete" id="deleteCart${status.index}" ><i class="fa fa-times" ></i></a>
							</td>
						</tr>
						</c:forEach>
						</form>
					</tbody>
				</table>
				
			</div>
		</div>
	</section> <!--/#cart_items-->

	<section id="do_action">
		<div class="container">

			<div class="heading">
				<hr>
				<p align="right">
					상품구매금액 : <strong id="total">0</strong> + 배송비 : <strong id="delivery">0</strong> =
					<strong style="color: red;" id="toalP">  </strong>
				</p>
				<hr>
			</div>
			
			
			<div class="heading" style="text-align:right">
				<a class="btn btn-default update" id="orderBtn" <%-- href="<c:url value='/order/cart'/>"  --%>>구매하기</a>
			</div>
			
		</div>

	</section>
	<!--/#do_action-->



	<!-- <script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
    <script src="js/jquery.prettyPhoto.js"></script> -->
</body>
</html>