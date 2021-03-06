(function () {
    'use strict';

    angular
        .module('app.productModal')
        .controller('ProductModalController', ProductModalController);

    ProductModalController.inject = ['$uibModalInstance','$http','$state', 'AlertService'];

    function ProductModalController($uibModalInstance, $http, $state, AlertService) {
        var vm = this;

        vm.crearError = false;
        vm.cancel = cancel;
        vm.create = create;
        vm.toggleCrear = toggleCrear;

        function toggleCrear() {
            vm.crearError = false;
        }

        function cancel() {
            vm = {
                name: null,
                descripcion: null,
                calorias: null
            };
            vm.crearError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function create(event) {
            event.preventDefault();

            var data = {
                nombre: vm.name,
                descripcion: vm.descripcion,
                calorias: vm.calorias
            };

            $http.post("/api/producto", data).then(function (response) {
                vm.name = null;
                vm.descripcion = null;
                vm.calorias = null;

                $uibModalInstance.dismiss('success');
                AlertService.addAlert('success', '¡Producto ' + data.nombre + ' añadido correctamente!');

                $state.reload();
            }, function (response) {
                vm.crearError = true;
            });

        }
    }
})();