package obs.scatter;

import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ObservableScatterGatherTest {

    private static final Logger logger = LoggerFactory.getLogger(ObservableScatterGatherTest.class);

    @Test
    public void testScatterGather() throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        List<Observable<String>> obs =
                IntStream.range(0, 10)
                    .boxed()
                    .map(i -> generateTask(i, executors)).collect(Collectors.toList());


        Observable<List<String>> merged = Observable.merge(obs).toList();

        CountDownLatch c = new CountDownLatch(1);

        merged.subscribe(
                l -> logger.info(l.toString()),
                (e) -> {
                    e.printStackTrace();
                    c.countDown();
                },
                () -> {
                    System.out.println("Completed");
                    c.countDown();
                });

        c.await();

    }

    private Observable<String> generateTask(int i, ExecutorService executorService) {
        return Observable
                .<String>create(s -> {
                    Util.delay(2000);
                    if ( i == 5) {
                        throw new RuntimeException("Run, it is a 5!");
                    }
                    s.onNext( i + "-test");
                    s.onCompleted();
                }).onErrorReturn(e -> e.getMessage()).subscribeOn(Schedulers.from(executorService));
    }
}
