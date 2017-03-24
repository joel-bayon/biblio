var biblioApp = angular.module('biblioApp', []);

biblioApp.controller ('livreController', [ '$http', '$scope', function($http, $scope) {
	$scope.livres = [];
	$scope.livre = null;
	$scope.loadAll = function() {
		$http.get('http://localhost:8080/biblio/livres.spring').then(function(value) {
			$scope.livres=value.data;
			console.log(value.data);
		}, function(reason) {
			//en cas de réponse en erreur reason contient l'erreur ....
			console.log(reason);
			
		}, function(value) {
			//optionnel ... ne sert à rien ici ... !
		});
	};
	$scope.load = function(id) {
		$http.get('http://localhost:8080/biblio/livres/'+id+'.spring').then(function(value) {
			$scope.livre=value.data;
			console.log(value.data);
		}, function(reason) {
			//en cas de réponse en erreur reason contient l'erreur ....
			console.log(reason);
			
		}, function(value) {
			//optionnel ... ne sert à rien ici ... !
		});
	};
	$scope.reset = function() {
		$scope.livres = [];
	};
	$scope.back = function() {
		$scope.livre = null;
	};
} ]);
