package bo.zhao.practice.algorithm.chapter03;

import junit.framework.Assert;

/**
 * 文件描述：
 *
 * @author Bo.Zhao
 * @version 3.0
 * @since 18/5/28
 */
public class BSTTest extends SortSTBaseTest {

    @Override
    protected void initST() {
        st = new BST<>();
    }


    @Override
    public void testAll() {
        prepare();
        BST<String, Integer> bst = (BST<String, Integer>) st;
        Assert.assertEquals(bst.height(), 5);
        Assert.assertEquals(bst.height(), bst.quickHeight());
        testSize();
        testKeys();
        testGet();
        testContains();
        testRank();
        testSelect();
        testMin();
        testMax();
        testFloor();
        testCeiling();
        testDeleteMin();
        testDeleteMax();
        testDelete();
        Assert.assertEquals(bst.height(), -1);
        Assert.assertEquals(bst.height(), bst.quickHeight());
    }
}
