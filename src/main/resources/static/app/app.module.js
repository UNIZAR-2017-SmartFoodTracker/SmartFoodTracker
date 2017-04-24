(function () {
    'use strict';

    angular
        .module('app', [
            'ngSanitize',
            'ui.router',
            'ngAnimate',
            'angular-loading-bar',
            //Ir agregando nuevos modulos aqui cuando se vayan creando
            'app.account',
            'app.costs',
            'app.home',
            'app.landing',
            'app.layout',
            'app.productModal',
            'app.search'])
        .config(function ($stateProvider, $urlRouterProvider, $logProvider, $locationProvider) {
            $logProvider.debugEnabled(false);
            $locationProvider.html5Mode(true);

            $urlRouterProvider.otherwise('/');

            $stateProvider
                .state('landing', {
                    url: '/',
                    templateUrl: 'app/landing/landing.html',
                    controller: 'LandingController as vm'
                })
                .state('home', {
                    url: '/home',
                    templateUrl: 'app/account/home/home.html',
                    controller: 'AccountHomeController as vm'

                })
                .state('gestionInventario', {
                    url: '/inventory',
                    templateUrl: 'app/account/inventory/manage/manage.html',
                    controller: 'ManageInventoryController as vm'

                })
                .state('resumenCostes', {
                    url: '/summary',
                    templateUrl: 'app/costs/summary/summary.html',
                    controller: 'CostsSummaryController as vm'

                })
                .state('recetasDietas', {
                    url: '/search',
                    templateUrl: 'app/search/search.html',
                    controller: 'SearchController as vm'

                });
        });

})();
