package algoritmo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//prueba de funcionamiento tablas de verdad
public class proposiciones {
    public static void main(String[] args) {
        refactor3 rf = new refactor3();
        try (Scanner entry = new Scanner(System.in)) {
            //((p#q)#r)&!((p#q)&r)
            //(((p#q)#r)&!((p#q)&r))~q
            System.out.print("Ingrese la ecuación: ");
            rf.entrada(entry.nextLine());
        }
    }
}

class refactor3 {
    private term2 cola, pila;
    private ArrayList<Character> p;
    private ArrayList<Character> exp;
    private int[][] pro;
    private int[][] calc;

    public refactor3() {
        cola = pila = null;
        p = new ArrayList<Character>();
        exp = new ArrayList<Character>();
        pro = calc = null;
    }

    public String getCola() {
        String rta = "";
        for (term2 aux = cola; aux != null; aux = aux.term2) {
            rta += aux.dig + " ";
        }
        return rta;
    }

    public void entrada(String input) {
        ArrayList<Character> aux = new ArrayList<Character>();
        char[] chx = input.toCharArray();
        for (char c : chx) {
            if(c != ' '){
                aux.add(c);
                if(c != '(' && c!= ')') exp.add(c);  
                if (Character.isLetter(c) && !p.contains(c))
                    p.add(c);
            };
        }
        Collections.sort(p);
        pro = matrizP();
        calc = matrizE();
        aSufijo(chx);
        System.out.println(getCola());
        resolve();
        print(aux);
    }

    public int[][] matrizP() {
        int filas = 2;
        for (int i = 1; i < p.size(); i++)
            filas *= 2;
        int[][] m = new int[filas][p.size()];
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

    public int[][] matrizE() {
        int filas = 2;
        for (int i = 1; i < p.size(); i++)
            filas *= 2;
        int[][] m = new int[filas][exp.size()];
        for (int i = 0; i < exp.size(); i++) {
            for (int j = 0; j < p.size(); j++) {
                if (exp.get(i) == p.get(j)) {
                    for (int k = 0; k < m.length; k++) {
                        m[k][i] = pro[k][j];
                    }
                }
            }
        }
        return m;
    }

    private void resolve(){
        int rta = 0;
        for (int i = 0; i < calc.length; i++) {
            for (term2 aux = cola; aux != null; aux = aux.term2) {
                if(!Character.isLetter(aux.dig.charAt(0))){
                    int col = aux.col;
                    rta = operacion(aux.dig.charAt(0));
                    addPila(String.valueOf(rta));
                    calc[i][col] = rta;
                }else{
                    for (int l = 0; l < p.size(); l++) {
                        if (aux.dig.equals(p.get(l)+"")) {
                            addPila(String.valueOf(pro[i][l]));
                        }
                    }
                }
            }
        }
    }

    private int operacion(char ch) {
        int rta, a, b;
        rta = a = b = 0;
        if (pila != null)
            b = Integer.parseInt(deletePila());
        if (pila != null && ch != '!')
            a = Integer.parseInt(deletePila());
        switch (ch) {
            case '&':
                rta = a*b;
                break;
            case '#':
                rta = a + b;
                if(rta>1) rta = 1;
                break;
            case '$':
                rta = a + b;
                if(rta>1) rta = 0;
                break;
            case '>':
                if(a==1 & b == 0) rta = 0;
                else rta = 1;
                break;
            case '~':
                if(a == b) rta = 1;
                break;
            case '!':
                if(b==1) rta = 0;
                else rta = 1;
                break;
            default:
                break;
        }
        return rta;
    }

    public void print(ArrayList<Character> aux) {
        for (Character c : p)
            System.out.print(c + " ");
        System.out.print("\t\t");
        for (int i = 0; i < aux.size(); i++)
            if(aux.get(i) == '(') System.out.print(aux.get(i));
            else if((i+1) < aux.size()){
                if(aux.get(i+1) == ')') System.out.print(aux.get(i));
                else System.out.print(aux.get(i) + "\t");
            }
            else System.out.print(aux.get(i) + "\t");
        System.out.println("\n");
        for (int i = 0; i < pro.length; i++) {
            for (int j : pro[i]) {
                if (j == 1)
                    System.out.print("V ");
                else
                    System.out.print("F ");
            }
            System.out.print("\t\t");
            for (int j =0; j< calc[0].length; j++) {
                if (calc[i][j] == 1)
                    System.out.print("V\t");
                else
                    System.out.print("F\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void aSufijo(char[] chx) {
        String aux = "";
        int i = 0;
        for (char c : chx) {
            if (Character.isLetterOrDigit(c)) // verifica si es letra o número
                aux += c;
            else if (c != ' ') { // no cuenta los espacios en blanco
                if (aux.length() > 0)
                    addCola(aux);
                aux = "";
                if(c == '(' || c == ')') i--;
                controller(c, i);
            }
            i++;
        }
        if (aux.length() > 0)
            addCola(aux);
        while (pila != null) {
            int col =pila.col;
            String c = deletePila();
            if (!c.equals("("))
                addCola(c,col);
            ;
        }
    }

    private void controller(char ch, int col) {
        if (ch == '(') {
            addPila(ch + "");
        } else if (ch == ')') {
            for (term2 x = pila; x != null; x = x.term2) {
                int colX = pila.col;
                String c = deletePila();
                if (!c.equals("("))
                    addCola(c, colX);
                else
                    break;
            }
        } else if (weight(ch) != -1) {
            if (pila != null) {
                if (weight(ch) >= weight(pila.dig.charAt(0))){
                    addPila(ch + "");
                    pila.col = col;
                }
                else {
                    int colX = pila.col;
                    String c = deletePila();
                    if (!c.equals("(")){
                        addCola(c,colX);
                    }
                    controller(ch, col);
                }
            } else{
                addPila(ch + "");
                pila.col = col;
            }
        }
    }

    private int weight(char ch) {
        switch (ch) {
            case '&':
            case '#':
            case '$':
            case '>':
            case '~':
                return 1;
            case '!':
                return 2;
            default:
                return -1;
        }
    }

    private void addPila(String sPila) {
        term2 aux = new term2(sPila);
        if (pila != null)
            aux.term2 = pila;
        pila = aux;
    }

    private String deletePila() {
        String exp = pila.dig;
        pila = pila.term2;
        return exp;
    }

    private void addCola(String sCola) {
        term2 aux = new term2(sCola);
        if (cola != null) {
            for (term2 rec = cola; rec != null; rec = rec.term2) {
                if (rec.term2 == null) {
                    rec.term2 = aux;
                    break;
                }
            }
        } else
            cola = aux;
    }

    private void addCola(String sCola, int col) {
        term2 aux = new term2(sCola);
        aux.col = col;
        if (cola != null) {
            for (term2 rec = cola; rec != null; rec = rec.term2) {
                if (rec.term2 == null) {
                    rec.term2 = aux;
                    break;
                }
            }
        } else
            cola = aux;
    }

}

class term2 {
    public String dig;
    public term2 term2;
    public int col;

    public term2(String dig) {
        this.dig = dig;
        this.term2 = null;
        this.col = 0;
    }
}
