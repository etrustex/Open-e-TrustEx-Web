package eu.europa.ec.etrustex.webaccess.business.job;

import eu.europa.ec.etrustex.webaccess.business.api.EtxJob;
import eu.europa.ec.etrustex.webaccess.business.api.IcaDetailsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LoadAllIcaDetailsJob implements EtxJob {

    private static final Logger logger = Logger.getLogger(LoadAllIcaDetailsJob.class);

    @Autowired
    private IcaDetailsService icaDetailsService;

    @Override
    public void execute() {
        logger.info("### LoadAllIcaDetailsJob start ###");

        long startTime = System.currentTimeMillis();

        icaDetailsService.loadAllIcaDetails();

        logger.info("### LoadAllIcaDetailsJob finished after " + (double) (System.currentTimeMillis() - startTime) / 1000 + " sec ###");
    }
}
