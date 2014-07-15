/**
 * This file takes care of initializing Angula js and setting up data
 * @author ssinha
 */
var app = angular.module('bidForMe',['ui.bootstrap.datetimepicker']);

var controllers = {};
controllers.IndexPageCtrl = function ($scope, appFactory) {
	$scope.headerTpl = 'model/views/common/header.html';
	
	var indexData = appFactory.getViewData('index');
	$scope.label = indexData.label;
	$scope.model = indexData.model;
	
	$scope.showCapital = function(index) {
		$scope.country = $scope.model.countries[index];
	}
};

app.controller(controllers);

app.config(function($routeProvider) {
	$routeProvider.when('/index', {
		controller: 'IndexPageCtrl',
		templateUrl: 'model/views/index.html'
	}).when('/traveller', {
		controller: 'IndexPageCtrl',
		templateUrl: 'model/views/traveller/traveller.html'
	}).otherwise( {redirectTo: '/index'} )
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