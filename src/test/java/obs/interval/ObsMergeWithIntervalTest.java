package obs.interval;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.TimeUnit;

public class ObsMergeWithIntervalTest {

    private static final Logger logger = LoggerFactory.getLogger(ObsMergeWithIntervalTest.class);

    @Test
    public void testWithMerge() throws Exception {
        Service1 service1 = new Service1();
        Observable<String> o1 = Observable.interval(1l, TimeUnit.SECONDS).map(Object::toString);
        Observable<String> o2 = service1.operation();
        Observable<String> o = Observable.merge(o2, o1);

        o.subscribe(s -> logger.info(s.toString()));
        logger.info("Completed");

        Thread.sleep(20000);
    }
}
