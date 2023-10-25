package edu.neu.coe.info6205.game;

public class  Move<T> {
    final Integer row;
    final Integer column;
    T val;

    public Move(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Move(int row, int column, T val) {
        this.row = row;
        this.column = column;
        this.val = val;
    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public T getVal() {
        return val;
    }
}
