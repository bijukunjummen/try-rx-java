package obs.threads;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class GeneralService {
    private static final Logger logger = LoggerFactory.getLogger(GeneralService.class);
    public Observable<String> getData() {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing a Service");
            for (int i = 1; i <= 3; i++) {
                Util.delay(200);
                logger.info("Emitting {}", "root " + i);
                s.onNext("root " + i);
            }
            logger.info("End: Executing a Service");
            s.onCompleted();
        });
    }

    public Observable<String> actOnAPage(int pageNum) {
        return Observable.<String>create(s -> {
            Util.delay(200);
            logger.info("Acting on page {}",  pageNum);
            s.onNext("Page " + pageNum);
            s.onCompleted();
        });
    }

}
