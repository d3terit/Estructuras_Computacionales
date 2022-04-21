package app.services.logicServices;

import java.util.ArrayList;

import model.Prop;
import model.Table;

public class OpSetsService {
    private static OpSetsService service;

    private ShutingYardService sShutingYard;
    private TableService sTable;
    private SetsService sSets;

    public OpSetsService() {
        sShutingYard = ShutingYardService.getService();
        sTable = TableService.getService();
        sSets = SetsService.getService();
    }

    public ArrayList<String> 
    calcElements(String ecuacion) {
        String proposicional = inputOp(ecuacion);
        ArrayList<Prop> props = sShutingYard.aSufijo(proposicional);
        Boolean canResolve = true;
        for (Prop prop : props) {
            if(Character.isLetterOrDigit(prop.getC()) && !sSets.contain(prop.getC()+"")) canResolve = false;
        }
        if(canResolve){
            ArrayList<String> elements = resolve(props, ecuacion);
            return elements;
        } 
        else return null;
    }

    public String inputOp(String ecuacion) {
        ecuacion = ecuacion.replace("∪", "∨");
        ecuacion = ecuacion.replace("∩", "∧");
        ecuacion = ecuacion.replace("∁", "¬");
        ecuacion = ecuacion.replace("∖", "∧¬");
        ecuacion = ecuacion.replace("⊕", "⊻");
        return ecuacion;
    }

    public ArrayList<String> resolve(ArrayList<Prop> props, String input) {
        Table table = sTable.entrada(props, input);
        ArrayList<Integer> filas = new ArrayList<Integer>();
        for (int i = 0; i < table.getResult().length; i++) {
            if (table.getResult()[i][table.getRta()] == 1)
                filas.add(i);
        }
        ArrayList<String> aux, temp, elements = new ArrayList<String>();
        for (Integer i : filas) {
            aux = new ArrayList<String>(sSets.getUniverse());
            temp = new ArrayList<String>(sSets.getUniverse());
            for (int j = 0; j < table.getValues()[0].length; j++) {
                if (table.getValues()[i][j] == 1) {
                    for (String element : aux) {
                        if (!sSets.getElements(table.getP().get(j)+"").contains(element))
                            temp.remove(element);
                    }
                } else
                    temp.removeAll(sSets.getElements(table.getP().get(j)+""));
            }
            elements.addAll(temp);
        }
        return elements;
    }

    public static OpSetsService getService() {
        if (service == null)
            service = new OpSetsService();
        return service;
    }
}
