package obs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class Service3 {
    private static final Logger logger = LoggerFactory.getLogger(Service3.class);

    public Observable<String> operation() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Service 3");
            Util.delay(3000);
            s.onNext("operation 3");
            logger.info("End: Executing slow task in Service 3");
            s.onCompleted();
        }).subscribeOn(Schedulers.computation());
    }
}
