package obs.scatter.backpressure;

import rx.Observable;
import rx.Subscriber;


public class TaskOnSubscribe implements Observable.OnSubscribe<Integer> {

    private final long numTasks;

    public TaskOnSubscribe(long numTasks) {
        this.numTasks = numTasks;
    }

    @Override
    public void call(Subscriber<? super Integer> subscriber) {
        TaskProducer taskProducer = new TaskProducer(subscriber, numTasks);
        subscriber.setProducer(taskProducer);
    }
}
