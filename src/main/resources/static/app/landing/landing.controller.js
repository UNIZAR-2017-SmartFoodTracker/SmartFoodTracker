(function () {
    'use strict';

    angular
        .module('app.landing')
        .controller('LandingController', LandingController);

    LandingController.inject = ['$state','LoginService','AlertService','RegisterService'];

    function LandingController($state,LoginService,AlertService,RegisterService) {
        if(LoginService.isLogged()){
            $state.go('home');
        }

        var vm = this;

        vm.login = login;
        vm.register = register;

        function login() {
            LoginService.open();
        }

        function register(){
            RegisterService.open();
        }

    }
})();