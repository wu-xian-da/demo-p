<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<style>
	span.error{
		display: block;
		background-position: 0 3px;
	}
</style>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-edit">
		
			<h2>${empty news.id ? "新增" : "编辑" }图片新闻</h2>
			
			<form id="news_form" method="post">
			<!-- hidden -->
			<input type="hidden" name="id" value="${news.id}"/>

			<div class="row">
				<div class="col-md-12">

					<hr>

					<div class="row row-list">
						<div class="col-md-1">
							<label>图片标题：</label>
						</div>

						<div class="col-md-8">
							<input name="title" type="text" class="form-control {required:true, maxlength:500, messages:{required:'图片标题为必填字段，请填写', maxlength:'图片标题的最大长度为500字符，请确认'}}" placeholder="图片标题" value="${news.title }">
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
							 <input type="file" id="imgPathFile" name="imgPathFile" />
							 <!-- hidden -->
							 <input type="hidden" id="imgPath" name="imgPath" value="${news.imgPath }" />
							 <span id="imgPathTips" class="error" style="display:none;">图片必须上传，请确认</span>
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
							<input name="orderNum" type="text" class="form-control"  placeholder="显示序号" value="${news.orderNum }">
						</div>
					</div>
				  	<div class="row row-list">
				  		<div class="col-md-1">
	
				  		</div>
				  		<div class="col-md-8">
				  			<div class="operation-box">
								<button type="submit" class="btn btn-gy btn-query">
									<span class="glyphicon glyphicon-ok-sign"></span>保存
								</button>
								<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort">
									<span class="glyphicon glyphicon-arrow-left"></span>返回
								</button>
							</div>
				  		</div>
				  	</div>

				</div>
			</div>
			</form>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/include/ckeditor.jsp" %>
<%@ include file="/WEB-INF/include/uploadify.jsp" %>
<script type="text/javascript">
	//定时清除文件上传控件的校验提示
	setInterval(function(){
		var imgPathVal = $("#imgPath").val();
		
	    if(imgPathVal){
	    	$("#imgPathTips").hide();
	    }
	}, 1000);
	//校验
	$("#news_form").validate({
		errorPlacement:function(error,element){
			error.appendTo(element.parent());
		},
		submitHandler:function(form){
			//图片是否上传的校验
		    var imgPathVal = $("#imgPath").val();
	    	if(!imgPathVal){
	    		$("#imgPathTips").show();
	    		return false;
	    	}
		        
		    form.submit();
		}
	
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

function dealImgNewsUpload(file,data,response){
	var result = eval("("+data+")");
	if (result.status) {
		$("#imgPathView").attr("src",result.url);
		$("#imgPath").val(result.url);
	} else {
		alert(result.message);
	}
};

$(document).ready(function(){
	uploadifyPro.auto = true;
	uploadifyPro.multi = false;
	uploadifyPro.fileExt = "*.gif;*.jpg;*.jpeg;*.png;*.bmp;*.ico";
	uploadifyPro.buttonClass = "btn btn-gy btn-upload";
	uploadifyPro.sizeLimit = "200k";
	uploadifyPro.buttonText = "上传图片";
	uploadifyPro.onUploadSuccess = dealImgNewsUpload;
		
	//上传二级栏目头标
	$("#imgPathFile").uploadify(uploadifyPro);
});
</script>