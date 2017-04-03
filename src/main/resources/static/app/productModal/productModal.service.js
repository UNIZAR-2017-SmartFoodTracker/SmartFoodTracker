(function () {
    'use strict';

    angular
        .module('app.productModal')
        .factory('ProductModalService', ProductModalService);

    ProductModalService.$inject = ['$uibModal'];

    function ProductModalService($uibModal) {
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
                templateUrl: 'app/productModal/productModal.html',
                controller: 'ProductModalController',
                controllerAs: 'vm'
            });
            modalInstance.result.then(
                resetModal,
                resetModal
            );
        }
    }
})();
