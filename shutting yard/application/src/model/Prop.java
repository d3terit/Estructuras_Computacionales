package model;

public class Prop {
    Character c;
    int col;
    
    public Character getC() {
        return c;

    }public Prop(Character c) {
        this.c = c;
        col = 0;
    }

    public Prop(Character c, int col) {
        this.c = c;
        this.col = col;
    }
    public void setC(Character c) {
        this.c = c;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    
}
