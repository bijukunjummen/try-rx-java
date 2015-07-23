package obs.chained;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class RootService {
    private static final Logger logger = LoggerFactory.getLogger(RootService.class);
    public Observable<String> getData() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Root Service");
            for (int i = 1; i <= 5; i++) {
                Util.delay(200);
                logger.info("Emiting {}", "root " + i);
                s.onNext("root " + i);
            }
            logger.info("End: Executing slow task in Root Service");
            s.onCompleted();
        });
    }

}
