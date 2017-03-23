(function () {
    'use strict';

    angular
        .module('app.account.login')
        .controller('LoginController', LoginController);

    LoginController.inject = ['$uibModalInstance','$http','$state'];

    function LoginController($uibModalInstance,$http,$state,LoginService,AlertService) {
        //console.log("Login controller called!");
        var vm = this;

        vm.loginError = false;
        vm.password = null;
        vm.username = null;
        vm.cancel = cancel;
        vm.login = login;
        vm.toggleLogin = toggleLogin;

        function toggleLogin(){
            vm.loginError = false;
        }

        function cancel() {
            vm = {
                username: null,
                password: null
            };
            vm.loginError = false;
            $uibModalInstance.dismiss('cancel');
        }

        function login(event) {
            event.preventDefault();

            //Comprobar mediante peticion a API RESTful que son correctos los campos
            $http.get("/api/usuario/" + vm.username).then(
                function (response) { //success
                    var usuario = response.data;

                    if(usuario.password == vm.password){
                        //Exito
                        vm.loginError = false;

                        AlertService.addAlert('info','¡Bienvenid@ de vuelta ' + vm.username + '!');
                        LoginService.login(usuario);

                        $state.go('home');
                        $uibModalInstance.dismiss('success');
                    } else{
                        //Password incorrecto
                        //console.log("Password introducida: " + vm.password + ", password valida: " + usuario.password);
                        vm.loginError = true;
                    }
                },
                function (response) { //error
                    vm.loginError = true;
                }
            );
        }
    }
})();