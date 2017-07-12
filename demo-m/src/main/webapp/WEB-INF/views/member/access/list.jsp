<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/taglib.jsp"%>

<div id="app-main-container">
					<div class="col-md-12">
						<div class="box">
							<div class="chart-box">
								<button type="button" class="btn btn-gy btn-exports"><span class="glyphicon glyphicon-share-alt"></span>数据导出</button>
								<div id="site-statistics-charts" style="height: 320px; width: 100%;"></div>
							</div>

							<div class="row">
								<div class="col-md-6">
									<div class="chart-box">
										<div class="chart-calendar-box">日期选择：<input type="text" class="flatpickr" id="authentication-statistics"><i class="calendar-icon"></i></div> 
										<div id="everyday-authentication-statistics-charts" style="height: 320px; width: 100%;"></div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="chart-box">
										<div class="chart-calendar-box">日期选择：<input type="text" class="flatpickr" id="device-statistics"><i class="calendar-icon"></i></div> 
										<div id="everyday-device-statistics-charts" style="height: 320px; width: 100%;"></div>
									</div>
								</div>

							</div>

							<div class="chart-box">
								<div id="hour-statistics-charts" style="height: 320px; width: 100%;"></div>
							</div>
						</div>
					</div>
				</div>
				
<%@ include file="/WEB-INF/include/echarts.jsp" %>
<%@ include file="/WEB-INF/include/flatpickr.jsp" %>
<script type="text/javascript">
		
		$(function(){
			flatpickr(".flatpickr",{
				onChange:function(selectedDates, dateStr, instance){
					switch(instance.element.id){
						case "device-statistics":
							getDevice(dateStr);
							break;
						case "authentication-statistics":
							getAuth(dateStr);
							break;
					}
		
				}
			});
			
			function getDevice(date){
				$.ajax({
			         type: "GET",
			         url: "${base}/sys/member/access/devices",
			         data: {date:date},
			         dataType: "json",
			         success: function(data){
			        	var list = data.aggs.entirys.buckets;
			        	var everyDayData = formatData(list);
						createPieChart("设备类型",everydayStatisticsChart,everyDayData);
			         }
			     });
			}
			
			function getAuth(date){
				$.ajax({
			         type: "GET",
			         url: "${base}/sys/member/access/auths",
			         data: {date:date},
			         dataType: "json",
			         success: function(data){
			        	var list = data.aggs.entirys.buckets;
			        	var everydayAuthenticationData = formatData(list);
						createPieChart("认证方式",everydayAuthenticationChart,everydayAuthenticationData);
			         }
			     });
			}
			
			getDevice('');
			getAuth('');
		});
		
		function createPieChart(title, chart, data){
			var option = {
			    title : {
			        text: title,
			        x:'left',
			        textStyle: {
			        	color: "#676464",
			        	fontWeight: "normal",
			        	fontSize: "14",
			        	fontFamily: '"Helvetica Neue", Helvetica, Arial, sans-serif'
			        }
			    },
		
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        bottom:'0',
			        right: '0',
			        data: data.title
			    },
			    series : [{
			            name: '访问来源',
			            type: 'pie',
			            radius : '65%',
			            center: ['34%', '55%'],
			            label:{
			            	normal:{
			            		position:"outside",
			            		formatter:"{d}%"
			            	}
			            },
			            labelLine:{
       		            	normal:{
       		            		length2:5,
       		            		length:10
        	 	            }
        	 	        },
			            data: data.data
			        }
			    ]
			};
			chart.setOption(option);
		}
		
		var everydayAuthenticationChart = echarts.init(document.getElementById('everyday-authentication-statistics-charts'));
		var everydayStatisticsChart = echarts.init(document.getElementById('everyday-device-statistics-charts'));

		function formatData(data){
			var _formatData = {
				title:[],
				data:[]
			};
			
			var _itemStyle = [{
		            	normal:{
		            		color:"#f36a5b",
		            		borderWidth: 3,
				    		borderColor: "#FFF"
		            	},
		            	emphasis:{borderWidth:0}
						},{
							normal:{
		            		color:"#35b8eb",
		            		borderWidth: 2
		            	},
		            	emphasis:{
		            		borderWidth:0
		            	}
		          	},{
		          		normal:{
		            		color:"#ff9900",
		            		borderWidth: 2
		            	},
		            	emphasis:{
		            		borderWidth:0
		            	}
		          	},{
		          		normal:{
		            		color: "#7eba1e",
		            		borderWidth: 2
		            	},
		            	emphasis:{
		            		borderWidth:0
		            	}
		          	}];
			
			for(var _i = 0, _len = data.length; _i < _len; _i++){
				_formatData.title.push(data[_i].key);
				_formatData.data.push({
						value: data[_i].count,
						name: data[_i].key,
						itemStyle:_itemStyle[_i] != undefined ? _itemStyle[_i] : parseInt(Math.random(_itemStyle.length)*(_itemStyle.length))
				});

			}
			return _formatData;
		}

		//站点统计
		var option = {
			    title: {
			        text: '站点统计',
			        textStyle: {
			        	color: "#676464",
			        	fontWeight: "normal",
			       		fontSize: "14",
			        	fontFamily: '"Helvetica Neue", Helvetica, Arial, sans-serif'
			        }
			    },
			  	tooltip: {
			        trigger: 'axis'
			    },
			    toolbox:{
			    	show:false
			    },
			    legend: {
			        data:['场站总体访问人数的变化情况'],
			        orient: 'horizontal',
			        align: 'right',
			        right: '200px'
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: [
					<c:set var="startIndex" value="${fn:length(days.aggs.entirys.buckets)-1 }"/>
					<c:forEach items="${days.aggs.entirys.buckets }" var="b" varStatus="stat">
					'${days.aggs.entirys.buckets[startIndex - stat.index].keyForDate }'<c:if test="${!stat.last }">,</c:if>
					</c:forEach>
	               ]
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [
			        {
			            name:'场站总体访问人数的变化情况',
			            type:'line',
			            stack: '总量',
			            smooth: true,
			            showSymbol: false,
			            itemStyle:{
			            	normal: {
			            		color:"#0288c7"
			            		borderColor: "#0288c7"
			            	}
			            },
			            lineStyle: {
			            	normal: {
			            		color: '#0288c7'
			            	}
			            },
			            areaStyle:{
			            	normal:{
			            		color:'rgb(209, 234, 245)',
			            		opacity:'.5'
			            	}
			            },
			            data:[
						<c:forEach items="${days.aggs.entirys.buckets }" var="b" varStatus="stat">
							${days.aggs.entirys.buckets[startIndex - stat.index].count }<c:if test="${!stat.last }">,</c:if>
						</c:forEach>
			                  ]
			        }
			    ]
		};

		var siteStatisticsChart = echarts.init(document.getElementById('site-statistics-charts'));
		siteStatisticsChart.setOption(option);

		// 24小时热点分布
		var option = {
			    title: {
			        text: '24小时热点分布',
			        textStyle: {
			        	color: "#676464",
			        	fontWeight: "normal",
			        	fontSize: "14",
			        	fontFamily: '"Helvetica Neue", Helvetica, Arial, sans-serif'
			        }
			    },
			    tooltip: {
			        trigger: 'axis'
			    },

			    toolbox:{
			    	show:false
			    },

			    legend: {
			        data:['24小时访问人数的变化情况(柱状)'],
			        orient: 'horizontal',
			        align: 'left',
			        right: '20',
			        tooltip: {
			        	borderColor:"#FF00FF"
			        }
			    },

			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },

			    xAxis: {
			        data: [
					<c:forEach items="${hours.aggs.entirys.buckets }" var="b" varStatus="stat">
						'${b.keyString }'<c:if test="${!stat.last }">,</c:if>
					</c:forEach>
	               ]
			    },
			    yAxis: {
			        type: 'value'
			    },
			    series: [{
			            name:'24小时访问人数的变化情况(柱状)',
			            type:'bar',
			            stack: '总量',
			            itemStyle:{
			            	normal:{
				            	color: "#3399cc"
				            },
				            emphasis:{},
			            },
			            data:[
						<c:forEach items="${hours.aggs.entirys.buckets }" var="b" varStatus="stat">
							${b.count }<c:if test="${!stat.last }">,</c:if>
						</c:forEach> 
	                  	]
			        }
			    ]
		};

		var hourStatisticsChart = echarts.init(document.getElementById('hour-statistics-charts'));
		hourStatisticsChart.setOption(option);
</script>
