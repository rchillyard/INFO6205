package edu.neu.coe.info6205.life.base;

import edu.neu.coe.info6205.reduction.Point;

/**
 * This class represents the physical 2-dimensional layout of a Group.
 * By convention, it must have clear rows and columns around any live cells.
 */
class Matrix {

		Matrix(int width, int height) {
				this.width = width;
				this.height = height;
				this.cells = new long[height][width / Bit.BitsPerLong + 1];
		}

		/**
		 * Add a cell at Point p.
		 *
		 * @param p the point at which we want a new cell.
		 */
		void addCell(Point p) {
				Bit x = new Bit(p.getX());
				x.or(row(p.getY()));
		}

		/**
		 * Method to determine if point p is a cell.
		 * @param p the point in question.
		 * @return true if p is alive, false if not.
		 */
		boolean isCell(Point p) {
				Bit x = new Bit(p.getX());
				long mask = x.test(row(p.getY()));
				return mask != 0L;
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
								sb.append(Long.toHexString(row(j)[i])).append(Newline);
				return sb.toString();
		}

		private long[] row(int x) {
				if (x >= 0 && x < cells.length)
						return cells[x];
				else
						throw new ArrayIndexOutOfBoundsException("No such row: " + x);
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
								final long[] row = row(j);
								for (int i = 0; i < width; i++) sb.append(new Bit(i).getGlyph(row));
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

				private void countNeighbors() {
						NeighborhoodMask mask = new NeighborhoodMask();
						for (int j = 0; j < height; j++) {
								long bits = HighBit;
								for (int i = 0; i < width; i++) {
										// NOTE: we do the logic here instead of in a Bit instance for performance reasons.
										final long l = row(j)[i / Bit.BitsPerLong] & bits;
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
		 * This class deals with bit operations required for the row-representation of cells.
		 */
		private static class Bit {

				/**
				 * Constructor:
				 * @param x is the (x) index of a point on the grid.
				 */
				Bit(int x) {
						bit = x % BitsPerLong;
						index = x / BitsPerLong;
				}

				private void or(long[] row) {
						long mask = HighBit;
						mask >>= bit;
						row[index] |= mask;
				}

				private void and(long[] row) {
						long mask = HighBit;
						mask >>= bit;
						row[index] &= mask;
				}

				private long test(long[] row) {
						long mask = HighBit;
						mask >>= bit;
						mask &= row[index];
						return mask;
				}

				private boolean isSet(long[] row) {
						return test(row) != 0;
				}

				private String getGlyph(long[] row) {
						return isSet(row) ? "*" : ".";
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

				static final int BitsPerLong = 64;
		}

		/**
		 * This constant represents the side of the square of influence of a cell.
		 */
		private static final int Three = 3;

		/**
		 * This constant represents a single bit at the high end of the 64 possible bits.
		 */
		private static final long HighBit = 0x80000000L;

		private static final String Newline = "\n";

		private static final String Dash = "−";

		/**
		 * These represent the bits corresponding to cells.
		 */
		private final long[][] cells;

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
}
