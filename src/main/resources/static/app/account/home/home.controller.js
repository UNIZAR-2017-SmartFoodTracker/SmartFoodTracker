(function () {
    'use strict';

    angular
        .module('app.account.home')
        .controller('AccountHomeController', AccountHomeController);

    //HomeController.$inject = ['$scope'];

    function AccountHomeController($scope) {
        //console.log("Account home controller called");

        $scope.labels = ["January", "February", "March", "April", "May", "June", "July"];
        $scope.data = [
            [65, 59, 80, 81, 56, 55, 40]
        ];
        $scope.onClick = function (points, evt) {
            console.log(points, evt);
        };
    }
})();