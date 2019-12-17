@echo off
rem This command generates static documentation file for REST specification contained in Swagger file
rem This bat should be executed manually when update of documentation file is needed (following REST apecification changes)
rem Swagger-codegen-cli tool from Swagger is used in order to generate documentation (can also be found in Maven repository)
rem -i argument signifies input specification file
rem -l argument signifies destination language used for generation (in case of documentation, should be html, but other languages are supported)
rem -o argument specifies the output folder where generated files should be placed
rem -c argument specifies the configuration to be used by the generator
rem -v enable verbose mode

rem Link to the Swagger Codegen project : https://github.com/swagger-api/swagger-codegen
rem To get a specific version from maven repository use wget, like:
rem     wget http://central.maven.org/maven2/io/swagger/swagger-codegen-cli/2.2.2/swagger-codegen-cli-2.2.2.jar -O swagger-codegen-cli.jar

@echo on
java -jar swagger-codegen-cli.jar generate -i etrustex_mobile_service_api_v.1.0.yaml -l spring -o generated/mock-ws -c generator-config.json