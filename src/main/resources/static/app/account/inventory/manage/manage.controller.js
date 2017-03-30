(function () {
    'use strict';

    angular
        .module('app.account.inventory.manage')
        .controller('ManageInventoryController', ManageInventoryController);

    ManageInventoryController.$inject = ['$scope', '$http', '$filter', 'NgTableParams',
                                        'LoginService', 'AlertService'];

    function ManageInventoryController($scope, $http, $filter,
                                       NgTableParams, LoginService, AlertService) {
        //console.log("Manage inventory controller called");
        var vm = this;
        var usuario = LoginService.currentLoggedUser();

        //*********************************************************************************************************//

        //Inventario

        vm.products = [];
        var originalData = [];

        //Obtenemos inventario
        $http.get("/api/inventario/" + usuario.username).then(
            function (response) { //success
                var objetoInventario = response.data;
                //console.log("Inventario: " + objetoInventario);

                for (var i = 0; i < objetoInventario.length; i++) {
                    var nombreProducto = objetoInventario[i].producto.nombre;
                    var cantidad = objetoInventario[i].cantidad;
                    var cantidadMinima = objetoInventario[i].cantidadMinima;
                    var fechaCaducidad = new Date(objetoInventario[i].fechaCaducidad);
                    var fechaParseada = $filter('date')(fechaCaducidad, "dd/MM/yyyy");

                    vm.products.push({
                        nombreProducto: nombreProducto, cantidad: cantidad,
                        cantidadMinima: cantidadMinima, fechaCaducidad: fechaCaducidad
                    });

                    originalData.push({
                        nombreProducto: nombreProducto, cantidad: cantidad,
                        cantidadMinima: cantidadMinima, fechaCaducidad: fechaCaducidad
                    });
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener el inventario del usuario ' + usuario.username);
            }
        );

        vm.cancel = cancel;
        vm.del = del;
        vm.save = save;

        function cancel(row, rowForm) {
            var originalRow = resetRow(row, rowForm);
            angular.extend(row, originalRow);
        }

        function del(row) {
            //Peticion API Delete



            vm.products.splice(row.id,1);
            vm.inventario.reload();
        }

        function resetRow(row, rowForm) {
            row.isEditing = false;
            rowForm.$setPristine();
            //vm.tableTracker.untrack(row);
            for (var i in originalData) {
                if (originalData[i].id === row.id) {
                    return originalData[i]
                }
            }
            // return _.findWhere(originalData, function(r){
            //     return r.id === row.id;
            // });
        }

        function save(row, rowForm) {
            var originalRow = resetRow(row, rowForm);
            console.log("Saving row " + row.nombreProducto + " with old value " + originalRow.nombreProducto);

            var data = {
                nombreUsuario: usuario.username,
                nombreProducto: row.nombreProducto,
                fechaCaducidad: row.fechaCaducidad,
                cantidad: row.cantidad,
                cantidadMinima: row.cantidadMinima
            };

            console.log(data);

            // $http.post("/api/usuario", data).then(
            //     function (response) { //success
            //         //console.log("Respuesta: " + response);
            //         vm.registerError = false;
            //
            //         AlertService.addAlert('success','¡El usuario ' + data.username + ' ha sido registrado con éxito!')
            //         $uibModalInstance.dismiss('success');
            //     },
            //     function (response) { //error
            //         vm.registerError = true;
            //     }
            // );

            angular.extend(originalRow, row);
        }

        //Creacion tabla inventario
        vm.inventario = new NgTableParams({count: 5}, {dataset: vm.products, counts: [5, 10, 20]});
    }
})();