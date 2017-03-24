(function () {
    'use strict';

    angular
        .module('app.account.login')
        .factory('LoginService', LoginService);

    LoginService.$inject = ['$uibModal','$localStorage','$sessionStorage'];

    function LoginService($uibModal, $scope, $localStorage, $sessionStorage) {
        var vm = this;
        //Objeto para guardar lo relacionado con la sesion
        vm.session = $localStorage;

        var service = {
            open: open,
            login: login,
            logout: logout,
            isLogged: isLogged,
            currentLoggedUser: currentLoggedUser
        };

        var modalInstance = null;
        var resetModal = function () {
            modalInstance = null;
        };

        return service;

        function open() {
            if (modalInstance !== null) return;
            modalInstance = $uibModal.open({
                animation: true,
                templateUrl: 'app/account/login/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }

        function login(usuario){
            vm.session.logged = {
                usuario: usuario
            };
        }

        function logout() {
            delete $localStorage.logged;
        }

        function isLogged(){
            return vm.session.logged != undefined;
        }

        function currentLoggedUser(){
            if(isLogged()){
                return vm.session.logged.usuario;
            } else return undefined;
        }
    }
})();
