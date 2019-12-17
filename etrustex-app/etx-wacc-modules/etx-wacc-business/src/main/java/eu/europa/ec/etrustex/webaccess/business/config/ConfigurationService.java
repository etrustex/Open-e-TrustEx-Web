package eu.europa.ec.etrustex.webaccess.business.config;

import eu.europa.ec.etrustex.webaccess.business.administration.CacheService;
import eu.europa.ec.etrustex.webaccess.business.facade.administration.CacheResetListener;
import eu.europa.ec.etrustex.webaccess.model.Config;
import eu.europa.ec.etrustex.webaccess.model.vo.RetentionMetadata;
import eu.europa.ec.etrustex.webaccess.persistence.ConfigurationDAO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The service class providing configuration table
 *
 * @author dzierma
 * @see Configuration
 */
@Service
@DependsOn("liquibase")
public class ConfigurationService implements CacheResetListener {

    private static Logger logger = Logger.getLogger(ConfigurationService.class);

    @Autowired
    private ConfigurationDAO configurationDAO;

    private Configuration configuration;

    @Autowired
    private CacheService cacheService;

    @PostConstruct
    protected void init() {
        reloadConfiguration();
        cacheService.registerCacheResetListener(this);
    }

    @Override
    public void onCacheReset() {
        reloadConfiguration();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    private void reloadConfiguration() {
        List<Config> cList;
        try {
            cList = configurationDAO.getConfiguration();
            configuration = buildConfiguration(cList);
        } catch (Exception e) {
            logger.fatal("System configuration preparation failed", e);
            throw new RuntimeException("System configuration preparation failed", e);
        }
    }

    private Configuration buildConfiguration(List<Config> configList) {

        Configuration config = new Configuration();

        fillDefaultValues(config);

        for (Config configEntity : configList) {
            logger.info("Handling configuration entry: " + configEntity);
            switch (configEntity.getName()) {
                case Configuration.CONFIG_DOC_WRAPPER_SERVICE_URI:
                    config.setDocumentWrapperServiceURI(configEntity.getValue());
                    break;
                case Configuration.CONFIG_NODE_DOCUMENT_BUNDLE_SERVICE_URI:
                    config.setDocumentBundleServiceURI(configEntity.getValue());
                    break;
                case Configuration.CONFIG_NODE_INTERCHANGEAGREEMENT_SERVICE_URI:
                    config.setInterchangeAgreementURI(configEntity.getValue());
                    break;
                case Configuration.CONFIG_APPLICATION_URL:
                    config.setApplicationUrl(configEntity.getValue());
                    break;
                case Configuration.CONFIG_WEBSERVICE_LOGGING_ENABLED:
                    config.setWebserviceLoggingEnabled(Boolean.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_RETENTION_POLICY_WEEKS:
                    config.setRetentionPolicyWeeks(Integer.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_RETENTION_POLICY_WARNINGS_BEFORE_DAYS:
                    config.setRetentionPolicyWarningBeforeDays(Integer.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_RETRIEVE_REQUEST_URI:
                    config.setRetrieveRequestURI(configEntity.getValue());
                    break;
                case Configuration.CONFIG_LOOKUP_FAILS_DEFAULT_ENABLED:
                    config.setLookupFailsDefaultEnabled(Boolean.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_NODE_APP_RESPONSE_SERVICE_URI:
                    config.setApplicationResponseURI(configEntity.getValue());
                    break;
                case Configuration.CONFIG_NODE_SECURITY_P2P_ENABLED:
                    config.setNodeSecurityP2pEnabled(Boolean.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_WORKSPACE_ROOT_PATH:
                    config.setWorkspaceRootPath(configEntity.getValue());
                    break;
                case Configuration.CONFIG_WORKSPACE_WS_TTL_IN_DAYS:
                    config.setWorkspaceWSTimeToLive(Integer.valueOf(configEntity.getValue()));
                    break;
                case Configuration.CONFIG_ANNOUNCEMENTS_CONTENT:
                    config.setAnnouncementsContent(configEntity.getValue());
                    break;
                case Configuration.CONFIG_MAIL_DISTRIBUTION:
                    config.setConfigMailDistribution(configEntity.getValue());
                    break;
                case Configuration.CONFIG_ZIP_SPLIT_SIZE:
                    config.setZipSplitSize(configEntity.getValue());
                    break;
                case Configuration.CONFIG_USERGUIDE_URL:
                    config.setUserGuideUrl(configEntity.getValue());
                    break;
                case Configuration.CONFIG_SUPPORT_EMAIL:
                    config.setSupportEmail(configEntity.getValue());
                    break;
                case Configuration.ARCHIVE_FILE_EXTENSIONS:
                    config.setArchiveFileExtensions(configEntity.getValue());
                    break;
                default:
                    logger.warn("Unknown configuration name found: [" + configEntity.getName() + "]");
            }
        }

        return config;
    }

    protected void fillDefaultValues(Configuration configuration) {
        configuration.setLookupFailsDefaultEnabled(true);
        configuration.setWorkspaceWSTimeToLive(1);
    }

    public boolean isRetentionPolicyValid(Configuration configuration) {
        if (configuration == null) {
            return false;
        }
        if (configuration.getRetentionPolicyWeeks() <= 0) {
            return false;
        }
        if (configuration.getRetentionPolicyWarningBeforeDays() < 0) {
            return false;
        }
        if (configuration.getRetentionPolicyWeeks() * 7 < configuration.getRetentionPolicyWarningBeforeDays()) {
            return false;
        }
        return true;
    }

    public RetentionMetadata computeRetentionMetadata(Configuration configuration, Date createdOn) {

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        RetentionMetadata retentionMetadata = new RetentionMetadata();

        int retentionPolicyWeeks = configuration.getRetentionPolicyWeeks();
        int retentionPolicyWarningBeforeDays = configuration.getRetentionPolicyWarningBeforeDays();

        Calendar expiryDate = Calendar.getInstance();
        expiryDate.setTime(createdOn);
        expiryDate.add(Calendar.DATE, retentionPolicyWeeks * 7);

        Calendar warningLimitDate = Calendar.getInstance();
        warningLimitDate.setTime(expiryDate.getTime());
        warningLimitDate.add(Calendar.DATE, -(retentionPolicyWarningBeforeDays));

        retentionMetadata.setRetentionExpired(now.compareTo(expiryDate) > 0);
        retentionMetadata.setRetentionWarning(!retentionMetadata.getRetentionExpired() && now.compareTo(warningLimitDate) > 0);
        retentionMetadata.setExpiredOn(expiryDate);
        return retentionMetadata;
    }
}
