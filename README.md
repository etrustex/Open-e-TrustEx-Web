# Open e-Trustex web

## eTrustEx 
eTrustex is a project of the Directorate General for Informatics of the European Commission under the ISA2 Program. 
## e-Trustex Web
e-TrustEx Web is a Web application offered to Public Administrations at European, national and regional 
level to set up secure exchange of digital documents by human users via standardized interfaces.

## Main Features
- Reusable and customisable cross-sector and open-source platform of the Interoperability Solutions for Public Administrations (ISA) Programme that is operational since 2011 and will be continued under ISA2 Programme.
- It helps public administrations to securely exchange electronic documents (natively digital documents or scanned documents) and data with other public administrations.
- It uses standardised messaging protocols and interfaces (interoperable).
- It ensures a secure and trusted exchange of data/documents (integrity, authenticity, confidentiality)
- It can be installed as a tool (by public administrations - MS) and used as a service (by the EU institutions). 
- Users can share documents (structured and unstructured) up to 5 GB.
- Technical support for existing users and Member States willing to re-use the platform: this includes activities such as increasing the user request resolving time, support of deployment, integration, specifications of the technical interface and of the several components of the platform, debugging, testing, etc.


## Run with docker-compose
**Pre-requisites**: docker & docker-compose installed
1. Download and extract zip or clone this repository﻿
2. Download the right Node Release package and place it in Docker/open-source/files/node/. Make sure that the name of the package is 'etrustex.ear'
3. Download the right Web Release package﻿ and place it in Docker/open-source/files/deployments/
4. From Docker/open-source folder, run `docker-compose up`




## Developer's environment setup

### Prerequisites
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
    
    
 ### Client side development
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

## Build with maven
```shell script
mvn clean package -P webpack,wildfly
```
