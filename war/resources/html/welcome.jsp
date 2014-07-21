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
		<!-- Bootstrap core CSS -->
		<link href="resources/css/parallax/bootstrap.css" rel="stylesheet">
		<!-- Custom styles for this template -->
		<link href="resources/css/parallax/style.css" rel="stylesheet">
		<!-- Google web Font -->
		<link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,100' rel='stylesheet' type='text/css'>
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
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#">THERE YOU GO</a>
				</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#home">What is It</a></li>
						<li><a href="#intro"><b>1. You ask</b></a></li>
						<li><a href="#people"><b>2. They bid</b></a></li>
						<li><a href="#work"><b>3. You go</b></a></li>
						<li><a href="#street">Early Registration</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
		<div class="full-panel" id="home">
			<div id="copertina">
				There You Go <img class="logo img-responsive" style="float:right;padding:20px;" src="resources/images/parallax/logo.png"/>
				<h3>A new, really easy way to book tailor made holidays</h3>
				<div class="container motto">
					<div class="row"  style="text-align:center">
						<div class="cols-xs3 col-sm-3" style="text-align:center;">
							<h1>You ask</h1>
							<a href="#intro"><img class="pictures img-responsive" src="resources/images/new/1.png"/></a>
							<h2>Tell us about your travel plan</h2>
						</div>
						<div class="cols-xs3 col-sm-3">
							<h1>They bid</h1>
							<a href="#people"><img  class="pictures img-responsive" src="resources/images/new/2.png"/></a>
							<h2>Let our network of travel experts build a tailor made offer</h2>
						</div>
						<div class="cols-xs3 col-sm-3" style="text-align:center;">
							<h1>You go</h1>
							<a href="#work"><img class="pictures img-responsive"  src="resources/images/new/3.png"/></a>
							<h2>Compare, Adjust and book your vacation</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="my-container">
			<div class="row">
				<div class="col-md-12 full-panel intro">
					<div class="in-panel" id="intro">
						<h2>01. You Ask</h2>
						<p>
							<strong>Tell us about your travel plan</strong> 
							<br/>
							<br/>
							Create a request that looks like you! With simple guided steps, tell our travel experts about your plans:
							<br/> Criteria are flexible, tick, slide and tag the trip you have in mind. Still not quite representing your plan? Use the free text form to provide additional details 
							<img class="img-responsive" src="resources/images/parallax/first.png"/>
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel people">
					<div class="in-panel" id="people">
						<h2>02. They Bid</h2>
						<p>
							<strong>Let our network of travel experts build a tailor made offer</strong>
							<br/><br/><br/><br/>
							So who are the experts? <br/><br/> We are building a network of travel agencies and independent providers. These are cherry picked, independent travel agencies with experience in the destinations or activities you are searching.
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel work">
					<div class="in-panel" id="work">
						<h2>03. You Go</h2>
						<p><strong>Compare, Adjust and book your vacation</strong>  
							<br/> <br/> 
							We will notify you when a new offer comes in, for you to review and compare.
							<br/> <br/>
							<b>Compare</b> the different offers in a personalised matrix including custom criteria based on your request.
							<br/> <br/>
							<b>Adjust</b> the travel plan by selecting the items you wish to change and messaging the agent for a direct interaction.
							<br/> <br/>
							<b>Book</b>... and go!
							<img class="img-responsive" src="resources/images/parallax/third.png"/>
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel street">
					<div class="in-panel" id="street">
						<h2>Early Registration</h2>
						<p id="ulSuccess">Want to be the first to try our new service? Subscribe today and we will notify you as soon as we'll be online, Thanks!</p>
						<div class="earlyreg">
							<form class="ng-pristine ng-valid" role="form">

								<div class="alert alert-danger" role="alert" id="dvError" style="display:none;">
									<ul id="ulError"></ul>
								</div>
								
								<div class="form-group">
									<label class="ng-binding" for="EMAIL_1">Email Address</label>
									<input class="form-control ng-pristine ng-valid ng-valid-email" id="EMAIL_1" placeholder="Email Address" type="email">
								</div>
								<div class="form-group">
									<label class="ng-binding" for="NAME_1">Name</label>
									<input class="form-control ng-pristine ng-valid" id="NAME_1" placeholder="Your Name" type="text">
								</div>
								<div id="feedback" class="form-group">
									<label class="ng-binding" for="FEEDBACK_1">Feedback</label>
									<textarea size="3" class="form-control ng-pristine ng-valid" id="FEEDBACK_1" placeholder="What do you think of us?"></textarea>
								</div>
								<button class="btn btn-lg btn-primary btn-block ng-binding" type="button" onclick="registerUser()">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="msk loading hidden">&nbsp;</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="resources/scripts/parallax/bootstrap.min.js"></script>
		<script src="resources/scripts/parallax/initialize.js"></script>
	</body>
</html>