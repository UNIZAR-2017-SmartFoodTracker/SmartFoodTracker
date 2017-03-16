(function() {
    'use strict';

    angular
        .module('app.layout.navbar')
        .directive('navbar', navbar);

    function navbar() {
        var directive = {
            bindToController: true,
            controller: NavbarController,
            controllerAs: 'vm',
            restrict: 'EA',
            scope: {
                'navline': '='
            },
            templateUrl: 'app/layout/navbar/navbar.html'
        };

        NavbarController.$inject = ['$scope','LoginService'];

        function NavbarController($scope,LoginService) {
            console.log("Navbar controller called!");
            var vm = this;
            $scope.isCollapsed = true;

            vm.login = login;

            function login() {
                LoginService.open();
            }
        }

        return directive;
    }
})();
