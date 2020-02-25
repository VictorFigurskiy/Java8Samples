package com.sample.testingJUnit_Mochito;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sonikb on 04.09.2017.
 */
public class OperatorsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testSimple() {
        // 4 -> 2
        // 9 -> 3
        // -1 -> ?
        Assert.assertEquals(2, sqrt(4), 0.00001);
        Assert.assertEquals(3, sqrt(9), 0.00001);
        try {
            double sqrt = Math.sqrt(-1); // NaN
            Assert.assertEquals("NaN", sqrt(-1), 0.00001);
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("java.lang.IllegalArgumentException",e.toString());
        }
        throw new IllegalArgumentException();
    }

    @Test
    public void testWrong() {
        Assert.assertNotEquals(2, sqrt(5), 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBoarders() {
        sqrt(-1);
    }

    private static double sqrt(double d) {
        if (d < 0) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(d);
    }
}

/*
public class StringUtilsJUnit4Test extends Assert {
  private final Map<String, byte[]> toHexStringData = new HashMap<String, byte[]>();

 @Before
  public static void setUpToHexStringData() {
    toHexStringData.put("", new byte[0]);
    toHexStringData.put("01020d112d7f", new byte[] { 1, 2, 13, 17, 45, 127 });
    toHexStringData.put("00fff21180", new byte[] { 0, -1, -14, 17, -128 });
    //...
  }

 @After
  public static void tearDownToHexStringData() {
    toHexStringData.clear();
  }

 @Test
  public void testToHexString() {
    for (Map.Entry<String, byte[]> entry : toHexStringData.entrySet()) {
      final byte[] testData = entry.getValue();
      final String expected = entry.getKey();
      final String actual = StringUtils.toHexString(testData);
      assertEquals(expected, actual);
    }
  }
}



  @Test(expected = NullPointerException.class)
  public void testToHexStringWrong() {
    StringUtils.toHexString(null);
  }

  @Ignore // Если пока нужно проигнорировать данный тест, а если поставить @Ignore над классом то весь класс не будет проганятся тестами
  @Test(timeout = 1000)
  public void infinity() {
    while (true);
  }



Добавление правил @Rule

public class OtherJUnit4Test {

  @Rule
  public final TemporaryFolder folder = new TemporaryFolder();

  @Rule
  public final Timeout timeout = new Timeout(1000);

  @Rule
  public final ExpectedException thrown = ExpectedException.none();

  @Ignore
  @Test
  public void anotherInfinity() {
    while (true);
  }

  @Test
  public void testFileWriting() throws IOException {
    final File log = folder.newFile("debug.log");
    final FileWriter logWriter = new FileWriter(log);
    logWriter.append("Hello, ");
    logWriter.append("World!!!");
    logWriter.flush();
    logWriter.close();
  }

  @Test
  public void testExpectedException() throws IOException {
    thrown.expect(NullPointerException.class);
    StringUtils.toHexString(null);
  }
}

*/