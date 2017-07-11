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
			<h2>已推送信息详情</h2>

			<div class="row">
				<div class="col-md-12">

					<hr>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息名称：</label>
						</div>

						<div class="col-md-8">
							<input type="text" value="${infoPush.infoName }" readonly="readonly" class="form-control" placeholder="信息名称">
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息类型：</label>
						</div>

						<div class="col-md-8">
							<select id="infoType" class="form-control" disabled="disabled">
								<option value="${infoPush.infoType }">${infoPush.infoType.name }</option>
							</select>
						</div>
					</div>


					<div id="infoImgDiv" style="display:none;" class="row row-list">
						<div class="col-md-1">
							<label>信息配图：</label>
						</div>

						<div class="col-md-8">
							<!-- 回显图片 -->
					        <c:choose>
					        	<c:when test="${not empty infoPush.infoImg }">
					        		<img id="infoImgView" src="${infoPush.infoImg }" alt="" style="width:75px;height:77px;">
					        	</c:when>
					        	<c:otherwise>
					        		<img id="infoImgView" src="${baseStatic }/img/no_pic.png" alt="" style="width:75px;height:77px;">
					        	</c:otherwise>
					        </c:choose>
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>推送范围：</label>
						</div>

						<div class="col-md-8">
							<label class="checkbox-inline">
							  <input type="checkbox" 
							  	<c:if test="${infoPush.pushRange eq 'wechat'}">
							  		checked="checked"
							  	</c:if>
							  value="wechat"> 微信公众号
							</label>
						</div>
					</div>

					<div class="row row-list">
			  		<div class="col-md-1">
			  			<label>信息内容：</label>
			  		</div>
			  		<div class="col-md-8">
			  			<textarea id="editor" >${infoPush.infoContent }</textarea>
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
<%@ include file="/WEB-INF/include/uploadify.jsp" %>
<script type="text/javascript">
function dealPushType(id){
	var pushType = $("#"+id).val();
	if(pushType && 'TPXW' == pushType){
		$("#infoImgDiv").show();
	}else{
		$("#infoImgDiv").hide();
	}
}

$(document).ready(function(){
	dealPushType('infoType');
});

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