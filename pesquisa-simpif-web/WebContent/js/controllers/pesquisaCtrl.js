angular.module("pesquisaSimpif").controller("pesquisaCtrl", function($scope, $http, $filter){
		
		$scope.app = "Entrega de Kits SIMPIF";
		$scope.participants = [];
		$scope.receivedTxt = "Recebido";
		$scope.receivedBool = false;

		$scope.changeReceived = function(){
			$scope.receivedBool = !$scope.receivedBool;
		}

		var loadparticipants = function(){

			$http.get("http://localhost:8080/pesquisa-simpif-service/services/get-all").success(function(data){
				$scope.participants = data;

				$scope.totalReceived = $scope.participants.filter(function(element){
						return element.isDelivered;
					}).length;
				$scope.totalNotReceived = $scope.participants.filter(function(element){
						return !element.isDelivered;
					}).length;
				
			}).error(function(data, status){
				$scope.message = "Aconteceu um problema: " + data;
			});
		};
		
		$scope.searchParticipantByFullName = function(event){
			
	        var value = document.getElementById('searchField').value;
	        var msgdata = {
	        			'fullName' : value
	        		};
	        var res = $http.post('http://localhost:8080/pesquisa-simpif-service/services/get-byname', msgdata);
	        res.success(function(data, status, headers, config) {
	        	$scope.participants = data;
	        });			
		};

		$scope.updateParticipant = function(participant){
			
			confirmation = window.confirm("Confirma a entrega do kit ao participante " 
					+ participant.fullName + " ?");

			if(confirmation === true){
				$http.post("http://localhost:8080/pesquisa-simpif-service/services/deliver-kit", angular.copy(participant)).success(function(){
					delete $scope.participant;
					$scope.searchCriteria = "";
				}).error(function(){
					participant.isDelivered = false;
					window.alert("Ocorreu um erro na comunicação com o servidor, favor chamar o suporte.");
				});
			}else{
				participant.isDelivered = false;
			}
		}

		$scope.setOrderCriteria = function(campo){
			$scope.orderCriteria = campo;
			$scope.orderDirection = !$scope.orderDirection;
		};
	});