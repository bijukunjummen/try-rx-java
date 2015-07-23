package obs.scatter;

import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CompletableFutureScatterGather {

	private static final Logger logger = LoggerFactory.getLogger(CompletableFutureScatterGather.class);

	@Test
	public void testCompletableFutureScatterGather() throws Exception {
		ExecutorService executors = Executors.newFixedThreadPool(5);
		List<CompletableFuture<String>> futures =
				IntStream.range(0, 10)
						.boxed()
						.map(i -> this.generateTask(i, executors).exceptionally(t -> t.getMessage()))
						.collect(Collectors.toList());

		CountDownLatch c = new CountDownLatch(1);

		CompletableFuture<List<String>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
				.thenApply(v -> futures.stream()
									.map(CompletableFuture::join)
									.collect(Collectors.toList()));

		result.thenAccept(l -> {
			logger.info(l.toString());
			c.countDown();
		});

		c.await();

	}

	private CompletableFuture<String> generateTask(int i,
			ExecutorService executorService) {
		return CompletableFuture.supplyAsync(() -> {
			Util.delay(2000);
			if (i == 5) {
				throw new RuntimeException("Run, it is a 5!");
			}
			return i + "-" + "test";
		}, executorService);
	}
}
