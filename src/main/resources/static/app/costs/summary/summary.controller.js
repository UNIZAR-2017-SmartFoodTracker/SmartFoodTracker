(function () {
    'use strict';

    angular
        .module('app.costs.summary')
        .controller('CostsSummaryController', CostsSummaryController);

    CostsSummaryController.$inject = ['$http', 'LoginService', 'AlertService'];

    /* @ngInject */
    function CostsSummaryController($http, LoginService, AlertService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        //*********************************************************************************************************//

        //Costes

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
        $http.get("/api/coste/" + usuario.username).then(
            function (response) { //success
                var objetoCoste = response.data;

                for (var i = 0; i < objetoCoste.length; i++) {
                    var coste = objetoCoste[i].coste;
                    var fecha = objetoCoste[i].fecha;

                    vm.labels.push(fecha);
                    vm.data[0].push(coste);
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener los gastos del usuario ' + usuario.username);
            }
        );
    }

})();

