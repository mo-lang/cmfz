<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var ops;
    $(function () {
        var tools = [{
            iconCls: "icon-search",
            text: "专辑详情",
            handler: function () {
                ops = $("#album").treegrid("getSelected");
                if (ops && ops.children) {
                    $("#queryAlbumDialog").dialog({
                        href: "${pageContext.request.contextPath}/query/album.jsp"
                    });
                    $("#queryAlbumDialog").dialog("open");
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加专辑",
            handler: function () {
                $("#addAlbumDialog").dialog({
                    href: "${pageContext.request.contextPath}/add/album.jsp"
                });
                $("#addAlbumDialog").dialog("open");
            }
        }, '-', {
            iconCls: 'icon-remove',
            text: "删除专辑",
            handler: function () {
                ops = $("#album").treegrid("getSelected");
                if (ops && ops.children) {
                    if (confirm("确定要删除吗？")) {
                        $.post("${pageContext.request.contextPath}/album/deleteAlbum", {"id": ops.id}, function () {
                            $("#album").treegrid("load");
                            $.messager.show({
                                title: "系统提示",
                                msg: "删除成功"
                            })
                        });
                    }
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: "添加章节",
            handler: function () {
                ops = $("#album").treegrid("getSelected");
                if (ops && ops.children) {
                    $("#addChapterDialog").dialog({
                        href: "${pageContext.request.contextPath}/add/chapter.jsp"
                    });
                    $("#addChapterDialog").dialog("open");
                }
            }
        }, '-', {
            iconCls: 'icon-remove',
            text: "删除章节",
            handler: function () {
                ops = $("#album").treegrid("getSelected");
                if (ops && !ops.children) {
                    if (confirm("确定要删除吗？")) {
                        $.post("${pageContext.request.contextPath}/chapter/deleteChapter", {
                            "id": ops.id,
                            "url": ops.url
                        }, function () {
                            $("#album").treegrid("load");
                            $.messager.show({
                                title: "系统提示",
                                msg: "删除成功"
                            })
                        });
                    }
                }
            }
        }, '-', {
            iconCls: 'icon-undo',
            text: "下载音频",
            handler: function () {
                ops = $("#album").treegrid("getSelected");
                if (ops && !ops.children) {
                    var url = "${pageContext.request.contextPath}/chapter/download?url=" + ops.url;
                    var xhr = new XMLHttpRequest();
                    xhr.open('GET', url, true);    // 也可以使用POST方式，根据接口
                    xhr.responseType = "blob";  // 返回类型blob
                    // 定义请求完成的处理函数，请求前也可以增加加载框/禁用下载按钮逻辑
                    xhr.onload = function () {
                        // 请求完成
                        if (this.status === 200) {
                            // 返回200
                            var blob = this.response;
                            var reader = new FileReader();
                            reader.readAsDataURL(blob);  // 转换为base64，可以直接放入a表情href
                            reader.onload = function (e) {
                                // 转换完成，创建一个a标签用于下载
                                var a = document.createElement('a');
                                a.download = ops.title + "." + ops.type;
                                a.href = e.target.result;
                                $("body").append(a);  // 修复firefox中无法触发click
                                a.click();
                                $(a).remove();
                            }
                        }
                    };
                    // 发送ajax请求
                    xhr.send()
                }
            }
        }];

        $("#album").treegrid({
            url: "${pageContext.request.contextPath}/album/queryAll",
            fitColumns: true,
            fit: true,
            idField: "id",
            treeField: "title",
            toolbar: tools,
            columns: [[
                {field: "title", title: "章节名", width: 1},
                {field: "size", title: "文件大小", width: 1},
                {field: "duration", title: "文件时长", width: 1},
                {field: "type", title: "文件类型", width: 1},
                {field: "uploadDate", title: "上传时间", width: 1},
                {
                    field: "url", title: "播放", width: 2, formatter: function (value, row, index) {
                        if (value) {
                            return "<audio controls src='${pageContext.request.contextPath}" + value + "'>";
                        }
                    }
                }
            ]]

        });

        $("#queryAlbumDialog").dialog({
            title: "专辑详情",
            height: 270,
            width: 380,
            closed: true,
            modal: true
        });

        $("#addAlbumDialog").dialog({
            title: "添加专辑",
            height: 270,
            width: 300,
            closed: true,
            modal: true
        });
        $("#addChapterDialog").dialog({
            title: "添加章节",
            height: 160,
            width: 280,
            closed: true,
            modal: true
        });
    })

</script>


<table id="album"></table>
<div id="queryAlbumDialog"/>
<div id="addAlbumDialog"/>
<div id="addChapterDialog"/>
