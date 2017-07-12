<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container" class="white-bg">
	<div class="col-md-12">
		<div class="box information-management-box">
		
		    <%@ include file="/WEB-INF/include/message.jsp" %>
		
			<h3>条件检索</h3>

            <form action="${base }/sys/push/ed" method="post">
			<div class="from-gy-controls">
				<div class="form-inline">


				  <div class="form-group">
				    <label>信息名称：</label>
				    <input type="text" name="infoName" value="${page.entity.infoName }" class="form-control" placeholder="信息名称">
				  </div>



				  <div class="form-group">
				    <label>信息类型：</label>
				    <select name="infoType" class="form-control">
					  	<option value="">全部(单选)</option>
					    <c:forEach items="${pushTypes }" var="pushType">
					    	<option value="${pushType }"
					    		<c:if test="${pushType eq page.entity.infoType }">
					    			selected="selected"
						  	 	</c:if>
					    	>${pushType.name }</option>
					    </c:forEach>
					</select>
				  </div>


				  <div class="form-group">
				    <label>推送范围：</label>
				    <select name="pushRange" class="form-control">
						  <option value="">全部</option>
						  <option value="wechat"
						  	   <c:if test="${page.entity.pushRange eq 'wechat'}">
							  		selected="selected"
							   </c:if>
						  >微信公众号</option>
					</select>
				  </div>


				  <div class="form-group">
				    <label>推送时间：</label>
				    <div class="date-time-box">
				    	<input type="text" name="beginPushTime" value="${page.entity.beginPushTime }" class="form-control flatpickr">
				    	<i class="date-time-icon"></i>
				    </div>
				          至
				   	<div class="date-time-box">
				   		<input type="text" name="endPushTime" value="${page.entity.endPushTime }" class="form-control flatpickr">
				   		<i class="date-time-icon"></i>
				   	</div>
				  </div>


				</div>
			</div>

			<div class="operation-box operation-um-box">
				<button type="submit" class="btn btn-gy btn-recovery">
					<span class="glyphicon glyphicon-search"></span>
					查询
				</button>
			</div>
			</form>

			<div class="box-table">
				<table class="table">

					<thead>
						<tr>
							<th>序号</th>
							<th>信息名称(已推送)</th>
							<th>信息类型</th>
							<th>推送范围</th>
							<th>推送时间</th>
							<th>管理</th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${page.data }" var="d" varStatus="stat">
						<tr>
							<td>${page.beginIndex + stat.count}</td>
							<td>${d.infoName }</td>
							<td>${d.infoType.name }</td>
							<td>
							    <c:choose>
							    	<c:when test="${d.pushRange eq 'wechat'}">
							    		微信公众号
							    	</c:when>
							    	<c:otherwise>
							    		 不详
							    	</c:otherwise>
							    </c:choose>
							</td>
							<td><fmt:formatDate value="${d.pushTime}" pattern="yyyy-MM-dd HH:mm" type="date" /></td>
							<td>
								<shiro:hasPermission name="push:ed:detail">
							    <a href="${base }/sys/push/ed/detail/${d.id }">&nbsp;<i class="glyphicon glyphicon-search"></i>&nbsp;查看</a>
							    </shiro:hasPermission>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>

				<%@ include file="/WEB-INF/include/page.jsp" %>

			</div>
		</div>

	</div>
</div>


<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript">
		if($(".flatpickr").length > 0){
			Flatpickr.l10ns.default.weekdays = {
				shorthand: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
				longhand: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六']
			}
			Flatpickr.l10ns.default.months = {
				shorthand: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
				longhand: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月']
			}

			flatpickr(".flatpickr");
		}
</script>