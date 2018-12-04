package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Config;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.List;

public class ConfigurationDAOTest extends DAOTest {

    private ConfigurationDAOImpl configurationDAO = new ConfigurationDAOImpl();

    @Override
    protected void onSetUp(EntityManager entityManager) {

        configurationDAO.entityManager = entityManager;

        insertConfigRow(Long.valueOf(1), "etrustex.doc.wrapper.service.uri", "http://wladig01.cc.cec.eu.int:1121/etrustex/external/DocumentWrapper-0.1?wsdl");
        insertConfigRow(Long.valueOf(2), "etrustex.file.storage.location", "c:/etx_file_repo");
    }

    private static void insertConfigRow(Long id, String name, String value) {
        executeQuery(
                "insert into ETX_WEB_CONFIG (CFG_ID, CFG_PROP_NAME, CFG_PROP_VALUE) values ("
                        + id.longValue() + ", '" + name + "', '" + value + "')");
    }

    @Test
    public void testGetConfiguration() {

        try {
            List<Config> conf = configurationDAO.getConfiguration();
            Assert.assertNotNull(conf);
            Assert.assertTrue(conf.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }

    }

}
