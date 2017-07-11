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
										<div class="chart-calendar-box">日期选择：<input type="text" class="flatpickr"> <i class="calendar-icon"></i></div>
										<div id="everyday-authentication-statistics-charts" style="height: 320px; width: 100%;"></div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="chart-box">
										<div class="chart-calendar-box">日期选择：<input type="text" class="flatpickr"> <i class="calendar-icon"></i></div>
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
					<c:forEach items="${days.aggs.entirys.buckets }" var="b" varStatus="stat">
						'${b.keyForDate }'<c:if test="${!stat.last }">,</c:if>
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
							${b.count }<c:if test="${!stat.last }">,</c:if>
						</c:forEach>
			                  ]
			        }
			    ]
		};

		var siteStatisticsChart = echarts.init(document.getElementById('site-statistics-charts'));
		siteStatisticsChart.setOption(option);


		//每日认证
		var option = {
		    title : {
		        text: '认证方式',
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
		        top: '180',
		        right: '20',
		        data: ['微信认证','短信认证']
		    },

		    series : [{
		            name: '访问来源',
		            type: 'pie',
		            radius : '65%',
		            center: ['30%', '55%'],
		            label:{
		            	normal:{
		            		position:"inside",
		            		formatter:"{d}%"
		            	}
		            },

		            data:[
		                {value:335, name:'微信认证', itemStyle:{
		                	normal:{
		                		color:"#f36a5b",
		                		borderWidth: 3,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }},
		                {value:310, name:'短信认证', itemStyle:{
		                	normal:{
		                		color:"#35b8eb",
		                		borderWidth: 3,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

		var everydayAuthenticationChart = echarts.init(document.getElementById('everyday-authentication-statistics-charts'));
		everydayAuthenticationChart.setOption(option);

		//每日分析
		var option = {
		    title : {
		        text: '设备类型',
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
		        top: '180',
		        right: '20',
		        data: ['苹果Mobile','安卓Mobile','苹果MAC','安卓平板', "其他"]
		    },

		    series : [{
		            name: '访问来源',
		            type: 'pie',
		            radius : '65%',
		            center: ['30%', '55%'],

		            label:{
		            	normal:{
		            		position:"outside",
		            		formatter:"{d}%"
		            	}
		            },
					labelLine:{
			    	normal:{
			    		length:10,
			    		length2:0
			    		}
			    	},

		            data:[
		                {value:335, name:'苹果Mobile', itemStyle:{
		                	normal:{
		                		color:"#f36a5b",
		                		borderWidth: 3,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }},
		                {value:310, name:'安卓Mobile', itemStyle:{
		                	normal:{
		                		color:"#35b8eb",
		                		borderWidth: 2,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }},
		                {value:234, name:'苹果MAC', itemStyle:{
		                	normal:{
		                		color:"#ff9900",
		                		borderWidth: 2,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }},
		                {value:135, name:'安卓平板', itemStyle: {
		                	normal:{
		                		color: "#7eba1e",
		                		borderWidth: 2,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }},

		                {value:75, name:'其他', itemStyle: {
		                	normal:{
		                		color: "#2aa7d8",
		                		borderWidth: 2,
						    	borderColor: "#FFF"
		                	},
		                	emphasis:{
		                		borderWidth:0
		                	}
		                }}
		            ],
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
		var everydayStatisticsChart = echarts.init(document.getElementById('everyday-device-statistics-charts'));
		everydayStatisticsChart.setOption(option);

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
