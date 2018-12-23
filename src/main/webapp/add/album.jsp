<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $(function () {
        $.extend($.fn.validatebox.defaults.rules, {
            twoNum: {
                validator: function (value) {
                    return value >= 0 && value <= 10;
                },
                message: "评分不能超过10"
            }
        });

        $("#addAlbumDialogFormImg").filebox({
            required: true,
            buttonText: "请选择",
            accept: "image/*"
        });
        $("#addAlbumDialogFormTitle").validatebox({
            required: true
        });
        $("#addAlbumDialogFormAuthor").validatebox({
            required: true
        });
        $("#addAlbumDialogFormBc").validatebox({
            required: true
        });
        $("#addAlbumDialogFormScore").validatebox({
            required: true,
            validType: "twoNum"
        });
        $("#addAlbumDialogFormBrief").validatebox({
            required: true
        });

        $("#addAlbumDialogFormSub").linkbutton({
            onClick: function () {
                $("#addAlbumDialogForm").form("submit", {
                    url: "${pageContext.request.contextPath}/album/addAlbum",
                    onSubmit: function () {
                        return $("#addAlbumDialogForm").form("validate");
                    },
                    success: function () {
                        $("#addAlbumDialog").dialog("close");
                        $("#album").treegrid("load");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功"
                        });
                    }
                });
            }
        });
        $("#addAlbumDialogFormReset").linkbutton({
            onClick: function () {
                $("#addAlbumDialogForm").form("reset");
            }
        });
    })

</script>

<h2 style="color: red;text-align:center">添加专辑</h2>
<form id="addAlbumDialogForm" method="post" enctype="multipart/form-data">
    名称：<input name="title" id="addAlbumDialogFormTitle"/><br>
    作者：<input name="author" id="addAlbumDialogFormAuthor"/><br>
    播音：<input name="broadcast" id="addAlbumDialogFormBc"/><br>
    封面：<input name="multipartFile" id="addAlbumDialogFormImg"/><br>
    评分：<input name="score" id="addAlbumDialogFormScore"/><br>
    详情：<input name="brief" id="addAlbumDialogFormBrief"/><br>
    <div style="text-align: center"><a id="addAlbumDialogFormSub">添加</a><a id="addAlbumDialogFormReset">重置</a></div>
</form>