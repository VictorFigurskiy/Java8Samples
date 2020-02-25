package com.sample.testingJUnit_Mochito;

import static org.junit.Assert.assertEquals;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;




@RunWith(value = Parameterized.class)
public class ParametersTest {
    private String correctUrl;
    private String testUrl;

    public ParametersTest(String corUrl, String tstUrl) {
        this.correctUrl = corUrl;
        this.testUrl = tstUrl;
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{
                {"http://example.com/display?category=foo%2Fbar%20baz", "http://example.com/display?category=foo/bar+baz"},
                {"https://mail.google.com/dom1/", "hTTPs://mail.GooGlE.coM/dom1/index.html"},
                {"http://example.com/dss/", "http://example.com/dss"}};
        return Arrays.asList(data);
    }

    // Этот тест будет выполняться 3 раза, так как мы определили 3 пары параметров в коллекции
    @Test
    public void tUrlNormalization() {
        String noNormalizedUrl = testUrl;
        URL locator = getLocator(noNormalizedUrl);
//        URLNormalization test = new URLNormalization();
//        test.setURLParameters(locator);
//        String normalizedUrl = test.getNormalizeURL();
//        assertEquals(correctUrl, normalizedUrl);
    }


    private URL getLocator(String urlElement) {
        URL locator = null;
        try {
            locator = new URL(urlElement);
        } catch (MalformedURLException e) {
            locator = null;
        }
        return locator;
    }



    // This is my test, not for Parameterized
    public static void main(String[] args) {

        Object[][] objectArray = new Object[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};

        for (int i = 0; i < objectArray.length; i++) {
            for (int j = 0; j < objectArray[i].length; j++) {
                System.out.print(objectArray[i][j] + " " + (j == objectArray[i].length - 1 ? "\n" : ""));
            }
//            System.out.println();
        }

        System.out.println("-----------------------------");

        List<Object[]> objectList = Arrays.asList(objectArray);

        for (Object[] objects : objectList) {
            for (int i = 0; i < objects.length; i++) {
                System.out.print(objects[i] + " ");
            }
            System.out.println();
        }
    }
}
