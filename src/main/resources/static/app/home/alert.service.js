(function () {
    'use strict';

    angular
        .module('app.home')
        .factory('AlertService', AlertService);

    function AlertService($rootScope) {
        var alertService;
        $rootScope.alerts = [];
        return alertService = {
            addAlert: function (type, msg) {
                $rootScope.alerts.push({
                    type: type,
                    msg: msg,
                    close: function () {
                        return alertService.closeAlert(this);
                    }
                });
            },
            closeAlert: function(alert) {
                return this.closeAlertIdx($rootScope.alerts.indexOf(alert));
            },
            closeAlertIdx: function(index) {
                return $rootScope.alerts.splice(index, 1);
            }
        };
    }
})();
