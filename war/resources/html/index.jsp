<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- set resource file location --%>
<fmt:setBundle basename="properties.ResourceBundle"/>
<html class="screen">
	<head>
		<title><fmt:message key="tx_bidforme_app_name"/></title>
    	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    	<link rel="icon" type="image/png" href="resources/images/favicon.png" />
    	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
    	<link rel="stylesheet" type="text/css" href="resources/css/second.css"/>
    	<link rel="stylesheet" type="text/css" href="resources/css/datetimepicker.css"/>
    	
    	<!-- Specific Google fonts -->
	    <link href="http://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet" type="text/css">
	    <link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">
	    <link href='http://fonts.googleapis.com/css?family=Josefin+Slab' rel='stylesheet' type='text/css'>
	</head>
	<body>
			
		<div data-ng-app='bidForMe'>
			<div data-ng-view=""></div>
			<div class="msk loading hidden">&nbsp;</div>
		</div>

		<%-- include dependencies js [START] --%>
		<script type="text/javascript" src="resources/scripts/angular/angular.js"></script>
		<script type="text/javascript" src="resources/scripts/application/moment.js"></script>
		<script type="text/javascript" src="resources/scripts/application/application.js"></script>
	    <script type="text/javascript" src="resources/scripts/application/datetimepicker.js"></script>
	    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=false"></script>
		<%-- include dependencies js [ END ] --%>
		
		<script type="text/javascript">
			var jsonResponse = ${serverResponse};
		</script>
	</body>
</html>