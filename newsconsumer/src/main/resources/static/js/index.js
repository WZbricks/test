$(function () {
var currentPage = 1;

$("#search").on("click",list);

function list() {
    $("#newslist").html("<tr>\n" +
        "            <td>序号</td>\n" +
        "            <td>新闻标题</td>\n" +
        "            <td>新闻摘要</td>\n" +
        "            <td>新闻作者</td>\n" +
        "            <td>创建时间</td>\n" +
        "            <td>操作</td>\n" +
        "        </tr>");
    $.ajax({
        url:"/list",
        method: "post",
        data: {
            currPage: currentPage
        },
        success: function (data) {
            var $tab = $("#newslist");
                $.each(data.newslist, function (j, val) {
                    $tab.append("<tr>" +
                        "<td>"+data.newslist[j].id+"</td>" +
                        "<td>"+data.newslist[j].title+"</td>" +
                        "<td>"+data.newslist[j].summary+"</td>" +
                        "<td>"+data.newslist[j].author+"</td>" +
                        "<td>"+data.newslist[j].createdate+"</td>" +
                        "<td><a href='/tomodify?id="+data.newslist[j].id+"'>修改</a>|<a href='/delete?id="+data.newslist[j].id+"'>删除</a></td>" +
                        "<td></td>" +
                        "</tr>")
                });
            currentPage = data.currentPage;
            $("#page").append("<a href='/list?currPage=1'>首页</a>" +
                "<a href='/list?currPage="+(data.page.currentPage-1)+"'>上一页</a>" +
                "<a href='/list?currPage="+(data.page.currentPage+1)+"'>下一页</a>" +
                "<a href='/list?currPage="+data.page.totalPage+"'>尾页</a>")
        }
    });

}
})