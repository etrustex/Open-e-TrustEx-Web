package eu.europa.ec.etrustex.test.support;

import eu.europa.ec.etrustex.test.hamcrest.EtxCommonMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.MockitoAnnotations;

public abstract class AbstractTest extends EtxCommonMatchers {
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        onSetUp();
    }

    @After
    public void tearDown() {
        onTearDown();
    }

    protected void onSetUp(){
    }

    protected void onTearDown() {
    }

	@Rule
	public ExpectedException thrown = ExpectedException.none();

}
