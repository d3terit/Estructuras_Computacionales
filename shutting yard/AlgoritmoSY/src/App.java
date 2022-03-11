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
        rf.aSufijo("(1-2)^4*(4*(5/((5-3)^2)))");
        System.out.println(rf.getExit());
    }
}

class refactor {
    private String exit;
    private term pila;
    private cond[] cond;

    public refactor() {
        exit = "";
        pila = null;
        cond = null;
    }

    public void setCond(cond[] cond) {
        this.cond = cond;
    }

    public String getExit() {
        return exit;
    }

    public void aSufijo(String exp) {
        char[] chx = exp.toCharArray();
        for (char c : chx) {
            if (Character.isLetterOrDigit(c))
                exit += c;
            else {
                controller(c);
            }
        }
        while (pila != null) {
            char c = deletePila();
            if (c != '(')
                exit += c;
        }
    }

    private void controller(char ch) {
        if (ch == '(') {
            addPila(ch);
        } else if (ch == ')') {
            for (term x = pila; x != null; x = x.term) {
                deletePila();
                if (x.dig == '(')
                    break;
                else
                    exit += x.dig;
            }
        } else if (weight(ch) != -1) {
            if (pila != null) {
                if (weight(ch) > weight(pila.dig))
                    addPila(ch);
                else {
                    exit += deletePila();
                    controller(ch);
                }
            } else
                addPila(ch);
        }
    }

    private int weight(char ch) {
        for (cond cond : cond) {
            if (cond.ch == ch)
                return cond.weight;
        }
        return -1;
    }

    private void addPila(char sPila) {
        term aux = new term(sPila);
        if (pila != null)
            aux.term = pila;
        pila = aux;
    }

    private char deletePila() {
        char exp = pila.dig;
        pila = pila.term;
        return exp;
    }
}

class term {
    public char dig;
    public term term;

    public term(char dig) {
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