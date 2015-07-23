package obs.subject;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class SubjectTest {

    private static final Logger logger = LoggerFactory.getLogger(SubjectTest.class);

    @Test
    public void aSubjectTest() throws Exception {

        Service1 service1 = new Service1();
        Observable<String> o = service1.operation();

        Subject<String, String> sub = PublishSubject.create();

        o.subscribe(sub);

        sub.subscribe(s -> logger.info("Sub 1 " + s));
        Thread.sleep(1000);
        sub.subscribe(s -> logger.info("Sub 2 " + s));

        Thread.sleep(10000);
    }
}
