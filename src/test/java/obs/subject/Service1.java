package obs.subject;

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
            s.onNext("getData 1 start");
            Util.delay(7000);
            s.onNext("getData 1 end");
            logger.info("End: Executing slow task in Service 1");
            s.onCompleted();
        }).subscribeOn(Schedulers.computation());
    }

}
