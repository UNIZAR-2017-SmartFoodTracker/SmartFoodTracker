(function () {
    'use strict';

    angular
        .module('app.account.register')
        .factory('RegisterService', RegisterService);

    RegisterService.$inject = ['$uibModal'];

    function RegisterService($uibModal) {
        var vm = this;

        var service = {
            open: open
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
                templateUrl: 'app/account/register/register.html',
                controller: 'RegisterController',
                controllerAs: 'vm'
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }
    }
})();
