var angular = require('angular');

var hello = require('./app/hello');
require('angular-ui-router');
require('angucomplete-alt');



var routesConfig = require('./routes');

require('./index.scss');

var app = 'app';
module.exports = app;

angular
  .module(app, ['ui.router', 'angucomplete-alt'])
  .config(routesConfig)
  .component('app', hello);
