<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-4 sysmanage-site-box-wrap">
		<div class="box sysmanage-site-box">
		
			<h2>新增栏目</h2>
			
			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目名称：</label>
						    <input type="text" class="form-control" placeholder="栏目名称">
						  </div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  
						  <div class="form-group">
						    <label>所属栏目：</label>
						    <select name="" id="" class="form-control">
						    	<option value="#">贵阳机场</option>
						    	<option value="#">紧急通知</option>
						    	<option value="#">机场向导</option>
						    	<option value="#">机场服务</option>
						    	<option value="#">机场交通</option>
						    </select>
						  </div>
						  
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目图标：</label>
								<img src="./public/img/login-logo.png" alt="">
								<button type="button" class="btn btn-gy btn-upload"><span class="glyphicon glyphicon-upload"></span>上传图标</button>
						  </div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>栏目类型：</label>
						    <select name="" id="" onchange="showColumn(this)" class="form-control">
						    	<option value="#" value="0">下辖二级菜单</option>
						    	<option value="#" value="1">URL外链</option>
						    	<option value="#" value="2">无二级菜单</option>
						    </select>
						  </div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="column-type">
				<div class="column-type-list" style="display: block;">
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>栏目头标：</label>
								    <img src="./public/img/login-logo.png" alt="">
										<button type="button" class="btn btn-gy btn-upload"><span class="glyphicon glyphicon-upload"></span>上传图标</button>
								  </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>列表模版：</label>
								    <select name="" id="" class="form-control" onchange="setListThumb(this,'show-list-thumb-wrap')">
								    	<option value="" data-thumb="./public/img/thumb-1.jpg">列表一</option>
								    	<option value="" data-thumb="./public/img/thumb-2.jpg">列表二</option>
											<option value="" data-thumb="./public/img/thumb-3.jpg">列表三</option>
											<option value="" data-thumb="./public/img/thumb-4.jpg">列表三</option>
								    </select>
								  </div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
									<div class="form-group">
										<label>内容模版：</label>
										<select name="" id="" class="form-control" onchange="setListThumb(this,'show-content-thumb-wrap')">
											<option value="" data-thumb="./public/img/thumb-1.jpg">内容一</option>
											<option value="" data-thumb="./public/img/thumb-2.jpg">内容二</option>
											<option value="" data-thumb="./public/img/thumb-3.jpg">内容三</option>
											<option value="" data-thumb="./public/img/thumb-4.jpg">内容三</option>
										</select>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="column-type-list">
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>URL：</label>
								    <input type="text" class="form-control" placeholder="URL">
								  </div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="column-type-list">
					<div class="row">
						<div class="col-md-12">
							<div class="from-gy-controls">
								<div class="form-inline">
								  <div class="form-group">
								    <label>内容模版：</label>
								    <select name="" id="" class="form-control" onchange="setListThumb(this, 'show-content-thumb-wrap')">
								    	<option value="" data-thumb="./public/img/thumb-2.jpg">内容模版一</option>
								    	<option value="" data-thumb="./public/img/thumb-3.jpg">内容模版二</option>
								    	<option value="" data-thumb="./public/img/thumb-4.jpg">内容模版三</option>
								    </select>
								  </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="from-gy-controls">
						<div class="form-inline">
						  <div class="form-group">
						    <label>显示序号：</label>
						    <input type="text" class="form-control" placeholder="栏目名称">
						  </div>
						</div>
					</div>
				</div>
			</div>

			<hr>
			
			<div class="operation-box">
				<button type="button" class="btn btn-gy btn-query"><span class="glyphicon glyphicon-ok-sign"></span>保存</button>
			</div>

		</div>
	</div>

	<div class="col-md-8 show-item-thumb-wrap">
		<div class="row">
			<div class="col-md-4">
				<h3>列表模板</h3>
				<div class="show-list-thumb-wrap"></div>
			</div>

			<div class="col-md-4 col-md-offset-1">
				<h3>内容模板</h3>
				<div class="show-content-thumb-wrap"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function getClass(e) {
		return document.getElementsByClassName(e);
	}

	function remove(node) {

		if(1 == node.nodeType) node.remove();

		if( node.length > 0){
			for (var i = 0, _nodeLength = node.length; i < _nodeLength; i++){
				if( 1 == node[i].nodeType) node[i].remove();
			}
		}
	}

	function getSelect(node) {
		for(var i = 0, nodeLength = node.childNodes.length; i < nodeLength; i++){
			if(1 == node.childNodes[i].nodeType && node.childNodes[i].selected) return node.childNodes[i];
		}
		return null;
	}

	var listThumbWrap = getClass("show-list-thumb-wrap")[0];
	var contentThumbWrap = getClass("show-content-thumb-wrap")[0];

	function showColumn(n) {
		remove(getClass("show-content-thumb-wrap")[0].childNodes);
		remove(getClass("show-list-thumb-wrap")[0].childNodes);
		function _getNode(n){
			for(var i = 0; i < n.childNodes.length; i++){
				if(n.childNodes[i].nodeType == 1 && n.childNodes[i].selected == true) return n.childNodes[i];
			}
		}

		var _e = _getNode(n);
		var _index = _e.index;

		var _columnTypeList = getClass("column-type-list");
		for(var i = 0; i < _columnTypeList.length; i++){
			_columnTypeList[i].style.display = "none";
		}
		_columnTypeList[_index].style.display = "block";

	}

	function setListThumb(d,node) {
		var _selectedNode = getSelect(d);
		remove(getClass(node)[0].childNodes);
		if(null != _selectedNode){
			var _thumbDom = document.createElement("img");
			_thumbDom.setAttribute("src", _selectedNode.getAttribute('data-thumb'));
			getClass(node)[0].appendChild(_thumbDom);
		}
	}
</script>