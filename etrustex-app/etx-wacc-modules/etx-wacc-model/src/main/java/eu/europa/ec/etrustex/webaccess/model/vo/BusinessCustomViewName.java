package eu.europa.ec.etrustex.webaccess.model.vo;

public enum BusinessCustomViewName {

    EDMA,

    EGREFFE,

    GENERIC;

    public static BusinessCustomViewName forCustomViewName(String customViewName) {
        if (customViewName.equalsIgnoreCase(EDMA.name())) {
            return EDMA;
        }
        if (customViewName.equalsIgnoreCase(EGREFFE.name())) {
            return EGREFFE;
        }
        if (customViewName.equalsIgnoreCase(GENERIC.name())) {
            return GENERIC;
        }

        throw new IllegalArgumentException("Unexpected custom view name: " + customViewName);
    }
}
