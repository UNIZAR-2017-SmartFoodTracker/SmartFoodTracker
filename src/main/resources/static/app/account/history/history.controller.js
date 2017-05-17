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
                var nombreReceta = objetoReceta[i].receta.nombre;
                var descripcion = objetoReceta[i].receta.descripcion;
                var productos = objetoReceta[i].receta.productos;
                var productosNombre = "";
                var calorias = 0;
                for (var j = 0; j < productos.length; j++) {
                    productosNombre += productos[j].nombre + ", ";
                    calorias += productos[j].calorias;
                }
                var valoracion = objetoReceta[i].valoracion;

                vm.recetas.push({id: id, nombreReceta: nombreReceta, descripcion: descripcion, calorias: calorias,
                    valoracion: valoracion, productos: productosNombre.substr(0, productosNombre.length - 2)});
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
                var nombreDieta = objetoDieta[i].dieta.nombre;
                var descripcion = objetoDieta[i].dieta.descripcion;
                var calorias = 0;
                var valoracion = objetoDieta[i].valoracion;
                var recetas = objetoDieta[i].dieta.recetas;
                var recetasNombre = "";
                for (var j = 0; j < recetas.length; j++) {
                    for (var n = 0; n < recetas[j].productos.length; n++) {
                        calorias += recetas[j].productos[n].calorias;
                    }
                    recetasNombre += recetas[j].nombre + ", ";
                }

                vm.dietas.push({id: id, nombreDieta: nombreDieta, descripcion: descripcion, calorias: calorias,
                    valoracion: valoracion, recetas: recetasNombre.substr(0, recetasNombre.length - 2)});
            }

            vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});
        }, function (response) {
            AlertService.addAlert('danger', 'Error al obtener el historial de dietas del usuario ' + usuario.username);
        });

        vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});
    }
})();
