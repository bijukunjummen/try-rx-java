package obs.interval;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class Service1 {
    private static final Logger logger = LoggerFactory.getLogger(Service1.class);
    public Observable<String> operation() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Service 1");
            Util.delay(1000);
            s.onNext("data 1");
            Util.delay(9000);
            s.onNext("data 2");
            logger.info("End: Executing slow task in Service 1");
            Util.delay(1000);
            s.onNext("data 3");
            s.onCompleted();
        });
    }
}