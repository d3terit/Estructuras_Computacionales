package app.client.components.calculatorProp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.Dimension;

import app.client.view.tables.TablesComp;
import app.services.logicServices.ShutingYardService;
import app.services.logicServices.TableService;
import app.services.logicServices.TablesService;
import model.Prop;
import model.Table;

public class CalculatorPropComp implements ActionListener {
    private TablesComp tablesComp;
    private CalculatorPropTemp calculatorTemp;
    private TablesService sTables;

    public CalculatorPropComp(TablesComp tablesComp, Dimension size) {
        this.tablesComp = tablesComp;
        calculatorTemp = new CalculatorPropTemp(this, size);
        sTables = TablesService.getService();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int caret = calculatorTemp.getInput().getCaretPosition();
        String ecuacion = calculatorTemp.getInput().getText().substring(0, caret);
        switch (e.getActionCommand().trim()) {
            case "calc":
                calc();
                break;
            case "∧":
            case "∨":
            case "⊻":
            case "¬":
            case "⮕":
            case "⟷":
            case "(":
            case ")":
                ecuacion += e.getActionCommand().trim();
                break;
            default:
                break;
        }
        if (!e.getActionCommand().equals("calc")) {
            int newCaret = ecuacion.length();
            ecuacion += calculatorTemp.getInput().getText().substring(caret);
            calculatorTemp.getInput().setText(ecuacion);
            calculatorTemp.getInput().setCaretPosition(newCaret);
        }
    }

    public void calc() {
        String ecuacion = calculatorTemp.getInput().getText().toLowerCase().replaceAll("\\s+", "");
        if(!ecuacion.equals("")){
            String out = "";
            char[] chx = ecuacion.toCharArray();
            for (char c : chx) {
                if (!Character.isLetterOrDigit(c)) {
                    if (c == '(' | c == ')' | c == '∧' | c == '∨'
                            | c == '⊻' | c == '¬' | c == '⮕' | c == '⟷') {
                        out += c;
                    }
                } else
                    out += c;
            }
            Boolean canResolve = true;
            char[] c = out.toCharArray();
            for (int i = 0; i < out.length(); i++) {
                if(Character.isLetterOrDigit(c[i])){
                    if(i>0 && Character.isLetterOrDigit(c[i-1])) canResolve= false;
                    if(out.length() > i+1 && Character.isLetterOrDigit(c[i+1]))canResolve = false;
                }
                if(c[i] == '¬' ){
                    if(i>0 && Character.isLetterOrDigit(c[i-1])) canResolve= false;
                    if(i>0 && !Character.isLetterOrDigit(c[i-1]) && c[i-1] == ')') canResolve= false;
                    if(out.length() > i+1 && !Character.isLetterOrDigit(c[i+1]) &&
                        ((c[i+1] != '(' && c[i+1] != '¬') |c[i+1] == ')')) canResolve = false;
                    if(out.length() == i+1) canResolve = false;
                }
                if(c[i] == '∧' | c[i] == '∨'| c[i] == '⊻' 
                 | c[i] == '⮕' | c[i] == '⟷'){
                    if(i>0 && (!Character.isLetterOrDigit(c[i-1]) &&(
                        c[i-1] == '(' | c[i-1] != ')'))) canResolve= false;
                    if(out.length() > i+1 && !Character.isLetterOrDigit(c[i+1]) &&
                        ((c[i+1] != '(' && c[i+1] != '¬') |c[i+1] == ')')) canResolve = false;
                    
                    if(out.length() == i+1) canResolve = false;
                }
            }
            ArrayList<Prop> props = ShutingYardService.getService().aSufijo(out);
            if(props!= null && canResolve){
                Table table = TableService.getService().entrada(props, out);
                if (!sTables.addTable(table))
                    JOptionPane.showMessageDialog(null, "No se pudo ingresar la ecuación:\n " + ecuacion, "Error", 0);
                else {
                    updateTables();
                    tablesComp.getTableComp().setInput(out);
                    calculatorTemp.getInput().setText("");
                }
            }else{
                JOptionPane.showMessageDialog(null, "La expresión esta mal escrita \n" + ecuacion, "Error", 0);                
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay nada por hacer" + ecuacion, "Error", 0);
        }
    }

    public void updateTables() {
        tablesComp.getHistoricsComp().chargeTables();
    }

    public CalculatorPropTemp getCalculatorTemp() {
        return calculatorTemp;
    }
}
