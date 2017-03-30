exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['test.js'],
    capabilities: {
        'browserName': 'chrome',
        'chromeOptions': {
            'args': ['no-sandbox']
        }
    }, jasmineNodeOpts: {
            defaultTimeoutInterval: 80000
    }
};