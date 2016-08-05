<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<%=basePath%>resources/echarts/build/dist/echarts.js"></script>

</head>
<div id="main" style="width: 600px; height: 400px;"></div>
<script type="text/javascript">
var dom = document.getElementById("main");

var app = {};
option = null;
// 路径配置
require.config({
    paths: {
        echarts: '<%=basePath%>resources/echarts/build/dist'
    }
});
require(
        [
            'echarts',
            'echarts/chart/force', 
        ],
        function (ec) {
        	setChats(ec);
        }
    );

function setChats (ec) {
	var myChart = ec.init(dom);
			$.getJSON('<%=basePath%>resources/npmdepgraph.min10.json', function(
				json) {
			myChart.setOption(option = {
				title : {
					text : 'NPM Dependencies'
				},
				animationDurationUpdate : 1500,
				animationEasingUpdate : 'quinticInOut',
				series : [ {
					type : 'force',
					layout : 'none',
					// progressiveThreshold: 700,
					data : json.nodes.map(function(node) {
						return {
							x : node.x,
							y : node.y,
							id : node.id,
							name : node.label,
							symbolSize : node.size,
							itemStyle : {
								normal : {
									color : node.color
								}
							}
						};
					}),
					edges : json.edges.map(function(edge) {
						return {
							source : edge.sourceID,
							target : edge.targetID
						};
					}),
					label : {
						emphasis : {
							position : 'right',
							show : true
						}
					},
					roam : true,
					focusNodeAdjacency : true,
					lineStyle : {
						normal : {
							width : 0.5,
							curveness : 0.3,
							opacity : 0.7
						}
					}
				} ]
			}, true);
		});
		;
	}
</script>
</body>
</html>