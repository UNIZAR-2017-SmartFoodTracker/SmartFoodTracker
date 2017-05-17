(function () {
    'use strict';

    angular
        .module('app.account.history')
        .controller('HistoryController', HistoryController);

    HistoryController.$inject = ['$scope', '$http', '$filter', 'NgTableParams',
        'LoginService', 'AlertService'];

    function HistoryController($scope, $http, $filter,
                                       NgTableParams, LoginService, AlertService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        //*********************************************************************************************************//

        // Recetas
        vm.recetas = [];

        $http.get('/api/recetasHistorial/' + usuario.username).then(function (response) {
            var objetoReceta = response.data;
            for (var i = 0; i < objetoReceta.length; i++) {
                var id = objetoReceta[i].id;
                var nombreReceta = objetoReceta[i].nombre;
                var descripcion = objetoReceta[i].descripcion;
                var calorias = objetoReceta[i].calorias;
                var valoracion = objetoReceta[i].valoracion;
                var productos = objetoReceta[i].productos;

                vm.recetas.push({id: id, nombreReceta: nombreReceta, descripcion: descripcion, calorias: calorias,
                    valoracion: valoracion, productos: productos});
            }

            vm.recetasTable = new NgTableParams({count: 5}, {dataset: vm.recetas, counts: [5, 10, 20]});
        }, function (response) {
            AlertService.addAlert('danger', 'Error al obtener el historial de recetas del usuario ' + usuario.username);
        });

        // Dietas
        vm.dietas = [];

        $http.get('/api/dietasHistorial/' + usuario.username).then(function (response) {
            var objetoDieta = response.data;
            for (var i = 0; i < objetoDieta.length; i++) {
                var id = objetoDieta[i].id;
                var nombreDieta = objetoDieta[i].nombre;
                var descripcion = objetoDieta[i].descripcion;
                var calorias = objetoDieta[i].calorias;
                var valoracion = objetoDieta[i].valoracion;
                var recetas = objetoDieta[i].recetas;

                vm.dietas.push({id: id, nombreDieta: nombreDieta, descripcion: descripcion, calorias: calorias,
                    valoracion: valoracion, recetas: recetas});
            }

            vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});
        }, function (response) {
            AlertService.addAlert('danger', 'Error al obtener el historial de dietas del usuario ' + usuario.username);
        });

        vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});
    }
})();
