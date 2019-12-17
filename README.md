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
    
    
 ##Client side development
 - cd into js-app dir 
    ```sh 
    cd etrustex-app/etx-wacc-modules/etx-wacc-web/src/main/js-app/
    ```
 - run watch-and-copy script passing your WL deployment dir as argument
    ```sh
    ./watch-and-copy.sh "/c/Pgm/dev/app/servers/WL_12.1.3/user_projects/domains/ETRUSTEX_WEB/servers/AdminServer/tmp/_WL_user/etrustex-assembler-webapp_ear_exploded/4k6ml8/war" &
    ```
 - build js-app
    ```sh
    npm run build
    ```
  The watch-and-copy PID is printed after bundle files are copied
    
[pre_1_url]: <https://webgate.ec.europa.eu/fpfis/wikis/display/eUI/00.+Prerequisites#id-00.Prerequisites-Step1:installNodeJS&NPM>
[pre_2_url]: <https://webgate.ec.europa.eu/fpfis/wikis/display/eUI/00.+Prerequisites#id-00.Prerequisites-Step2:configureNPMtogothroughtheproxyandtousetheECNPMregistry>
