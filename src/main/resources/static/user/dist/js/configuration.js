$(function () {
    //修改个人信息
    $('#updateUserInfoButton').click(function () {
        $("#updateUserInfoButton").attr("disabled",true);
        var nickName = $('#nickName').val();
        if (validUserNameForUpdate(nickName)) {
            //ajax提交数据
            var params = $("#userInfoForm").serialize();
            $.ajax({
                type: "POST",
                url: "/user/userInfo",
                data: params,
                success: function (r) {
                    if (r == 'success') {
                        $('#updateUserName-info').css("display", "none");
                        swal("修改成功", {
                            icon: "success",
                        });
                    } else {
                        swal("修改失败", {
                            icon: "error",
                        });
                        $("#updateUserInfoButton").prop("disabled",false);
                    }
                }
            });
        } else {
            $("#updateUserInfoButton").prop("disabled",false);
        }
    });
    //修改密码
    $('#updatePasswordButton').click(function () {
        $("#updatePasswordButton").attr("disabled",true);
        var originalPassword = $('#originalPassword').val();
        var newPassword = $('#newPassword').val();
        if (validPasswordForUpdate(originalPassword, newPassword)) {
            var params = $("#userPasswordForm").serialize();
            $.ajax({
                type: "POST",
                url: "/user/updatePassword",
                data: params,
                success: function (r) {
                    console.log(r);
                    if (r == 'success') {
                        swal("修改成功", {
                            icon: "success",
                        });
                        window.location.href = '/user/login';
                    } else {
                        swal("修改失败", {
                            icon: "error",
                        });
                        $("#updatePasswordButton").attr("disabled",false);
                    }
                }
            });
        } else {
            $("#updatePasswordButton").attr("disabled",false);
        }
    });

})

/**
 * 名称验证
 */
function validUserNameForUpdate(nickName) {
    if (isNull(nickName) || nickName.trim().length < 1) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("昵称不能为空！");
        return false;
    }
    if (!validCN_ENString2_18(nickName)) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("请输入符合规范的昵称！");
        return false;
    }
    return true;
}

/**
 * 密码验证
 */
function validPasswordForUpdate(originalPassword, newPassword) {
    if (isNull(originalPassword) || originalPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("请输入原密码！");
        return false;
    }
    if (isNull(newPassword) || newPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("新密码不能为空！");
        return false;
    }
    return true;
}