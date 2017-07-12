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
			<h2>${empty infoPush.id ? "新增" : "编辑" }待处理推送信息</h2>

			<form id="infoPush_form" method="post">
			<!-- hidden -->
			<input type="hidden" name="id" value="${infoPush.id }" />
			
			<div class="row">
				<div class="col-md-12">

					<hr>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息名称：</label>
						</div>

						<div class="col-md-8">
							<input type="text" name="infoName" value="${infoPush.infoName }" class="form-control {required:true, maxlength:500, messages:{required:'信息名称为必填字段，请填写', maxlength:'信息名称的最大长度为500字符，请确认'}}" placeholder="信息名称">
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息类型：</label>
						</div>

						<div class="col-md-8">
							<select id="infoType" name="infoType" onchange="dealPushType(this.id);" class="form-control {required:true, messages:{required:'请选择信息类型'}}">
								<option value="">---请选择---</option>
								<c:forEach items="${pushTypes }" var="pushType">
							    	<option value="${pushType }"
							    		<c:if test="${pushType eq infoPush.infoType }">
							    			selected="selected"
								  	 	</c:if>
							    	>${pushType.name }</option>
							    </c:forEach>
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
					        <style>
					        	#infoImgFile-button{width:auto;}
					        </style>
							<input type="file" id="infoImgFile" name="infoImgFile" />
							<!-- hidden -->
							<input type="hidden" id="infoImg" name="infoImg" value="${infoPush.infoImg }" />
							
							<span class="tips-text">（64K以内）</span>
							
							<span id="infoImgTips" class="error" style="display:none;">图片必须上传，请确认</span>
						</div>
					</div>

					<div class="row row-list">
			  		<div class="col-md-1">
			  			<label>信息内容：</label>
			  		</div>
			  		<div class="col-md-8">
			  			<textarea name="infoContent" id="editor">${infoPush.infoContent }</textarea>
			  		</div>
			  	</div>

			  	<div class="row row-list">
			  		<div class="col-md-1">

			  		</div>
			  		<div class="col-md-8">
			  			<div class="operation-box">
							<button type="submit" class="btn btn-gy btn-query">
								<span class="glyphicon glyphicon-ok-sign"></span>
								保存
							</button>
							
							<button type="button" onclick="javascript:history.back();" class="btn btn-gy btn-sort">
								<span class="glyphicon glyphicon-arrow-left"></span>
								返回
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

//定时清除文件上传控件的校验提示
setInterval(function(){
	 var infoImgVal = $("#infoImg").val();
	
    if(infoImgVal){
    	$("#infoImgTips").hide();
    }
}, 1000);

//校验
$("#infoPush_form").validate({
	errorPlacement: function (error, element){
		error.appendTo(element.parent());  
	},
	submitHandler:function(form){
		 //图片是否上传的校验
		var pushType = $("#infoType").val();
        var infoImgVal = $("#infoImg").val();
        if(pushType && ('TPXW' == pushType) && !infoImgVal){
       	  $("#infoImgTips").show();
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

function dealInfoImgUpload(file, data, response) {
	var result = eval("(" + data + ")");
      if(result.status){
           $("#infoImgView").attr("src", result.url);
           $("#infoImg").val(result.url);
      }else{
           alert(result.message);
      }
}

$(document).ready(function(){
	uploadifyPro.auto = true;
	uploadifyPro.multi = false;
	uploadifyPro.fileExt = "*.gif;*.jpg;*.jpeg;*.png;*.bmp;*.ico";
	uploadifyPro.buttonClass = "btn btn-gy btn-upload";
	uploadifyPro.fileSizeLimit = "64k";
	uploadifyPro.buttonText = "上传图片";
	uploadifyPro.width='75px', //按钮宽度
	uploadifyPro.onUploadSuccess = dealInfoImgUpload;
	
	//上传二级栏目头标
	$("#infoImgFile").uploadify(uploadifyPro);
});
</script>