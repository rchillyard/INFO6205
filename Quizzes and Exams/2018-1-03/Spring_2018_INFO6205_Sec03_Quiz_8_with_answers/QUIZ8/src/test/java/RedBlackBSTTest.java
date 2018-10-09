import org.junit.Test;
import static org.junit.Assert.*;

public class RedBlackBSTTest {
    @Test
    public void testPut0() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('s', 1);
        st.put('a', 2);
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals(2, (int)st.inorderTraverse().get(0));
    }

    @Test
    public void testPut1() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('a', 1);
        st.put('s', 2);
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals(1, (int)st.inorderTraverse().get(0));
        assertEquals(2, (int)st.inorderTraverse().get(1));
    }

    @Test
    public void testPut2() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('s', 1);
        st.put('k', 2);
        st.put('b', 3);
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals(2, (int)st.inorderTraverse().get(1));
    }

    @Test
    public void testPut3() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('s', 1);
        st.put('k', 2);
        st.put('b', 3);
        st.put('e', 4);
        st.put('m', 5);
        st.put('w', 6);
        st.put('q', 7);
        st.put('a', 8);
        st.put('l', 9);
        st.put('g', 10);
        assertTrue(st.check());
        assertTrue(st.isRBTree());
        assertEquals(4, (int)st.inorderTraverse().get(2));
        assertEquals(10, (int)st.inorderTraverse().get(3));
        assertEquals(1, (int)st.inorderTraverse().get(8));
    }

    @Test
    public void testGet0() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        assertEquals(null, st.get('a'));
    }

    @Test
    public void testGet1() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('s', 1);
        st.put('k', 2);
        st.put('b', 3);
        st.put('e', 4);
        st.put('m', 5);
        st.put('w', 6);
        st.put('q', 7);
        st.put('a', 8);
        st.put('l', 9);
        st.put('g', 10);
        assertEquals(new Integer(5), st.get('m'));
    }

    @Test
    public void testGet2() {
        RedBlackBST<Character, Integer> st = new RedBlackBST<Character, Integer>();
        st.put('s', 1);
        st.put('k', 2);
        st.put('b', 3);
        st.put('e', 4);
        st.put('f', 5);
        st.put('w', 6);
        st.put('q', 7);
        st.put('a', 8);
        st.put('l', 9);
        st.put('g', 10);
        assertEquals(null, st.get('m'));
        assertEquals(new Integer(7), st.get('q'));
    }
}
