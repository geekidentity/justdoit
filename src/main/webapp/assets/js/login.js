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
		$(".")
	}
	var form = "{username:'" + username + "',password:'" + password + "'";
	/*$.post("con",form, function(data) {
		alert(data);
	});*/
});

function Loginvalidate() {
	$(".login-form").validate({

	});
}