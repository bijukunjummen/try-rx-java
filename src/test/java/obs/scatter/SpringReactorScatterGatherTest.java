package obs.scatter;

import obs.Util;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpringReactorScatterGatherTest {

    private static final Logger logger = LoggerFactory.getLogger(ObservableScatterGatherTest.class);

    @Test
    public void testScatterGather() {
        ExecutorService executors = Executors.newFixedThreadPool(5);

        List<Flux<String>> fluxList = IntStream.range(0, 10)
                .boxed()
                .map(i -> generateTask(executors, i)).collect(Collectors.toList());

        Mono<List<String>> merged = Flux.merge(fluxList).toList();

        List<String> list = merged.get();

        logger.info(list.toString());


    }

    public Flux<String> generateTask(ExecutorService executorService, int i) {
        return Flux.<String>create(s -> {
            Util.delay(2000);
            s.onNext(i + "-test");
            s.onComplete();
        }).subscribeOn(executorService);
    }
}
