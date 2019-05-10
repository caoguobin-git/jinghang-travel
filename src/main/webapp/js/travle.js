
function userRegister() {
    var username=$("#user_register .username").val()
    var password=$("#user_register .password").val()
    console.log(username+"  "+password)
    var url="/user/register.do";
    var param={
        "username":username,
        "password":password
    }
    $.ajax({
        url:url,
        data:param,
        dataType:"json",
        type:"post",
        success:function (result) {
            console.log(result)
        }
    })
}
function userLogin() {
    var username=$("#user_login .username").val()
    var password=$("#user_login .password").val()
    console.log(username+"  "+password)
    var url="/user/login.do";
    var param={
        "username":username,
        "password":password
    }
    $.ajax({
        url:url,
        data:param,
        dataType:"json",
        type:"post",
        success:function (result) {
            console.log(result)
        }
    })
}


