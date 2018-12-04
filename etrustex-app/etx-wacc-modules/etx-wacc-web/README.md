# Developer's environment setup


## Prerequisites
 - [nodejs & npm][pre_1_url] 
 
 - [configure NPM to go through the proxy and to use the EC NPM registry][pre_2_url]
     ```sh
     npm config set https-proxy http://username:password@ps-bxl-usr.cec.eu.int:8012
     npm config set registry http://diplazium.cc.cec.eu.int:8081/repository/npm-all/
    ```
 - Install webpack && webpack-cli
     ```sh
      npm install -D webpack
      npm install -D webpack-cli
     ```
     If it tries to access github via ssh. Ste the following git properties:
     ```sh
       git config --global url."https://github.com/".insteadOf git@github.com:
       git config --global url."https://".insteadOf git://
      ```
    
    
    
    
    
[pre_1_url]: <https://webgate.ec.europa.eu/fpfis/wikis/display/eUI/00.+Prerequisites#id-00.Prerequisites-Step1:installNodeJS&NPM>
[pre_2_url]: <https://webgate.ec.europa.eu/fpfis/wikis/display/eUI/00.+Prerequisites#id-00.Prerequisites-Step2:configureNPMtogothroughtheproxyandtousetheECNPMregistry>
