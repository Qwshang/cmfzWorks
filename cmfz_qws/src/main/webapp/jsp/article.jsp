<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script charset="utf-8" src="../kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="../kindeditor/lang/zh-CN.js"></script>
<script>
    $(function(){
        //初始化jqGrid
        $("#articleTable").jqGrid({
            styleUI:"Bootstrap",
            url: "${pageContext.request.contextPath}/article/queryAll",//发送请求的地址
            datatype: "json",//预期的结果类型
            autowidth: true,//页面显示自适应宽度
            height:"100%",
            colNames: ["编号", "标题", "内容", "发布时间","操作"],//表格的表头，定义每个列的名字，类型数组
            colModel: [//表格的内容，类型数组
                {name: "id"},

                {name: "title", editable: true},
                {name: "content"},
                {name: "publishTime", editable: true},
                {
                    name: "", formatter: function (cellvalue, options, rowObject) {
                        return "<button class='btn btn-primary' onclick='editRow(this)' uid='"+rowObject.id+"'>文章详情</button>"
                    }
                }
            ],
            //分页相关
            pager: "#articlePager",//获取分页组件的ID，自动添加样式
            viewrecords: true,
            rowNum: 5,// 每页显示的行数
            rowList: [2, 5, 10, 15], // 行数的数组
            page: 1,  // 默认显示的页码
            //增删改相关
            editurl: "${pageContext.request.contextPath}/user/edit"

        }).jqGrid("navGrid", "#articlePager", {edit : false, add : false, del : true},
            {
                //修改的部分

            },{
                //添加的部分
                closeAfterAdd:true,
                afterSubmit:function (response) {
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/user/upload",
                        fileElementId:"profile",
                        data:{"id":response.responseText},
                        type:"post",
                        success:function () {
                            $("#tableid").trigger("reloadGrid");
                        }
                    })
                    return "[true]";
                }
            });




            KindEditor.create('#editor_id',{
                width : '700px',
                uploadJson:'${pageContext.request.contextPath}/article/upload',
                fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
                allowFileManager:true,
                filePostName:'file',
                afterBlur:function(){
                    this.sync();
                }
            });

    })
    function addArticle(){
        $.ajax({
            url:"${pageContext.request.contextPath}/article/addArticle",
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),
            success:function(){
                $("#myModal").modal("hide");
                $("#articleTable").trigger("reloadGrid");
            }
        })
    }
</script>

<div class="page-header">
    <h1>文章管理</h1>
</div>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    文章上传
</button>

<!-- 文章上传模态框 -->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width:800px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <form action="javascript:void(0);" id="articleForm">
            <div class="modal-body">
                <input type="text" name="title">
                <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                &lt;strong&gt;HTML内容&lt;/strong&gt;
                </textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addArticle()">保存</button>
            </div>
            </form>
        </div>
    </div>
</div>


<table id="articleTable">
    <div id="articlePager"></div>
</table>