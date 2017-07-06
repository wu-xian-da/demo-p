<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="/WEB-INF/include/header.jsp"%>
	<%@ include file="/WEB-INF/include/css.jsp"%>
</head>	
<body>
	<div class="banner">
		<div class="swiper-container">
		    <div class="swiper-wrapper">
		        <c:forEach items="${imgNewsList }" var="imgNews">
		        	 <div class="swiper-slide">
		        	    <a href="${base }/web/news/toDetail/${imgNews.id}">
		        	 		<img src="${imgNews.imgPath }" alt="${imgNews.title }">
		        	 		<h3>${imgNews.title }</h3>
		        	 	</a>
		        	 </div>
		        </c:forEach>
		    </div>
		    <div class="swiper-pagination" style="text-align:right;"></div>
		</div>
	</div>

	<!-- //banner end -->

	<div class="navigation">
	    <c:forEach items="${navList }" var="nav">
	        <c:choose>
	        	<c:when test="${not empty nav.navType and 'XXEJCD' eq nav.navType}">
	        <a href="${base }/web/nav/toList/${nav.id}">		
	        	</c:when>
	        	<c:when test="${not empty nav.navType and 'WEJCD' eq nav.navType}">
	        <a href="${base }/web/info/toContent/${nav.id}">	
	        	</c:when>
	        	<c:when test="${not empty nav.navType and 'URLWL' eq nav.navType}">
	        <a href="${nav.navUrl.url }" target="${nav.navUrl.target }">
	        	</c:when>
	        	<c:otherwise>
	        <a href="javascript:void(0);">
	        	</c:otherwise>
	        </c:choose>
				<h3><img src="${nav.navIcon }" alt="${nav.navName }"></h3>
				<p>${nav.navName }</p>
			</a>
	    </c:forEach>
	
	    <div class="weather-tips-wrap">
			<div class="weather-tips">
				<ul>
					<c:forEach items="${noticeList }" var="notice">
						<li><a href="${base }/web/notice/toDetail/${notice.id }">【紧急通告】${notice.title }</a></li>
					</c:forEach>
				</ul>
			</div>
			
			<a href="${base }/web/notice/toList" class="more">更多>></a>
		</div>
	</div>
	
	<!-- //navigation weather end -->


	<div class="weather-banner">
		<img src="${baseStatic}/img/a-1.jpg" alt="">

		<div class="weather-banner-info">
			<dl>
				<dt><img src="${baseStatic}/img/sun-icon.png" alt=""></dt>
				<dd>晴天</dd>
				<dd>16-19℃ 空气质量良 能见度好</dd>
				<dd class="last">出行建议：阳光明媚，正是出行的好日子</dd>
			</dl>
		</div>
	</div>


	<div class="news-box">
		<h3>机场资讯</h3>

		<div class="news-list">
			<div class="news-thumb">
				<img src="${baseStatic}/img/news-thumb.png" alt="">
			</div>

			<div class="news-destro">
				<h3>上海出发，新加坡6天4晚自由行</h3>
				<h4>上海返回直机，五星级酒店，名额有限storing....</h4>
				<p><strong>￥2999</strong>起 <i>5.5折</i></p>
			</div>
		</div>

		<div class="news-list">
			<div class="news-thumb">
				<img src="${baseStatic}/img/news-thumb.png" alt="">
			</div>

			<div class="news-destro">
				<h3>上海出发，新加坡6天4晚自由行</h3>
				<h4>上海返回直机，五星级酒店，名额有限storing....</h4>
				<p><strong>￥2999</strong>起 <i>5.5折</i></p>
			</div>
		</div>

		<div class="news-list">
			<div class="news-thumb">
				<img src="${baseStatic}/img/news-thumb.png" alt="">
			</div>

			<div class="news-destro">
				<h3>上海出发，新加坡6天4晚自由行</h3>
				<h4>上海返回直机，五星级酒店，名额有限storing....</h4>
				<p><strong>￥2999</strong>起 <i>5.5折</i></p>
			</div>
		</div>

		<div class="news-list">
			<div class="news-thumb">
				<img src="${baseStatic}/img/news-thumb.png" alt="">
			</div>

			<div class="news-destro">
				<h3>上海出发，新加坡6天4晚自由行</h3>
				<h4>上海返回直机，五星级酒店，名额有限storing....</h4>
				<p><strong>￥2999</strong>起 <i>5.5折</i></p>
			</div>
		</div>
	</div>
	
	<!-- home页版权特殊 -->
    <div class="footer">
		<h2>贵州机场集团有限公司 版权所有</h2>
	</div>
	
    <%@ include file="/WEB-INF/include/js.jsp"%>

<script>
	document.addEventListener("DOMContentLoaded", function(){
		var speed = 50;
		var scrollBox = document.querySelector(".weather-tips");
		var scrollUl = scrollBox.innerHTML + scrollBox.innerHTML;
		scrollBox.innerHTML = scrollUl;

		var scrollUlNode1 = scrollBox.querySelectorAll("ul")[0];
		var scrollUlNode2 = scrollBox.querySelectorAll("ul")[1];
		
		var scrollFun = function(){
			if(scrollUlNode2.offsetTop-scrollBox.scrollTop <=0 ){
				scrollBox.scrollTop-=scrollUlNode1.offsetHeight
			}else{
				scrollBox.scrollTop++;
			}
		}

		var SCROLL = setInterval(scrollFun,speed);
		
		 if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini|Mobile/i.test(navigator.userAgent)){
			scrollBox.ontouchstart = function(){clearInterval(SCROLL);}
			scrollBox.ontouchend = function(){ SCROLL = setInterval(scrollFun,speed);}
		}else{
			scrollBox.onmouseover = function(){clearInterval(SCROLL);}
			scrollBox.onmouseout = function(){ SCROLL = setInterval(scrollFun,speed);}
		}
	});
</script>
</body>
</html>