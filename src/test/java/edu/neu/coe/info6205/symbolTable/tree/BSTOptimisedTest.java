package edu.neu.coe.info6205.symbolTable.tree;

import edu.neu.coe.info6205.util.PrivateMethodTester;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
public class BSTOptimisedTest {
    @Test
    public void testGet1() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        BST<String, Integer> bst = new BSTOptimisedDeletion<>(map, 0);
        Integer a = bst.get("A");
        assertEquals(Integer.valueOf(1), a);
        Integer b = bst.get("B");
        assertEquals(Integer.valueOf(2), b);
    }

    @Test
    public void testSetRoot1() {
        BST<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        System.out.println(bst);
    }

    @Test
    public void testSetRoot2() {
        BST<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node nodeX = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeY = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 52, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeZ = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Z", 99, 0);
        nodeY.smaller = nodeX;
        nodeY.larger = nodeZ;
        tester.invokePrivate("setRoot", nodeY);
        System.out.println(bst);
    }

    @Test
    public void testPut0() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        assertEquals(0, bst.size());
        bst.put("X", 42);
        assertEquals(1, bst.size());
        bst.put("Z", 99);
        assertEquals(2, bst.size());
    }

    @Test
    public void testPut1() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.put("Y", 99);
        BSTOptimisedDeletion<String, Integer>.Node root = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivate("getRoot");
        assertEquals("X", root.key);
        assertEquals("Y", root.larger.key);
        assertNull(root.smaller);
        assertEquals(2, bst.size());
    }

    @Test
    public void testPut2() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.put("X", 99);
        bst.put("Z", 37);
        BSTOptimisedDeletion<String, Integer>.Node root = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivate("getRoot");
        assertEquals("Y", root.key);
        assertEquals("X", root.smaller.key);
        assertEquals("Z", root.larger.key);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPut3() {
        BstDetail<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        bst.put("Y", 42);
        BSTOptimisedDeletion<String, Integer>.Node root = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivate("getRoot");
        assertEquals("Y", root.key);
        assertNull(root.smaller);
        assertNull(root.larger);
        bst.put("X", 99);
        assertEquals("X", root.smaller.key);
        bst.put("Z", 37);
        assertEquals("Z", root.larger.key);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPutN() {
        BstDetail<String, Integer> bst = new BSTOptimisedDeletion<>();
        bst.put("Hello", 3);
        bst.put("Goodbye", 5);
        bst.put("Ciao", 8);
        assertEquals(3, bst.size());
    }

    @Test
    public void testPutAll() {
        final Map<String, Integer> map = new HashMap<>();
        map.put("Hello", 3);
        map.put("Goodbye", 5);
        map.put("Ciao", 6);
        BstDetail<String, Integer> bst = new BSTOptimisedDeletion<>();
        bst.putAll(map);
        assertEquals(map.size(), bst.size());
    }

    @Test
    public void testDelete1() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.delete("X");
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
    }

    @Test
    public void testDelete2() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.put("Y", 57);
        BSTOptimisedDeletion<String, Integer>.Node root1 = getPrivateRoot(tester);
        assertNotNull(root1.larger);
        bst.delete("Y");
        BSTOptimisedDeletion<String, Integer>.Node root2 = getPrivateRoot(tester);
        assertNull(root2.smaller);
        assertNull(root2.larger);
        assertEquals(1, bst.size(node));
    }

    @Test
    public void testDelete3() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.put("W", 57);
        bst.delete("W");
        BSTOptimisedDeletion<String, Integer>.Node root = getPrivateRoot(tester);
        assertNull(root.smaller);
        assertNull(root.larger);
        assertEquals(1, bst.size(root));
    }

    @Test
    public void testDelete4() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node node = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        tester.invokePrivate("setRoot", node);
        bst.put("W", 57);
        bst.delete("A");
        BSTOptimisedDeletion<String, Integer>.Node root = getPrivateRoot(tester);
        assertEquals(2, bst.size(root));
    }

    @Test
    public void testSize1() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        for (int i = 0; i < 100; i++) bst.put(Integer.toString(i), i);
//        System.out.println(bst);
        assertEquals(100, bst.size());
        bst.validate();
    }

    @Test
    public void testSize2() {
        Random random = new Random(0L);
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        for (int i = 0; i < 100; i++) bst.put(Integer.toString(random.nextInt(200)), i);
        assertEquals(79, bst.size());
    }

    @Test
    public void testDepthKey1() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node nodeX = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeY = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 52, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeZ = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Z", 99, 0);
        nodeY.smaller = nodeX;
        nodeY.larger = nodeZ;
        tester.invokePrivate("setRoot", nodeY);
        assertEquals(1, bst.depth("X"));
        assertEquals(0, bst.depth("Y"));
        assertEquals(1, bst.depth("Z"));
        assertEquals(-1, bst.depth("A"));
    }

    @Test
    public void testDepthKey2() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        bst.put("Hello", 3);
        bst.put("Goodbye", 5);
        bst.put("Ciao", 8);
        assertEquals(0, bst.depth("Hello"));
        assertEquals(1, bst.depth("Goodbye"));
        assertEquals(2, bst.depth("Ciao"));
    }

    @Test
    public void testDepth1() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        Class<?>[] classes = {Comparable.class, Object.class, int.class};
        BSTOptimisedDeletion<String, Integer>.Node nodeX = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "X", 42, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeY = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Y", 52, 0);
        BSTOptimisedDeletion<String, Integer>.Node nodeZ = (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivateExplicit("makeNode", classes, "Z", 99, 0);
        nodeY.smaller = nodeX;
        nodeY.larger = nodeZ;
        tester.invokePrivate("setRoot", nodeY);
        assertEquals(2, bst.depth());
    }

    @Test
    public void testDepth2() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>();
        bst.put("Hello", 3);
        bst.put("Goodbye", 5);
        bst.put("Ciao", 8);
        assertEquals(3, bst.depth());
    }

    @Test
    public void testOptimisedDeleteOfNode() {
        BSTOptimisedDeletion<String, Integer> bst = new BSTOptimisedDeletion<>(2);
        PrivateMethodTester tester = new PrivateMethodTester(bst);
        BSTOptimisedDeletion<String, Integer>.Node root1 = getPrivateRoot(tester);
        assertEquals(0, bst.size(root1));
        bst.put("15", 15);
        bst.put("10", 10);
        bst.put("20", 20);
        bst.put("08", 8);
        bst.put("12", 12);
        bst.put("18", 18);
        bst.put("16", 16);
        bst.put("30", 30);
//        System.out.println(bst);
        bst.validate();

        BSTOptimisedDeletion<String, Integer>.Node root2 = getPrivateRoot(tester);
        assertEquals(8, bst.size(root2));

        // test : delete and replace with predecessor
        // accessing node with key"20"
        assertEquals(2, bst.size(root2.larger.smaller));
        assertEquals(1, bst.size(root2.larger.larger));
        bst.delete("20");
//        System.out.println(bst);
        bst.validate();

        assertEquals(1, bst.size(root2.larger.smaller));
        assertEquals(1, bst.size(root2.larger.larger));

        // test: delete and replace with successor
        bst.put("42", 42);
//        System.out.println(bst);
        bst.validate();
        BSTOptimisedDeletion<String, Integer>.Node root3 = getPrivateRoot(tester);

        // accessing node with key "30"
        assertEquals(0, bst.size(root3.larger.larger.smaller));
        assertEquals(1, bst.size(root3.larger.larger.larger));
        bst.delete("30");
        BSTOptimisedDeletion<String, Integer>.Node root4 = getPrivateRoot(tester);
//        System.out.println(bst);
        bst.validate();
        assertEquals(0, bst.size(root4.larger.larger.smaller));
        assertEquals(0, bst.size(root4.larger.larger.larger));
    }

    private BSTOptimisedDeletion<String, Integer>.Node getPrivateRoot(PrivateMethodTester tester) {
        return (BSTOptimisedDeletion<String, Integer>.Node) tester.invokePrivate("getRoot");
    }


}