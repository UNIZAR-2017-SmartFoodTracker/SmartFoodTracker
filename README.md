# Smart Food Tracker

## Pasos previos para trabajar en la aplicación usando IntelliJ
1. Copiar la carpeta runConfigurations dentro de la carpeta .idea.

## Aportar al proyecto
1. Hacer un fork del proyecto
2. Hacer un clone del fork
3. Configurar el upstream al de la organización `git remote add upstream https://github.com/UNIZAR-2017-SmartFoodTracker/SmartFoodTracker.git`
4. Una vez realizado el git remote add, se debe ejecutar `git fetch` y `git merge` para igualar tu proyecto local al repositorio general.
5. Tras realizar algún cambio y tras haber hecho push al repositorio del fork, solicitar un `pull request` al repositorio de la organización.

### F.A.Q.
1. Nada más crear el proyecto y/o cuando se creen nuevas dependencias tanto en el fichero package.json o bower.json, ejecutar el setup "Instalar dependencias".
2. Cuando se esté en desarrollo en local en Intellij ejecutar siempre el setup "Ejecutar Spring".
3. Si por algún motivo se tuviese que borrar la configuración de node/bower en el proyecto, borrar los siguientes directorios:
  3.1. node_modules.
  3.2. node.
  3.3. src/main/resources/static/bower_components.
4. Para limpiar el proyecto en la carpeta de salida, ejecutar la operación de maven "clean".
5. Una vez instaladas las dependencias se debe crear una base de datos de nombre 'food', además de un usuario "food" con contraseña "food" y permisos: 
  CREATE DATABASE food;
  CREATE USER 'food'@localhost' IDENTIFIED BY 'food';
  GRANT ALL PRIVILEGES * . * TO 'food'@'localhost';
