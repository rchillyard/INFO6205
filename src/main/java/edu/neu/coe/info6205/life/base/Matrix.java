package edu.neu.coe.info6205.life.base;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiFunction;

/**
 * This class represents the physical 2-dimensional layout of a Group.
 * By convention, it must have clear rows and columns around any live cells.
 * <p>
 * This class was designed to optimize the performance of Groups.
 * However, it hasn't been thoroughly tested and I'm fairly sure it needs significant work.
 */
class Matrix {
    /**
     * Constructor designed for creating a blank Matrix.
     *
     * @param width  the width.
     * @param height the height.
     */
    Matrix(int width, int height) {
        this(width, height, (x, y) -> 0, (x, y) -> 0L);
    }

    Matrix(Matrix source, int width0, int widthN, int height0, int heightN) {
        this(source.width + width0 + widthN, source.height + height0 + heightN, source.count, shift(source.copyCells(), width0, widthN, height0, heightN));
    }

    /**
     * Constructor designed for creating a blank Matrix.
     * NOTE: the count and bits functions must be consistent.
     *
     * @param width         the width.
     * @param height        the height.
     * @param countFunction the count function.
     * @param bitsFunction  the initialization function.
     */
    Matrix(int width, int height, BiFunction<Integer, Integer, Integer> countFunction, BiFunction<Integer, Integer, Long> bitsFunction) {
        this(width, height, countFunction.apply(width, height), initializeCells(width, height, bitsFunction));
    }

    /**
     * Constructor designed for creating the next generation of a Matrix.
     * Note that only "fit" Matrix instances can be cloned in this way.
     *
     * @param width  the width.
     * @param height the height.
     * @param count  the count of live cells.
     * @param cells  the representation of cells.
     */
    private Matrix(int width, int height, int count, Bits[][] cells) {
        this.width = width;
        this.height = height;
        this.count = count;
        this.cells = cells;
        this.fit = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return width == matrix.width &&
                height == matrix.height &&
                count == matrix.count &&
                fit == matrix.fit &&
                cellsEqual(matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(width, height, count, fit);
        result = 31 * result + Arrays.hashCode(cells);
        return result;
    }

    /**
     * Add a cell at Point p.
     *
     * @param p the point at which we want a new cell.
     */
    void addCell(Point p) {
        Bit x = new Bit(p.getX());
        final Bits bits = getBits(p);
        bits.or(x.getMask());
        count++;
    }

    /**
     * Add a cell at Point p.
     *
     * @param p the point at which we want to remove a cell.
     */
    void removeCell(Point p) {
        Bit x = new Bit(p.getX());
        final Bits bits = getBits(p);
        bits.and(x.flip().getMask());
        count--;
    }

    /**
     * Method to determine if point p is a cell.
     *
     * @param p the point in question.
     * @return true if p is alive, false if not.
     */
    boolean isCell(Point p) {
        Bit x = new Bit(p.getX());
        final Bits bits = getBits(p);
        long mask = bits.test(x.getMask());
        return mask != 0L;
    }

    /**
     * Method to get the current count of live cells in this Matrix.
     *
     * @return the count.
     */
    int getCount() {
        return count;
    }

    boolean cellsEqual(Matrix matrix) {
        boolean ok = cells.length == matrix.cells.length;
        for (int j = 0; ok && j < cells.length; j++) {
            Bits[] rowA = row(j);
            Bits[] rowB = matrix.row(j);
            ok = rowA.length == rowB.length;
            for (int i = 0; ok && i < rowA.length; i++)
                ok = rowA[i].equals(rowB[i]);
        }
        return ok;
    }

    /**
     * Method to get the neighbors of each cell according to the current state of this Matrix.
     *
     * @return an instance of Neighbors.
     */
    Neighbors getNeighbors() {
        return new Neighbors();
    }

    /**
     * Method to get a "pretty" String representing this Matrix.
     *
     * @return a String which graphically shows this Matrix.
     */
    String render() {
        return new Pretty().toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < height; j++)
            for (int i = 0; i < row(j).length; i++)
                sb.append(Long.toHexString(getBits(j, i).bits)).append(Newline);
        return sb.toString();
    }

    private Bits[] row(int y) {
        if (y >= 0 && y < cells.length)
            return cells[y];
        else
            throw new ArrayIndexOutOfBoundsException("No such row: " + y);
    }

    /**
     * Class to aid in the construction of a "pretty" representation of the Matrix.
     */
    private class Pretty {

        Pretty() {
            this.sb = new StringBuilder(Space);
        }

        @Override
        public String toString() {
            for (int i = 0; i < width; i++) sb.append(Dash);
            sb.append(Newline);
            for (int j = 0; j < height; j++) {
                sb.append(Vbar);
                final Bits[] row = row(j);
                for (Bits bits : row) sb.append(bits);
                sb.append(Vbar).append(Newline);
            }
            sb.append(Space);
            for (int i = 0; i < width; i++) sb.append(Dash);
            sb.append(Newline);
            return sb.toString();
        }

        private static final String Vbar = "|";
        private static final String Space = " ";

        private final StringBuilder sb;
    }

    /**
     * Class to represent the neighbor counts of the current state of this Matrix.
     */
    class Neighbors {

        /**
         * Constructor.
         */
        Neighbors() {
            this.neighbors = new int[width][height];
            countNeighbors();
            assert (doCountsMatch());
        }

        /**
         * Method to get the number of neighbors of the point p.
         *
         * @param p the point in question.
         * @return the number of neighbors, or -1 if point is out of range (maybe should throw exception).
         */
        int getCount(Point p) {
            if (isValid(p)) return getCount(p.getX(), p.getY());
            else return -1;
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < height; j++) {
                for (int i = 0; i < width; i++)
                    sb.append(neighbors[i][j]);
                sb.append(Newline);
            }

            return sb.toString();
        }

        boolean doCountsMatch() {
            int total = 0;
            for (int j = 0; j < height; j++)
                for (int i = 0; i < width; i++)
                    total += neighbors[i][j];
            return total == count * 8;
        }

        private void countNeighbors() {
            NeighborhoodMask mask = new NeighborhoodMask();
            for (int j = 0; j < height; j++) {
                long bits = HighBit;
                for (int i = 0; i < width; i++) {
                    // NOTE: we do the logic here instead of in a Bit instance for performance reasons.
                    final long l = getBits(j, i / BitsPerLong).test(bits);
                    if (l != 0L) mask.updateNeighborhood(i, j);
                    bits >>= 1;
                }
            }
        }

        private class NeighborhoodMask {
            void updateNeighborhood(int i, int j) {
                for (int k = 0; k < Three; k++)
                    for (int l = 0; l < Three; l++) {
                        int v = i + k - 1;
                        int w = j + l - 1;
                        if (v >= 0 && v < width && w >= 0 && w < height)
                            neighbors[v][w] += mask[k][l];
                    }
            }

            NeighborhoodMask() {
                mask = getMask();
            }

            private final int[][] mask;

            private int[][] getMask() {
                int[][] mask = new int[Three][Three];
                for (int k = 0; k < Three; k++) {
                    for (int l = 0; l < Three; l++)
                        mask[k][l] = 1;
                }
                mask[1][1] = 0;
                return mask;
            }

        }

        private int getCount(int i, int j) {
            return neighbors[i][j];
        }

        /**
         * The array of neighbor counts.
         */
        private final int[][] neighbors;
    }

    private boolean isValid(Point p) {
        return isValid(p.getX(), p.getY());
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    /**
     * This static inner class represents a series of up to 64 bits in the matrix.
     */
    static class Bits {
        private long bits; // the actual bit values.
        private int length; // the number of bits which are significant.

        Bits(long bits, int length) {
            this.bits = bits;
            this.length = Math.min(length, BitsPerLong);
        }

        Bits(long bits) {
            this(bits, BitsPerLong);
        }

        Bits(int length) {
            this(0L, length);
        }

        Bits() {
            this(0L);
        }

        Bits(Bits source) {
            this(source.bits, source.length);
        }

        void shift(int places) {
            bits >>= places;
        }

        void extend(int newLength) {
            length = newLength;
        }

        private void or(long mask) {
            bits |= mask;
        }

        private void and(long mask) {
            bits &= mask;
        }

        private long test(long mask) {
            mask &= bits;
            return mask;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            long mask = HighBit;
            for (int i = 0; i < length; i++) {
                sb.append(getGlyph(mask));
                mask >>= 1;
            }
            return sb.toString();
        }

        private boolean isSet(long row) {
            return test(row) != 0;
        }

        private String getGlyph(long row) {
            return isSet(row) ? "*" : ".";
        }

        static int index(int x) {
            return x / BitsPerLong;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bits bits1 = (Bits) o;
            return bits == bits1.bits &&
                    length == bits1.length;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bits, length);
        }
    }

    /**
     * This class deals with bit operations required for the row-representation of cells.
     */
    private static class Bit {

        /**
         * Constructor:
         *
         * @param bit   the index of the bit (0..63) which this Bit represents.
         * @param index the index of long in the Matrix row which this Bit represents.
         * @param on    is true if this represents an "on" bit.
         */
        Bit(int bit, int index, boolean on) {
            this.bit = bit;
            this.index = index;
            this.on = on;
        }

        /**
         * Constructor:
         *
         * @param x  is the (x) index of a point on the grid.
         * @param on is true if this represents an "on" bit.
         */
        Bit(int x, boolean on) {
            this(x % BitsPerLong, x / BitsPerLong, on);
        }

        /**
         * Constructor:
         *
         * @param x is the (x) index of a point on the grid.
         */
        Bit(int x) {
            this(x, true);
        }

        /**
         * Constructor: for copying an existing Bit.
         *
         * @param bits the source instance of Bit.
         */
        Bit(Bit bits) {
            this(bits.bit, bits.index, bits.on);
        }

        private long getMask() {
            long mask = HighBit;
            if (!on) mask = ~mask;
            mask >>= bit;
            return mask;
        }

        /**
         * The index of the appropriate bit, with 0 corresponding to the highest bit,
         * and 63 corresponding to the lowest bit.
         */
        private final int bit;

        /**
         * The index of the long which contains this point.
         */
        private final int index;

        /**
         * True if this represents an "on" bit, else false.
         */
        private final boolean on;

        Bit flip() {
            return new Bit(bit, index, !on);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Bit bit1 = (Bit) o;
            return bit == bit1.bit &&
                    index == bit1.index &&
                    on == bit1.on;
        }

        @Override
        public int hashCode() {
            return Objects.hash(bit, index, on);
        }
    }

    private Bits getBits(int y, int index) {
        return row(y)[index];
    }

    private Bits getBits(Point p) {
        return getBits(p.getY(), Bits.index(p.getX()));
    }

    private static Bits[][] initializeCells(int width, int height, BiFunction<Integer, Integer, Long> function) {
        final Bits[][] bits = new Bits[height][width / BitsPerLong + 1];
        for (int j = 0; j < height; j++) {
            int w = width;
            for (int i = 0; i < bits[j].length; i++, w -= BitsPerLong) bits[j][i] = new Bits(function.apply(i, j), w);
        }
        return bits;
    }

    private static Bits[][] shift(Bits[][] cells, int width0, int widthN, int height0, int heightN) {
        int cellsLen = cells.length;
        // FIXME read but never written
        final Object[] objects1 = new Object[cellsLen + height0 + heightN];
        int objects1Len = objects1.length;
        Bits[][] rows = Arrays.copyOf(objects1, objects1Len, Bits[][].class);
        int rowsLen = rows.length;
        // FIXME read but never written
        final Object[] objects2 = new Object[cells[0].length];
        int objects2Len = objects2.length;
        Bits[] emptyRow0 = Arrays.copyOf(objects2, objects2Len, Bits[].class);
        Bits[] emptyRowN = Arrays.copyOf(objects2, objects2Len, Bits[].class);
        int emptyRowLen = emptyRow0.length;
        for (int i = 0; i < emptyRowLen; i++) {
            emptyRow0[i] = new Bits(cells[0][0].length);
            emptyRowN[i] = new Bits(cells[0][0].length);
        }
        System.arraycopy(cells, 0, rows, height0, cellsLen);
        if (height0 > 0) rows[0] = emptyRow0;
        if (heightN > 0) rows[rowsLen - 1] = emptyRowN;
        for (final Bits[] row : rows) {
            final Bits bits = shift(row, width0, widthN);
            if (bits.length > 0) // extend rows[j] with new element
                throw new RuntimeException("NotYetImplemented");
        }
        return rows;
    }

    private static Bits shift(Bits[] row, int width0, int widthN) {
        Bits bitsPre = new Bits(width0);
        Bits bitsPost = new Bits(0);
        for (int i = 0; i < row.length; i++) {
            if (i == row.length - 1) bitsPost = new Bits(widthN);
            bitsPre = shift(bitsPre, row[i], bitsPost);
        }
        return bitsPre;
    }

    private static Bits shift(Bits bitsPre, Bits bits, Bits bitsPost) {
        final int newLength = bits.length + bitsPre.length + bitsPost.length;
        int overflow = Math.max(newLength - BitsPerLong, 0);
        long carryOver = bits.bits << BitsPerLong - overflow;
        bits.shift(bitsPre.length);
        bits.extend(newLength);
        bits.or(bitsPre.bits);
        final Bits bits1 = new Bits(bitsPost);
        bits1.shift(bits.length + bitsPre.length);
        bits.or(bits1.bits);
        return new Bits(carryOver, overflow);
    }

    /**
     * This constant represents the side of the square of influence of a cell.
     */
    private static final int Three = 3;

    /**
     * The number of bits in a long word.
     */
    private static final int BitsPerLong = 64;

    /**
     * This constant represents a single bit at the high end of the 64 possible bits.
     */
    private static final long HighBit = 0x80000000L;

    private static final String Newline = "\n";

    private static final String Dash = "−";

    /**
     * These represent the bits corresponding to cells.
     */
    private final Bits[][] cells;

    private Bits[][] copyCells() {
        Matrix.Bits[][] result = Arrays.copyOf(new Object[cells.length], cells.length, Matrix.Bits[][].class);
        for (int i = 0; i < cells.length; i++) {
            result[i] = Arrays.copyOf(cells[i], cells[i].length, Bits[].class);
            for (int j = 0; j < result[i].length; j++)
                result[i][j] = new Bits(cells[i][j]);
        }
        return result;
    }

    private int getWidth() {
        return width;
    }

    private int getHeight() {
        return height;
    }

    private boolean isFit() {
        return fit;
    }

    /**
     * This is the overall width of this Matrix.
     * NOTE: there should be no cells in the outer rows/columns.
     */
    private final int width;

    /**
     * This is the overall height of this Matrix.
     * Ditto as for width.
     */
    private final int height;

    /**
     * This is the count of cells in this Matrix.
     */
    private int count;

    /**
     * This indicates whether all of the points fit inside the single outer rows/columns of this Matrix.
     */
    // TODO implement me
    private final boolean fit;

}
