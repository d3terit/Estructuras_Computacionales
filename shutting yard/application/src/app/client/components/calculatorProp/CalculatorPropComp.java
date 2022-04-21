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
        ArrayList<Prop> props = ShutingYardService.getService().aSufijo(out);
        Table table = TableService.getService().entrada(props, out);
        if (!sTables.addTable(table))
            JOptionPane.showMessageDialog(null, "No se pudo ingresar la ecuación:\n " + ecuacion, "Error", 0);
        else {
            updateTables();
            tablesComp.getTableComp().setInput(out);
            calculatorTemp.getInput().setText("");
        }
    }

    public void updateTables() {
        tablesComp.getHistoricsComp().chargeTables();
    }

    public CalculatorPropTemp getCalculatorTemp() {
        return calculatorTemp;
    }
}
