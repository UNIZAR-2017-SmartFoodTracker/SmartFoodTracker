(function () {
    'use strict';

    angular
        .module('app.costs.summary')
        .controller('CostsSummaryController', CostsSummaryController);

    CostsSummaryController.$inject = ['$scope', '$http', '$filter', 'NgTableParams', 'LoginService', 'AlertService'];

    /* @ngInject */
    function CostsSummaryController($scope, $http, $filter, NgTableParams, LoginService, AlertService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        //*********************************************************************************************************//

        //Pesos

        //Cosas de chartjs
        vm.onClick = function (points, evt) {
            //console.log(points, evt);
        };
        vm.chartOptions = {
            scales: {
                yAxes: [{id: 'y-axis-1', type: 'linear', position: 'left', ticks: {min: 30}}]
            }
        };
        vm.labels = [];
        vm.data = [[]];

        //Obtenemos pesos del usuario
        $http.get("/api/peso/" + usuario.username).then(
            function (response) { //success
                var objetoPeso = response.data;

                for (var i = 0; i < objetoPeso.length; i++) {
                    var peso = objetoPeso[i].peso;
                    var fecha = new Date(objetoPeso[i].fecha);
                    var fechaParseada = $filter('date')(fecha, "dd/MM/yyyy");

                    vm.labels.push(fechaParseada);
                    vm.data[0].push(peso);
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener los pesos del usuario ' + usuario.username);
            }
        );



    }

})();

