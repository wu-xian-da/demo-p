<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<style>
	span.error{
		display:block;
		background-position: 0 3px;
	}
</style>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-edit">
		
			<h2>查看图片新闻</h2>
			
			<div class="row">
				<div class="col-md-12">

					<hr>

					<div class="row row-list">
						<div class="col-md-1">
							<label>图片标题：</label>
						</div>

						<div class="col-md-8">
							<input name="title" type="text" readonly="readonly" class="form-control" value="${news.title }">
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>选择图片：</label>
						</div>
						
						<div class="col-md-8">
						    <!-- 回显图片 -->
					        <c:choose>
					        	<c:when test="${not empty news.imgPath }">
					        		<img id="imgPathView" src="${news.imgPath }" alt="" style="width:75px;height:77px;">
					        	</c:when>
					        	<c:otherwise>
					        		<img id="imgPathView" src="${baseStatic }/img/no_pic.png" alt="" style="width:75px;height:77px;">
					        	</c:otherwise>
					        </c:choose>
						</div>
					</div>

					<div class="row row-list">
				  		<div class="col-md-1">
				  			<label>新闻内容：</label>
				  		</div>
				  		<div class="col-md-8">
				  			<textarea name="content" id="editor">${news.content }</textarea>
				  		</div>
				  	</div>
				  	
				  	<div class="row row-list">
						<div class="col-md-1">
							<label>显示序号：</label>
						</div>

						<div class="col-md-8">
							<input name="orderNum" type="text" readonly="readonly" class="form-control"  placeholder="显示序号" value="${news.orderNum }">
						</div>
					</div>
	
				  	<div class="row row-list">
				  		<div class="col-md-1">
	
				  		</div>
				  		<div class="col-md-8">
				  			<div class="operation-box">
								<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort">
									<span class="glyphicon glyphicon-arrow-left"></span>
									返回
								</button>
							</div>
				  		</div>
				  	</div>

				</div>
			</div>

		</div>
	</div>
</div>

<%@ include file="/WEB-INF/include/ckeditor.jsp" %>
<script type="text/javascript">
if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
	CKEDITOR.tools.enableHtml5Elements( document );

	// The trick to keep the editor in the sample quite small
	// unless user specified own height.
	CKEDITOR.config.height = 250;
	CKEDITOR.config.width = 'auto';
	
	var initSample = ( function() {
		var wysiwygareaAvailable = isWysiwygareaAvailable(),
			isBBCodeBuiltIn = !!CKEDITOR.plugins.get( 'bbcode' );
	
		return function() {
			var editorElement = CKEDITOR.document.getById( 'editor' );

			// Depending on the wysiwygare plugin availability initialize classic or inline editor.
			if ( wysiwygareaAvailable ) {
				CKEDITOR.replace( 'editor' );
			} else {
				editorElement.setAttribute( 'contenteditable', 'true' );
				CKEDITOR.inline( 'editor' );
	
				// TODO we can consider displaying some info box that
				// without wysiwygarea the classic editor may not work.
			}
		};
	
		function isWysiwygareaAvailable() {
			// If in development mode, then the wysiwygarea must be available.
			// Split REV into two strings so builder does not replace it :D.
			if ( CKEDITOR.revision == ( '%RE' + 'V%' ) ) {
				return true;
			}
	
			return !!CKEDITOR.plugins.get( 'wysiwygarea' );
		}
	} )();
	
	initSample();
	
	var _screen = {
	   width: $(window).width(),
	   height: $(window).height()
	}
	
	
	function resize(){
			var _leftMenu = $("#left-menu").height();
			var _appMain = $(".information-management-edit").height()+100;
	    var __appMainHeight = _leftMenu > _appMain ? _leftMenu : _appMain;
	   	var __screenAppMainHeight = parseInt($(window).height() - ($("#top").height()+1));
	
	   	__appMainHeight = __appMainHeight > __screenAppMainHeight ? __appMainHeight : __screenAppMainHeight;
	
	   	$("#app-main").css({
		  	height: __appMainHeight + "px"
	   	});
	
	   	console.log(__appMainHeight);
	}

CKEDITOR.on('instanceReady', function (e) {
	resize();
});
</script>