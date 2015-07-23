package obs.withoutobs;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service3 {
    private static final Logger logger = LoggerFactory.getLogger(Service3.class);

    public String operation() {
        logger.info("Start: Executing slow task in Service 3");
        Util.delay(1000);
        logger.info("End: Executing slow task in Service 3");
        return "getData 3";
    }
}
