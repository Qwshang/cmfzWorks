
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 引入 ECharts 文件 -->
    <script src="../echarts/echarts.js"></script>
    <script src="https://www.echartsjs.com/gallery/vendors/echarts/map/js/china.js?_v_=1553896255267"></script>
    <script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script type="text/javascript">
        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
            $.post("${pageContext.request.contextPath}/user/map",function (map) {

                // 指定图表的配置项和数据
                var map = {
                    title : {
                        text: '注册人数分布图',
                        subtext: '',
                        left: 'center'
                    },
                    tooltip : {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data:['男性','女性']
                    },
                    visualMap: {
                        min: 0,
                        max: 2500,
                        left: 'left',
                        top: 'bottom',
                        text:['高','低'],           // 文本，默认为数值文本
                        calculable : true
                    },
                    toolbox: {
                        show: true,
                        orient : 'vertical',
                        left: 'right',
                        top: 'center',
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    series : [
                        {
                            name: '男性',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:map.man
                        },
                        {
                            name: '女性',
                            type: 'map',
                            mapType: 'china',
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data:map.woman
                }
            ]
            };

                var goEasy = new GoEasy({
                    appkey: 'BC-798823e7d33c4c098f87da28c613fd78'
                });
                goEasy.subscribe({
                    channel:'qwshang',
                    onMessage: function(message){
                        var maps = JSON.parse(message.content);
                        // 指定图表的配置项和数据
                        var map = {
                            title : {
                                text: '注册人数分布图',
                                subtext: '',
                                left: 'center'
                            },
                            tooltip : {
                                trigger: 'item'
                            },
                            legend: {
                                orient: 'vertical',
                                left: 'left',
                                data:['男性','女性']
                            },
                            visualMap: {
                                min: 0,
                                max: 2500,
                                left: 'left',
                                top: 'bottom',
                                text:['高','低'],           // 文本，默认为数值文本
                                calculable : true
                            },
                            toolbox: {
                                show: true,
                                orient : 'vertical',
                                left: 'right',
                                top: 'center',
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                            series : [
                                {
                                    name: '男性',
                                    type: 'map',
                                    mapType: 'china',
                                    roam: false,
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:maps.man
                                },
                                {
                                    name: '女性',
                                    type: 'map',
                                    mapType: 'china',
                                    label: {
                                        normal: {
                                            show: false
                                        },
                                        emphasis: {
                                            show: true
                                        }
                                    },
                                    data:maps.woman
                                }
                            ]
                        };
                        // 使用刚指定的配置项和数据显示图表。
                        myChart.setOption(map);
                    }
                });


                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(map);
            },"json")

        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>
