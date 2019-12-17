package eu.europa.ec.etrustex.webaccess.business;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.persistence.BusinessDAO;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

public class BusinessManagerImplTest extends AbstractTest{

    @InjectMocks
    private BusinessManagerImpl businessManager;

    @Mock
    private BusinessDAO businessDAO;

    @Test
    public void test_getAllBusinesses_should_callBusinessDAO() {
        //ACTUAL CALL
        businessManager.getAllBusinesses();

        verify(businessDAO).getAllBusinesses();
    }
}
