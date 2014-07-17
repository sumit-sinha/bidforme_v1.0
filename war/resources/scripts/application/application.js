/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',['ui.bootstrap.datetimepicker', 'ngAutocomplete']);

var controllers = {};
controllers.HeaderCtrl = function ($scope, appFactory, requestManager) {
	$scope.headerTpl = 'model/views/common/header.html';
	
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
		$scope.signin = null;
		
		if ($scope.register != null) {
			$scope.register.message = null;
		}
		
		// data prefilling
		if ($scope.login == null) {
			$scope.login = {};
		} else {
			$scope.login.email = null;
			$scope.login.password = null;
		}
		
		$scope.login.email = localStorage.getItem('email');
		
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
	
	$scope.onCloseTutorialClick = function() {
		hideOverlay();
		$scope.popupTpl = null;
		localStorage.setItem('no_tutorial', 'true');
	}
	
	$scope.onNextTutorialClick = function() {
		if ($scope.tutorial.classname == 'tutorial1') {
			$scope.tutorial.classname = 'tutorial2'
		} else if ($scope.tutorial.classname == 'tutorial2') {
			$scope.tutorial.classname = 'tutorial3';
		} else {
			$scope.onCloseTutorialClick();
		}
	}
	
	$scope.onTutorialClick = function() {
		
		if ($scope.tutorial == null) {
			$scope.tutorial = {};
		}
		
		$scope.tutorial.classname = 'tutorial1';
		
		showOverlay();
		$scope.popupTpl = 'model/views/common/modal.html';
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
		
		if (this.login.remember) {
			localStorage.setItem('email', this.login.email);
		}
		
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
	
	$scope._onRegisterSuccessCallback = function (args) {
		
		if (args.data.register.model.success) {
			
			// show success
			$scope.register = {
				message: {
					type: 'I',
					text: args.data.register.label.tx_bidforme_registration_success
				}
			}
			
			if ($scope.login == null) {
				$scope.login = {};
			}
			$scope.login.email = args.config.params.email;
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
	
	// to show the tutorial
	if (localStorage.getItem('no_tutorial') != 'true') {
		$scope.onTutorialClick();
	}
}

controllers.TravelerPageCtrl = function ($scope, $location, $injector, appFactory, requestManager) {
	
	$injector.invoke(controllers.HeaderCtrl, this, {$scope: $scope});

	if ($scope.data == null) {
		$scope.data = {};
	}
	
	// for autocomplete [START] */
	$scope.data.origin = '';
	$scope.data.destination = '';
	$scope.options1 = null;
	$scope.details1 = '';
	// for autocomplete [ END ] */
	
	var indexData = appFactory.getViewData('traveler');
	$scope.user = appFactory.getViewData('user');
	$scope.label = indexData.label;
	$scope.model = indexData.model;
	
	$scope.onTagEntry = function() {
		if ($scope.data.tag != null && $scope.data.tag.indexOf(';') != -1) {
			if ($scope.data.tags == null) {
				$scope.data.tags = [];
			}
			var tags = $scope.data.tag.split(';');
			for (var i = 0; i < tags.length; i++) {
				if (tags[i] != null && tags[i] != '') {
					$scope.data.tags.push(tags[i]);
				}
			}
			
			$scope.data.tag = '';
		}
	}
	
	$scope.onRemoveTag = function(index) {
		if ($scope.data.tags != null && $scope.data.tags.length > 0) {
			$scope.data.tags.splice(index,1);
		}
	}
	
	$scope.onTagClick = function() {
		var element = document.getElementById('txtTag');
		if (element != null) {
			element.focus();
		}
	}
	
	$scope.onSubmitTravelRequest = function() {
		
        this.data.startDate = this.data.startDateObj?this.data.startDateObj.getTime():null;
        this.data.endDate = this.data.endDateObj?this.data.endDateObj.getTime():null;

		
		if (this.data.origin == null ||  this.data.origin == '') {
			var element = document.getElementById('txtOrigin');
			if (element != null) {
				this.data.origin = element.value;
			}
		}
		
		if (this.data.destination == null ||  this.data.destination == '') {
			var element = document.getElementById('txtDestination');
			if (element != null) {
				this.data.destination = element.value;
			}
		}
		
        if (this.checkTravelRequestInput())
        {
            requestManager.makeServerCall({
                method: 'POST',
                url: '/requestCreate',
                data: $scope.data,
                showOverlay: true,
                onSuccessCallback: $scope._onRequestSubmitSuccessCallback,
                onErrorCallback: $scope._onRequestSubmitSuccessCallback
            });
        }
        else
        {
            alert('Missing or invalid information in form!');
        }
	}
	
    $scope.checkTravelRequestInput = function() {
        
        if (this.data.destination == null 
                || this.data.origin == null 
                || this.data.startDate == null 
                || this.data.endDate == null 
                || this.data.budget == null)
        {
                return false;
        }
        
        if (this.data.destination.indexOf("+") != -1 
                || this.data.destination.indexOf("/") != -1 
                || this.data.origin.indexOf("+") != -1
                || this.data.origin.indexOf("/") != -1)
            {
                return false;
            }
        
        var aNumericRegex = new RegExp("^[0-9]+$");
        if (!aNumericRegex.test(this.data.budget)
                || !aNumericRegex.test(this.data.startDate)
                || !aNumericRegex.test(this.data.endDate))
            {
                return false;
            }
                
        return true;
    }
	
	$scope._onRequestSubmitSuccessCallback = function (args) {
		hideOverlay();
		document.location = 'request?request=' + args.data.request.model.request_id;
	}
};

controllers.ProviderPageCtrl = function ($scope, appFactory) {
	
	$injector.invoke(controllers.HeaderCtrl, this, {$scope: $scope});	
	var providerData = appFactory.getViewData('provider');
	$scope.label = providerData.label;
	$scope.model = providerData.model;
}

controllers.RequestPageCtrl = function ($scope, appFactory) {
	$injector.invoke(controllers.HeaderCtrl, this, {$scope: $scope});
	$scope.bidList = 'model/views/request/bidList.html';
	$scope.travelSummaryTpl = 'model/views/common/travelsummary.html';
	
	if ($scope.data == null) {
		$scope.data = {};
	}
	
	$scope.data.bids = [{
		name: 'TUI Travel',
		amount: '300'
	}, {
		name: 'Global Travel',
		amount: '500'
	}]
	
	var requestData = appFactory.getViewData('request');
	$scope.label = requestData.label;
	$scope.model = requestData.model;
	
	$scope.onBidClick = function(index) {
		$scope.selected_bid = $scope.data.bids[index];
		$scope.bidList = 'model/views/request/bid.html';
	}
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
	}).when('/requestCreate', {
		controller: 'RequestPageCtrl',
		templateUrl: 'model/views/request/request.html'
	}).when('/bid', {
		controller: 'RequestPageCtrl',
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

