(function () {
    'use strict';

    angular
        .module('app.productModal')
        .controller('ProductModalController', ProductModalController);

    ProductModalController.inject = ['$uibModalInstance','$http','AlertService'];

    function ProductModalController($uibModalInstance, $http, AlertService) {
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
                name: null
            };
            vm.crearError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function create(event) {
            event.preventDefault();

            var data = {
                nombre: vm.name
            };

            $http.post("/api/producto", data).then(function (response) {
                vm.name = null;

                $uibModalInstance.dismiss('success');
                AlertService.addAlert('success', '¡Producto ' + data.nombre + ' añadido correctamente!');
            }, function (response) {
                vm.crearError = true;
            });

        }
    }
})();