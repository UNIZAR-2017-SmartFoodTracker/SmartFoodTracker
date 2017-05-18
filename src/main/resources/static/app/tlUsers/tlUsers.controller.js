(function () {
    'use strict';

    angular
        .module('app.tlUsers')
        .controller('TlUsersController', TlUsersController);

    TlUsersController.$inject = ['$scope', '$http', 'NgTableParams', 'LoginService', 'AlertService'];

    function TlUsersController($scope, $http, NgTableParams, LoginService, AlertService) {
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        vm.suscribeReceta = suscribeReceta;
        vm.suscribeDieta = suscribeDieta;

        //*********************************************************************************************************//

        // Recetas
        vm.recetas = [];

        $http.get('/api/recetasHistorial').then(function (response) {
            var objetoReceta = response.data;
            for (var i = 0; i < objetoReceta.length; i++) {
                var usuarioReceta = objetoReceta[i].usuario.username;
                if (usuarioReceta !== usuario.username) {
                    var id = objetoReceta[i].receta.id;
                    var nombreReceta = objetoReceta[i].receta.nombre;
                    var descripcion = objetoReceta[i].receta.descripcion;
                    var valoracion = objetoReceta[i].valoracion;
                    var productos = objetoReceta[i].receta.productos;
                    var productosNombre = "";
                    var calorias = 0;
                    for (var j = 0; j < productos.length; j++) {
                        productosNombre += productos[j].nombre + ", ";
                        calorias += productos[j].calorias;
                    }

                    vm.recetas.push({usuario: usuarioReceta, id: id, nombreReceta: nombreReceta, descripcion: descripcion,
                        calorias: calorias, valoracion: valoracion, productos: productosNombre});
                }
            }

            vm.recetasTable = new NgTableParams({count: 5}, {dataset: vm.recetas, counts: [5, 10, 20]});
        }, function (response) {
            AlertService.addAlert('danger', 'Error al obtener el historial de recetas del usuario ' + usuario.username);
        });

        // Dietas
        vm.dietas = [];

        $http.get('/api/dietasHistorial').then(function (response) {
            var objetoDieta = response.data;
            for (var i = 0; i < objetoDieta.length; i++) {
                var usuarioDieta = objetoDieta[i].usuario.username;
                if (usuarioDieta !== usuario.username) {
                    var id = objetoDieta[i].dieta.id;
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

                    vm.dietas.push({usuario: usuarioDieta, id: id, nombreDieta: nombreDieta, descripcion: descripcion, calorias: calorias,
                        valoracion: valoracion, recetas: recetasNombre.substr(0, recetasNombre.length - 2)});
                }
            }

            vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});
        }, function (response) {
            AlertService.addAlert('danger', 'Error al obtener el historial de dietas del usuario ' + usuario.username);
        });

        vm.dietasTable = new NgTableParams({count: 5}, {dataset: vm.dietas, counts: [5, 10, 20]});

        function suscribeReceta(row) {
            var data = {
                username: usuario.username,
                idReceta: row.id,
                valoracion: 2.5
            };

            $http.post("/api/recetasHistorial", data).then(
                function (response) { //success
                    AlertService.addAlert('success','Exito: Se ha suscrito a la receta ' + row.nombreReceta);
                },
                function (response) { //error
                    AlertService.addAlert('danger', 'Error al suscribirse a la receta ' + row.nombreReceta);
                }
            );
        }

        function suscribeDieta(row) {
            var data = {
                username: usuario.username,
                idDieta: row.id,
                valoracion: 2.5
            };

            $http.post("/api/dietasHistorial", data).then(
                function (response) { //success
                    AlertService.addAlert('success','Exito: Se ha suscrito a la receta ' + row.nombreDieta);
                },
                function (response) { //error
                    AlertService.addAlert('danger', 'Error al suscribirse a la receta ' + row.nombreDieta);
                }
            );
        }
    }
})();
