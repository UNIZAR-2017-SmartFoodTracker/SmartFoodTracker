(function() {
    'use strict';

    angular
        .module('app.layout')
        .controller('LayoutController', LayoutController);

    //LayoutController.$inject = ['logger'];

    function LayoutController() {
        var vm = this;
        vm.navline = {
            title: 'Campus Manager',
            text: 'Created by John Papa',
            link: 'http://twitter.com/john_papa'
        };
    }
})();
