package model;

import java.util.ArrayList;

public class Table {
    private String input, output;
    private int[][] values, result;
    private ArrayList<Character> p, exp;
    private int rta;

    public Table() {
        p = new ArrayList<Character>();
        exp = new ArrayList<Character>();
        rta = 0;
        input = "";
        values = null;
        result = null;
    }

    public Table(int cols) {
        p = new ArrayList<Character>();
        exp = new ArrayList<Character>();
        for (int i = 0; i < cols; i++) {
            exp.add('.');
        }
        rta = 0;
        input = "";
        values = null;
        result = null;
    }

    public String getInput() {
        return input;
    }

    public String getOutput(){
        return output;
    }

    public void setInput(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public int[][] getValues() {
        return values;
    }

    public void setValues(int[][] values) {
        this.values = values;
    }

    public ArrayList<Character> getExp() {
        return exp;
    }

    public void setExp(ArrayList<Character> exp) {
        this.exp = exp;
    }

    public void addExp(int col ,Character c){
        exp.set(col, c);
    }

    public int[][] getResult() {
        return result;
    }

    public void setResult(int[][] result) {
        this.result = result;
    }

    public void addResult(int fil, int col ,int num){
        result[fil][col] = num;
    }

    public ArrayList<Character> getP() {
        return p;
    }

    public void setP(ArrayList<Character> p) {
        this.p = p;
    }

    public boolean addP(Character c){
        return p.add(c);
    }

    public int getRta() {
        return rta;
    }

    public void setRta(int rta) {
        this.rta = rta;
    }
}
