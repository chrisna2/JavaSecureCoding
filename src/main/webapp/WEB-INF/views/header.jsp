<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>	
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header id="header"><!--header-->
		
		<div class="header-middle"><!--header-middle-->
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="logo pull-left">
							<a href="<c:url value='/'/>"><img src="<c:url value='/images/home/logo2.png'/>" alt="" /></a> &nbsp; &nbsp; Book King
						</div>
					</div>
					<div class="col-sm-8">
						<div class="shop-menu pull-right">
						<c:choose>
							<c:when test="${sessionScope.loginId != null }">
							<ul class="nav navbar-nav">
								<li><a>${sessionScope.loginId } 님 로그인</a></li>
								<li><a href="<c:url value='/user/mypage'/>"><i class="fa fa-user"></i> MyPage</a></li>
								<li><a href="<c:url value='/cart/cart' />"><i class="fa fa-shopping-cart"></i> Cart</a></li>
 								<li><a href="<c:url value='/user/logout'/>"><i class="fa fa-lock"></i> Logout</a></li> 
							</ul>
							</c:when>
							<c:otherwise>
							<ul class="nav navbar-nav">
								<li><a href="<c:url value='/user/userLogin'/>"><i class="fa fa-lock"></i> Login</a></li>
							</ul>
							</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-middle-->
	
		<div class="header-bottom"><!--header-bottom-->
			<div class="container">
				<div class="row">
					<div class="col-sm-offset-7 col-sm-5">
						<div class="search_box pull-right">
							<input type="text" placeholder="Search"/>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header-bottom-->
	</header><!--/header-->
