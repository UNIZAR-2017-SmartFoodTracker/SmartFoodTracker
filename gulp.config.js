module.exports = function() {
    var client = './src/main/resources/static/';
    //var server = './src/server/';
    var clientApp = client + 'app/';
    //var report = './report/';
    var root = './';
    //var specRunnerFile = 'specs.html';
    var temp = './.tmp/';
    var wiredep = require('wiredep');
    var bowerFiles = wiredep({ devDependencies: true })['js'];
    var bower = {
        json: require('./bower.json'),
        directory: './src/main/resources/static/bower_components/',
        ignorePath: ''
    };

    var config = {
        /**
         * File paths
         */
        // all javascript that we want to vet
        alljs: [
            './src/**/*.js',
            './*.js'
        ],
        build: './target/classes/static/',
        client: client,
        css: temp + 'styles.css',
        fonts: bower.directory + 'font-awesome/fonts/**/*.*',
        glyphicon: bower.directory + 'bootstrap/fonts/**/*.*',
        html: client + '**/*.html',
        htmltemplates: clientApp + '**/*.html',
        images: client + 'images/**/*.*',
        index: client + 'index.html',
        // app js, with no specs
        js: [
            clientApp + '**/*.module.js',
            clientApp + '**/*.js',
            '!' + clientApp + '**/*.spec.js'
        ],
        jsOrder: [
            '**/app.module.js',
            '**/*.module.js',
            '**/*.js'
        ],
        less: client + 'styles/styles.less',
        root: root,
        source: 'src/',
        stubsjs: [
            bower.directory + 'angular-mocks/angular-mocks.js',
            client + 'stubs/**/*.js'
        ],
        temp: temp,

        /**
         * optimized files
         */
        optimized: {
            app: 'app.js',
            lib: 'lib.js'
        },

        /**
         * template cache
         */
        templateCache: {
            file: 'templates.js',
            options: {
                module: 'app.core',
                root: 'app/',
                standalone: false
            }
        },

        /**
         * Bower and NPM files
         */
        bower: bower,
        packages: [
            './package.json',
            './bower.json'
        ]
    };

    /**
     * wiredep and bower settings
     */
    config.getWiredepDefaultOptions = function() {
        var options = {
            bowerJson: config.bower.json,
            directory: config.bower.directory,
            ignorePath: config.bower.ignorePath
        };
        return options;
    };


    return config;
};
