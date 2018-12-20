<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    $(function () {
        $.extend($.fn.validatebox.defaults.rules, {
            sta: {
                validator: function (value) {
                    return value == "Y" || value == "N";
                },
                message: "必须为Y/N"
            }
        });

        $("#bannerDg").edatagrid({
            fit: true,
            fitColumns: true,
            url: "${pageContext.request.contextPath}/banner/queryAll",
            pagination: true,
            pageList: [2, 4, 6, 8, 10],
            pageSize: 6,
            toolbar: "#bannerDgBtn",
            updateUrl: "${pageContext.request.contextPath}/banner/updateStatus",
            columns: [[
                {field: "title", title: "标题", width: 1},
                {
                    field: "status",
                    title: "展示状态",
                    width: 1,
                    editor: {type: "validatebox", options: {required: true, validType: "sta"}}
                },
                {field: "pubDate", title: "发布日期", width: 1},
                {field: "description", title: "描述", width: 1}
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}' + rowData.imgPath + '" style="height:100px;"></td>' +
                    '<td style="border:0">' +
                    '<p>&nbsp;&nbsp;标题: ' + rowData.title + '</p>' +
                    '<p>&nbsp;&nbsp;展示状态: ' + rowData.status + '</p>' +
                    '<p>&nbsp;&nbsp;发布日期: ' + rowData.pubDate + '</p>' +
                    '<p>&nbsp;&nbsp;描述: ' + rowData.description + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });


        $("#bannerDgAdd").linkbutton({
            iconCls: "icon-add",
            onClick: function () {
                $("#bannerDgDialog").dialog({
                    href: "${pageContext.request.contextPath}/add/banner.jsp"
                });
                $("#bannerDgDialog").dialog("open");
            }
        });
        $("#bannerDgDialog").dialog({
            title: "添加轮播图",
            height: 240,
            width: 300,
            closed: true,
            modal: true
        });

        $("#bannerDgUp").linkbutton({
            iconCls: "icon-edit",
            onClick: function () {
                var idx = $("#bannerDg").edatagrid("getRowIndex", $("#bannerDg").edatagrid("getSelected"));
                if (idx != -1) {
                    $("#bannerDg").edatagrid("editRow", idx);
                }
            }
        });
        $("#bannerDgDel").linkbutton({
            iconCls: "icon-remove",
            onClick: function () {
                if (confirm("确定要删除吗？")) {
                    $.post("${pageContext.request.contextPath}/banner/deleteBanner", "id=" + $("#bannerDg").edatagrid("getSelected").id, function () {
                        $("#bannerDg").edatagrid("reload");
                        $.messager.show({
                            title: "系统提示",
                            msg: "删除成功"
                        })
                    });
                }
            }
        });
        $("#bannerDgSave").linkbutton({
            iconCls: "icon-save",
            onClick: function () {
                $("#bannerDg").edatagrid("saveRow");
            }
        });
    })

</script>
<table id="bannerDg"></table>
<div id="bannerDgBtn">
    <a id="bannerDgAdd">添加</a>
    <a id="bannerDgUp">修改</a>
    <a id="bannerDgDel">删除</a>
    <a id="bannerDgSave">保存</a>
</div>
<div id="bannerDgDialog"></div>