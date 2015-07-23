package obs.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BasicObservablesTest {
    private Service1 service1 = new Service1();

    private static final Logger logger = LoggerFactory.getLogger(BasicObservablesTest.class);

    @Test
    public void testBasicIntervalsObs() throws Exception {
        Observable<String> op1 = service1.operation();

        CountDownLatch latch = new CountDownLatch(3);

        op1.subscribe(s -> logger.info("From Subscriber 1: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        op1.subscribe(s -> logger.info("From Subscriber 2: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        op1.subscribe(s -> logger.info("From Subscriber 3: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        latch.await();
    }
}
