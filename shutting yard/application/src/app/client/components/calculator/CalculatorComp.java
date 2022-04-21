package app.client.components.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.awt.Dimension;

import app.client.view.setsRelations.SetsRelationsComp;
import app.services.logicServices.OpSetsService;
import app.services.logicServices.RelationsService;
import app.services.logicServices.SetsService;
import model.Set;

public class CalculatorComp implements ActionListener{
    private SetsRelationsComp setsRelationsComp;
    private CalculatorTemp calculatorTemp;
    private SetsService sSets;
    private OpSetsService sOpSets;
    private RelationsService sRelations;
    public CalculatorComp(SetsRelationsComp setsRelationsComp, Dimension size){
        this.setsRelationsComp = setsRelationsComp;
        calculatorTemp = new CalculatorTemp(this, size);
        sSets = SetsService.getService();
        sOpSets = OpSetsService.getService();
        sRelations = RelationsService.getService();
        updateSets();
    }

    public void updateSets() {
        ArrayList<Set> sets = sSets.getSets();
        calculatorTemp.getDropSets().removeAllItems();
        for (Set set : sets) {
            calculatorTemp.getDropSets().addItem(set.getName());
        }
        calculatorTemp.getDropSets().setSelectedIndex(sets.size() - 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int caret = calculatorTemp.getInput().getCaretPosition();
        String ecuacion = calculatorTemp.getInput().getText().substring(0,caret);
        switch (e.getActionCommand()) {
            case "calc":
                calc();
                break;
            case "addSet":
                ecuacion += calculatorTemp.getDropSets().getSelectedItem().toString();
                break;
            case "union":
                ecuacion += "∪";
                break;
            case "interseccion":
                ecuacion +="∩"; 
                break;
            case "complemento":
                ecuacion +="∁"; 
                break;
            case "diferencia":
                ecuacion +="∖"; 
                break;
            case "simetrica":
                ecuacion +="⊕"; 
                break;
            case "open":
                ecuacion +="("; 
                break;
            case "close":
                ecuacion +=")"; 
                break;
            default:
                break;
        }
        if(!e.getActionCommand().equals("calc")){
            int newCaret = ecuacion.length();
            ecuacion += calculatorTemp.getInput().getText().substring(caret);
            calculatorTemp.getInput().setText(ecuacion);
            calculatorTemp.getInput().setCaretPosition(newCaret);
        }
    }

    public void calc(){
        String ecuacion = calculatorTemp.getInput().getText().toUpperCase().replaceAll("\\s+", "");
        if(!sRelations.addRelation(ecuacion))
            JOptionPane.showMessageDialog(null, "No se pudo ingresar la ecuación:\n " + ecuacion, "Error", 0);
        else
            updateRelations();
        calculatorTemp.getInput().setText("");
    }

    public void updateRelations(){
        setsRelationsComp.getRelationsComp().chargeRelations();
    }

    public CalculatorTemp getCalculatorTemp(){
        return calculatorTemp;
    }
}
