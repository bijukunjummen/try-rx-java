package obs.scatter.backpressure;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Producer;
import rx.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TaskProducer implements Producer {
    private volatile AtomicLong requested = new AtomicLong(0L);
    private final long numTasks;
    private Subscriber<? super Integer> subscriber;
    private AtomicInteger toEmit = new AtomicInteger(0);
    private volatile AtomicLong remaining;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskProducer.class);

    public TaskProducer(Subscriber<? super Integer> subscriber,  long numTasks) {
        this.subscriber = subscriber;
        this.numTasks = numTasks;
        this.remaining = new AtomicLong(this.numTasks);
    }

    @Override
    public void request(long n) {
        LOGGER.info("Got Request for {}", n);
        if (requested.get() == Long.MAX_VALUE) {
            return;
        }
        if (n == Long.MAX_VALUE) {
            requested.set(n);
            for (int i = 0; i < this.remaining.get(); i++) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                //spit out tasks every second..
                Util.delay(500);
                LOGGER.info("Emiting {}", "task-" + i);
                subscriber.onNext(i);
            }

            this.remaining.set(0L);

            if (!subscriber.isUnsubscribed()) {
                subscriber.onCompleted();
            }
        } else if (n > 0) {
            long _c = getAndAddRequest(requested, n);
            LOGGER.info("Calculated current request backlog to {}", _c);
            if (_c == 0) {


                while (true) {
                    long r = requested.get();

                    long toReturn = Math.min(r, remaining.get());
                    System.out.println("toReturn = " + toReturn);

                    for (int i = 0; i < toReturn; i++) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        //spit out tasks every second..
                        Util.delay(500);
                        int taskId = toEmit.addAndGet(1);
                        LOGGER.info("Emiting {}", "task-" + taskId);
                        subscriber.onNext(taskId);
                    }

                    getAndAddRequest(requested, -toReturn);

                    remaining.addAndGet(-toReturn);
                    System.out.println("remaining.toString() = " + remaining.toString());

                    if (remaining.get() == 0 && !subscriber.isUnsubscribed()) {
                        subscriber.onCompleted();
                        return;
                    }
                }
            }
        }


    }

    static <T> long getAndAddRequest(AtomicLong requested, long n) {
        // add n to field but check for overflow
        while (true) {
            long current = requested.get();
            long next = current + n;
            // check for overflow
            if (next < 0)
                next = Long.MAX_VALUE;
            if (requested.compareAndSet(current, next))
                return current;
        }
    }
}
