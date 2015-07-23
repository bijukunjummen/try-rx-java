package obs.withoutobs;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

public class WithoutObsTest {
    private Service1 service1 = new Service1();
    private Service2 service2 = new Service2();
    private Service3 service3 = new Service3();
    
    private static final Logger logger = LoggerFactory.getLogger(WithoutObsTest.class);
	
	@Test
	public void testWithoutObs() throws Exception {
        long start = System.nanoTime();

        String op1 = service1.operation();
        String op2 = service2.operation();
        String op3 = service3.operation();
        
        List<String> lst = Arrays.asList(op1, op2, op3);

        logger.info(lst.toString());

        long end = System.nanoTime();
        logger.info("time taken: " + (end - start) / (1000 * 1000) + " ms");
    }

}
