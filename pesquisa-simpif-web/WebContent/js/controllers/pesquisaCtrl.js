angular.module("pesquisaSimpif").controller("pesquisaCtrl", function($scope, $http, $filter, cfpLoadingBar){
		var SERVICE_URL = "http://localhost:8080/pesquisa-simpif-service";
		
		$scope.app = "Entrega de Kits SIMPIF";
		$scope.participants = [];
		$scope.receivedTxt = "Recebido";
		$scope.receivedBool = false;
		
		$scope.changeReceived = function(){
			$scope.receivedBool = !$scope.receivedBool;
		}

		var updateCounters = function(){
			var data = $http.get(SERVICE_URL + "/services/get-status").success(function(data){
				$scope.totalReceived = data.delivered;
				$scope.totalNotReceived = data.notDelivered;
				
			}).error(function(data, status){
				window.alert("Ocorreu um erro na comunicação com o servidor, favor chamar o suporte.");
			});
			
		}
		
		var loadparticipants = function(){

			$http.get(SERVICE_URL + "/services/get-all").success(function(data){
				$scope.participants = data;
				
			}).error(function(data, status){
				window.alert("Ocorreu um erro na comunicação com o servidor, favor chamar o suporte.");
			});
		};
		
		$scope.searchParticipantByFullName = function(event){
			
	        var value = document.getElementById('searchField').value;
	        var msgdata = {
	        			'fullName' : value
	        		};
	        var res = $http.post(SERVICE_URL + '/services/get-byname', msgdata);
	        res.success(function(data, status, headers, config) {
	        	$scope.participants = data;
	        });
	        updateCounters();
		};

		$scope.updateParticipant = function(participant){
			
			confirmation = window.confirm("Confirma a entrega do kit ao participante " 
					+ participant.fullName + " ?");

			if(confirmation === true){
				$http.post(SERVICE_URL + "/services/deliver-kit", angular.copy(participant)).success(function(){
					delete $scope.participant;
					$scope.searchCriteria = "";
				}).error(function(){
					participant.isDelivered = false;
					window.alert("Ocorreu um erro na comunicação com o servidor, favor chamar o suporte.");
				});
			}else{
				participant.isDelivered = false;
			}
			updateCounters();
		}

		$scope.setOrderCriteria = function(campo){
			$scope.orderCriteria = campo;
			$scope.orderDirection = !$scope.orderDirection;
		};
		
		updateCounters();
		
	});