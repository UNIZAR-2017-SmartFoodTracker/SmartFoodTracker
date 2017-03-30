(function () {
    'use strict';

    angular
        .module('app.account.inventory.manage', [
            'ui.bootstrap',
            'ngMaterial',
            'ngTable'
        ])
        .config(function($mdDateLocaleProvider) {
            $mdDateLocaleProvider.formatDate = function(date) {
                var day = date.getDate();
                var monthIndex = date.getMonth();
                var year = date.getFullYear();

                return day + '/' + (monthIndex + 1) + '/' + year;
            };
        });
})();