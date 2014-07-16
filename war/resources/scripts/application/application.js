/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',['ui.bootstrap.datetimepicker']);

var controllers = {};

controllers.TravelerPageCtrl = function ($scope, appFactory, requestManager) {

	$scope.headerTpl = 'model/views/common/header.html';
	
	var indexData = appFactory.getViewData('traveler');
	$scope.label = indexData.label;
	$scope.model = indexData.model;
	
	$scope.onSignInClick = function() {
		showOverlay();
		$scope.popupTpl = 'model/views/traveler/loginPopup.html';
	}
	
	$scope.onRegisterClick = function() {
		showOverlay();
		$scope.registeration = null;
		$scope.popupTpl = 'model/views/traveler/register.html';
	}
	
	$scope.onCloseClick = function(tplName) {
		hideOverlay();
		$scope['popupTpl'] = null;
	}
	
	$scope.onTransportClick = function(mode) {

		// if not existing
		if ($scope.data == null) {
			$scope.data = {};
		}

		if ($scope.data.mode == null) {
			$scope.data.mode = {};
		}

		var element = document.getElementById(mode + 'El');
		if (element != null) {
			if (element.className.indexOf('selected') == -1) {
				$scope.data.mode[mode] = 1;
				element.className += ' selected';
			} else {
				$scope.data.mode[mode] = 0;
				element.className = element.className.replace( /(?:^|\s)selected(?!\S)/g , '' );
			}
		}
	};

	$scope.onSubmitPress = function() {
		
		// reset errors
		$scope.registeration = null;
		
		requestManager.makeServerCall({
			method: 'POST',
			url: '/register',
			data: this.register,
			showOverlay: true,
			onSuccessCallback: $scope._onSuccessCallback,
			onErrorCallback: $scope._onSuccessCallback
		});
	}
	
	$scope._onSuccessCallback = function (args) {
		
		if (args.data.register.model.success) {
			
			// show success
			$scope.modal = {
				message: {
					type: 'I',
					text: args.data.register.label.tx_bidforme_registration_success
				}, close: {
					functionName: "onCloseClick('popupTpl')"
				}
			}
			
			$scope['popupTpl'] = null;
			$scope.popupTpl = 'model/views/common/modal.html';
		} else {
			$scope.registeration = {
				error: {
					title: args.data.register.label.tx_bidforme_common_errors,
					list: args.data.register.error.validation_error
				}
			}
			
			removeOverlayClass({
				classes: ['loading']
			});
		}
		
		
	}
};

controllers.ProviderPageCtrl = function ($scope, appFactory) {
	
	$scope.headerTpl = 'model/views/common/header.html';
	
	var providerData = appFactory.getViewData('provider');
	$scope.label = providerData.label;
	$scope.model = providerData.model;
}
controllers.RequestPageCtrl = function ($scope, appFactory) {
	$scope.headerTpl = 'model/views/common/header.html';
	var requestData = appFactory.getViewData('request');
	$scope.label = requestData.label;
	$scope.model = requestData.model;
}

app.controller(controllers);

app.config(function($routeProvider) {

	var pathName = document.location.pathname;
	if (pathName == null || pathName == '' || pathName == '/') {
		pathName = '/traveler';
	}

	$routeProvider.when('/traveler', {
		controller: 'TravelerPageCtrl',
		templateUrl: 'model/views/traveler/traveler.html'
	}).when('/provider', {
		controller: 'ProviderPageCtrl',
		templateUrl: 'model/views/provider/provider.html'
	}).when('/request', {
		controller: 'RequestPageCtrl',
		templateUrl: 'model/views/request/request.html'
	}).otherwise( {redirectTo: pathName} )
});

app.factory('appFactory', function() {

	var factory = {};
	var data = jsonResponse;

	/**
	 * returns data stored in this factory
	 * @param key name of view
	 * @return JSON
	 */
	factory.getViewData = function(key) {
		if (data == null) {
			data = {};
		}

		return data[key];
	}

	/**
	 * set data to factory
	 * @param args JSON which contains key name and associated data
	 */
	factory.setViewData = function(args) {
		if (data == null) {
			data = {};
		}

		data[args.key] == args.data;
	}

	return factory;
});

app.factory('requestManager', function ($http) {
	var factory = {};
	
	factory.makeServerCall = function (args) {
		
		if (args.showOverlay) {
			showOverlay({
				loading: true
			});
		}
		
		$http({
			method: args.method,
			params: args.data, 
			url: args.url,
			headers: {'X-HTTP-RESULT':'json'}
		}).
	    success(function(data, status, headers, config) {
	    	if (args.onSuccessCallback != null) {
		    	args.onSuccessCallback({
		    		data: data, 
		    		status: status, 
		    		headers: headers, 
		    		config: config
		    	});
	    	}
	    }).
	    error(function(data, status, headers, config) {
	    	if (args.onErrorCallback != null) {
		    	args.onErrorCallback({
		    		data: data, 
		    		status: status, 
		    		headers: headers, 
		    		config: config
		    	});
	    	}
	    })
	}
	
	return factory;
});

/**
 * displays the overlay
 * @param args JSON which contains a Boolean loading, if true displays the loading icon
 */
function showOverlay(args) {
	var mskEls = document.getElementsByClassName('msk');
	for (var i = 0; i < mskEls.length; i++) {
		mskEls[i].className = 'msk';
		if (args != null && args.loading) {
			mskEls[i].className += ' loading';
		}
	}
}

/**
 * hides the overlay
 */
function hideOverlay() {
	var mskEls = document.getElementsByClassName('msk');
	for (var i = 0; i < mskEls.length; i++) {
		mskEls[i].className = 'msk loading hidden';
	}
}

/**
 * removes any class from overlay
 */
function removeOverlayClass(args) {
	var mskEls = document.getElementsByClassName('msk');
	for (var i = 0; i < mskEls.length; i++) {
		for (var j = 0; j < args.classes.length; j++) {
			mskEls[i].className = mskEls[i].className.replace(args.classes[j], '');
		}
	}
}

