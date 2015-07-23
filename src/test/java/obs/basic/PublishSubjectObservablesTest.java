package obs.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subjects.PublishSubject;

import java.util.concurrent.CountDownLatch;

public class PublishSubjectObservablesTest {
    private Service1 service1 = new Service1();

    private static final Logger logger = LoggerFactory.getLogger(PublishSubjectObservablesTest.class);

    @Test
    public void testSubjectsTest() throws Exception {
        Observable<String> op1 = service1.operation();

        PublishSubject<String> publishSubject = PublishSubject.create();

        op1.subscribe(publishSubject);

        CountDownLatch latch = new CountDownLatch(3);

        publishSubject.subscribe(s -> logger.info("From Subscriber 1: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        publishSubject.subscribe(s -> logger.info("From Subscriber 2: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());

        publishSubject.subscribe(s -> logger.info("From Subscriber 3: {}", s),
                e -> logger.error(e.getMessage(), e),
                () -> latch.countDown());


        latch.await();
    }
}
