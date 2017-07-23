package thanh.ha.english;

import org.junit.Test;

import thanh.ha.english.utils.Utils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getStartTimeToday() throws Exception {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        System.out.print("timestamp: " + timestamp);
        assertEquals(Utils.startTimeToday(timestamp), 1498410000);
    }
}