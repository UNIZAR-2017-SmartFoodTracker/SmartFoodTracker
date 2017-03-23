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

            var data = {
                username: vm.username
            };

            // console.log(vm.username);
            // console.log(vm.password);

            //Comprobar mediante peticion a API RESTful que son correctos los campos
            $http.get("/api/usuario", data).then(
                function (response) { //success
                    //console.log("Respuesta: " + response);

                    var usuario = response.data[0];

                    if(usuario.password == vm.password){
                        //Exito
                        vm.loginError = false;

                        AlertService.addAlert('info','¡Bienvenid@ de vuelta ' + vm.username + '!');
                        LoginService.login(usuario);

                        $state.go('home');
                        $uibModalInstance.dismiss('success');
                    } else{
                        //Password incorrecto
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