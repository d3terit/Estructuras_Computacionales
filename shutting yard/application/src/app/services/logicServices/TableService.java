package app.services.logicServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import model.Table;

import model.Prop;

public class TableService {
    private static TableService service;
    private Stack<String> stack;
    
    public TableService(){
        stack = new Stack<String>();
    }

    public Table entrada(ArrayList<Prop> elements, String input) {
        Table table = new Table(elements.size());
        table.setInput(input);
        for (Prop element : elements) {
            table.addExp(element.getCol(), element.getC());
            if (Character.isLetterOrDigit(element.getC()) && !table.getP().contains(element.getC()))
                table.addP(element.getC());
        }
        Collections.sort(table.getP());
        table.setValues(matrizP(table));
        table.setResult(matrizE(table));
        resolve(table, elements);
        return table;
    }

    private int[][] matrizP(Table table) {
        int filas = 2;
        for (int i = 1; i < table.getP().size(); i++)
            filas *= 2;
        int[][] m = new int[filas][table.getP().size()];
        for (int i = 0; i < m[0].length; i++) {
            int ff = filas / 2;
            for (int index = 0; index < i; index++)
                ff /= 2;
            int f1 = ff;
            for (int j = 0; j < m.length; j++) {
                if ((j + 1) < f1)
                    m[j][i] = 1;
                else if ((j + 1) == f1) {
                    m[j][i] = 1;
                    j += ff;
                    f1 += ff * 2;
                }
            }
        }
        return m;
    }

    private int[][] matrizE(Table table) {
        int filas = table.getValues().length;
        int[][] m = new int[filas][table.getExp().size()];
        for (int i = 0; i < table.getExp().size(); i++) {
            for (int j = 0; j < table.getP().size(); j++) {
                if (table.getExp().get(i).equals(table.getP().get(j))) {
                    for (int k = 0; k < m.length; k++) {
                        m[k][i] = table.getValues()[k][j];
                    }
                }
            }
        }
        return m;
    }

    private void resolve(Table table, ArrayList<Prop> elements){
        int op = 0;
        for (int i = 0; i < table.getResult().length; i++) {
            for (Prop element : elements) {
                if(!Character.isLetterOrDigit(element.getC())){
                    int col = element.getCol();
                    op = operacion(element.getC());
                    stack.add(String.valueOf(op));
                    table.addResult(i, col, op);
                    table.setRta(col);
                }else{
                    for (int l = 0; l < table.getP().size(); l++) {
                        if (element.getC().equals(table.getP().get(l))) {
                            stack.add(String.valueOf(table.getValues()[i][l]));
                        }
                    }
                }
            }
        }
    }

    private int operacion(char ch) {
        int rta, a, b;
        rta = a = b = 0;
        if (!stack.empty())
            b = Integer.parseInt(stack.pop());
        if (!stack.empty() && ch != '¬')
            a = Integer.parseInt(stack.pop());
        switch (ch) {
            case '∧':
                rta = a*b;
                break;
            case '∨':
                rta = a + b;
                if(rta>1) rta = 1;
                break;
            case '⊻':
                rta = a + b;
                if(rta>1) rta = 0;
                break;
            case '⮕':
                if(a==1 & b == 0) rta = 0;
                else rta = 1;
                break;
            case '⟷':
                if(a == b) rta = 1;
                break;
            case '¬':
                if(b==1) rta = 0;
                else rta = 1;
                break;
            default:
                break;
        }
        return rta;
    }
    
    public static TableService getService(){
        if (service == null) service = new TableService();
        return service;
    }
}
