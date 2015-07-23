package err;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import rx.Observable;

public class ErrorTest {
	
	@Test
	public void testErrorFlow() throws Exception {
		Observable<Long> obs1 = Observable.interval(1l, TimeUnit.SECONDS).map(l -> {
			if (l==5) {
				throw new RuntimeException("throwing a deliberate exception");
			} else {
				return l;
			}
		});
		Observable<Long> obs2 = Observable.interval(2l, TimeUnit.SECONDS);
		
		obs1.mergeWith(obs2).subscribe(System.out::println);
		
		obs2.subscribe(l -> {System.out.println("From second subscription: " + l);});
		
		Thread.sleep(30000L);
	}
}
