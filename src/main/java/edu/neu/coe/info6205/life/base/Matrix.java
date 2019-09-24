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
				this.cells = new long[height][width / 64 + 1];
		}

		void addCell(Point p) {
				Bit x = new Bit(p.getX());
				x.or(row(p.getY()));
		}

		boolean isCell(Point p) {
				Bit x = new Bit(p.getX());
				long mask = x.test(row(p.getY()));
				return mask != 0L;
		}

		int[][] countNeighbors() {
				int[][] neighbors = new int[height][width];
				NeighborhoodMask mask = new NeighborhoodMask();
				for (int j = 1; j < height - 1; j++) {
						long bits = 0xE0000000L;
						for (int i = 1; i < width - 1; i++) {
								int index = i / 64;
								if ((row(j)[index] & bits) != 0L) mask.updateNeighborhood(neighbors, j, i);
								bits >>= 1;
						}
				}
				for (int j = 0; j < height; j++)
						for (int i = 0; i < width; i++)
								neighbors[i][j] += neighbors[i][j] / 8;
				return neighbors;
		}

		String render() {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < width; i++) sb.append("_");
				sb.append("\n");
				for (int j = 0; j < height; j++) {
						long bits = LEFTMOST_BIT;
						for (int i = 0; i < width; i++) {
								sb.append((row(j)[i / 64] & bits) != 0L ? "*" : " ");
								bits >>= 1;
						}
						sb.append("|\n");
				}
				for (int i = 0; i < width; i++) sb.append("_");
				sb.append("\n");
				return sb.toString();
		}

		@Override
		public String toString() {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < height; j++)
						for (int i = 0; i < row(j).length; i++)
								sb.append(Long.toHexString(row(j)[i])).append("\n");
				return sb.toString();
		}

		private long[] row(int x) {
				if (x >= 0 && x < cells.length)
						return cells[x];
				else
						throw new ArrayIndexOutOfBoundsException("No such row: " + x);
		}

		private static class NeighborhoodMask {
				void updateNeighborhood(int[][] neighbors, int j, int i) {
						for (int k = 0; k < 3; k++)
								for (int l = 0; l < 3; l++)
										neighbors[i + k - 1][j + l - 1] += mask[k][l];
				}

				NeighborhoodMask() {
						mask = getMask();
				}

				private final int[][] mask;

				private static int[][] getMask() {
						int[][] mask = new int[3][3];
						for (int k = 0; k < 3; k++) {
								for (int l = 0; l < 3; l++)
										mask[k][l] = 1;
						}
						mask[1][1] = 0;
						return mask;
				}

		}

		private static class Bit {

				private final int bit;
				private final int index;

				Bit(int x) {
						bit = x % 64;
						index = x / 64;
				}

				private void or(long[] row) {
						long mask = LEFTMOST_BIT;
						mask >>= bit;
						row[index] |= mask;
				}

				private void and(long[] row) {
						long mask = LEFTMOST_BIT;
						mask >>= bit;
						row[index] &= mask;
				}

				private long test(long[] row) {
						long mask = LEFTMOST_BIT;
						mask >>= bit;
						mask &= row[index];
						return mask;
				}
		}

		private static final long LEFTMOST_BIT = 0x80000000L;
		private final long[][] cells;
		private final int width;
		private final int height;

}
