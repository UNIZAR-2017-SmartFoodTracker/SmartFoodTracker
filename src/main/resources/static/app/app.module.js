(function () {
    'use strict';

    angular
        .module('app', [
            'ngSanitize',
            //Ir agregando nuevos modulos aqui cuando se vayan creando
            'app.account',
            'app.home',
            'app.layout',
            'app.buildings']);

})();
