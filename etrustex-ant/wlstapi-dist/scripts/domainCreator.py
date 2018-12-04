import wlstModule as wlst
from eu.cec.digit.wlst.domain import DomainBasic
from eu.cec.digit.wlst.domain import Domain

from eu.cec.digit.wlst.jdbc import JdbcBasic
from eu.cec.digit.wlst.jdbc import JdbcDatasourceParams
from eu.cec.digit.wlst.jdbc import JdbcConnectionPoolParams
from eu.cec.digit.wlst.jdbc import DBOperation

from eu.cec.digit.wlst.server import AdminServer
from eu.cec.digit.wlst.server import Server
from eu.cec.digit.wlst.server import Machine
from eu.cec.digit.wlst.server import Cluster

from eu.cec.digit.wlst.security import AdminUser
from eu.cec.digit.wlst.security import User

from eu.cec.digit.wlst.utils import PropertyManager
from eu.cec.digit.wlst.utils import Utils
from eu.cec.digit.wlst.utils import WlsUtilities
from eu.cec.digit.java.utils import BuildInfo
from eu.cec.digit.wlst.utils import Logger

from eu.cec.digit.wlst.tools import Importer

# Java import
from java.lang import Exception

# Jython import
import sys
import getopt

def createDomain(propertyFile):
    properties = PropertyManager.getPropertyManager(propertyFile)
    Logger.getLogger().configure(properties.getProperty('script.log.level'), properties.getProperty('script.log.file'))
    Utils.logInfo("WLST-API version %s running script [%s]" % (BuildInfo.getVersionAndBuild(), "domainCreator.py"))
    Utils.logInfo("Running with Weblogic version %s located at [%s]" % (WlsUtilities.getWeblogicVersion(), WlsUtilities.getWLHOME()))   
    
    try:
        #-------------------------------
        # Backup domain recommended action
        #---------------------------------
        if (properties.getProperty('domain.backup.enable') == 'true'):
            DomainBasic.backupDomain(properties.getProperty('domain.location'),
                                               properties.getProperty('domain.backup.location'),
                                               properties.getProperty('domain.backup.file.prefix'))    
                                           
        ###########################################################
        #                        STEP 1
        # - Create domain
        # - Set default admin password
        # - Configure a simple datasource used to load data
        ###########################################################
                            
        # Create the domain from template
        #  Specify weblogic/weblogic as user password since it may be updated when adding templates
        #---------------------------------
        topologyProfile = properties.getPropertyEx('domain.template.topology.profile', None)        
        if(DomainBasic.createDomainFromCustomTemplate(properties.getPropertyEx('domain.name',None), properties.getPropertyEx('domain.template.location',None), topologyProfile)):
            # Retrieve the name of the administration server
            myAdminServer=properties.getPropertyEx('admin.server.name',None)

            ###########################################################
            #                        STEP 2
            # - Configure the domain
            ###########################################################
                    
            # Server start mode
            Domain.setStartMode(properties.getPropertyEx('domain.production.mode',None))
            
            # Domain administration
            if properties.getPropertyEx('domain.administration.enable','false') == 'true':
                Domain.configureAdministration(properties.getPropertyEx('domain.name', None), 
                                               properties.getPropertyEx('domain.administration.mbean.auditing.enable', None), 
                                               properties.getPropertyEx('domain.administration.port', None), 
                                               properties.getPropertyEx('domain.administration.port.enable', None), 
                                               properties.getPropertyEx('domain.administration.protocol', None))
                
            ###########################################################
            #                        STEP 3
            # - Configure Administration server
            ###########################################################
    
            #Rename admin server with proper name
            # Default admin server is AdminServer 
            AdminServer.rename('AdminServer', myAdminServer)        
            
            # Change listen address and port for TCP
            AdminServer.setListenAddress(myAdminServer, 
                                                properties.getPropertyEx('admin.server.host',None), 
                                                properties.getPropertyEx('admin.server.port',None), 
                                                'false')            
            
            # Configure the SSL listen port if needed
            if(properties.getPropertyEx('admin.server.ssl.enable','false') == 'true'):
                # By default SSL support is not activated
                AdminServer.createSSLServer(myAdminServer)
                AdminServer.setListenSSLPort(myAdminServer,properties.getPropertyEx('admin.server.ssl.listen.port',None))
                    
    
            # Configure log support
            if(properties.getPropertyEx('admin.server.log.enable','false') == 'true'):
                AdminServer.createLogResource(myAdminServer,properties.getPropertyEx('admin.server.log.severity',None))                    
    
                logFilename = properties.getPropertyEx('admin.server.log.file.name',None)
                if(not Utils.empty(logFilename)):
                    AdminServer.setLogFilename(myAdminServer,logFilename)

                # Configure log file rotation
                if(not Utils.empty(properties.getPropertyEx('admin.server.log.rotation.enable',None))):
                    # NumberOfFilesLimited: This property exists to force to enable rotation which is by default set to false in production mode and true in development mode
                    AdminServer.setLogRotationNumberOfFilesLimited(myAdminServer, properties.getPropertyEx('admin.server.log.rotation.enable',None))

                AdminServer.setLogRotationType(myAdminServer,
                                                      properties.getPropertyEx('admin.server.log.rotation.type',None), 
                                                      properties.getPropertyEx('admin.server.log.rotation.data',None))
                # Log rotation file count
                rotationCount = properties.getPropertyEx('admin.server.log.rotation.filecount',None)
                if(not Utils.empty(rotationCount)):
                    AdminServer.setLogRotationFileCount(myAdminServer,rotationCount)
                    
                # Rotation on startup
                onStartup = properties.getPropertyEx('admin.server.log.rotation.onstartup',None)
                if(not Utils.empty(onStartup)):
                    AdminServer.setLogRotationOnStartup(myAdminServer,onStartup)                    
            
            # Configure webserver
            if(properties.getPropertyEx('admin.server.webserver.enable','false') == 'true'):
                AdminServer.createWebServer(myAdminServer,properties.getPropertyEx('admin.server.webserver.log.enable',None))
                if(properties.getPropertyEx('admin.server.webserver.log.enable','false') == 'true'):
                    logFilename = properties.getPropertyEx('admin.server.webserver.log.file.name',None)
                    if(not Utils.empty(logFilename)):
                        AdminServer.setWebLogFilename(myAdminServer,logFilename)
                    # Configure log file rotation
                    # NumberOfFilesLimited: This property exists to force to enable rotation which is by default set to false in production mode and true in development mode
                    AdminServer.setWebLogRotationNumberOfFilesLimited(myAdminServer, properties.getPropertyEx('admin.server.webserver.log.rotation.enable','false'))
                    AdminServer.setWebLogRotationType(myAdminServer,
                                                          properties.getPropertyEx('admin.server.webserver.log.rotation.type',None), 
                                                          properties.getPropertyEx('admin.server.webserver.log.rotation.data',None))
                    # Log rotation file count
                    rotationCount = properties.getPropertyEx('admin.server.webserver.log.rotation.filecount',None)
                    if(not Utils.empty(rotationCount)):
                        AdminServer.setWebLogRotationFileCount(myAdminServer,rotationCount)
                    # Rotation on startup
                    onStartup = properties.getPropertyEx('admin.server.webserver.log.rotation.onstartup',None)
                    if(not Utils.empty(onStartup)):
                        AdminServer.setWebLogRotationOnStartup(myAdminServer,onStartup)
    
            #  Configuration of the NodeManager
            if(properties.getPropertyEx('admin.server.serverstart.enable','false') == 'true'):            
                # Create the server start object
                Server.createServerStart(myAdminServer)
                # Configure server start
                beaHome = properties.getPropertyEx('bea.home',None)
                username = properties.getPropertyEx('admin.server.serverstart.username',None)
                password = properties.getPropertyEx('admin.server.serverstart.password',None)
                classpathServer = properties.getPropertyEx('admin.server.serverstart.classpath',None)
                javaHome = properties.getPropertyEx('admin.server.serverstart.javahome',None)
                javaVendor = properties.getPropertyEx('admin.server.serverstart.javavendor',None)
                argumentsServer = properties.getPropertyEx('admin.server.serverstart.arguments',None)
                securityPolicyFile = properties.getPropertyEx('admin.server.serverstartpolicyfile',None) 
                rootDir=properties.getPropertyEx('admin.server.serverstart.rootdir',None)
    
                Server.configureServerStart(myAdminServer,
                                                   beaHome,
                                                   rootDir,
                                                   username,
                                                   password,
                                                   securityPolicyFile,
                                                   classpathServer,
                                                   javaHome,
                                                   javaVendor,
                                                   argumentsServer)
            
            ###########################################################
            #                        STEP 4
            # - Configure administrator
            ###########################################################
            
            # Change the user name and password for the administrator server
            # Default user is weblogic
            AdminUser.rename('weblogic',properties.getPropertyEx('admin.server.security.username',None))                
    
            # Set the administrator password
            AdminUser.setPassword(properties.getPropertyEx('admin.server.security.username',None), properties.getPropertyEx('admin.server.security.password',None))
            
            ###########################################################
            #                        STEP 5
            # - Save domain
            # - close domain template
            ###########################################################
                        
            # Save domain
            DomainBasic.saveDomainConfiguration(properties.getPropertyEx('domain.location',None),'true')
        
            # Close template
            DomainBasic.closeTemplate()
            
            ###########################################################
            #                        STEP 6
            # - Adding template
            ###########################################################
            # Read domain
            DomainBasic.readDomain(properties.getPropertyEx('domain.location',None))
    
            # Set the nodemanager username/password
            if(properties.getPropertyEx('domain.nodemanager.security.enable','false') == 'true'):
                Domain.setNodeManagerConfiguration(properties.getPropertyEx('domain.name', None), properties.getPropertyEx('domain.location',None), properties.getPropertyEx('domain.nodemanager.security.username',None), properties.getPropertyEx('domain.nodemanager.security.password',None))                

            schemes = properties.getPropertyEx('domain.template.additional.scheme',None)
            
            if (not Utils.empty(schemes)):
                if(DomainBasic.addingOptionsToDomain(schemes,
                                                       properties.getPropertyEx('domain.template.replaceduplicate','false'),
                                                       properties.getPropertyEx('domain.template.appdir',None))):
                    
                    ###########################################################
                    #                        STEP 6.1 
                    # - Create database schema needed by the server
                    ###########################################################
    
                    # This is done to have a workaround for p13n solution
                    DBOperation.loadDatabaseScript(properties.getPropertyEx('domain.template.additional.scheme',None),
                                                   properties.getPropertyEx('domain.jdbc.script.loader.dbversion',None))

                wlst.dumpStack()
            
            if topologyProfile == "Compact":
                # disable use of Derby database (undocumented option... thx Oracle !)
                Utils.logDebug("Disabling use of sample (Derby) database")
                wlst.setOption('UseSampleDatabase', 'false') # can only be used with 'Compact' topology profile

            ###########################################################
            #                        STEP 7
            # - Configure machines
            # - Configure managed server
            # - Configure cluster
            # - Configure JDBC resources
            ###########################################################
            processMachines(properties) 
            processServers(properties)
            Importer.createCluster()
            Importer.setDriverParameter()
            
            ###########################################################
            #                         STEP 7.1
            # - if JRF template was added, check to see if the JRF libraries must be deployed to a cluster
            ###########################################################
            if (DomainBasic.hasJRFTemplate(schemes)):
                fixJRFDeployment(properties)

        # Set production mode
        Domain.setProductionMode(properties.getPropertyEx('domain.production.mode',None))

        # force creation of boot.properties for Prod domains
        if (properties.getPropertyEx('domain.production.mode','false') == 'true'):
            createBootProperties(properties)
    
        # Dump domain configuration to the file system                
        DomainBasic.updateDomain()
        wlst.dumpStack()

        # Close domain
        DomainBasic.closeDomain()
        Utils.logInfo('Domain [' + properties.getPropertyEx('domain.name',None) + '] created...')
        Utils.logInfo('Before configuring deeply your domain, start/stop the administration server....')

    except Exception,e:
        WlsUtilities.disconnectOnException("read",e)
    except TypeError,e:
        WlsUtilities.disconnectOnException("read",e)
    except NameError,e:
        WlsUtilities.disconnectOnException("read",e)
    except AttributeError,e:
        WlsUtilities.disconnectOnException("read",e)
    except SyntaxError,e:
        WlsUtilities.disconnectOnException("read",e)
        
def processMachines(properties):
    machines = Utils.zeroint(properties.getPropertyEx('machine.items', '0'))
    for index in range(machines):
        if (not properties.hasProperty('machine.%d.name' % index)):            
            Utils.logWarning('machine.%d.name'%index +' does not exist, range is skipped')
        else:
            (machineName, renameFrom, isUnix, listenAddress, listenPort) = (
                properties.getPropertyEx('machine.%d.name'%index,None),
                properties.getPropertyEx('machine.%d.rename.from' % index, None),
                properties.getPropertyEx('machine.%d.type.unix'%index,None),
                properties.getPropertyEx('machine.%d.node.manager.listen.address'%index,None),
                properties.getPropertyEx('machine.%d.node.manager.listen.port'%index,None))
            if (renameFrom == None):
                if (not Machine.create(machineName, isUnix, listenAddress, listenPort)):
                    raise Exception('Unable to create machine resource')
            else:
                if (not Machine.rename(renameFrom, machineName)):
                    raise Exception('Unable to rename machine')
                if (not Machine.configure(machineName, listenAddress, listenPort)):
                    raise Exception('Unable to configure machine')

def processServers(properties):
    managedServers = Utils.zeroint(properties.getPropertyEx('managed.server.items','0'))
    for index in range(managedServers):
        wlServerName = properties.getPropertyEx('managed.server.%d.name'%index,None)
        wlServerRenameFrom = properties.getPropertyEx('managed.server.%d.rename.from'%index,None)
        address = properties.getPropertyEx('managed.server.%d.listen.address'%index,None)
        port = properties.getPropertyEx('managed.server.%d.listen.port'%index,None)
        sslPort = properties.getPropertyEx('managed.server.%d.ssl.listen.port'%index,None)
        sslEnabled = properties.getPropertyEx('managed.server.%d.ssl.enable'%index,'true')
        serverGroups = properties.getPropertyEx('managed.server.%d.servergroups'%index,None)
        if wlServerRenameFrom == None and not Server.exists(wlServerName):
            Server.createManagedServer(wlServerName, address, port, sslPort, sslEnabled, serverGroups)
        else:
            if not Server.updateManagedServer(wlServerName, wlServerRenameFrom, address, port, sslPort, sslEnabled, serverGroups):
                raise Exception('Unable to rename server from [%s] to [%s]' % (wlServerRenameFrom, wlServerName))
        # Machine 
        machineName = properties.getPropertyEx('managed.server.%d.machine.name'%index,None)
        if(not Utils.empty(machineName)):
            Server.assignMachine(wlServerName,machineName)

        #  Configuration of the NodeManager
        if(properties.getPropertyEx('managed.server.%d.serverstart.enable'%index,'false') == 'true'):
            
            # Create the server start object
            Server.createServerStart(wlServerName)
            
            # Configure server start
            beaHome = properties.getPropertyEx('bea.home',None)
            username = properties.getPropertyEx('managed.server.%d.serverstart.username'%index,None)
            password = properties.getPropertyEx('managed.server.%d.serverstart.password'%index,None)
            classpathServer = properties.getPropertyEx('managed.server.%d.serverstart.classpath'%index,None)
            javaHome = properties.getPropertyEx('managed.server.%d.serverstart.javahome'%index,None)
            javaVendor = properties.getPropertyEx('managed.server.%d.serverstart.javavendor'%index,None)
            argumentsServer = properties.getPropertyEx('managed.server.%d.serverstart.arguments'%index,None)
            securityPolicyFile = properties.getPropertyEx('managed.server.%d.serverstart.policyfile'%index,None) 
            rootDir=properties.getPropertyEx('managed.server.%d.serverstart.rootdir' %index,None)

            Server.configureServerStart(wlServerName,
                                               beaHome,
                                               rootDir,
                                               username,
                                               password,
                                               securityPolicyFile,
                                               classpathServer,
                                               javaHome,
                                               javaVendor,
                                               argumentsServer)
                
        # Log configuration
        if(properties.getPropertyEx('managed.server.%d.log.enable'%index,'false') == 'true'):
            Server.createLogResource(wlServerName,properties.getPropertyEx('managed.server.%d.log.severity'%index,None))
            logFilename = properties.getPropertyEx('managed.server.%d.log.file.name'%index,None)
            if(not Utils.empty(logFilename)):
                Server.setLogFilename(wlServerName,logFilename)
            # Configure log file rotation                    
            if(not Utils.empty(properties.getPropertyEx('managed.server.%d.log.rotation.enable'%index,None))):
                # NumberOfFilesLimited: This property exists to force to enable rotation which is by default set to false in production mode and true in development mode
                Server.setLogRotationNumberOfFilesLimited(wlServerName, properties.getPropertyEx('managed.server.%d.log.rotation.enable'%index,None))                    
            Server.setLogRotationType(wlServerName,
                                             properties.getPropertyEx('managed.server.%d.log.rotation.type'%index,None), 
                                             properties.getPropertyEx('managed.server.%d.log.rotation.data'%index,None))
            # Log rotation file count
            rotationCount = properties.getPropertyEx('managed.server.%d.log.rotation.filecount'%index,None)
            if(not Utils.empty(rotationCount)):
                Server.setLogRotationFileCount(wlServerName,rotationCount)                        
            # Rotation on startup
            onStartup = properties.getPropertyEx('managed.server.%d.log.rotation.onstartup'%index,None)
            if(not Utils.empty(onStartup)):
                Server.setLogRotationOnStartup(wlServerName,onStartup)
        # Configure webserver
        if(properties.getPropertyEx('managed.server.%d.webserver.enable'%index,'false') == 'true'):
            Server.createWebServer(wlServerName,properties.getPropertyEx('managed.server.%d.webserver.log.enable'%index,None))
            if(properties.getPropertyEx('managed.server.%d.webserver.log.enable'%index,'false') == 'true'):
                Server.setWebLogFilename(wlServerName,properties.getPropertyEx('managed.server.%d.webserver.log.file.name'%index,None))
                # Configure log file rotation
                if(not Utils.empty(properties.getPropertyEx('managed.webserver.%d.log.rotation.enable'%index,None))):
                    # NumberOfFilesLimited: This property exists to force to enable rotation which is by default set to false in production mode and true in development mode
                    Server.setLogRotationNumberOfFilesLimited(wlServerName, properties.getPropertyEx('managed.webserver.%d.log.rotation.enable'%index,None))                        
                Server.setWebLogRotationType(wlServerName,
                                                    properties.getPropertyEx('managed.server.%d.webserver.log.rotation.type'%index,None), 
                                                    properties.getPropertyEx('managed.server.%d.webserver.log.rotation.data'%index,None))
                # Log rotation file count
                rotationCount = properties.getPropertyEx('managed.server.%d.webserver.log.rotation.filecount'%index,None)
                if(not Utils.empty(rotationCount)):
                    Server.setWebLogRotationFileCount(wlServerName,rotationCount)                            
                # Rotation on startup
                onStartup = properties.getPropertyEx('managed.server.%d.webserver.log.rotation.onstartup'%index,None)
                if(not Utils.empty(onStartup)):
                    Server.setWebLogRotationOnStartup(wlServerName,onStartup)

def fixJRFDeployment(properties):
    Utils.logInfo("Fixing JRF Deployment")
    managedServerNames = []
    managedServers = Utils.zeroint(properties.getPropertyEx('managed.server.items','0'))
    for index in range(managedServers):
        managedServerNames.append(properties.getPropertyEx('managed.server.%d.name'%index,None))

    if (len(managedServerNames) > 0):
        domainLocation = properties.getProperty('domain.location')
        # applyJRF to clusters
        clusters = Utils.zeroint(properties.getPropertyEx('cluster.items','0'))
        for index in range(clusters):
            clusterName = properties.getPropertyEx('cluster.%d.name'%index,None)
            Utils.logDebug("applyJRF to %s" % clusterName)
            wlst.applyJRF(target=clusterName, domainDir=domainLocation, shouldUpdateDomain=0)
            # now remove managed beonging to cluster form the ones to process
            managedServers = properties.getPropertyEx('cluster.%d.managed.servers' % index,None)
            for srvName in managedServers:
                # remove it from server list
                managedServerNames.remove(srvName)
        
        # now process remaining managed servers that have not yet been processed (belonging to a cluster)
        for srvName in managedServerNames:
            Utils.logDebug("applyJRF to %s" % srvName)
            wlst.applyJRF(target=srvName, domainDir=domainLocation, shouldUpdateDomain=0)

def createBootProperties(properties):
    username = properties.getPropertyEx('admin.server.security.username', None)
    password = properties.getPropertyEx('admin.server.security.password', None)
    domainLocation = properties.getPropertyEx('domain.location', None)
    domainName = properties.getPropertyEx('domain.name', None)                                            
    User.updateBootProperties(username,password,domainName,domainLocation,create=1)

def main():
    # parse WLST-API command line
    propFiles = Utils.parseWlstApiCmdLine(sys.argv[1:], isOnlineMode=0)
    createDomain(propFiles)

main()