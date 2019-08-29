<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<style>
/* ///// */
/* 	.oneOfQna{
		cursor:pointer;
	} */
</style>
<script>
	$(function() {
		/** 도서후기 페이지 */
		var bookId = ${book.bookId};
		bookId = parseInt(bookId);
		$(document).on('click','ul.pagination li',function(){
		var total = ${total};
		var currentPage = parseInt($("#page li.active").text());
		var page;
		if($(this).text()=='다음페이지'){
			page= currentPage+1;
		}else if($(this).text()=='이전페이지'){
			page= currentPage-1;
		}else{
			page = $(this).text();
		}
		$.ajax({
			url : "/bookking/book/cmtList/"+bookId+"?page="+page,
			method: "get",
			success : function(data) {
				total= (total-5*(page-1));
				var html = '';
				html += '<tr> <th style="text-align: center;">번호</th>';
				html += '<th style="text-align: center;">내용</th><th style="text-align: center;">ID</th>';
				html += '<th style="text-align: center;">평점</th><th style="text-align: center;">등록일</th></tr>';
				$.each(data, function(i, value) {
				html += '<td>'+(total-i)+'</td><td>'+value.pCommentLatterPart+'</td>';
				html += '<td>'+value.pCommentUserId+'</td><td style="letter-spacing:-3px">';
					for(var j=0; j<value.pCommentPoint;j++){
						html += '★';
					}
					for(value.pCommentPoint; value.pCommentPoint<5;value.pCommentPoint++){
						html += '☆';
					}
				html += '</td><td>'+value.pCommentRegistrationDate+'</td></tr>';
				})
				$("#cmtTable").html(html);
				$.ajax({
					url : "/bookking/book/pagenate/"+bookId+"?page="+page,
					method: "get",
					contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
					success : function(data) {
						$("#page").html(data);
					}
				});
			}
		});
		return false;
	});
		
		/* Q&A
		$('.oneOfQna').click(function(){
			var qnaId = $(this).parent().parent().children('td').first().attr('value');
			if($(this).parent().parent().hasClass(qnaId)){
				$(this).parent().parent().removeClass(qnaId);
				$(this).parent().parent().next().remove();
			}else{
				$(this).parent().parent().addClass(qnaId);
			}
			$.ajax({
				url: "${pageContext.request.contextPath}/qna/list",
				method: "GET",
				contentType: "application/json; charset=utf-8",
				dataType : "text",
				data: {
					"qnaId" : qnaId
				},
				success: function(data){
					$('<tr style="background-color:#f9f9f9"><td><h2 style="margin:0px">Q</h2></td><td colspan="3" style="text-align:left">'+data+'</td></tr>').insertAfter($('.'+qnaId));
				},
				error : function(xhr, status, error) {
					console.log("도서리스트를 가져오지 못했습니다.");
					console.log(xhr.status);
				}
			});
		});
		*/
		
		/* Q&A 작성 */
		$('#writeQNA').click(function(){
			if("${userId}"==null || "${userId}"==""){
				alert('로그인을 하신 후 이용해주세요.');
				document.location = "<c:url value='/user/userLogin'/>";
			}else{
				window.open('${pageContext.request.contextPath}/qna/popup?bookId='+bookId+'&userId='+'${userId}','_blank', 'width=730, height=750, toolbar=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no' );
			}
		});
		
	
		$("#orderBook").click(function(){
			var stock = $('#bookStock').val();
			if(stock==0){
				alert('1개 이상 담아주세요^^');
				return false;
			}
			var url = '<c:url value="/order/books"/>' + "?bookId=" + bookId + "&cartStock=" + stock ;
			$(location).attr("href",url );
		});
		
		//카트에 상품 담기
		$("#addCartBtn").click(function(){
				var stock = $("#bookStock").val();	//수량
				if(stock==0){
					alert('1개 이상 담아주세요^^');
					return false;
				}
				bookId = "${book.bookId}";
//   				var url = '<c:url value="/cart/addCart"/>' + "?bookId=" + bookId+ "&stock=" + stock ;
//  				$(location).attr("href",url );
 				
 				$.ajax({
 					url : "/bookking/cart/addCart?bookId=" +bookId + "&stock=" + stock
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


<section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">
					<jsp:include page="../left-sidebar.jsp"></jsp:include>
				</div>
				
				<div class="col-sm-9 padding-right">
					<div class="product-details"><!--product-details-->
						<div class="col-sm-5">
							<div class="view-product">
								<img src="<c:url value='/${book.bookListImage}'/>" alt="" />
							</div>

						</div>
						<div class="col-sm-7">
							<div class="product-information"><!--/product-information-->
								<h2>▶ ${book.bookName}</h2>
								<h4>▶ 저자 : ${book.bookWriter}</h4>
								
								<p><b>재고: </b>${book.bookStock}</p>
								<p><b>출판사:</b> ${book.bookPublisher}</p>
								<p><b>등록일:</b> ${book.bookDate}</p>
								<span>
									<span>가격: <fmt:formatNumber>${book.bookPrice}</fmt:formatNumber> 원</span><br>
									<label>수량:</label><input type="text" value="1"  id="bookStock"/>
								</span>
								<span>
									<button type="button" class="btn btn-fefault cart" id="addCartBtn">
										<i class="fa fa-shopping-cart"></i>
										Add to cart
									</button></span><br>
								<a id="orderBook">
								<button type="button" class="btn btn-default cart">
								<i class="fa fa-flag"></i>
									Order
								</button></a>
								
								
							</div><!--/product-information-->
						</div>
					</div><!--/product-details-->
					
					<div class="category-tab shop-details-tab"><!--category-tab-->
						<div class="col-sm-12">
							<ul class="nav nav-tabs">
								<li><a href="#details" >BOOK CONTENTS</a></li>
								<li><a href="#review" >BOOK REVIEW</a></li>
								<li><a href="#qna" >BOOK Q&A</a></li>
							</ul>
						</div>
					</div>
					<!--recommended_items-->
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center" id="details">Book Contents</h2>
						<div class="col-sm-12">
							<h5>${book.bookContents}</h5>
						</div>
					</div><!--/book_contents-->
					
					<div class="reply_items" style="height: 380px;">
						<h2 class="title text-center" id="review">Book Review</h2>
						<div class="col-sm-12">
							<table class="table table-striped" style="text-align: center;" id="cmtTable">
								<tr>
									<th style="text-align: center;">번호</th>
									<th style="text-align: center;">내용</th>
									<th style="text-align: center;">ID</th>
									<th style="text-align: center;">평점</th>
									<th style="text-align: center;">등록일</th>
								</tr>
								<c:forEach items="${list}" var="comment" varStatus="count">
								<tr>
									<td>${total-count.count+1}</td>
									<td>${comment.pCommentLatterPart}</td>
									<td>${comment.pCommentUserId}</td>
									<td style='letter-spacing:-3px'>
										<c:forEach begin="1" end="${comment.pCommentPoint}">★</c:forEach>
										<c:forEach begin="${comment.pCommentPoint+1}" end="5">☆</c:forEach>
									</td>
									<td>${comment.pCommentRegistrationDate}</td>
								</tr>
								</c:forEach>
							</table>
							<div id="pagination" style="text-align: center; margin: auto;">
							<ul class="pagination" id="page">
							 	${pagenate}
							</ul>
							</div>
						</div>
					</div>
				<br><br>
				
					<div class="comment_items">
						<h2 class="title text-center" id="qna">Product Q&A</h2>
						<div class="col-sm-12">

							<table class="table" id="qnaTable" style="text-align:center;">
								<tr>
									<th style="text-align: center;">번호</th>
									<th style="text-align: center;">문의/답변</th>
									<th style="text-align: center;">사용자ID</th>
									<th style="text-align: center;">등록일</th>
								</tr>
								
								<c:forEach items="${qnaList}" var="qna" varStatus="status">
									<tr class="trOfQna">
										<td value="${qna.qnaId}">${qnaList.size()-status.index}</td>
										<td><a name="myQna" href="#none" class="oneOfQna" >${qna.qnaId}</a></td>
										<td>${qna.qnaUserId}</td>
										<td>${qna.qnaRegistrationDate}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<button type="button" class="btn btn-default get" id="writeQNA" style="float:right; top:0px; margin:15px">
							상품Q&A작성
						</button>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script>
		$('a[name="myQna"]').on('click',function(){
				//alert('myQna');
				//alert($(this).html());
				//var anotherTr='<tr><td>another td</td></tr>';
				//$(anotherTr).insertAfter($(this).closest('tr'));
				var qnaId = $(this).html();
				var myTr = $(this).closest('tr');
				$.ajax({
						url:'${pageContext.request.contextPath}/qna/list',
						data:{'qnaId':qnaId},
						success: function(data){
								//alert('success');
								//alert(data);
								var anotherTr = '<tr><td>'+data+'</td></tr>';
								$(anotherTr).insertAfter(myTr);
							}
					});
				
				
			});
	</script>