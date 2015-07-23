package obs.interval;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ObsZipWithIntervalTest {

    private static final Logger logger = LoggerFactory.getLogger(ObsZipWithIntervalTest.class);

    @Test
    public void testWithZip() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Observable<String> data = Observable.just("one", "two", "three", "four", "five");
        Observable.zip(data, Observable.interval(1, TimeUnit.SECONDS), (d, t) -> {
            return d + " " + t;
        }).finallyDo(latch::countDown).forEach(System.out::println);

        latch.await(10, TimeUnit.SECONDS);
    }
}
