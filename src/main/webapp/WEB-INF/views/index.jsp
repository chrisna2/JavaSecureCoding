<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt"%>
<script>
$(function(){
	//도서 상품을 클릭할시, detail 페이지로 이동한다.
	$('.single-products').click(function(event){
 		var bookId = $(this).children().children().next().next().next().val();
		document.location = "<c:url value='/book/"+bookId+"'/>";
	});	

//addCart클릭시 cart에 상품 담기
$(".btn").click(function(event){
	event.stopPropagation();
	var bookId = $(this).prev().val();
		$.ajax({
				url : "/bookking/cart/addCart?bookId=" +bookId + "&stock=" + 1
				,method: "get"
				,contentType: "application/x-www-form-urlencoded; charset=UTF-8"
				,success: function(data){
					if(data == 1){
						var result = alert("이미담겨있는 상품입니다.");
						if(result){
							return false;
						}
					}else{
						var result = confirm("상품을 담았습다. 장바구니로 이동하시겠습니까?");
						if(result == true){
							location.href='/bookking/cart/cart';
						
						}else{
							return false;
						}
					
					}
				}
				,error : function(xhr, status, error) {
					alert(xhr.status);
				}
			});
	});
	
	
});
</script>

<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active" style="height: 400px;">
								<div class="col-sm-6">
									<h1>대박세일찬스!!</h1>
									<h2>모든 책이 오직 99달러</h2>
									<p>김영하, 이문열, 무라카미 하루키, 히가시노 게이고, 베르베르 베르나르, 기욤뮈소 등 세계 거장들의 책 모음전. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<img src="<c:url value='/images/home/main1.PNG'/>" class="girl img-responsive" alt="" />
									<img src="<c:url value='/images/home/pricing.png'/>"  class="pricing" alt="" />
								</div>
							</div>
							<div class="item" style="height: 400px;">
								<div class="col-sm-6">
								<button type="button" class="btn btn-default get">둘러 보기</button>
								</div>
								<div class="col-sm-6">
									<img src="<c:url value='/images/home/main2.PNG'/>" class="pricing" alt="" />
								</div>
							</div>
							
							<div class="item" style="height: 400px; text-align: center;">
								<div class="col-sm-12">
									<img src="<c:url value='/images/home/main3.jpg'/>" width="800px" height="350px" alt="" style="margin:0 auto;"/>
								</div>
							</div>
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	
	<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3"><!-- left-sidebar -->
					<jsp:include page="left-sidebar.jsp"></jsp:include>
				</div><!-- /left-sidebar -->
				
				<div class="col-sm-9 padding-right">
					<div class="features_items"><!--features_items-->
						<h2 class="title text-center">Best Seller</h2>
						
						<c:forEach items="${bestList }" var="bestList">
						<div class="col-sm-4">
							<div class="product-image-wrapper">
								<div class="single-products">
									<div class="productinfo text-center">
										<img src="<c:url value='/${bestList.bookListImage }'/>" width="255.260px"  height="350"/>
										<h2><fmt:formatNumber>${bestList.bookPrice}</fmt:formatNumber> 원</h2>
										<p>${bestList.bookName }</p>
										<input type="hidden"  value="${bestList.bookId }">
										<a class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Add to cart</a>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
						
					</div><!--features_items-->
				</div>
			</div>
		</div>
	</section>