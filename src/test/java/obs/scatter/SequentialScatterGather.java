package obs.scatter;

import obs.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequentialScatterGather {

	private static final Logger logger = LoggerFactory.getLogger(SequentialScatterGather.class);

	@Test
	public void testSequentialScatterGather() throws Exception {
		List<String> list =
				IntStream.range(0, 10)
						.boxed()
						.map(this::generateTask)
						.collect(Collectors.toList());

		logger.info(list.toString());
	}

	private String generateTask(int i) {
		Util.delay(2000);
		return i + "-" + "test";
	}
}
