import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        refactor rf = new refactor();
        cond menos = new cond('-', 1);
        cond mas = new cond('+', 1);
        cond por = new cond('*', 2);
        cond entre = new cond('/', 2);
        cond ala = new cond('^', 3);
        cond[] cond = { menos, mas, por, entre, ala };
        rf.setCond(cond);
        try (Scanner entry = new Scanner(System.in)) {
            System.out.print("Ingrese la ecuación: ");
            // (1-2)^4*(4*(5/((5-3)^2))) = 5
            rf.aSufijo(entry.nextLine());
        }
        System.out.println("Notación sufija: " + rf.getCola());
        System.out.println("Resultado al operar: " + rf.calc());
    }
}

class refactor {
    private term cola, pila;
    private cond[] cond;

    public refactor() {
        cola = pila = null;
        cond = null;
    }

    public void setCond(cond[] cond) {
        this.cond = cond;
    }

    public String getCola() {
        String rta = "";
        for (term aux = cola; aux != null; aux = aux.term) {
            rta += aux.dig + " ";
        }
        return rta;
    }

    public void aSufijo(String exp) {
        char[] chx = exp.toCharArray();
        String aux = "";
        for (char c : chx) {
            if (Character.isLetterOrDigit(c)) //verifica si es letra o número
                aux += c;
            else if(c != ' ') { // no cuenta los espacios en blanco
                if (aux.length() > 0)
                    addCOla(aux);
                aux = "";
                controller(c);
            }
        }
        if (aux.length() > 0)
            addCOla(aux);
        while (pila != null) {
            String c = deletePila();
            if (c != "(")
                addCOla(c);
            ;
        }
    }

    private void controller(char ch) {
        if (ch == '(') {
            addPila(ch + "");
        } else if (ch == ')') {
            for (term x = pila; x != null; x = x.term) {
                deletePila();
                if (x.dig.equals("("))
                    break;
                else
                    addCOla(x.dig);
            }
        } else if (weight(ch) != -1) {
            if (pila != null) {
                if (weight(ch) > weight(pila.dig.charAt(0)))
                    addPila(ch + "");
                else {
                    addCOla(deletePila());
                    controller(ch);
                }
            } else
                addPila(ch + "");
        }
    }

    private int weight(char ch) {
        for (cond cond : cond) {
            if (cond.ch == ch)
                return cond.weight;
        }
        return -1;
    }

    private void addPila(String sPila) {
        term aux = new term(sPila);
        if (pila != null)
            aux.term = pila;
        pila = aux;
    }

    private String deletePila() {
        String exp = pila.dig;
        pila = pila.term;
        return exp;
    }

    private void addCOla(String sCola) {
        term aux = new term(sCola);
        if (cola == null)
            cola = aux;
        else {
            for (term rec = cola; rec != null; rec = rec.term) {
                if (rec.term == null) {
                    rec.term = aux;
                    break;
                }
            }
        }
    }

    public float calc() {
        float rta = 0;
        for (term aux = cola; aux != null; aux = aux.term) {
            if (aux.dig.length() == 1) {
                if (!Character.isLetterOrDigit(aux.dig.charAt(0))) {
                    rta = operacion(aux.dig.charAt(0));
                    addPila(String.valueOf(rta));
                } else {
                    addPila(aux.dig);
                }
            } else
                addPila(aux.dig);
        }
        rta = operacion('+');
        return rta;
    }

    private float operacion(char ch) {
        float rta, a, b;
        rta = 0;
        if (pila != null)
            b = Float.parseFloat(deletePila());
        else
            b = 0;
        if (pila != null)
            a = Float.parseFloat(deletePila());
        else
            a = 0;
        switch (ch) {
            case '+':
                rta = a + b;
                break;
            case '-':
                rta = a - b;
                break;
            case '*':
                rta = a * b;
                break;
            case '/':
                rta = a / b;
                break;
            case '^':
                rta = 1;
                for (int i = 1; i <= b; i++) {
                    rta *= a;
                }
                break;
            default:
                break;
        }
        return rta;
    }
}

class term {
    public String dig;
    public term term;

    public term(String dig) {
        this.dig = dig;
        this.term = null;
    }
}

class cond {
    public char ch;
    public int weight;

    public cond(char ch, int weight) {
        this.ch = ch;
        this.weight = weight;
    };
}
