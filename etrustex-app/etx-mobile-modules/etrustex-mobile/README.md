# eTrustEx Mobile app

## General

* This project is based on Ionic 3.x for building the mobile web interface for eTrustEx.
* RESTful web services are used for getting the needed data from the web component.
* It is integrated with maven for generating a zip artifact to be included as dependecy an unziped inside the real web project
* A mock project (etrustex-mock-ws) is used for keeping the development of the mobile UI separated from the web component providing the REST web services 
* Temporary demo available via [MOBIMEET ACC] (https://webgate.acceptance.ec.europa.eu/mobimeet)

Important files and folders:
* package.json - defines the node modules and dev dependencies needed for building and running the project
* package-lock.json - automatically generated for any operations where npm modifies either the node_modules tree, or package.json. Should be kept in the source repository.
* ionic.config.json - ionic specific file. Useful for defining the proxy to be used when developing in stand alone mode
* pom.xml - standalone maven file for building the artifact etrustex-mobile-xxx.zip
* assembly_dep.xml - used by maven (via pom.xml) to define what gets inside the zip file 
* env folder - stores the json files with the data used by gulpfile.js to generate the app configuration file when building the maven artifact or using `ionic serve`   
* gulpfile.js - hooks to be used for generating _src\etc\configuration.ts_ file used by the mobile app at runtime
* src\etc\configuration.ts.sample - template file used by scripts in gulpfile.js to generate the configuration file


## Building the maven artifact

Building the maven artifact is needed when the development is done and the updated UI is to be included in the main war file. 

From command line run `mvn clean install -P dev`

The command will build a zip file and save it to the local maven repository.

Via the maven profile (e.g. dev), one selects the json file from _env_ folder to be used. 
Changing the selected json file can be done via:
* script entry specified via maven property _npm.build.cmd_ for selected maven profile (see pom.xml <profiles/> section) 
  E.g. _mvn:prod:build_ or _mvn:build_ 
* command associated to script entry in _package.json_.
  E.g. `ionic-app-scripts build` or `ionic-app-scripts build --prod` 


## Developing in stand alone mode

Following tools are needed to be installed before using this mode:
* node and npm 
  * See [Node JS web site](https://nodejs.org/en/download/)
  * tested with node 6.11.x and npm 5.3.0, newer version should work too 
* ionic cli 
  * see [Ionic Framework site] (https://ionicframework.com/getting-started/)
  * Installed from admin console via `npm install -g ionic` 
* Gulp
  * Installed from admin console via `npm install -g gulp`  
  
*NOTE:* 
* The first time the project is checked out, the developer should run `npm install` from command line to install the node modules dependencies (folder _node-modules_ is to be created)
* Every time another developer updates the dependencies in _package.json_ file, one should run `npm update` to update its local dependencies in node_modules       

Start the local server via command `ionic serve` . This should start the server on localhost:8100 and open a link to the app in the default browser. 
 
 
  

