package obs.scatter.backpressure;

import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.internal.operators.OnSubscribeRange;
import rx.internal.operators.OnSubscribeRefCount;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableBackPressureTest {
    private static final Logger LOG = LoggerFactory.getLogger(ObservableBackPressureTest.class);

    @Test
    public void testBackPressure() throws Exception {
        ExecutorService executors1 = Executors.newFixedThreadPool(5);
        ExecutorService executors2 = Executors.newFixedThreadPool(5);

        Observable<Integer> obs = Observable.create(new TaskOnSubscribe(10)).observeOn(Schedulers.from(executors1));
        CountDownLatch cl = new CountDownLatch(1);

        obs.subscribe(new Subscriber<Integer>() {

            @Override
            public void onStart() {
                request(3);
            }

            @Override
            public void onCompleted() {
                cl.countDown();
            }

            @Override
            public void onError(Throwable e) {
                cl.countDown();
            }

            @Override
            public void onNext(Integer n) {
                LOG.info("Got = " + n);
                Util.delay(1000);
                request(5);
            }
        });


        cl.await();

    }


}
