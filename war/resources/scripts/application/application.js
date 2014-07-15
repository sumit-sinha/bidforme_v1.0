/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',['ui.bootstrap.datetimepicker']);

var controllers = {};

controllers.TravelerPageCtrl = function ($scope, appFactory) {

	$scope.headerTpl = 'model/views/common/header.html';
	var indexData = appFactory.getViewData('traveler');
	$scope.label = indexData.label;
	$scope.model = indexData.model;
	
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
		
		// create parameters
		var params = '';
		if ($scope.data != null) {
			for (var key in $scope.data) {
				if ($scope.data.hasOwnProperty(key)) {
					params += ((params != '')?'&': '') + key + '=' + $scope.data[key];
				}
			}
		}

		$http({
				method: 'POST',
				params: params, 
				url: '/index',
				headers: {'X-HTTP-RESULT':'json'}
		}).
	    success(function(data, status, headers, config) {
	      console.log(data);
	      // this callback will be called asynchronously
	      // when the response is available
	    }).
	    error(function(data, status, headers, config) {
	      // called asynchronously if an error occurs
	      // or server returns response with an error status.
	    });
		
		alert('You pressed submit button');
	}
};
controllers.ProviderPageCtrl = function ($scope, appFactory) {
	$scope.headerTpl = 'model/views/common/header.html';
	var providerData = appFactory.getViewData('provider');
	$scope.label = providerData.label;
	$scope.model = providerData.model;
}

app.controller(controllers);

/*var url_split = document.URL.split("#")[0].split("/")*/

app.config(function($routeProvider) {
	$routeProvider.when('/traveler', {
		controller: 'TravelerPageCtrl',
		templateUrl: 'model/views/traveler/traveler.html'
	}).when('/provider', {
		controller: 'ProviderPageCtrl',
		templateUrl: 'model/views/provider/provider.html'
	}).otherwise( {redirectTo: '/traveler'} )
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