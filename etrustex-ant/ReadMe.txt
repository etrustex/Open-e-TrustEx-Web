Configure an environment (DEV, LOCAL, TEST, LOAD, PROD etc...)

Prerequisites:
* Java 1.7 (system variable JAVA_HOME should be configured)
* Ant 1.8.2 (system variable ANT_HOME should be configured)
* Add binary location for ANT and Java to the system variable PATH
* Weblogic 10.3.4 (a local domain with name ETRUSTEX_WEB should be created)

1) Configure LOCAL environment

1.1) change environment.properties file by uncomment line etrustex.environment = your environment; the other lines should be comment with #; Ex: etrustex.environment=op-acc. See the environemnt posible values in the file mentioned.
     Let's replace etrustex.environment value as ${env} for the rest of document.
1.2) check and update config/config.${env}.properties file appropriately; db.adapter.user,db.adapter.password, db.web.user and db.web.password should be adjusted with the values reserved; Adapter and Web will share the same user and password till DC will create separated database users.

1.3) check and update scripts/weblogic/domain.${env}.properties file appropriately; 
- domain.connect.username and domain.connect.password should be provided;
- db.adapter.user, db.adapter.password, db.web.user and db.web.password should be adjusted with the values reserved;

1.4) Ant Tasks related to DB 
1.4.1) to drop the database schema for both Adapter and Web run ant db-drop command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-drop

1.4.2) to create (without prior drop the schema) the database schema for both Adapter and Web run ant db-create command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-create

1.4.3) to recreate (drop and create the schema) the database schema for both Adapter and Web run ant db-recreate command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-recreate

1.4.4) to drop the database schema for Adapter run ant db-adapter-drop command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-adapter-drop

1.4.5) to create (without prior drop the schema) the database schema for Adapter run ant db-adapter-create command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-adapter-create

1.4.6) to recreate (drop and create the schema) the database schema for Adapter run ant db-adapter-recreate command in command line.
EX: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant>ant db-adapter-recreate
1.4.7) drop, create or recreate the database schema for Web is similar with commands for the Adapter; in the name of the command "adapter" should be replaced with "web";


1.5) Ant Tasks related to WEBLOGIC 
1.5.1) open jlauncher.cmd ((from etrustex-ant/wlstapi-dist/bin)) and set your ${env} WL_HOME (weblogic home) and save the file;
Ex: set WL_HOME=D:/Oracle/Middleware/wlserver_10.3/
1.5.2) to configure the ${env} weblogic domain (ETRUSTEX_WEB) resources, run conf-${env}-env.cmd (from etrustex-ant/wlstapi-dist/bin) in command line;
Ex: D:\EclipseWorkspace\etrustExWork-New\etrustex\etrustex-ant\wlstapi-dist\bin\>conf-${env}-env.cmd
1.5.3) Normally the Weblogic should be configured and up-and-running, but I recommand to restart the weblogic server;
1.5.4) Check in Weblogic Server console (http://host:${port}/console) if all JDBC datasources, JMSServers, Queues .. etc are in place and up and running;
1.5.5) if something goes wrong undo the last configuration by runing again the step 1.5.2 and then restart the server; check again Prerequisites, steps 1.1 and 1.3 and after that run again step 1.5.2 and 1.5.3
