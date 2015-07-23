package obs.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.List;

public class MergeObservablesTest {
    private Service1 service1 = new Service1();
    private Service2 service2 = new Service2();
    private Service3 service3 = new Service3();
    
    private static final Logger logger = LoggerFactory.getLogger(MergeObservablesTest.class);
	
	@Test
	public void testBasicIntervalsObs() throws Exception {
        long start = System.nanoTime();

        Observable<String> op1 = service1.operation();
        Observable<String> op2 = service2.operation();
        Observable<String> op3 = service3.operation();

        Observable<List<String>> lst = Observable.merge(op1, op2, op3).toList();

        lst.toBlocking().forEach(l -> logger.info(l.toString()));
        long end = System.nanoTime();
        logger.info("time taken: " + (end - start) / (1000 * 1000) + " ms");
    }

    private Observable<String> from(Integer n) {
        return Observable.create(s -> {
            for (int i = 0; i < n ; i++) {

            }
        });
    }

}
