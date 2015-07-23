package obs.threads;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadedObsTest {
    private GeneralService aService = new GeneralService();

    private static final Logger logger = LoggerFactory.getLogger(ThreadedObsTest.class);
    private ExecutorService executor1 = Executors.newFixedThreadPool(5, new ThreadFactoryBuilder().setNameFormat("SubscribeOn-%d").build());
    private ExecutorService executor2 = Executors.newFixedThreadPool(5, new ThreadFactoryBuilder().setNameFormat("ObserveOn-%d").build());

	@Test
	public void testSubscribeOn() throws Exception {
        Observable<String> ob1 = aService.getData();

        CountDownLatch latch = new CountDownLatch(1);

        ob1/*subscribeOn(Schedulers.from(executor1))*/.observeOn(Schedulers.from(executor2)).subscribe(s -> {
            Util.delay(500);
            logger.info("Got {}", s);
        }, e -> logger.error(e.getMessage(), e), () -> latch.countDown());

        latch.await();
    }

    @Test
    public void testThreadedObservable2() throws Exception {
        CountDownLatch latch = new CountDownLatch(5);
        getPages(5).flatMap(  page -> aService.actOnAPage(page).subscribeOn(Schedulers.from(executor1)) )
                .subscribe(s -> {
                    logger.info("Completed Processing page: {}", s);
                    latch.countDown();
        });

        latch.await();
    }

    public Observable<Integer> getPages(int totalPages) {
        return Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                logger.info("Getting pages");
                for (int i = 1; i <= totalPages; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });
    }
}
