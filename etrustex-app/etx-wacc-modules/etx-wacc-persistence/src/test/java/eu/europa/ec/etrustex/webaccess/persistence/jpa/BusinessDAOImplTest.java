package eu.europa.ec.etrustex.webaccess.persistence.jpa;

import eu.europa.ec.etrustex.webaccess.model.Business;
import eu.europa.ec.etrustex.webaccess.model.BusinessConfig;
import eu.europa.ec.etrustex.webaccess.model.BusinessConfigProperty;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

import static eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers.assertThat;
import static org.hamcrest.Matchers.is;

public class BusinessDAOImplTest extends DAOTest {
    private BusinessDAOImpl businessDAO;


    @Override
    protected void onSetUp(EntityManager entityManager) {
        businessDAO = new BusinessDAOImpl();
        businessDAO.entityManager = entityManager;
    }

    @Test
    public void test_getAllBusinesses() {

        insertUser(1L, "user1", "user1", new Date(), 1L, true);

        insertBusiness(1L, "testBusiness", new Date(), 1L, true);
        insertBusinessConfig(1L, 1L, "etx.business.custom.view.name", "test");

        // ACTUAL CALL
        List<Business> businessList = businessDAO.getAllBusinesses();

        assertThat(businessList.size(), is(1));

        Business business = businessList.get(0);
        assertThat(business.getName(), is("testBusiness"));

        List<BusinessConfig> businessConfigs = business.getBusinessConfigs();
        assertThat(businessConfigs.size(), is(1));

        BusinessConfig bc = businessConfigs.get(0);
        assertThat(bc.getBusinessConfigProperty(), is(BusinessConfigProperty.BUS_CUSTOM_VIEW_NAME));
        assertThat(bc.getPropertyValue(), is("test"));

//        System.out.println("businessConfigs = " + businessConfigs);
    }
}