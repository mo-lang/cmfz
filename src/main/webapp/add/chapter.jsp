<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $(function () {

        $("#addChapterDialogFormAlbId").val(ops.id);

        $("#addChapterDialogFormAudio").filebox({
            required: true,
            buttonText: "请选择",
            accept: "audio/*"
        });

        $("#addChapterDialogFormTitle").validatebox({
            required: true,
        });

        $("#addChapterDialogFormSub").linkbutton({
            onClick: function () {
                $("#addChapterDialogForm").form("submit", {
                    url: "${pageContext.request.contextPath}/chapter/addChapter",
                    onSubmit: function () {
                        return $("#addChapterDialogForm").form("validate");
                    },
                    success: function () {
                        $("#addChapterDialog").dialog("close");
                        $("#album").treegrid("load");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功"
                        });
                    }
                });
            }
        });
        $("#addChapterDialogFormReset").linkbutton({
            onClick: function () {
                $("#addChapterDialogForm").form("reset");
            }
        });
    })

</script>

<h2 style="color: red;text-align:center">添加章节</h2>
<form id="addChapterDialogForm" method="post" enctype="multipart/form-data">
    <input name="albId" id="addChapterDialogFormAlbId" type="hidden"/>
    章节名称：<input name="title" id="addChapterDialogFormTitle"/><br>
    上传章节：<input name="multipartFile" id="addChapterDialogFormAudio"/><br>
    <div style="text-align: center"><a id="addChapterDialogFormSub">添加</a><a id="addChapterDialogFormReset">重置</a></div>
</form>