<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	.category{
		cursor:pointer;
	}
	.single-products{
		cursor:pointer;
	}
</style>
<script>
	$(function() {
	init();
	//index or detail 화면에서 카테고리 선택시 
	if('${categoryId}' > 0){
		$('.category').slice(0,15).each(function(index){
			if($(this).attr('id')=='${categoryId}'){
				$(this).parent().parent().parent().parent().attr("aria-expanded", "true");
				$(this).parent().parent().parent().parent().removeClass();
				$(this).parent().parent().parent().parent().addClass("panel-collapse collapse in");
			}
		});
		getBookListByCategoryId('${categoryId}');
	}
	
	//콤마찍기
	function comma(str) {
	    str = String(str);
	    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	}
		
	function init(){
		//booklist 화면진입시, 카테고리에 클래스를 추가해준다.
		//pageType/classType => index, bookList, detail, cart
		$('.category').addClass('${pageType}');
	}
		
	
	//booklist 화면에서 카테고리를 클릭시
	$('.category').click(function(){
		var categoryId = $(this).attr('id');
		if($(this).hasClass('bookList')){
			getBookListByCategoryId(categoryId);
		} else{
			document.location = "<c:url value='/category/"+categoryId+"'/>";
		}
	});
	
	function getBookListByCategoryId(categoryId){
		//ajax로 도서 리스트를 가져온다.
		$.ajax({
			url: "${pageContext.request.contextPath}/category/booklist",
			method: "GET",
			contentType: "application/json; charset=utf-8",
			dataType : "json",
			data: {
				"categoryId" : categoryId
			},
			success: function(data,e){
				if($('.features_items .col-sm-4').length!=0){
					$('.features_items .col-sm-4').remove()
				};
				$.each(data,function(i){
					var price = comma(data[i].bookPrice);
					$('<div class="col-sm-4">').append(
							"<div class='product-image-wrapper'>"+
								"<div class='single-products'>"+
									"<div class='productinfo text-center'>"+
										"<img src='<c:url value='/"+data[i].bookListImage+"'/>' alt='' width='255.260px' height='350px'/>"+
										"<p id='bookId' hidden>"+data[i].bookId+"</p>"+
										"<h2>"+price+"원</h2>"+
										"<p>"+ data[i].bookName +"</p>"+
										"<a class='btn btn-default add-to-cart' ><i class='fa fa-shopping-cart'></i>Add to cart</a>"+
									"</div>"+
								"</div>"+
							"</div>"+
						"</div>"
					).insertAfter('h2.title');
				});
				//도서 상품을 클릭할시, detail 페이지로 이동한다.
				$('.single-products').click(function(event){
					var bookId = $(this).children().children('#bookId').text();
					document.location = "<c:url value='/book/"+bookId+"'/>";
					
				});
				
				//addCart클릭시 cart에 상품 담기
				$(".btn").click(function(event){
					event.stopPropagation();
					var bookId = $(this).prev().prev().prev("#bookId").text();
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
			},
			error : function(xhr, status, error) {
				console.log("도서리스트를 가져오지 못했습니다.");
				console.log(xhr.status);
			}
		});
	}
		
	
});
		
</script>

<div class="left-sidebar">
<h2>Category</h2>
<div class="panel-group category-products" id="accordian"><!--category-productsr-->
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordian" href="#novel">
					<span class="badge pull-right"><i class="fa fa-plus"></i></span>
					소설
				</a>
			</h4>
		</div>
		<div id="novel" class="panel-collapse collapse">
			<div class="panel-body">
				<ul>
				<c:forEach items="${categoryList}" var="category" begin="0" end="6">
					<li>
				<a class="category" id="${category.categoryId}">${category.categoryKind2} </a></li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordian" href="#self_imp">
					<span class="badge pull-right"><i class="fa fa-plus"></i></span>
					자기계발
				</a>
			</h4>
		</div>
		<div id="self_imp" class="panel-collapse collapse">
			<div class="panel-body">
				<ul>
				<c:forEach items="${categoryList}" var="category" begin="7" end="10">
					<li><a class="category" id="${category.categoryId}">${category.categoryKind2} </a></li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a data-toggle="collapse" data-parent="#accordian" href="#religion">
					<span class="badge pull-right"><i class="fa fa-plus"></i></span>
					종교
				</a>
			</h4>
		</div>
		<div id="religion" class="panel-collapse collapse">
			<div class="panel-body">
				<ul>
					<c:forEach items="${categoryList}" var="category" begin="11" end="14">
						<li><a class="category" id="${category.categoryId}">${category.categoryKind2} </a></li>
<%-- 						<li><a class="category" href="<c:url value='/category/${category.categoryId}'/>">${category.categoryKind2} </a></li> --%>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
				<a class="category" id="${categoryList[15].categoryId}">${categoryList[15].categoryKind1} </a>
<%-- 				<a class="category" href="<c:url value='/category/${categoryList[15].categoryId}'/>">${categoryList[15].categoryKind1}</a> --%>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
			<a class="category" id="${categoryList[16].categoryId}">${categoryList[16].categoryKind1} </a>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
			<a class="category" id="${categoryList[17].categoryId}">${categoryList[17].categoryKind1} </a>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
			<a class="category" id="${categoryList[18].categoryId}">${categoryList[18].categoryKind1} </a>
			</h4>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4 class="panel-title">
			<a class="category" id="${categoryList[19].categoryId}">${categoryList[19].categoryKind1} </a>
			</h4>
		</div>
	</div>
</div><!--/category-products-->

<div class="shipping text-center"><!--shipping-->
	<img src="<c:url value='/images/home/shipping.jpg'/>" alt="" />
</div><!--/shipping-->

</div>