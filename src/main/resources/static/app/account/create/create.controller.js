(function () {
    'use strict';

    angular
        .module('app.account.create')
        .controller('CreateController', CreateController);

    //CreateController.inject = ['dependency1'];

    function CreateController($scope) {
        var vm = this;

        //TODO: Obtener Roles de la API
        var availableOptions = [
            {value: 'ROL_1', name: 'ROL_1_NAME'},
            {value: 'ROL_2', name: 'ROL_2_NAME'},
            {value: 'ROL_3', name: 'ROL_3_NAME'}
        ];

        vm.create = create;
        vm.availableOptions = availableOptions;

        $scope.selected = {};

        function create(event) {
            event.preventDefault();

            if($scope.selected.value == undefined){
                console.log("Selecciona ROL loco");
            } else console.log("Email: " + vm.email + " , Password: " + vm.password + " , rol: " + $scope.selected.value.value);
        }
    }
})();