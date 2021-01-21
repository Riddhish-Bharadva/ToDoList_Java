<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Todo List</title>
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	</head>
	<style>
		.pageBody{
		 	background:#757C88;
		}
		.signinBox{
			margin-left:5%;
		 	margin-top:10%;
		 	max-width:25%;
		 	background:#ffffff;
		 	border:2px solid black;
		 	border-radius: 10px;
		 }
	    .ModalLinksStyle
	    {
	    	font-size:14px;
			color:black;
			font-style:bold;
			background:rgba(0,0,0,0);
			border-radius:10px;
			border:0px;
	    }
	    .ModalLinksStyle:hover
	    {
			font-style:oblique;
	    }
	</style>
	<body class="pageBody">
		<div class="signinBox">
			<div class="container">
				<form action="/" method="post">
					<div class="row">
						<div class="col-sm-12">
							<h5 style="padding:20px;">Sign-in</h5>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
							<input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-1"></div>
						<div class="col-sm-10">
							<input type="password" id="password" name="password" class="form-control" placeholder="Password" required autofocus>
						</div>
						<div class="col-sm-1"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<button type="submit" class="btn btn-primary btn-block" name="signIn" value="signIn">Sign-In</button>
						</div>
						<div class="col-sm-4">
							<button type="reset" class="btn btn-primary btn-block" name="reset" value="reset">Reset</button>
						</div>
						<div class="col-sm-2"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-sm-12">
							<% if(request.getAttribute("message")!=null && request.getAttribute("message").equals("User Not Found")) { %>
								<div style="background:red;color:white;padding:5px;border-radius:10px;">
									Username entered is invalid.
								</div>
							<% } else if(request.getAttribute("message")!=null && request.getAttribute("message").equals("Password Missmatch")) { %>
								<div style="background:red;color:white;padding:5px;border-radius:10px;">
									Entered password is incorrect.
								</div>
							<% } %>
						</div>
					</div>
				</form>
				<br>
<!--				<div class="row">
					<div class="col-sm-4">
						<button class="btn ModalLinksStyle" data-toggle="modal" data-target=""><b>Sign Up</b></button>
					</div>
					<div class="col-sm-2"></div>
					<div class="col-sm-6">
						<button class="btn ModalLinksStyle" data-toggle="modal" data-target=""><b>Forgot Password?</b></button>
					</div>
				</div>
				<br>  -->
			</div>
		</div>
	</body>
</html>