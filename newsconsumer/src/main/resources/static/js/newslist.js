$(function () {
    var $title = $("#title");
    var pageNow = 1;
    var pageTotle;

    $("#submit0").on("click",function () {
        getList($title.val(), 1);
    });

    $(".paging").on("click",function () {
        var mess = $(this).attr("mess");
        page(mess);
    });

    function page(mess) {
        if (mess == 'firstPage') {
            pageNow = 1;
        }else if (mess == 'previousPage') {
            pageNow = pageNow - 1 > 0 ? pageNow - 1 : 1;
        }else if (mess == 'nextPage') {
            pageNow = pageNow < pageTotle ? pageNow + 1 : pageTotle;
        }else if (mess == 'lastPage') {
            pageNow = pageTotle;
        }
        getList($title.val(), pageNow);
    }

    function getList(title, pageNow) {
        $.ajax({
            type: "get",
            url: "/detail/list",
            data:{pageNow: pageNow,title:title},
            success: function (data) {
                pageNow = data.pageNow;
                pageTotle = data.pageTotle;
                var list = data.list;
                $("#list").html("");
                // $(list).each(function () {
                $.each(list,function (i,item) {
                    var $tr = $("<tr></tr>");
                    $tr.append("<td>" + item.id + "</td>");
                    $tr.append("<td>" + item.title + "</td>");
                    $tr.append("<td>" + item.summary + "</td>");
                    $tr.append("<td>" + item.author + "</td>");
                    $tr.append("<td>" + item.createdate + "</td>");
                    var $td = $("<td></td>");
                    $td.append("<a href=''>查看评论</a>");
                    $td.append("<a href=''>评论</a>");
                    $td.append("<a href='javascript:;' class='del'>删除</a>");
                    $tr.append($td);
                    $("#list").append($tr);
                });
                $("#list").next().find("td").show();
                if (list.length == 0) {
                    $("#list").append("<tr><th colspan='6'>查无此新闻</th></tr>");
                    $("#list").next().find("td").hide();
                }
                var $pageShow = $("#list").next().find("span");
                $pageShow.html("");
                $pageShow.append("第"+pageNow+"页/共"+pageTotle+"页");
            },
            error: function () {
                alert("信息加载出错");
            }
        });
    }

    getList('', 1);

});