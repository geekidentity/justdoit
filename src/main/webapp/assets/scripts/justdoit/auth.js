var base = "http://211.87.147.138:8081/web"
window.access_token = "access_token="+ getToken()
console.log(access_token)
$("#logout").click(function(m){
    $.ajax({
        url: base + "/logout?" + access_token,
        dataType: "json",
        type:"get",
        success: function(data) {
            if(data.code == 1) {
                location.href = "../login.html"
            } else {
                alert(data.mgs)
            }
        }
    })
})

function getToken() {
    return location.search.substr(1);
}