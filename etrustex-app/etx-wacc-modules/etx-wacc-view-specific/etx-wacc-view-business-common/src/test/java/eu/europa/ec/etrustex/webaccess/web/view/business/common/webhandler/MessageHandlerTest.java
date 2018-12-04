package eu.europa.ec.etrustex.webaccess.web.view.business.common.webhandler;

import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageListQueryParams;
import eu.europa.ec.etrustex.webaccess.model.vo.MessageQueryParams;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.Map;

public class MessageHandlerTest extends AbstractTest {

    @InjectMocks
    private AbstractMessageHandler handler = new AbstractMessageHandler() {
        @Override
        protected Map<String, Object> buildBusinessModel(MessageQueryParams queryParams) throws Exception {
            return null;
        }
    };

    @Test(expected = IllegalStateException.class)
    public void test_buildBusinessModel_should_throwIllegalStateException_when_queryParamTypeIncorrect() throws Exception {
        // DO THE ACTUAL CALL
        handler.buildBusinessModel(new MessageListQueryParams());
    }
}
