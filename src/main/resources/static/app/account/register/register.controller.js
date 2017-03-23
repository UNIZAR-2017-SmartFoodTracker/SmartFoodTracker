(function () {
    'use strict';

    angular
        .module('app.account.register')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['$uibModalInstance', '$http'];

    function RegisterController($uibModalInstance, $http) {
        var vm = this;

        vm.registerError = false;
        vm.username = null;
        vm.email = null;
        vm.nombre = null;
        vm.apellidos = null;
        vm.password = null;
        vm.aviso = null;

        vm.register = register;
        vm.cancel = cancel;

        function cancel() {
            vm = {
                username: null,
                email: null,
                nombre: null,
                apellidos: null,
                password: null,
                aviso: null
            };
            vm.registerError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function register(event) {
            event.preventDefault();

            var data = {
                username: vm.username,
                email: vm.email,
                nombre: vm.nombre,
                apellidos: vm.apellidos,
                password: vm.password,
                aviso: vm.aviso
            };

            //console.log(data);

            $http.post("/api/usuario", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);
                    vm.registerError = false;
                    $uibModalInstance.dismiss('success');
                },
                function (response) { //error
                    vm.registerError = true;
                }
            );
        }
    }
})();