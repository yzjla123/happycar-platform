jQuery(document).ready(function() {     
		$.backstretch([
	        base+"/static/images/login/bg/1.jpg",
	        base+"/static/images/login/bg/2.jpg",
	        base+"/static/images/login/bg/3.jpg",
	        base+"/static/images/login/bg/4.jpg"
	        ], {
	          fade: 1000,
	          duration: 8000
	    });
	});
	$(document).ready(function() {
		
		$("#loginButton").click(function() {
			if (check()) {
				login();
			}
		});
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		    	if (check()) {
					login();
				}
		     }
		} 
	});

	function check() {
		var username = $("#username").val();
		var password = $("#password").val();
		if (username == "" || username == "请输入用户名") {
			$("#username").focus();
			return false;
		}
		if (password == "" || password == "请输入密码") {
			$("#password").focus();
			return false;
		}
		return true;
	}

