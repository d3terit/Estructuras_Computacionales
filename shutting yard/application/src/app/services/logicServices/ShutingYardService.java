package app.services.logicServices;

import java.util.ArrayList;
import java.util.Stack;

import model.Prop;

public class ShutingYardService {
    private static ShutingYardService service;
    private Stack<Prop> stack;
    private ArrayList<Prop> elements;

    public ShutingYardService() {
        stack = new Stack<Prop>();
        elements = new ArrayList<Prop>();
    }

    public ArrayList<Prop> aSufijo(String exp) {
        elements = new ArrayList<Prop>();
        int parentesis = 0;
        char[] chx = exp.toCharArray();
        int col = 0;
        for (char c : chx) {
            if (Character.isLetterOrDigit(c)) // verifica si es letra o número
                elements.add(new Prop(c, col));
            else {
                if (c == '(' || c == ')'){
                    col--;
                    if(c == '(') parentesis ++;
                    else if(parentesis >0) parentesis --;
                }
                controller(c, col);
            }
            col++;
        }
        if(parentesis !=  0) return null;
        while (!stack.empty()) {
            Prop c = stack.pop();
            if (!c.getC().equals('('))
                elements.add(c);
            ;
        }
        return elements;
    }

    private void controller(Character ch, int col) {
        if (ch == '(') {
            stack.push(new Prop(ch));
        } else if (ch == ')') {
            while (!stack.empty()) {
                Prop x = stack.pop();
                if (!x.getC().equals('('))
                    elements.add(x);
                else
                    break;
            }
        } else if (weight(ch) != -1) {
            if (!stack.empty()) {
                if (weight(ch) > weight(stack.peek().getC()) || (weight(ch) == weight(stack.peek().getC()) && weight(ch) == 3)) {
                    stack.add(new Prop(ch, col));
                }else{
                    Prop x = stack.pop();
                    elements.add(x);
                    controller(ch, col);
                }
            } else {
                stack.add(new Prop(ch, col));
            }
        }
    }

    private int weight(char ch) {
        switch (ch) {
            case '∧':
            case '∨':
            case '⊻':
                return 1;
            case '⮕':
            case '⟷':
                return 2;
            case '¬':
                return 3;
            default:
                return -1;
        }
    }

    public static ShutingYardService getService() {
        if (service == null)
            service = new ShutingYardService();
        return service;
    }
}