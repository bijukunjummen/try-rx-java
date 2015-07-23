package obs.chained;

import obs.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Observable;

public class GeneralService {
    private static final Logger logger = LoggerFactory.getLogger(GeneralService.class);

    public Observable<String> operation(String rootResp1) {
        return Observable.<String>create(s -> {
            logger.info("Start: Executing slow task in Gen Service");
            Util.delay(500);
            s.onNext(rootResp1.toUpperCase());
            logger.info("End: Executing slow task in Gen Service");
            s.onCompleted();
        });
    }
}
