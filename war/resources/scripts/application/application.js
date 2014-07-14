/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',[]);

var controllers = {};
controllers.SampleController = function ($scope, appFactory) {
	$scope.countries = appFactory.getViewData('sample');
};

app.controller(controllers);

app.config(function($routeProvider) {
	$routeProvider.when('/index', {
		controller: 'SampleController',
		templateUrl: 'model/views/index.html'
	}).when('/traveller', {
		controller: 'SampleController',
		templateUrl: 'model/views/traveller/traveller.html'
	}).otherwise( {redirectTo: '/index'} )
});

app.factory('appFactory', function() {
	var data = {
		'sample': [{
				name: 'India',
				capital: 'New Delhi'
			}, {
				name: 'United States of America',
				capital: 'Washington DC'
			}, {
				name: 'France',
				capital: 'Paris'
			}, {
				name: 'Germany',
				capital: 'Berlin'
			}, {
				name: 'United Kingdom',
				capital: 'London'
			}]
	};
	var factory = {};
	
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