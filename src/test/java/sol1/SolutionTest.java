package sol1;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

public class SolutionTest {

    @Test
    public void testGetIndex1() {
        int A[] = {5,5,1,7,2,3,5};

        Solution s = new Solution();
        assertThat(s.solution(5, A), equalTo(4));
    }
}
