package obs.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.concurrent.CountDownLatch;

public class ConnectableObservablesTest {
    private Service1 service1 = new Service1();

    private static final Logger logger = LoggerFactory.getLogger(ConnectableObservablesTest.class);

    @Test
    public void testConnectableObservable() throws Exception {
        Observable<String> op1 = service1.operation();

        ConnectableObservable<String> connectableObservable =  op1.publish();

        CountDownLatch latch = new CountDownLatch(3);

        connectableObservable.subscribe(s -> logger.info("From Subscriber 1: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        connectableObservable.subscribe(s -> logger.info("From Subscriber 2: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        connectableObservable.subscribe(s -> logger.info("From Subscriber 3: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        connectableObservable.connect();

        latch.await();
    }
}
