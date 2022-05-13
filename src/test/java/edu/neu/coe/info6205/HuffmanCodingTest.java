package edu.neu.coe.info6205;

import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static edu.neu.coe.info6205.HuffmanCoding.showTree;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HuffmanCodingTest {

    @Test
    public void testGenerateCode() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node tree = huffmanCoding.generateCode();
        assertNotNull(tree);
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testCreateHuffmanCoding() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        assertNotNull(huffmanCoding);
    }

    @Test
    public void testShowTree() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node tree = huffmanCoding.generateCode();
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter);
        showTree(tree, pw);
        pw.flush();
        pw.close();
        String s = stringWriter.toString();
        // CONSIDER just testing the length of this string.
        assertEquals("  : N-2-NT-X-null-XX-9-Q-8-J-T-A-K-7-6-P-5-4-D️-1-3-S️-C️-H️-W-Z-E (319) \n" +
                "    0: N-2-NT-X-null-XX-9-Q-8-J-T-A-K-7-6 (122) \n" +
                "    0  0: N-2-NT-X-null-XX-9 (57) \n" +
                "    0  0  0: N (28) \n" +
                "    0  0  1: 2-NT-X-null-XX-9 (29) \n" +
                "    0  0  1  0: 2 (14) \n" +
                "    0  0  1  1: NT-X-null-XX-9 (15) \n" +
                "    0  0  1  1  0: NT-X-null-XX (7) \n" +
                "    0  0  1  1  0  0: NT (3) \n" +
                "    0  0  1  1  0  1: X-null-XX (4) \n" +
                "    0  0  1  1  0  1  0: X (2) \n" +
                "    0  0  1  1  0  1  1: null-XX (2) \n" +
                "    0  0  1  1  0  1  1  0: null (1) \n" +
                "    0  0  1  1  0  1  1  1: XX (1) \n" +
                "    0  0  1  1  1: 9 (8) \n" +
                "    0  1: Q-8-J-T-A-K-7-6 (65) \n" +
                "    0  1  0: Q-8-J-T (32) \n" +
                "    0  1  0  0: Q-8 (16) \n" +
                "    0  1  0  0  0: Q (8) \n" +
                "    0  1  0  0  1: 8 (8) \n" +
                "    0  1  0  1: J-T (16) \n" +
                "    0  1  0  1  0: J (8) \n" +
                "    0  1  0  1  1: T (8) \n" +
                "    0  1  1: A-K-7-6 (33) \n" +
                "    0  1  1  0: A-K (16) \n" +
                "    0  1  1  0  0: A (8) \n" +
                "    0  1  1  0  1: K (8) \n" +
                "    0  1  1  1: 7-6 (17) \n" +
                "    0  1  1  1  0: 7 (8) \n" +
                "    0  1  1  1  1: 6 (9) \n" +
                "    1: P-5-4-D️-1-3-S️-C️-H️-W-Z-E (197) \n" +
                "    1  0: P-5-4-D️-1-3-S️ (89) \n" +
                "    1  0  0: P-5-4 (41) \n" +
                "    1  0  0  0: P (20) \n" +
                "    1  0  0  1: 5-4 (21) \n" +
                "    1  0  0  1  0: 5 (10) \n" +
                "    1  0  0  1  1: 4 (11) \n" +
                "    1  0  1: D️-1-3-S️ (48) \n" +
                "    1  0  1  0: D️-1 (24) \n" +
                "    1  0  1  0  0: D️ (12) \n" +
                "    1  0  1  0  1: 1 (12) \n" +
                "    1  0  1  1: 3-S️ (24) \n" +
                "    1  0  1  1  0: 3 (12) \n" +
                "    1  0  1  1  1: S️ (12) \n" +
                "    1  1: C️-H️-W-Z-E (108) \n" +
                "    1  1  0: C️-H️-W (52) \n" +
                "    1  1  0  0: C️-H️ (24) \n" +
                "    1  1  0  0  0: C️ (12) \n" +
                "    1  1  0  0  1: H️ (12) \n" +
                "    1  1  0  1: W (28) \n" +
                "    1  1  1: Z-E (56) \n" +
                "    1  1  1  0: Z (28) \n" +
                "    1  1  1  1: E (28) \n", s);
    }

    @Test
    public void testEncode1() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node code = huffmanCoding.generateCode();
        HuffmanCoding.HuffmanEncoder huffmanEncoder = new HuffmanCoding.HuffmanEncoder(code);
        Long[] longs = huffmanEncoder.encode(new String[]{null});
        assertEquals(1, longs.length);
        assertEquals(0x3600000000000000L, longs[0].longValue());
    }

    @Test
    public void testEncode2() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node code = huffmanCoding.generateCode();
        HuffmanCoding.HuffmanEncoder huffmanEncoder = new HuffmanCoding.HuffmanEncoder(code);
        String board = "https://www.bridgebase.com/tools/handviewer.html?lin=st||pn|Beowulf,blueceo,yinyichen,xinyu320|md|1SK2H862D976CKQT97,S4HQJ974DAK5CJ432,SAJT86HAKTDQJT3C8,SQ9753H53D842CA65|sv|e|rh||ah|Board%2019|mb|P|mb|1H|mb|1S|mb|P|mb|2C|mb|P|mb|3N|mb|P|mb|P|mb|P|pc|H5|pc|H2|pc|HJ|pc|HA|pc|DT|pc|D2|pc|D6|pc|DK|pc|S4|pc|ST|pc|SQ|pc|SK|pc|D7|pc|D5|pc|DQ|pc|D4|pc|D3|pc|D8|pc|D9|pc|DA|pc|H4|pc|HT|pc|H3|pc|H6|pc|DJ|pc|C5|pc|C7|pc|C4|pc|SA|pc|S3|pc|S2|pc|H7|pc|C8|pc|CA|pc|C9|pc|C2|pc|S5|pc|CT|pc|C3|pc|S6|pc|HK|pc|C6|pc|H8|pc|H9|pc|SJ|pc|S7|pc|CQ|pc|CJ|pc|S8|pc|S9|pc|CK|pc|HQ|";
        assertEquals(560, board.length());
        String[] strings = HuffmanCoding.parseLin(board);
        assertEquals(186, strings.length);
        StringBuilder sb = new StringBuilder();
        for (String string : strings) sb.append(string);
        System.out.println(sb.toString());
        // number of bytes in strings = 2 * 186 = 372
        Long[] longs = huffmanEncoder.encode(strings);
        assertEquals(15, longs.length);
        // number of bytes in Huffman code = 8 * 15 = 120
        assertEquals(0x69652F2A1DCFC350L, longs[0].longValue());
    }

    @Test
    public void testParseLin() {
        String board = "https://www.bridgebase.com/tools/handviewer.html?lin=st||pn|Beowulf,blueceo,yinyichen,xinyu320|md|1SK2H862D976CKQT97,S4HQJ974DAK5CJ432,SAJT86HAKTDQJT3C8,SQ9753H53D842CA65|sv|e|rh||ah|Board%2019|mb|P|mb|1H|mb|1S|mb|P|mb|2C|mb|P|mb|3N|mb|P|mb|P|mb|P|pc|H5|pc|H2|pc|HJ|pc|HA|pc|DT|pc|D2|pc|D6|pc|DK|pc|S4|pc|ST|pc|SQ|pc|SK|pc|D7|pc|D5|pc|DQ|pc|D4|pc|D3|pc|D8|pc|D9|pc|DA|pc|H4|pc|HT|pc|H3|pc|H6|pc|DJ|pc|C5|pc|C7|pc|C4|pc|SA|pc|S3|pc|S2|pc|H7|pc|C8|pc|CA|pc|C9|pc|C2|pc|S5|pc|CT|pc|C3|pc|S6|pc|HK|pc|C6|pc|H8|pc|H9|pc|SJ|pc|S7|pc|CQ|pc|CJ|pc|S8|pc|S9|pc|CK|pc|HQ|";
        String[] strings = HuffmanCoding.parseLin(board);
//        System.out.println(Arrays.toString(strings));
        assertEquals(186, strings.length);
    }

    @Test
    public void testDecode1() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node code = huffmanCoding.generateCode();
        HuffmanCoding.HuffmanDecoder huffmanDecoder = new HuffmanCoding.HuffmanDecoder(code);
        long empty = 0b00110110L << 56;
        long[] longs = {empty};
        String s = huffmanDecoder.decode(longs);
        assertEquals("", s);
    }

    @Test
    public void testDecode2() {
        HuffmanCoding huffmanCoding = HuffmanCoding.createHuffmanCoding();
        HuffmanCoding.Node code = huffmanCoding.generateCode();
        HuffmanCoding.HuffmanDecoder huffmanDecoder = new HuffmanCoding.HuffmanDecoder(code);
        long empty = 0b110100110110L << 52;
        long[] longs = {empty};
        String s = huffmanDecoder.decode(longs);
        assertEquals("W", s);
    }


}