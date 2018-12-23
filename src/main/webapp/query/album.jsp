<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>


<script type="text/javascript">
    $(function () {
        $("#albumDialogFormId").val(ops.id);
        $("#albumDialogFormImg").prop("src", "${pageContext.request.contextPath}" + ops.coverImg)
        $("#albumDialogFormImg").validatebox({editable: false});
        $("#albumDialogFormTitle").val(ops.title);
        $("#albumDialogFormTitle").validatebox({editable: false});
        $("#albumDialogFormAuthor").val(ops.author);
        $("#albumDialogFormAuthor").validatebox({editable: false});
        $("#albumDialogFormBc").val(ops.broadcast);
        $("#albumDialogFormBc").validatebox({editable: false});
        $("#albumDialogFormScore").val(ops.score);
        $("#albumDialogFormScore").validatebox({editable: false});
        $("#albumDialogFormBrief").val(ops.brief);
        $("#albumDialogFormBrief").validatebox({editable: false});
        $("#albumDialogFormPub").val(ops.pubDate);
        $("#albumDialogFormPub").validatebox({editable: false});
    })

</script>

<h2 style="color: red;text-align:center">专辑信息</h2>
<form id="queryAlbumDialogForm" method="post">
    <input name="id" id="albumDialogFormId" type="hidden"/>
    <img name="coverImg" style="float: left;" id="albumDialogFormImg" height="150px" width="120px"/>
    名称：<input name="title" id="albumDialogFormTitle"/><br>
    作者：<input name="author" id="albumDialogFormAuthor"/><br>
    播音：<input name="broadcast" id="albumDialogFormBc"/><br>
    评分：<input name="score" id="albumDialogFormScore"/><br>
    详情：<input name="brief" id="albumDialogFormBrief"/><br>
    发布日期：<input name="pubDate" id="albumDialogFormPub"/><br>
</form>