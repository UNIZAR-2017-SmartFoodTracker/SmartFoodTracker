(function() {
    'use strict';

    angular
        .module('app.home')
        .controller('HomeController', HomeController);

    function HomeController() {
        console.log("HOME CONTROLLER CALLED!");
        var vm = this;
        vm.home = {
            title: 'Vista general',
            subtitle: 'Campus Escuela de Ingeniería y Arquitectura'
        };
    }
})();
