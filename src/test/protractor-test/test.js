describe('test protractor', function() {
    it('introducir pesos manualmente', function() {
        browser.get('http://localhost:8080/');

        //Login
        element(by.linkText("Acceso")).click();
        element(by.id("username")).sendKeys("userTest");
        element(by.id("password")).sendKeys("1234");
        element(by.css("button.btn.btn-primary")).click().then(function(){
            browser.getCurrentUrl().then(function (url) {
                if (url == "http://localhost:8080/") { //Comprueba si existe el usuario en la BBDD
                    console.log("No existe usuario test en la BBDD, registrando...");

                    //Registro
                    element(by.css("button.close")).click();
                    element(by.linkText("Regístrate")).click();
                    element(by.model("vm.username")).sendKeys("userTest");
                    element(by.model("vm.email")).sendKeys("test@test.com");
                    element(by.model("vm.nombre")).sendKeys("Selenium");
                    element(by.model("vm.apellidos")).sendKeys("Test");
                    element(by.model("vm.password")).sendKeys("1234");
                    element.all(by.model("vm.aviso")).get(1).click();
                    element(by.buttonText("Regístrate")).click();

                    //Login
                    element(by.linkText("Acceso")).click();
                    element(by.id("username")).sendKeys("userTest");
                    element(by.id("password")).sendKeys("1234");
                    element(by.css("button.btn.btn-primary")).click()
                }

                console.log("Existe usuario test en la BBDD, continuando...");

                // Añadir pesos
                element(by.model("vm.peso")).sendKeys("35");
                element(by.buttonText("Añadir peso")).click();
                element(by.model("vm.peso")).clear();
                element(by.model("vm.peso")).sendKeys("75");
                element(by.buttonText("Añadir peso")).click();

                //Cerrar sesión
                element(by.id("logout")).click();
            });
        });
    });
});

