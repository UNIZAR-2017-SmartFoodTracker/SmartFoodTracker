(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance'];
    function LoginController($uibModalInstance) {
        console.log("Login controller called!");
        var vm = this;

        vm.authenticationError = false;
        vm.password = null;
        vm.username = null;

        function cancel () {
            console.log("Cancelando...");
            $uibModalInstance.dismiss('cancel');
            vm.credentials = {
                username: null,
                password: null
            };
            vm.authenticationError = false;
        }

        function login (event) {
            console.log("Logueando...");
            event.preventDefault();
        }
    }
})();