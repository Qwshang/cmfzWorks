<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<head>


    <script type="text/javascript">
        <%--页面加载完，执行以下代码--%>
        $(function () {
            //初始化jqGrid
            $("#tableid").jqGrid({
                styleUI:"Bootstrap",
                url: "${pageContext.request.contextPath}/carousel/queryByPage",//发送请求的地址
                datatype: "json",//预期的结果类型
                autowidth: true,//页面显示自适应宽度
                height:"100%",
                colNames: ["编号", "轮播图名称", "轮播图片", "状态", "创建时间", "操作"],//表格的表头，定义每个列的名字，类型数组
                colModel: [//表格的内容，类型数组
                    {name: "id"},
                    {name: "title", editable: true},
                    {name: "imgPath", editable: true, edittype: "file",formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/carouselPic/"+cellvalue+"'/>"
                        }},
                    {name: "status", editable: true},
                    {name: "createTime", editable: true,edittype: "date"},
                    {
                        name: "", formatter: function (cellvalue, options, rowObject) {
                            return "<button class='btn btn-primary'>修改</button> <button class='btn btn-primary'>删除</button>"
                        }
                    }
                ],
                //分页相关
                pager: "#pager",//获取分页组件的ID，自动添加样式
                viewrecords: true,
                rowNum: 5,// 每页显示的行数
                rowList: [2, 5, 10, 15], // 行数的数组
                page: 1,  // 默认显示的页码
                multiselect: true,//复选框
                rownumbers: true,//序号
                //增删改相关
                editurl: "${pageContext.request.contextPath}/carousel/edit"

            }).jqGrid("navGrid", "#pager", {},
                {
                 //修改的部分
                closeAfterEdit:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/carousel/upload",
                        fileElementId:"imgPath",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#tableid").trigger("reloadGrid");
                        }
                    })
                    return "[true]";
                }
            },{
                //添加的部分
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/carousel/upload",
                        fileElementId:"imgPath",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#tableid").trigger("reloadGrid");
                        }
                    })
                    return "[true]";
                }
            });
        });


    </script>

</head>
<div class="page-header">
    <h1>轮播图管理</h1>
</div>

<table id="tableid">
    <div id="pager"></div>
</table>
