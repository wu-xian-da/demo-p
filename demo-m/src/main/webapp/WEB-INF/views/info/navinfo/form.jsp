<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-edit">
		
			<h2>${empty navInfo.id ? "新增" : "编辑" }信息</h2>

 			<form id="navInfo_form" method="post">
			<!-- hidden -->
			<input type="hidden" name="id" value="${navInfo.id }" />
			<div class="row">
				<div class="col-md-12">

					<hr>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息名称：</label>
						</div>

						<div class="col-md-8">
							<input type="text" name="title" value="${navInfo.title}" class="form-control" placeholder="信息名称">
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>所属栏目：</label>
						</div>

						<div class="col-md-8">
							<select class="form-control" name="navId">
								<option value="">---请选择(单选)---</option>
								<c:forEach items="${navList}" var="nav">
									<option value="${nav.id}"
										<c:if test="${nav.id eq navInfo.navId }">selected="selected"</c:if>
									></option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row row-list">
						<div class="col-md-1">
							<label>信息类型：</label>
						</div>

						<div class="col-md-8">
							<c:forEach items="${infoType}" var="infoType">
								<label class="radio-inline">
									<input type="radio" name="type" value="${infoType }"
										<c:if test="${infoType eq navInfo.type }">checked="checked"</c:if>
									>${infoType.name }
								</label>
							</c:forEach>
						</div>
					</div>
	
					<div class="row row-list">
				  		<div class="col-md-1">
				  			<label>信息内容：</label>
				  		</div>
				  		<div class="col-md-8">
				  			<div id="editor">${navInfo.content }</div>
				  		</div>
			  	    </div>

				  	<div class="row row-list">
				  		<div class="col-md-1"></div>
				  		<div class="col-md-8">
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
			</form>
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