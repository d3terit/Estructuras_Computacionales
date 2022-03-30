package algoritmo;
import java.util.Scanner;

public class Algoritmo {
    public static void main(String[] args) throws Exception {
        refactor rf = new refactor();
        try (Scanner entry = new Scanner(System.in)) {
            System.out.print("Ingrese la ecuación: ");
            // operación de ejemplo
            // (1-2)^4*(4*(5/((5-3)^2))) = 5
            rf.aSufijo(entry.nextLine());
        }
        System.out.println("Notación sufija: " + rf.getCola()); // convertir a notación sufija
        System.out.println("Resultado al operar: " + rf.calc()); //calcular el resultado de la operacion
        
    }
    
}

class refactor { // clase principal donde se efectuan las operaciones
    private term cola, pila;  

    public refactor() {
        cola = pila = null;
    }

    public String getCola() { //retorna una cadena con los valores de la cola (ó notacion sufija por el momento)
        String rta = "";
        for (term aux = cola; aux != null; aux = aux.term) {
            rta += aux.dig + " ";
        }
        return rta;
    }

    public void aSufijo(String exp) { //recibe la operacion de entrada y se encarga de llamar diferentes métodos para pasar a notación sufija
        char[] chx = exp.toCharArray(); //convierte la entrada en un arreglo de caracteres
        String aux = "";
        for (char c : chx) {
            if (Character.isLetterOrDigit(c)) // verifica si es letra o número
                aux += c;
            else if (c != ' ') { // no cuenta los espacios en blanco
                if (aux.length() > 0)
                    addCola(aux);
                aux = "";
                controller(c);
            }
        }
        if (aux.length() > 0) //añade los valores finales a la cola
            addCola(aux);
        while (pila != null) { // añade los operadores a la cola
            String c = deletePila();
            if (!c.equals("("))
                addCola(c);
            ;
        }
    }
    //recibe como entrada un caracter
    //se encarga de comprobar si el caracter es valido y lo añade a la pila
    //tambien retira elementos de la pila y los añade a la cola
    private void controller(char ch) {
        if (ch == '(') {
            addPila(ch + "");
        } else if (ch == ')') { 
            for (term x = pila; x != null; x = x.term) { //recorre la pila quitando elementos hasta encontrar un parentesis de abertura
                String c = deletePila();
                if (!c.equals("("))
                    addCola(c);
                else
                    break;
            }
        } else if (weight(ch) != -1) { //comprueba el valor correspondiente al operador y evalua en que momento añadirlo a la pila
            if (pila != null) {
                if (weight(ch) >= weight(pila.dig.charAt(0)))
                    addPila(ch + "");
                else {
                    String c = deletePila();
                    if (!c.equals("("))
                        addCola(c);
                    controller(ch);
                }
            } else
                addPila(ch + "");
        }
    }

    private int weight(char ch) { //recibe un caracter u operador y retorna su respectivo valor para el patio de maniobras (jerarquia)
        switch (ch) {
            case '-':
            case '+':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return -1;
        }
    }

    private void addPila(String sPila) { // recibe un string y lo añade a la pila
        term aux = new term(sPila);
        if (pila != null)
            aux.term = pila;
        pila = aux;
    }

    private String deletePila() { //retorna el valor de encima de la pila y lo elimina de la misma
        String exp = pila.dig;
        pila = pila.term;
        return exp;
    }

    private void addCola(String sCola) { // recibe un string y lo añade a la cola
        term aux = new term(sCola);
        if (cola != null) {
            for (term rec = cola; rec != null; rec = rec.term) {
                if (rec.term == null) {
                    rec.term = aux;
                    break;
                }
            }
        } else
            cola = aux;
    }

    //realiza la respectiva operacion según la notación sufija almancenada en la cola
    //se encarga de realizar todas las operaciones y retornar el resultado expresado en decimal
    public float calc() {
        float rta = 0;
        for (term aux = cola; aux != null; aux = aux.term) { //recorre la cola
            if (!Character.isLetterOrDigit(aux.dig.charAt(0))) { //evalua si es un operador
                rta = operacion(aux.dig.charAt(0));  //llama la funcion que relaiza la operacion y le entrega el respectivo operador
                addPila(String.valueOf(rta)); //añade el resultado de la operacion a la pila
            } else { // en caso de ser un numero lo añade a la pila
                addPila(aux.dig);
            }
        }
        rta = operacion('+');
        return rta; 
    }
    //recibe el caracter que representa la operacion
    //busca los elementos en la pila y según sea el caso ejecuta una operación y retorna el resultado como decimal
    private float operacion(char ch) { 
        float rta, a, b;
        rta = a = b = 0;
        if (pila != null)
            b = Float.parseFloat(deletePila());
        if (pila != null)
            a = Float.parseFloat(deletePila());
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


class term { // nodo para la pila y cola
    public String dig;
    public term term;
    public term(String dig) {
        this.dig = dig;
        this.term = null;
    }
}
