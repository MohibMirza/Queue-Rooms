<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome: Login/Register</title>
<link rel="stylesheet" href="styles.css">
</head>
<body onload="Login()">
	<script>
		// This is called with the results from from FB.getLoginStatus().
		function statusChangeCallback(response) {
			console.log('statusChangeCallback');
			console.log(response);
			console.log(response.authResponse.accessToken);
			//alert(response.authResponse.accessToken);
			if (response.status === 'connected') {
				window.location.href = 'Sign_in_Controller.jsp?access_token='
						+ response.authResponse.accessToken;
			} else {
				// The person is not logged into your app or we are unable to tell.
				document.getElementById('status').innerHTML = 'Please log '
						+ 'into this app.';
			}
		}
		// This function is called when someone finishes with the Login
		// Button. See the onlogin handler attached to it in the sample
		// code below.
		function checkLoginState() {
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		}
		window.fbAsyncInit = function() {
			FB.init({
				appId : '257073648672064',
				cookie : true, // enable cookies to allow the server to access 
				// the session
				xfbml : true, // parse social plugins on this page
				version : 'v3.0' // use graph api version 2.8
			});
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		};
		// Load the SDK asynchronously
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "https://connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
		// Here we run a very simple test of the Graph API after login is
		// successful. See statusChangeCallback() for when this call is made.
	</script>
	<div class="backgroundz">
		<div class="box">
			<div class="top-panel">
				<div id="btn"></div>
				<button id="top_login" type="button" class="lgn-regis"
					onclick="Login()">Login</button>
				<button id="top_Signup" type="button" class="lgn-regis"
					onclick="Register()">Signup</button>
			</div>

			<fb:login-button scope="public_profile,email"
				onlogin="checkLoginState();" text-align: center>
			</fb:login-button>
			<div id="status"></div>
			<form id="Login" class="input-data" method="post" action="./Login">
				<input type="email" class="input-field" placeholder="Email"
					name="email_login" required> <input type="password"
					class="input-field" placeholder="Password" name="password_login"
					required>
				<button type="submit" class="submit-btn">Login</button>
			</form>

			<form id="Register" class="input-data" method="post"
				action="./java_bend">
				<input type="email" class="input-field" placeholder="Email-ID"
					name="email_regis" required> <input type="text"
					class="input-field" placeholder="UserID" name="userid" required>
				<input type="password" class="input-field" placeholder="Password"
					name="password_regis" required>
				<button type="submit" class="submit-btn">Signup</button>
			</form>
		</div>
	</div>

	<script>
		var x = document.getElementById("Login");
		var y = document.getElementById("Register");
		var z = document.getElementById("btn");
		var a = document.getElementById("top_login");
		var b = document.getElementById("top_Signup");

		function Register() {
			x.style.left = "-400px";
			y.style.left = "50px";
			z.style.left = "100px";
			a.style.font = 'normal 10px Arial';
			b.style.font = 'bold 30px Arial';

		}

		function Login() {
			x.style.left = "50px";
			y.style.left = "450px";
			z.style.left = "0px";
			x.style.font = "bold";
			y.style.font = "normal";
			b.style.font = 'normal 10px Arial';
			a.style.font = 'bold 30px Arial';

		}
	</script>

</body>
</html>

