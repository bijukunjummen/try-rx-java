package obs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class Service2 {
    private static final Logger logger = LoggerFactory.getLogger(Service2.class);

    public Observable<String> operation() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Service 2");
            Util.delay(5000);
            s.onNext("operation 2");
            logger.info("End: Executing slow task in Service 2");
            s.onCompleted();
        }).subscribeOn(Schedulers.computation());
    }
}
