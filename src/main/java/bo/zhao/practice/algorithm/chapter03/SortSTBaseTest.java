package bo.zhao.practice.algorithm.chapter03;

import junit.framework.Assert;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/28
 */
public abstract class SortSTBaseTest extends STBaseTest {


    @Override
    protected SortST<String, Integer> getSt() {
        return (SortST<String, Integer>) st;
    }

    @Override
    public void testRank() {
        Assert.assertTrue(getSt().rank("A") == 0);
        Assert.assertTrue(getSt().rank("Y") == 10);
        Assert.assertTrue(getSt().rank("D") == 2);
        Assert.assertTrue(getSt().rank("X") == 9);
    }

    @Override
    public void testSelect() {
        for (Tuple<String, Integer> tuple : tuples) {
            Assert.assertEquals(getSt().select(getSt().rank(tuple.getKey())), tuple.getKey());
        }
    }

    @Override
    public void testMin() {
        Assert.assertEquals(getSt().min(), "A");
    }

    @Override
    public void testMax() {
        Assert.assertEquals(getSt().max(), "X");
    }

    @Override
    public void testFloor() {
        Assert.assertEquals(getSt().floor("E"), "E");
        Assert.assertEquals(getSt().floor("F"), "E");
    }

    @Override
    public void testCeiling() {
        Assert.assertEquals(getSt().ceiling("E"), "E");
        Assert.assertEquals(getSt().ceiling("D"), "E");
    }

    @Override
    public void testDeleteMin() {
        int size = getSt().size();
        getSt().deleteMin();
        Assert.assertTrue(getSt().size() == (size - 1));
        Assert.assertEquals(getSt().min(), "C");
    }

    @Override
    public void testDeleteMax() {
        int size = getSt().size();
        getSt().deleteMax();
        Assert.assertTrue(getSt().size() == (size - 1));
        Assert.assertEquals(getSt().max(), "S");
    }
}
