/**
 * Register hooks for 'ionic serve' and 'ionic build'. This requires gulp and @ionic/cli-plugin-gulp plugin to be installed locally.
 *
 * The hooks are to be used for dynamically generating the configuration file of the app, based on selected environment.
 * The content is automatically generated when executing either
 *    'ionic server --env=<env>'
 * or something like
 *    'ionic build --prod --env=<env>'
 * If not environment is specified the default one is local.
 *
 * Sample configuration file: src/env/env.configuration.ts.sample
 * Generated configuration: src/env/env.configuration.ts
 * JSON files with config for each environment: etc/env/<env>.json
 *
 * @type {Gulp}
 */
const gulp = require('gulp');
const path = require('path');
const process = require('process');
const fs = require('fs-extra');

// hooks for ionic cli when executing 'ionic serve'
gulp.task('ionic:build:before', copyEnvironmentFile);
gulp.task('ionic:watch:before', copyEnvironmentFile);

// task to be called from package.json as npm hook for 'mvn:build' and 'mvn:prod:build'
gulp.task('copyEnvironmentFile', copyEnvironmentFile);

/**
 * @param args The process arguments as an array
 * @return {string} The identified environment
 */
function getEnvironment(args) {
  const defaultEnv = 'local'; //Set default environment

  // environment can be specified as '--env=<env>' or '--env <env>'
  const envArg = args.find(arg => arg.indexOf('--env=') === 0);
  if (envArg) {
    env = envArg.split('=')[1];
  } else {
    let index = args.indexOf('--env');
    env = (index > -1) ? args[index+1] : defaultEnv;
  }
  return env;
}

/**
 * Copy app configuration file based on selected environment.
 */
function copyEnvironmentFile() {
  const env = getEnvironment(process.argv);
  console.log(`Selected Ionic environment is: ${env}`);

  // read config from identified environment
  const envSrc = `./etc/env/${env }.json`;
  var envVars;
  try {
    envVars = require(envSrc);
  } catch(e) {
    throw new Error(`Could not read config file '${envSrc}' for environment '${env}'! Details: ${e}`);
  }
  const ENV = Object.assign(envVars, {
    env: env,
    __now__: new Date()
  });

  // read sample content and replace tokens with environment data
  const sampleSrc = './src/env/configuration.ts.sample';
  var content;
  try {
    content = fs.readFileSync(sampleSrc);
  } catch(e) {
    throw new Error(`Could not read sample config file '${sampleSrc}'! Details: ${e}`);
  }
  var contentReplace = content.toString()
    .replace(/ENV.__now__/, JSON.stringify(ENV.__now__))
    .replace(/ENV.env/, JSON.stringify(ENV.env))
    .replace(/ENV.ctx/, JSON.stringify(ENV.ctx))
    .replace(/ENV.apiBaseUrl/, JSON.stringify(ENV.apiBaseUrl))
    .replace(/ENV.useFakeIdentity/, ENV.useFakeIdentity)
    .replace(/ENV.reportErrors/, ENV.reportErrors)
    .replace(/ENV.ecasUrl/, JSON.stringify(ENV.ecasUrl))
    .replace(/ENV.version/, JSON.stringify(ENV.version))
    ;

  // generate new configuration file for selected environment
  const dst = './src/env/configuration.ts';
  try {
    var wstream = fs.createWriteStream(dst);
    wstream.write(contentReplace);
    wstream.end();
    console.log(`Generated config for env '${env}' to ${dst}`);
  } catch (e) {
    throw new Error(`The config file for environment '${env}' could not be copied! Details: ${e}`);
  }
}
