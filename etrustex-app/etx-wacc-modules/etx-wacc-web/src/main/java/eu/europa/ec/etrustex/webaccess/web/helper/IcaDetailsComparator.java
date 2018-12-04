package eu.europa.ec.etrustex.webaccess.web.helper;

import eu.europa.ec.etrustex.webaccess.model.vo.IcaDetailsVO;

import java.util.Comparator;

public class IcaDetailsComparator implements Comparator<IcaDetailsVO> {

    @Override
    public int compare(IcaDetailsVO icaDetails1, IcaDetailsVO icaDetails2) {
        if (icaDetails1.getSenderParty() == null || icaDetails2.getSenderParty() == null) {
            return 0;
        }

        return icaDetails1.getSenderParty().compareTo(icaDetails2.getSenderParty());
    }

}
