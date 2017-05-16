(function () {
    'use strict';

    angular
        .module('app.search')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$http', 'AlertService'];

    /* @ngInject */
    function SearchController($http, AlertService) {
        var vm = this;
        vm.search = null;
        vm.searchCal = null;

        vm.dietasAPI = [];
        vm.recetasAPI = [];

        // vm.dietasAPI = [
        //     {
        //         title: 'Cucurucho',
        //         content: 'Primera comida: follar' +
        //         'Segunda comida: un cacahuete' +
        //         'Tercera comida: follar' +
        //         'Cuarta comida: otro cacahuete'
        //     },
        //     {
        //         title: 'Alcaparra',
        //         content: 'Primera y única comida: alcaparra'
        //     }
        // ];
        // vm.recetasAPI = [
        //     {
        //         title: 'Pene al vodka',
        //         content: 'Pues primero se coge el pene, luego se le echa vodka, y ale.'
        //     },
        //     {
        //         title: 'Canelones',
        //         content: 'Los compras congelados, los descongelas, y ale.'
        //     }
        // ];

        $http.get("/api/recetas/").then(
            function (response) { //success
                var objetoRecetas = response.data;

                for (var i = 0; i < objetoRecetas.length; i++) {
                    var nombreProducto = objetoRecetas[i].nombre;
                    var cantidad = objetoRecetas[i].descripcion;
                    var arrayProductos = objetoRecetas[i].productos;
                    var calories = 0;
                    for (var j=0; j < arrayProductos.length; j++) {
                        calories = calories + arrayProductos[i].calorias;
                    }

                    vm.recetasAPI.push({
                        title: nombreProducto,
                        content: cantidad,
                        calories: calories
                    });
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las recetas del sistema');
            }
        );


        $http.get("/api/dietas/").then(
            function (response) { //success
                var objetoDietas = response.data;
                console.log(objetoDietas);

                for (var i = 0; i < objetoDietas.length; i++) {
                    var nombreProducto = objetoDietas[i].nombre;
                    var cantidad = objetoDietas[i].descripcion;
                    var arrayRecetas = objetoDietas[i].recetas;
                    var calories = 0;

                    console.log(arrayRecetas);
                    console.log(arrayRecetas[0].productos);
                    for (var j=0; j < arrayRecetas.length; j++) {
                        var arrayProductosDieta = arrayRecetas[j].productos;
                        for (var k=0; k < arrayProductosDieta.length; k++) {
                            calories = calories + arrayProductosDieta[k].calorias;
                        }

                    }
                    console.log(calories);

                    vm.dietasAPI.push({
                        title: nombreProducto,
                        content: cantidad,
                        calories: calories
                    });
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las dietas del sistema');
            }
        );

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

        function filter(query){
            vm.dietas = [];
            vm.recetas = [];

            for(var i = 0; i < vm.dietasAPI.length; i++){
                if((vm.dietasAPI[i].calories <= query || vm.dietasAPI[i].title === query || vm.dietasAPI[i].title.toLowerCase().startsWith(query.toLowerCase()))){
                    vm.dietas.push(vm.dietasAPI[i]);
                }
            }
            for(var j = 0; j < vm.recetasAPI.length; j++){
                if((vm.recetasAPI[j].calories <= query || vm.recetasAPI[j].title === query || vm.recetasAPI[j].title.toLowerCase().startsWith(query.toLowerCase()))){
                    vm.recetas.push(vm.recetasAPI[j]);
                }
            }
        }
    }

})();

