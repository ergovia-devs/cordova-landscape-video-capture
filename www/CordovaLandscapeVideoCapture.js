var exec = require('cordova/exec');
var pluginName = 'CordovaLandscapeVideoCapture';

function CordovaLandscapeVideoCapture() {}

CordovaLandscapeVideoCapture.prototype.captureVideo = function(success, error, options) {
  var self = this;
  var win = function(result) {
    if (typeof result.progress !== 'undefined') {
      if (typeof options.progress === 'function') {
        options.progress(result.progress);
      }
    } else {
      success(result);
    }
  };
  exec(win, error, pluginName, 'captureVideo', [options]);
};

CordovaLandscapeVideoCapture.prototype.playVideo = function(success, error, options) {
  var self = this;
  var win = function(result) {
    if (typeof result.progress !== 'undefined') {
      if (typeof options.progress === 'function') {
        options.progress(result.progress);
      }
    } else {
      success(result);
    }
  };
  exec(win, error, pluginName, 'playVideo', [options]);
};

module.exports = new CordovaLandscapeVideoCapture();