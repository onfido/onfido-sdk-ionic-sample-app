
var exec = require('cordova/exec');

var PLUGIN_NAME = 'MyCordovaPlugin';

var MyCordovaPlugin = {
  launchFido: function(cb) {
    exec(cb, null, PLUGIN_NAME, 'launchFido', []);
  }
};

module.exports = MyCordovaPlugin;
