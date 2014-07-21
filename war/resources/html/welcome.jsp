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
						<li><a href="#intro"><b>01. Ask</b></a></li>
						<li><a href="#people"><b>02. Bid</b></a></li>
						<li><a href="#work"><b>03. Go</b></a></li>
						<li><a href="#street">Early Registration</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
		<div class="full-panel" id="home">
			<div id="copertina">
				There You Go <img class="logo img-responsive" style="float:right;padding:20px;" src="resources/images/parallax/logo.png"/>
				<h3>Comparing your travel offers.<br/><br/>booking your next holidays?<br/><br/> Sit back, relax and let our network of experts make you personalised offers</h3>
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
							<b>ThereYouGo</b> provides a simple interface that allows you to
							<br/> <br/>
							<b>Compare</b> the different offers in a personalised matrix including custom criteria based on your request
							<br/> <br/>
							<b>Adjust</b> the travel plan either by selecting the items you wish to change and messaging the agent
							<br/> <br/>
							<b>Book</b>... and go!
							<img class="img-responsive" src="resources/images/parallax/third.png"/>
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel street">
					<div class="in-panel" id="street">
						<h2>Early Registration</h2>
						<p>Want to be the first to try our new service? Subscribe today and we will notify you as soon as we'll be online, Thanks!</p>
						<div class="earlyreg">
							<form class="ng-pristine ng-valid" role="form">
							
								<div class="alert alert-success" role="alert" id="dvSuccess" style="display:none">
									<ul id="ulSuccess"></ul>
								</div>
							
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
		<!-- /.container -->
		<!-- Bootstrap core JavaScript
			================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="resources/scripts/parallax/bootstrap.min.js"></script>
		<script src="resources/scripts/parallax/initialize.js"></script>
	</body>
</html>