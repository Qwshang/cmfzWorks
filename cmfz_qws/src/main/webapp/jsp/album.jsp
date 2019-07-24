<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<head>


    <script type="text/javascript">
        <%--页面加载完，执行以下代码--%>
        $(function () {
            //初始化jqGrid
            $("#albumTable").jqGrid({
                styleUI:"Bootstrap",
                url: "${pageContext.request.contextPath}/album/queryAll",//发送请求的地址
                datatype: "json",//预期的结果类型
                autowidth: true,//页面显示自适应宽度
                height:"100%",
                colNames: ["编号","名称","封面","数量","得分","作者","播音员","简介","上传时间"],//表格的表头，定义每个列的名字，类型数组
                colModel: [//表格的内容，类型数组
                    {name: "id"},
                    {name: "title", editable: true},
                    {name: "cover", editable: true, edittype: "file",formatter:function (cellvalue, options, rowObject) {
                        return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/albumPic/"+cellvalue+"'/>"
                        }},
                    {name: "count", editable: true},
                    {name:"score",editable:true},
                    {name:"author",editable:true},
                    {name:"broadcast",editable:true},
                    {name:"brief",editable:true},
                    {name:"publishTime",editable:true,edittype:"date"}],
                //分页相关
                pager:"albumPager",//获取分页组件的ID，自动添加样式
                viewrecords: true,
                rowNum: 5,// 每页显示的行数
                rowList: [2, 5, 10, 15], // 行数的数组
                page: 1,  // 默认显示的页码
                multiselect: true,//复选框
                rownumbers: true,//序号
                //子表格开始
                subGrid : true,
                caption : "Grid as Subgrid",
                subGridRowExpanded : function(subgrid_id, row_id) {
                    // we pass two parameters
                    // subgrid_id is a id of the div tag created whitin a table data
                    // the id of this elemenet is a combination of the "sg_" + id of the row
                    // the row_id is the id of the row
                    // If we wan to pass additinal parameters to the url we can use
                    // a method getRowData(row_id) - which returns associative array in type name-value
                    // here we can easy construct the flowing
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id
                        + "' class='scroll'></table><div id='"
                        + pager_id + "' class='scroll'></div>");
                    jQuery("#" + subgrid_table_id).jqGrid(
                        {
                            url : "${pageContext.request.contextPath}/chapter/queryAll?albumId=" + row_id,
                            datatype : "json",
                            styleUI:"Bootstrap",
                            autowidth:true,
                            colNames : [ '编号', '专辑编号', '名称', '大小','下载路径','上传时间','操作' ],
                            colModel : [
                                {name : "id"},
                                {name : "albumId"},
                                {name : "title", editable: true},
                                {name : "size"},
                                {name : "downPath", editable: true, edittype: "file"},
                                {name : "uploadTime", editable: true,edittype:"date"},
                                {name : "downPath",formatter:function(cellvalue, options, rowObject){
                                        return "<a onclick=\"\" class=\"btn btn-primary\"href=\"${pageContext.request.contextPath}/chapter/download?downPath="+cellvalue+"\" role=\"button\">下载</a>"
                                    }},
                            ],
                            rowNum : 20,
                            pager : pager_id,
                            sortname : 'num',
                            sortorder : "asc",
                            height : '100%',
                            editurl: "${pageContext.request.contextPath}/chapter/edit"
                        });
                    jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                        "#" + pager_id, {
                            edit : false,
                            add : true,
                            del : false
                        },{
                        //修改
                        },{
                            //添加的部分
                            closeAfterAdd:true,
                            afterSubmit:function (response) {
                                $.ajaxFileUpload({
                                    url:"${pageContext.request.contextPath}/chapter/upload?albumId=" + row_id,
                                    fileElementId:"downPath",
                                    data:{"id":response.responseText},
                                    type:"post",
                                    success:function () {
                                        $("#tableid").trigger("reloadGrid");
                                    }
                                })
                                return "[true]";
                            }
                        });
                },
                //子表格结束
                //增删改相关
                editurl: "${pageContext.request.contextPath}/album/edit",

            }).jqGrid("navGrid", "#albumPager", {},
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
                        url:"${pageContext.request.contextPath}/album/upload",
                        fileElementId:"cover",
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
    <h1>专辑管理</h1>
</div>

<table id="albumTable">
    <div id="albumPager"></div>
</table>

