package obs.withoutobs;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service2 {
    private static final Logger logger = LoggerFactory.getLogger(Service2.class);

    public String operation() {
        logger.info("Start: Executing slow task in Service 2");
        Util.delay(2000);
        logger.info("End: Executing slow task in Service 2");
        return "getData 2";
    }
}
