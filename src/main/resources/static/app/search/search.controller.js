(function () {
    'use strict';

    angular
        .module('app.search')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$http', 'AlertService', 'LoginService'];

    /* @ngInject */
    function SearchController($http, AlertService, LoginService) {
        var vm = this;
        var cambiarReceta = false;
        var cambiarDieta = false;
        vm.search = null;
        vm.searchCal = null;

        vm.dietasAPI = [];
        vm.recetasAPI = [];
        vm.ratingsRecetas = [];
        vm.ratingsDietas = [];

        var usuario = LoginService.currentLoggedUser().username;

        var dietasUsuario = [];
        var recetasUsuario = [];

        $http.get("/api/recetasHistorial/" + usuario).then(
            function (response) { //success
                console.log('Recetas historial');
                response.data.forEach(function (recetaHistorial) {
                    console.log(recetaHistorial);
                    recetasUsuario.push(recetaHistorial);
                })
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las recetas guardadas de ' + usuario);
            }
        );

        $http.get("/api/dietasHistorial/" + usuario).then(
            function (response) { //success
                console.log('Dietas historial');
                response.data.forEach(function (dietaHistorial) {
                    console.log(dietaHistorial);
                    dietasUsuario.push(dietaHistorial);
                })
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las dietas guardadas de ' + usuario);
            }
        );

        $http.get("/api/recetas/").then(
            function (response) { //success
                var objetoRecetas = response.data;

                for (var i = 0; i < objetoRecetas.length; i++) {
                    var id = objetoRecetas[i].id;
                    var nombreProducto = objetoRecetas[i].nombre;
                    var cantidad = objetoRecetas[i].descripcion;
                    var arrayProductos = objetoRecetas[i].productos;
                    var calories = 0;
                    for (var j=0; j < arrayProductos.length; j++) {
                        calories = calories + arrayProductos[i].calorias;
                    }

                    vm.recetasAPI.push({
                        id: id,
                        title: nombreProducto,
                        content: cantidad,
                        calories: calories
                    });

                    vm.ratingsRecetas[id] = 0;

                    for(var j = 0; j < recetasUsuario.length; j++){
                        console.log(recetasUsuario[j]);
                        if(recetasUsuario[j].receta.id === id){
                            vm.ratingsRecetas[id] = recetasUsuario[j].valoracion;
                        }
                    }
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las recetas del sistema');
            }
        );


        $http.get("/api/dietas/").then(
            function (response) { //success
                var objetoDietas = response.data;
                //console.log(objetoDietas);

                for (var i = 0; i < objetoDietas.length; i++) {
                    var id = objetoDietas[i].id;
                    var nombreProducto = objetoDietas[i].nombre;
                    var cantidad = objetoDietas[i].descripcion;
                    var arrayRecetas = objetoDietas[i].recetas;
                    var calories = 0;

                    //console.log(arrayRecetas);
                    //console.log(arrayRecetas[0].productos);
                    for (var j=0; j < arrayRecetas.length; j++) {
                        var arrayProductosDieta = arrayRecetas[j].productos;
                        for (var k=0; k < arrayProductosDieta.length; k++) {
                            calories = calories + arrayProductosDieta[k].calorias;
                        }

                    }
                    //console.log(calories);

                    vm.dietasAPI.push({
                        id: id,
                        title: nombreProducto,
                        content: cantidad,
                        calories: calories
                    });

                    vm.ratingsDietas[id] = 0;

                    for(var j = 0; j < dietasUsuario.length; j++){
                        if(dietasUsuario[j].dieta.id === id){
                            vm.ratingsDietas[id] = dietasUsuario[j].valoracion;
                        }
                    }
                }
            },
            function (response) { //error
                AlertService.addAlert('danger', 'Error al obtener las dietas del sistema');
            }
        );

        vm.filter = filter;
        vm.getDietas = getDietas;
        vm.getRecetas = getRecetas;
        vm.changeRatingDieta = changeRatingDieta;
        vm.changeRatingReceta = changeRatingReceta;

        vm.dietas = vm.dietasAPI;
        vm.recetas = vm.recetasAPI;

        ////////////////

        function changeRatingDieta(dieta){
            if(vm.ratingsDietas[dieta.id] !== undefined && cambiarDieta){
                console.log('Cambiando rating dieta');
                console.log(dieta);
                console.log(vm.ratingsDietas[dieta.id]);
                cambiarDieta = false;
                var data = {
                    username: usuario,
                    idDieta: dieta.id,
                    valoracion: vm.ratingsDietas[dieta.id]
                };

                $http.post("/api/dietasHistorial", data).then(
                    function (response) { //success
                        AlertService.addAlert('success','Exito: Se ha actualizado la valoracion de la dieta ' +
                        dieta.title + ' a ' + vm.ratingsDietas[dieta.id]);
                    },
                    function (response) { //error
                        AlertService.addAlert('danger', 'Error al cambiar la valoracion de la dieta ' + dieta.title);
                    }
                );
            }
            else {
                cambiarDieta = true;
            }
        }

        function changeRatingReceta(receta){
            if(vm.ratingsRecetas[receta.id] !== undefined && cambiarReceta){
                console.log('Cambiando rating receta');
                console.log(receta);
                console.log(vm.ratingsRecetas[receta.id]);
                cambiarReceta = false;
                var data = {
                    username: usuario,
                    idReceta: receta.id,
                    valoracion: vm.ratingsRecetas[receta.id]
                };

                $http.post("/api/recetasHistorial", data).then(
                    function (response) { //success
                        AlertService.addAlert('success','Exito: Se ha actualizado la valoracion de la receta ' +
                            receta.title + ' a ' + vm.ratingsRecetas[receta.id]);
                    },
                    function (response) { //error
                        AlertService.addAlert('danger', 'Error al cambiar la valoracion de la receta ' + receta.title);
                    }
                );
            }
            else {
                cambiarReceta = true;
            }
        }

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
                if((vm.dietasAPI[i].calories <= query || vm.dietasAPI[i].title === query || (vm.dietasAPI[i].content.toLowerCase().search(query.toLowerCase()) != -1) ||
                    vm.dietasAPI[i].title.toLowerCase().startsWith(query.toLowerCase()))){
                    vm.dietas.push(vm.dietasAPI[i]);
                }
            }
            for(var j = 0; j < vm.recetasAPI.length; j++){
                if((vm.recetasAPI[j].calories <= query || vm.recetasAPI[j].title === query || (vm.recetasAPI[j].content.toLowerCase().search(query.toLowerCase()) != -1) ||
                    vm.recetasAPI[j].title.toLowerCase().startsWith(query.toLowerCase()))){
                    vm.recetas.push(vm.recetasAPI[j]);
                }
            }
        }
    }

})();

