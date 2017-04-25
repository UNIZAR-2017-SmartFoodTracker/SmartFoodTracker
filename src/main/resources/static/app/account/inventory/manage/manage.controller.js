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
        var availableOptions = [];

        //Obtenemos productos
        $http.get("/api/producto").then(
            function (response) { //success
                var objetoProducto = response.data;

                for (var i = 0; i < objetoProducto.length; i++) {
                    var idProducto = objetoProducto[i].idProducto;
                    var nombreProducto = objetoProducto[i].nombre;

                    vm.availableOptions.push({
                        value: idProducto, name: nombreProducto
                    });
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener los productos del sistema');
            }
        );

        vm.cantidad = null;
        vm.cantidadMinima = null;
        vm.coste = null;
        vm.fechaCaducidad = new Date();
        vm.availableOptions = availableOptions;
        $scope.selected = {};
        vm.cancel = cancel;
        vm.del = del;
        vm.save = save;
        vm.addProducto = addProducto;

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
                    var coste = objetoInventario[i].coste;

                    vm.products.push({
                        nombreProducto: nombreProducto, cantidad: cantidad,
                        cantidadMinima: cantidadMinima, fechaCaducidad: fechaCaducidad,
                        coste: coste
                    });

                    originalData.push({
                        nombreProducto: nombreProducto, cantidad: cantidad,
                        cantidadMinima: cantidadMinima, fechaCaducidad: fechaCaducidad,
                        coste: coste
                    });
                }

                //Creacion tabla inventario
                vm.inventario = new NgTableParams({count: 5}, {dataset: vm.products, counts: [5, 10, 20]});
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener el inventario del usuario ' + usuario.username);
            }
        );

        function addProducto(event){
            event.preventDefault();

            if($scope.selected.value === undefined){
            } else{
                var data = {
                    nombreUsuario: usuario.username,
                    nombreProducto: $scope.selected.value.name,
                    fechaCaducidad: ($filter('date')(vm.fechaCaducidad,"dd/MM/yyyy")),
                    cantidad: vm.cantidad,
                    cantidadMinima: vm.cantidadMinima,
                    coste: vm.coste
                };

                //console.log(data);

                $http.post("/api/inventario", data).then(
                    function (response) { //success
                        //console.log("Respuesta: " + response);

                        vm.products.push({
                            nombreProducto: data.nombreProducto,
                            fechaCaducidad: vm.fechaCaducidad,
                            cantidad: data.cantidad,
                            cantidadMinima: data.cantidadMinima,
                            coste: data.coste
                        });

                        originalData.push({
                            nombreProducto: data.nombreProducto,
                            fechaCaducidad: vm.fechaCaducidad,
                            cantidad: data.cantidad,
                            cantidadMinima: data.cantidadMinima,
                            coste: data.coste
                        });

                        AlertService.addAlert('success','¡El producto ' + data.nombreProducto + ' ha sido añadido con éxito!');

                        vm.cantidad = null;
                        vm.cantidadMinima = null;
                        vm.coste = null;
                        vm.fechaCaducidad = new Date();
                        vm.availableOptions = availableOptions;
                        $scope.selected = {};

                        vm.inventario.reload();
                    },
                    function (response) { //error
                        AlertService.addAlert('danger','Error: El producto ' + data.nombreProducto + ' no se ha podido añadir al inventario');
                    }
                );
            }
        }

        function cancel(row, rowForm) {
            var originalRow = resetRow(row, rowForm);
            angular.extend(row, originalRow);
        }

        function del(row) {
            //Peticion API Delete

            var data = {
                nombreUsuario: usuario.username,
                nombreProducto: row.nombreProducto,
                fechaCaducidad: ($filter('date')(row.fechaCaducidad,"dd/MM/yyyy")),
                cantidad: row.cantidad,
                cantidadMinima: row.cantidadMinima,
                coste: row.coste
            };

            //console.log(data);

            //Esto va por magia
            $http({
                method: 'DELETE',
                url: '/api/inventario',
                data: {
                    nombreUsuario: usuario.username,
                    nombreProducto: row.nombreProducto
                },
                headers: {'Content-type': 'application/json;charset=utf-8'}}).then(
                function(response) {
                    //console.log("Exito " + response.data);

                    var rowId = vm.products.indexOf(row);
                    //console.log(rowId);

                    vm.products.splice(rowId,1);
                    originalData.splice(rowId,1);
                    vm.inventario.reload();

                    AlertService.addAlert('success','¡Producto ' + data.nombreProducto + ' borrado con éxito!');
                }, function(rejection) {
                    //console.log("Fracaso " + rejection.data);
                    AlertService.addAlert('danger','Error al eliminar el producto ' + data.nombreProducto);
                });

            //Esto no va, por magia
            // $http.delete("/api/inventario", data).then(
            //     function (response) { //success
            //         console.log("Response: " + response.data);
            //
            //         vm.products.splice(row.id,1);
            //         originalData.splice(row.id,1);
            //         vm.inventario.reload();
            //
            //         AlertService.addAlert('success','¡Producto ' + data.nombreProducto + ' borrado con éxito!');
            //     },
            //     function (response) { //error
            //         console.log("Response: " + response.data);
            //         AlertService.addAlert('danger','Error al eliminar el producto ' + data.nombreProducto);
            //     }
            // );
        }

        function resetRow(row, rowForm) {
            row.isEditing = false;
            rowForm.$setPristine();
            for (var i in originalData) {
                if (originalData[i].id === row.id) {
                    return originalData[i]
                }
            }
        }

        function save(row, rowForm) {
            var originalRow = resetRow(row, rowForm);
            //console.log("Saving row " + row.nombreProducto + " with old value " + originalRow.nombreProducto);

            var data = {
                nombreUsuario: usuario.username,
                nombreProducto: row.nombreProducto,
                fechaCaducidad: ($filter('date')(row.fechaCaducidad,"dd/MM/yyyy")),
                cantidad: row.cantidad,
                cantidadMinima: row.cantidadMinima,
                coste: row.coste
            };

            console.log(data);

            $http.put("/api/inventario", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);

                    angular.extend(originalRow, row);

                    AlertService.addAlert('success','¡Producto ' + data.nombreProducto + ' actualizado con éxito!');
                },
                function (response) { //error
                    AlertService.addAlert('danger','Error al actualizar el producto ' + data.nombreProducto);
                }
            );
        }
    }
})();