$(function () {
    //提交申请
    $('#applyForBlog').click(function () {
        $("#applyForBlog").attr("disabled",true);
        var userid = $('#userId').val();
        window.location.href = "/user/bloger/"+userid;
    });

})