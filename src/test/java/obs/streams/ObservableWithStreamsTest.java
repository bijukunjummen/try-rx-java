package obs.streams;

import org.junit.Test;
import rx.Observable;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ObservableWithStreamsTest {

    @Test
    public void withStreamsTest() {
        IntStream intStream = IntStream.range(1, 100);
        Stream<Integer> aStream = intStream.boxed();

        Iterable<Integer> iterable = new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return aStream.iterator();
            }
        };

        Observable.from(iterable);

        Observable.from(aStream::iterator).subscribe(i -> {
            if ((i % 3) == 0 && (i % 5 == 0)) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            }
        });
    }

}
