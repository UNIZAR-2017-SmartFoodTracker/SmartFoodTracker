(function () {
    'use strict';

    angular
        .module('app', [
            'ngSanitize',
            'ui.router',
            //Ir agregando nuevos modulos aqui cuando se vayan creando
            'app.account',
            'app.home',
            'app.landing',
            'app.layout'])
        .config(function ($stateProvider, $urlRouterProvider,$logProvider,$locationProvider) {
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
                    templateUrl: 'app/account/create/create.html',
                    controller: 'CreateController as vm'

                });
            //     .state('betan', {
            //         url: '/betan',
            //         templateUrl: 'app/buildings/betan/betan.html',
            //         controller: 'BetanController as vm'
            //
            //     })
            //     .state('torres', {
            //         url: '/torres',
            //         templateUrl: 'app/buildings/torres/torres.html',
            //         controller: 'TorresController as vm'
            //
            //     })
            //     .state('crearUsuario', {
            //         url: '/crearUsuario',
            //         templateUrl: 'app/account/create/create.html',
            //         controller: 'CreateController as vm'
            //
            //     });
        });

})();
