<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<head>


    <script type="text/javascript">
        <%--页面加载完，执行以下代码--%>
        $(function () {
            //初始化jqGrid
            $("#userTable").jqGrid({
                styleUI:"Bootstrap",
                url: "${pageContext.request.contextPath}/user/queryAll",//发送请求的地址
                datatype: "json",//预期的结果类型
                autowidth: true,//页面显示自适应宽度
                height:"100%",
                colNames: ["编号", "手机号", "密码", "盐", "法名", "省","市","性别","个性签名","头像","状态","注册时间","操作"],//表格的表头，定义每个列的名字，类型数组
                colModel: [//表格的内容，类型数组
                    {name: "id"},
                    {name: "phone", editable: true},
                    {name: "password", editable: true},
                    {name: "salt"},
                    {name: "dharmaName", editable: true},
                    {name: "province", editable: true},
                    {name: "city", editable: true},
                    {name: "gender", editable: true},
                    {name: "personalSign", editable: true},
                    {name: "profile", editable: true, edittype: "file",formatter:function (cellvalue, options, rowObject) {
                            return "<img style='width:50px;height:50px' src='${pageContext.request.contextPath}/profilePic/"+cellvalue+"'/>"
                        }},
                    {name: "status"},
                    {name: "registTime", editable: true, edittype: "date"},
                    {
                        name: "", formatter: function (cellvalue, options, rowObject) {
                            return "<button class='btn btn-primary' onclick='editRow(this)' uid='"+rowObject.id+"'>修改用户状态</button>"
                        }
                    }
                ],
                //分页相关
                pager: "#userPager",//获取分页组件的ID，自动添加样式
                viewrecords: true,
                rowNum: 5,// 每页显示的行数
                rowList: [2, 5, 10, 15], // 行数的数组
                page: 1,  // 默认显示的页码
                //增删改相关
                editurl: "${pageContext.request.contextPath}/user/edit"

            }).jqGrid("navGrid", "#userPager", {edit : false, add : true, del : false},
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
        });

        //修改用户状态
        function editRow(user) {
           $.post("${pageContext.request.contextPath}/user/updateStatus",{id:user.getAttribute("uid")},function () {
               $("#userTable").trigger("reloadGrid");
           },"json");
        }

        $("#upload").click(function () {
            var formData = new FormData($('#uploadForm')[0]);
            $.ajax({
                type: 'post',
                url: "http://192.168.1.101:8080/springbootdemo/file/upload", //上传文件的请求路径必须是绝对路劲
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
            }).error(function () {
                alert("上传失败");
            });
        });
        function daoru() {
            var formData = new FormData($('#uploadForm')[0]);
            $.ajax({
                type: 'post',
                url: "http://192.168.1.101:8080/user/impot", //上传文件的请求路径必须是绝对路劲
                data: formData,
                cache: false,
                processData: false,
                contentType: false,
            }).success(function (data) {
                alert(data);
            }).error(function () {
                alert("上传失败");
            });
        }


    </script>

</head>
<div class="page-header">
    <h1>用户管理</h1>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/user/out" role="button">一键导出</a>


    <form method="post"  enctype="multipart/form-data" id="daorufrom">
        导入用户文件：<input id="file" type="file" name="file"/>
    </form>
    <button id="upload" onclick="daoru()">导入用户文件</button>
</div>

<table id="userTable">
    <div id="userPager"></div>
</table>
