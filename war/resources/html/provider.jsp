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
		<link rel="icon" type="image/png" href="resources/images/parallax/logo.png" />
		
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
						<li><a href="#ask"><b>1. They ask</b></a></li>
						<li><a href="#bid"><b>2. You bid</b></a></li>
						<li><a href="#go"><b>3. They go</b></a></li>
						<li><a href="#register">Early Registration</a></li>
						<li class="fb-share-button" data-href="http://there-u-go.appspot.com/provider">&nbsp;</li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
		<div class="full-panel" id="home">
			<div id="copertina">
				There You Go <img class="logo img-responsive" style="float:right;padding:20px;" src="resources/images/parallax/logo.png"/>
				<h3>A new, really easy way to book tailor made holidays</h3>
				<div class="in-panel" id="first-panel">
				<p>
					Every traveler is unique and so are you. <br><br>
					
					At ThereYouGo, we believe there is no one-size-fits-all solution, and we want to help providers grow their businesses and demonstrate their value online.
					
					
				</p>
				</div>
			</div>
		</div>
		<div class="my-container">
			<div class="row">
				<div class="col-md-12 full-panel intro">
					<div class="in-panel" id="ask">
						<h2>01. They Ask</h2>
						<p>
							<strong>Ever dreamed of receiving qualified leads for free ?</strong><br> 
							Reduce your acquisition costs with ThereYouGo! <br>
							We are building a website to match travelers with providers just like you!
							Travelers will be able to build detailed travel requests thanks to our intuitive and open interface.
							<img class="img-responsive" src="resources/images/parallax/first.png"/>
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel people">
					<div class="in-panel" id="bid">
						<h2>02. You Bid</h2>
						<p> <strong>Focus on what you do best.</strong><br><br><br><br><br><br>
							We are able to process and qualify travel requests so that you only receive requests that correspond to your area of expertise. 
							<br>
							We take care of bringing in travelers and ease the handling process so that you can focus on what you do best: building tailor made offers that fit their needs.
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel work">
					<div class="in-panel" id="go">
						<h2>03. They Go</h2>
						<p><strong>Close the deal quickly, or move on.</strong><br>
							We provide a tag-based interface that lets you tell exactly which of the traveler's expectations you can meet. The user can then quickly review the offers in a comparison matrix including his own personal criteria.
							<br>
							You can chat with the traveler if additional information or adjustments are required, until he is 100% happy with your offer and he books his vacation.
							<br>
							Providers can subscribe to view offers and respond for free. We will charge a 1% commission on each sale.
							<img class="img-responsive" src="resources/images/parallax/third.png"/>
						</p>
					</div>
				</div>
				<div class="col-md-12 full-panel street">
					<div class="in-panel" id="register">
						<h2 class="register less-margin provider">Early Registration</h2>
						<p id="ulMessage">Want to be the first to try our new service? Subscribe today and we will notify you as soon as we'll be online, Thanks!</p>
						<div class="earlyreg">
							<form class="ng-pristine ng-valid" role="form">
								
								<div class="alert alert-success" role="alert" id="dvSuccess" style="display:none;">
									Thank You!<br/>We are delighted to receive your feedback.
								</div>
								
								<div class="alert alert-danger" role="alert" id="dvError" style="display:none;">
									<ul id="ulError"></ul>
								</div>
								
								<div class="form-group">
									<label class="ng-binding" for="COMPANY_1">Company Name*</label>
									<input class="form-control ng-pristine ng-valid" id="COMPANY_1" placeholder="Your company name" type="text">
								</div>
								<div class="form-group">
									<label class="ng-binding" for="EMAIL_1">Email Address*</label>
									<input class="form-control ng-pristine ng-valid ng-valid-email" id="EMAIL_1" placeholder="Email Address" type="email">
								</div>
								<div class="form-group">
									<label class="ng-binding" for="CITY_1">City</label>
									<input class="form-control ng-pristine ng-valid ng-valid-email" id="CITY_1" placeholder="Email Address" type="email">
								</div>
								<div id="feedback" class="form-group">
									<label class="ng-binding" for="FEEDBACK_1">Feedback</label>
									<textarea size="3" class="form-control ng-pristine ng-valid" id="FEEDBACK_1" placeholder="What do you think of us?"></textarea>
								</div>
								<button class="btn btn-lg btn-primary btn-block ng-binding" type="button" onclick="registerProvider()">Register</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="fb-root"></div>
		<div class="msk loading hidden">&nbsp;</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="http://code.jquery.com/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script src="resources/scripts/parallax/bootstrap.min.js"></script>
		<script src="resources/scripts/parallax/initialize.js"></script>
	</body>
</html>