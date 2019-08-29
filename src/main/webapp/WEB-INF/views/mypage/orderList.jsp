<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<style>
	tbody tr{
		text-align:center;
	}
</style>
<script>

	$(function() {
		init();

		function init(){
			var total = 0;
			$('p[name^="orderPrice"]').each(function() {
				var price = uncomma($(this).text());
			    total += parseInt(price);
			});
			total = comma(total);
			$('#totalP').html(total+' 원');
		}
		//콤마찍기
		function comma(str) {
		    str = String(str);
		    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
		}
		
		//콤마풀기
		function uncomma(str) {
		    str = String(str);
		    return str.replace(/[^\d]+/g, '');
		}
		
	});
</script>

<body>

	<section id="cart_items">

		<div class="container">
		<div class="category-tab shop-details-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
								<li><a href="<c:url value='/user/mypage'/> " >회원정보수정</a></li>
								<li><a href="<c:url value='/user/orderList'/>" >주문조회</a></li>
							</ul>
						</div>
			</div>
	
		<div class="table-responsive cart_info">
			<table class="table table-condensed">
				<thead>
					<tr class="cart_menu">
						<td class="image">orderNo</td>
						<td class="image">Item</td>
						<td class="description">Book Name</td>
						<td class="price">Price</td>
						<td class="quantity">Quantity</td>
						<td class="total">Total</td>
					</tr>
				</thead>
				<tbody>
						<!-- 카트에서 넘어온경우 -->
					<c:forEach items="${orderGoodsList}" var="book">
					<tr>
						<td>${book.ORDER_ID}</td>
						<td class="cart_product"><a href="<c:url value='/book/${book.orderGoodsId}'/>">
						<img src="<c:url value='/${book.BOOK_LIST_IMAGE}'/>" alt="" width="110px"  height="110px"></a></td>
						<td class="cart_description">
							<h4>
								<a href="">${book.BOOK_NAME}</a>
							</h4>
							<p>저자 : ${book.BOOK_WRITER} </p>
						</td>
						<td class="cart_price">
							<p><fmt:formatNumber>${book.BOOK_PRICE}</fmt:formatNumber></p>
						</td>
						<td class="cart_quantity">
							<div class="cart_quantity_button">
								<input
									class="cart_quantity_input" type="text" name="orderStock"
									value="${book.ORDER_GOODS_STOCK}" autocomplete="off" size="2">
							</div>
						</td>
						<td class="cart_total">
							<p class="cart_total_price" name="orderPrice"><fmt:formatNumber>${book.ORDER_GOODS_PRICE }</fmt:formatNumber></p>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="4">&nbsp;</td>
						<td colspan="2">
							<table class="table table-condensed total-result">
								<tr>
									<td>Total</td>
									<td>
									<span id="totalP"></span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
					
			</table>
		</div>

	</section>