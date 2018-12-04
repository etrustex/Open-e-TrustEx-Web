package eu.europa.ec.etrustex.webaccess.web.payload;

import eu.europa.ec.etrustex.webaccess.model.vo.BusinessCustomViewName;
import eu.europa.ec.etrustex.webaccess.web.payload.edma.EdmaPayloadBuilder;

public class PayloadBuilderProvider {


    public static PayloadBuilder getPayloadBuilder(String businessName) {
        BusinessCustomViewName business = BusinessCustomViewName.forCustomViewName(businessName);
        PayloadBuilder payloadBuilder;
        if (BusinessCustomViewName.EDMA.equals(business)) {
            payloadBuilder = new EdmaPayloadBuilder();
        } else {
            payloadBuilder = null;
        }

        return payloadBuilder;
    }

    public static boolean hasPayloadBuilder(String businessName) {
        boolean hasPayloadBuilder;
        BusinessCustomViewName business = BusinessCustomViewName.forCustomViewName(businessName);
        if (BusinessCustomViewName.EDMA.equals(business)) {
            hasPayloadBuilder = true;
        } else {
            hasPayloadBuilder = false;
        }
        return hasPayloadBuilder;
    }
}
