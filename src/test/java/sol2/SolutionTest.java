package sol2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class SolutionTest {

    @Test
    public void test1() {
        int A[] = {1,0,0,1,1};

        Solution s = new Solution();
        assertThat(s.solution(A), equalTo(new int[]{1,1,0,1}));
    }

    @Test
    public void test2() {
        int A[] = {1,0,0,1,1,1};

        Solution s = new Solution();
        assertThat(s.solution(A), equalTo(new int[]{1,1,0,1,0,1,1}));
    }
}
