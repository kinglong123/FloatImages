package com.lijian.FloatImage;

import org.junit.Test;

import android.net.Uri;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);

        String s = Uri.encode("MAC id=\'9C6F5299B8858B7C85A66AB5F6B8CCC090986ED024E2C75A80E10A50DC870640910535DEEA25DDA2\',nonce=\'1528886172516:jiAwE49R\',mac=\'GVCE+c+h/fWlisge5nLXAq+AjFUKDTfiFooBQ4Zdl7Y=\'");

        System.out.println("111111:"+s);
    }
}