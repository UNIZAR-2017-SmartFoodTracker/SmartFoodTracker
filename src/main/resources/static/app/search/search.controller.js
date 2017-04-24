(function () {
    'use strict';

    angular
        .module('app.search')
        .controller('SearchController', SearchController);

    //SearchController.$inject = ['dependency'];

    /* @ngInject */
    function SearchController() {
        var vm = this;
        vm.search = null;

        vm.dietasAPI = [
            {
                title: 'Cucurucho',
                content: 'Primera comida: follar' +
                'Segunda comida: un cacahuete' +
                'Tercera comida: follar' +
                'Cuarta comida: otro cacahuete'
            },
            {
                title: 'Alcaparra',
                content: 'Primera y única comida: alcaparra'
            }
        ];
        vm.recetasAPI = [
            {
                title: 'Pene al vodka',
                content: 'Pues primero se coge el pene, luego se le echa vodka, y ale.'
            },
            {
                title: 'Canelones',
                content: 'Los compras congelados, los descongelas, y ale.'
            }
        ];

        vm.filter = filter;
        vm.getDietas = getDietas;
        vm.getRecetas = getRecetas;

        vm.dietas = vm.dietasAPI;
        vm.recetas = vm.recetasAPI;

        ////////////////

        function getDietas() {
            return vm.dietas;
        }

        function getRecetas() {
            return vm.recetas;
        }

        function filter(name){
            vm.dietas = [];
            vm.recetas = [];

            for(var i = 0; i < vm.dietasAPI.length; i++){
                if((vm.dietasAPI[i].title === name || vm.dietasAPI[i].title.toLowerCase().startsWith(name.toLowerCase()))){
                    vm.dietas.push(vm.dietasAPI[i]);
                }
            }

            for(var j = 0; j < vm.recetasAPI.length; j++){
                if((vm.recetasAPI[j].title === name || vm.recetasAPI[j].title.toLowerCase().startsWith(name.toLowerCase()))){
                    vm.recetas.push(vm.recetasAPI[j]);
                }
            }
        }

    }

})();

