package obs.chained;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChainedObsTest {
    private RootService rootService = new RootService();
    private GeneralService aService = new GeneralService();

    private static final Logger logger = LoggerFactory.getLogger(ChainedObsTest.class);

	@Test
	public void testChainedObservable() throws Exception {
        long start = System.nanoTime();

        Observable<String> op1 = rootService.getData();

        CountDownLatch latch = new CountDownLatch(1);

        op1.flatMap(aService::operation).subscribe(s -> {
            logger.info(s);
        }, e -> logger.error(e.getMessage(), e), () -> latch.countDown());

        latch.await();
        long end = System.nanoTime();
        logger.info("time taken: " + (end - start) / (1000 * 1000) + " ms");
    }

}
