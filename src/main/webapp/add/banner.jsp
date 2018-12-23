<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {

        $("#addBannerFormTitle").validatebox({
            required: true
        });
        $("#addBannerFormStatus").validatebox({
            required: true
        });
        $("#addBannerFormDes").validatebox({
            required: true
        });
        $("#addBannerFormPic").filebox({
            buttonText: '选择文件',
            accept: "image/*"
        });
        $("#addBannerFormSub").linkbutton({
            onClick: function () {
                $("#addBannerForm").form("submit", {
                    url: "${pageContext.request.contextPath}/banner/addBanner",
                    onSubmit: function () {
                        return $("#addBannerForm").form("validate");
                    },
                    success: function () {
                        $("#bannerDgDialog").dialog("close");
                        $("#bannerDg").edatagrid("reload");
                        $.messager.show({
                            title: "系统提示",
                            msg: "添加成功"
                        });
                    }
                });
            }
        });
        $("#addBannerFormReset").linkbutton({
            onClick: function () {
                $("#addBannerForm").form("reset")
            }
        });
    })
</script>
<h2 style="text-align:center">添加轮播图</h2>
<form id="addBannerForm" method="post" enctype="multipart/form-data">
    图片标题：<input id="addBannerFormTitle" name="title"/><br>
    展示状态：<input id="addBannerFormStatus" name="status"/><br>
    详细描述：<input id="addBannerFormDes" name="description"/><br>
    上传图片：<input id="addBannerFormPic" name="multipartFile"/><br>
    <div style="text-align: center"><a id="addBannerFormSub">提交</a><a id="addBannerFormReset">重置</a></div>
</form>