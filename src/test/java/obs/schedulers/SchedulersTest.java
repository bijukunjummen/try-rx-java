package obs.schedulers;

import org.junit.Test;
import rx.Scheduler;
import rx.schedulers.Schedulers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SchedulersTest {

    @Test
    public void schedulersTest() throws Exception{
        Scheduler scheduler = Schedulers.computation();
        CountDownLatch cl = new CountDownLatch(3);
        scheduler.createWorker().schedulePeriodically(() -> {
            System.out.println("Performing some action!");
            cl.countDown();
        }, 10, 5, TimeUnit.SECONDS);


        cl.await();
    }

}
