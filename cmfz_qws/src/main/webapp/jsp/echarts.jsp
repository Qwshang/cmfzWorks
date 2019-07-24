<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.js"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            $.post("${pageContext.request.contextPath}/user/month",function (montchs) {

                var mon =new Array();
                var cou =new Array();
                for (var i = 0; i < montchs.length; i++) {
                    var x = montchs[i];
                    var montch=x.month;
                    var count=x.count;
                    mon.push(montch);
                    cou.push(count);
                }
                // 指定图表的配置项和数据
                var option = {
                    title: {
                        text: 'ECharts 入门示例'
                    },
                    tooltip: {},
                    legend: {
                        data:['注册统计']
                    },
                    xAxis: {
                        data: mon
                    },
                    yAxis: {},
                    series: [{
                        name: '注册统计',
                        type: 'bar',
                        data: cou
                    }]
                };

                var goEasy = new GoEasy({
                    appkey: 'BC-798823e7d33c4c098f87da28c613fd78'
                });
                goEasy.subscribe({
                    channel:'qwshang2',
                    onMessage: function(mont){
                        var montchs = JSON.parse(mont.content);
                        var mon =new Array();
                        var cou =new Array();
                        for (var i = 0; i < montchs.length; i++) {
                            var x = montchs[i];
                            var montch=x.month;
                            var count=x.count;
                            mon.push(montch);
                            cou.push(count);
                        }
                        // 指定图表的配置项和数据
                        var option = {
                            title: {
                                text: 'ECharts 入门示例'
                            },
                            tooltip: {},
                            legend: {
                                data:['注册统计']
                            },
                            xAxis: {
                                data: mon
                            },
                            yAxis: {},
                            series: [{
                                name: '注册统计',
                                type: 'bar',
                                data: cou
                            }]
                        };

                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(option);
                    }
                });

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },"json")

        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>

