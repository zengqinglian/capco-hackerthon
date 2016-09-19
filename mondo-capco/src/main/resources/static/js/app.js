angular.module('sso', [ 'ngRoute', 'ngResource' ]).config(
		function($routeProvider) {

			$routeProvider.otherwise('/');
			$routeProvider.when('/', {
				templateUrl : 'home.html',
				controller : 'home'
			}).when('/dashboard', {
				templateUrl : 'dashboard.html',
				controller : 'dashboard'
			});

}).controller('navigation', function($scope, $http, $window, $route,$resource) {
	$scope.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};
	
	if (!$scope.user) {
		$resource('/dashboard/user', {}).get({}, function(data) {
			$scope.user = data.details.tokenValue;
			$scope.authenticated = true;
		}, function() {
			$scope.authenticated = false;
		});
	}
		
	$scope.logout = function() {
		$http.post('/dashboard/logout', {}).success(function() {
			delete $scope.user;
			$scope.authenticated = false;
			// Force reload of home page to reset all state after logout
			$window.location.hash = '';
		});
	};
}).controller('home', function($scope, $resource) {
}).controller('dashboard', function($scope, $resource, $http) {
	
	$resource('/dashboard/user', {}).get({}, function(data) {
		$scope.user = data.details.tokenValue;
		$scope.authenticated = true;
		
		$http.get('/dashboard/accounts', {
	        headers: {'Auth':$scope.user}}).success(function(response){
	        	$scope.message = response;
	    });
	}, function() {
		$scope.authenticated = false;
	});
});