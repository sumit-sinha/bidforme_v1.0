/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',['ui.bootstrap.datetimepicker', 'ngAutocomplete']);

var controllers = {};
controllers.AutoCompleteCtrl = function ($scope) {

	  $scope.result1 = '';
	  $scope.options1 = null;
	  $scope.details1 = '';

	  $scope.result2 = '';
	  $scope.options2 = {
	    country: 'ca',
	    types: '(cities)'
	  };    $scope.details2 = '';
	  
	  $scope.result3 = '';
	  $scope.options3 = {
	    country: 'gb',
	    types: 'establishment'
	  };
	  $scope.details3 = '';
};
controllers.TravelerPageCtrl = function ($scope, $location, appFactory, requestManager) {
	
	if ($scope.transport == null) {
		$scope.transport = {};
	}
	
	if ($scope.data == null) {
		$scope.data = {};
	}
	
	$scope.data.mode = {
		air: 1,
		car: 1,
		ship: 1,
		train: 1
	}
	
	$scope.headerTpl = 'model/views/common/header.html';
	$scope.transport.airsegment = 'resources/images/air-checked.png';
	$scope.transport.carsegment = 'resources/images/car-checked.png';
	$scope.transport.trainsegment = 'resources/images/train-checked.png';
	$scope.transport.shipsegment = 'resources/images/ship-checked.png';
	
	var indexData = appFactory.getViewData('traveler');
	$scope.user = appFactory.getViewData('user');
	$scope.label = indexData.label;
	$scope.model = indexData.model;
	
	$scope.onToggleUnderConstruction = function() {
		var element = document.getElementById('underConstEl');
		if (element != null) {
			if (element.className.indexOf('show') == -1) {
				element.className += ' show';
				showOverlay();
			} else {
				element.className = element.className.replace( /(?:^|\s)show(?!\S)/g , '' );
				hideOverlay();
			}
		}
	}
	
	$scope.onSignInClick = function() {
		showOverlay();
		$scope.register = null;
		$scope.popupTpl = 'model/views/traveler/loginPopup.html';
	}
	
	$scope.onContactUsClick = function() {
		showOverlay();
		$scope.popupTpl = 'model/views/common/contactus.html';
	}
	
	$scope.onRegisterClick = function() {
		showOverlay();
		$scope.registeration = null;
		$scope.popupTpl = 'model/views/traveler/register.html';
	}
	
	$scope.onCloseClick = function() {
		hideOverlay();
		$scope['popupTpl'] = null;
	}
	
	$scope.onNewCriteriaClick = function() {
		if ($scope.criterias == null) {
			$scope.criterias = [];
		}
		
		$scope.criterias.push({});
	}
	
	$scope.onCriteriaRemoveClick = function() {
		if ($scope.criterias != null && $scope.criterias.length > 0) {
			$scope.criterias.pop();
		}
	}
	
	$scope.onLogoutClick = function() {
		
		// remove local data
		$scope.onToggleUnderConstruction();
		$scope.user = null;
		
		requestManager.makeServerCall({
			method: 'POST',
			url: '/logout',
			showOverlay: false
		});
	}
	
	$scope.onLoginClick = function() {
		requestManager.makeServerCall({
			method: 'POST',
			url: '/signin',
			data: this.login,
			showOverlay: true,
			onSuccessCallback: $scope._onLoginSuccessCallback,
			onErrorCallback: $scope._onLoginSuccessCallback
		});
	}
	
	$scope._onLoginSuccessCallback = function(args) {
		
		if (args.data.login.model.success) {
			
			// show success
			appFactory.setViewData({key: 'login', data: args.data.login});
			appFactory.setViewData({key: 'user', data: args.data.user});
			
			$scope.user = args.data.user;
			$scope.onCloseClick();
			
		} else {
			$scope.signin = {
				error: {
					title: args.data.login.label.tx_bidforme_common_errors,
					list: args.data.login.error.validation_error
				}
			}
			
			removeOverlayClass({
				classes: ['loading']
			});
		}
	}
	
	$scope.onTransportClick = function(mode) {

		// if not existing
		if ($scope.data == null) {
			$scope.data = {};
		}

		if ($scope.data.mode == null) {
			$scope.data.mode = {};
		}
		
		if ($scope.transport[mode + 'segment'].indexOf('unchecked') == -1) {
			$scope.data.mode[mode] = 0;
			$scope.transport[mode + 'segment'] = 'resources/images/' + mode + '-unchecked.png';
		} else {
			$scope.data.mode[mode] = 1;
			$scope.transport[mode + 'segment'] = 'resources/images/' + mode + '-checked.png';
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
			onSuccessCallback: $scope._onRegisterSuccessCallback,
			onErrorCallback: $scope._onRegisterSuccessCallback
		});
	}
	
	$scope.onSubmitTravelRequest = function() {
		requestManager.makeServerCall({
			method: 'POST',
			url: '/requestCreate',
			data: this.data,
			showOverlay: true,
			onSuccessCallback: $scope._onRequestSubmitSuccessCallback,
			onErrorCallback: $scope._onRequestSubmitSuccessCallback
		});
	}
	
	$scope._onRequestSubmitSuccessCallback = function (args) {
		
	}
	
	$scope._onRegisterSuccessCallback = function (args) {
		
		if (args.data.register.model.success) {
			
			// show success
			$scope.register = {
				username: args.config.params.email,
				message: {
					type: 'I',
					text: args.data.register.label.tx_bidforme_registration_success
				}
			}
			
			$scope.popupTpl = 'model/views/traveler/loginPopup.html';
			
		} else {
			$scope.registeration = {
				error: {
					title: args.data.register.label.tx_bidforme_common_errors,
					list: args.data.register.error.validation_error
				}
			}
		}
		
		removeOverlayClass({
			classes: ['loading']
		});		
	}
	
	$scope.openDtPicker = function(elementId) {
		var element = document.getElementById(elementId);
		if (element != null) {
			if (element.className.indexOf('open') == -1) {
				element.className += ' open';
			} else {
				element.className = element.className.replace( /(?:^|\s)open(?!\S)/g , '' );
			}
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
	}).when('/bid', {
		controller: 'TravelerPageCtrl',
		templateUrl: 'model/views/request/bid.html'
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

angular.module( "ngAutocomplete", [])
.directive('ngAutocomplete', function($parse) {
  return {

    scope: {
      details: '=',
      ngAutocomplete: '=',
      options: '='
    },

    link: function(scope, element, attrs, model) {

      //options for autocomplete
      var opts

      //convert options provided to opts
      var initOpts = function() {
        opts = {}
        if (scope.options) {
          if (scope.options.types) {
            opts.types = []
            opts.types.push(scope.options.types)
          }
          if (scope.options.bounds) {
            opts.bounds = scope.options.bounds
          }
          if (scope.options.country) {
            opts.componentRestrictions = {
              country: scope.options.country
            }
          }
        }
      }
      initOpts()

      //create new autocomplete
      //reinitializes on every change of the options provided
      var newAutocomplete = function() {
        scope.gPlace = new google.maps.places.Autocomplete(element[0], opts);
        google.maps.event.addListener(scope.gPlace, 'place_changed', function() {
          scope.$apply(function() {
              scope.details = scope.gPlace.getPlace();
            scope.ngAutocomplete = element.val();
          });
        })
      }
      newAutocomplete()

      //watch options provided to directive
      scope.watchOptions = function () {
        return scope.options
      };
      scope.$watch(scope.watchOptions, function () {
        initOpts()
        newAutocomplete()
        element[0].value = '';
        scope.ngAutocomplete = element.val();
      }, true);
    }
  };
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

