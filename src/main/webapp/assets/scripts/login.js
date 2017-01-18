/**
 * Created by geek1994 on 2015/11/18.
 */

$.backstretch([
    "assets/img/bg/1.jpg",
    "assets/img/bg/2.jpg",
    "assets/img/bg/3.jpg",
    "assets/img/bg/4.jpg"
], {
    fade: 1000,
    duration: 8000
});

/**
 * 登陆
 */
$("#login-btn").click(function(){
	var username = $("#username").val();
	var password = $("#password").val();
	if(username===""||password===""){
	}
	$.ajax({
		url:"http://211.87.147.138:8081/web/login",
		type:"post",
		data:$("form").serialize(),
		dataType:"json",
		success:function(data) {
			console.log(data.code)
			if(data.code == 1) {
				location.href="justdoit/index.html?"+ data.access_token
			}else {
				alert(data.msg);
			}
		}
	})
});

function Loginvalidate() {
	$(".login-form").validate({

	});
}