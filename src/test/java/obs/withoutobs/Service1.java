package obs.withoutobs;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service1 {
    private static final Logger logger = LoggerFactory.getLogger(Service1.class);

    public String operation() {
        logger.info("Start: Executing slow task in Service 1");
        Util.delay(3000);
        logger.info("End: Executing slow task in Service 1");
        return "getData 1";
    }
}
