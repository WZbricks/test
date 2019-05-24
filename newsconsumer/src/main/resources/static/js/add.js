$(function () {
    $("#sub").on("click",function () {
        $("#form").submit();
    })
    $("#back").on("click",function () {
        window.history.back();
    })
})