<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<title>There You Go! Stop Searching for Travel: Start Asking!</title>

<!-- favicon icon -->
<link rel="icon" type="image/png" href="resources/images/favicon.png" />

<!-- Bootstrap core CSS -->
<link href="resources/css/parallax/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/css/parallax/style.css" rel="stylesheet">
<!-- Google web Font -->
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,100'
	rel='stylesheet' type='text/css'>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
		<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
		<![endif]-->
</head>
<body data-spy="scroll" data-target="#my-navbar">
	<div class="container">
		<div class="navbar navbar-inverse navbar-fixed-top" id="my-navbar">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">THERE YOU GO</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#home">What is It</a></li>
					<li><a href="#register">Early Registration For Providers</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="full-panel" id="home">
		<div id="copertina">
			There You Go <img class="logo img-responsive"
				style="float: right; padding: 20px;"
				src="resources/images/parallax/logo.png" />
			<h3>A new, really easy way to book tailor made holidays</h3>
			<div class="container" style="width: 100%; text-align: center;">
				<a href="/"><button id="link-from-provider"
						class="btn btn-lg btn-primary btn-block ng-binding" type="button"
						onclick="document.location='/'">See travelers' website</button></a>
			</div>
		</div>
	</div>
	<div class="col-md-12 full-panel street">
		<div class="in-panel" id="register">
			<h2 class="register">Early Registration For Providers</h2>
			<p id="ulMessage">Want to be the first to try our new service?
				Subscribe today and we will notify you as soon as we are online,
				Thanks!</p>
			<div class="earlyreg">
				<form class="ng-pristine ng-valid" role="form">

					<div class="alert alert-success" role="alert" id="dvSuccess"
						style="display: none;">
						<strong>Thank You!</strong><br />We are delighted to receive your
						feedback.
					</div>

					<div class="alert alert-danger" role="alert" id="dvError"
						style="display: none;">
						<ul id="ulError"></ul>
					</div>

					<div class="form-group">
						<label class="ng-binding" for="EMAIL_1">Email Address</label> <input
							class="form-control ng-pristine ng-valid ng-valid-email"
							id="EMAIL_1" placeholder="Email Address" type="email">
					</div>
					<div class="form-group">
						<label class="ng-binding" for="NAME_1">Name</label> <input
							class="form-control ng-pristine ng-valid" id="NAME_1"
							placeholder="Your Name" type="text">
					</div>
					<div id="feedback" class="form-group">
						<label class="ng-binding" for="FEEDBACK_1">Feedback</label>
						<textarea size="3" class="form-control ng-pristine ng-valid"
							id="FEEDBACK_1" placeholder="What do you think of us?"></textarea>
					</div>
					<button class="btn btn-lg btn-primary btn-block ng-binding"
						type="button" onclick="registerUser()">Register</button>
				</form>
			</div>
		</div>
	</div>
	</div>
	</div>

	<div class="msk loading hidden">&nbsp;</div>
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="http://code.jquery.com/jquery-1.9.1.min.js"
		type="text/javascript"></script>
	<script src="resources/scripts/parallax/bootstrap.min.js"></script>
	<script src="resources/scripts/parallax/initialize.js"></script>
</body>
</html>