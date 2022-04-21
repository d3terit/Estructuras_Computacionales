package app.client.components.setsTools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.client.view.setsRelations.SetsRelationsComp;
import app.services.logicServices.SetsService;
import model.Set;

public class SetsToolsComp implements ActionListener {

    private SetsToolsTemp setsToolsTemp;
    private SetsRelationsComp setsRelationsComp;
    private SetsService sSets;

    public SetsToolsComp(SetsRelationsComp setsRelationsComp, Dimension size) {
        this.setsToolsTemp = new SetsToolsTemp(this, size);
        this.setsRelationsComp = setsRelationsComp;
        sSets = SetsService.getService();
        updateSets();
    }

    public void updateSets() {
        setsRelationsComp.getCalculatorComp().updateSets();
        ArrayList<Set> sets = sSets.getSets();
        setsToolsTemp.getDropSets().removeAllItems();
        for (Set set : sets) {
            setsToolsTemp.getDropSets().addItem(set.getName());
        }
        setsToolsTemp.getDropSets().setSelectedIndex(sets.size() - 1);
    }

    public void chargeElements() {
        setsRelationsComp.getElementsComp().chargeSets();
        setsRelationsComp.getRelationsComp().chargeRelations();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "addSet":
                addSet();
                break;
            case "addElement":
                addElement();
                break;
            case "subSet":
                subSet();
                break;
            case "subElement":
                subElement();
                break;
            default:
                break;
        }
        // this.SetsRelationsComp.mostrarComponente(e.getActionCommand().trim());
    }

    public void addSet() {
        String[] sets = setsToolsTemp.getTextCon().getText().toUpperCase().replaceAll("\\s+", "").split(",");
        ArrayList<String> errors = new ArrayList<String>();
        for (String name : sets) {
            if (name.length()==1) {
                if(Character.isLetter(name.charAt(0))){
                    if(!sSets.addSet(name))
                        errors.add(name);
                }
            }else   
                errors.add(name);
        }
        if (errors.size() != sets.length) {
            updateSets();
            chargeElements();
        }
        if (errors.size() > 0) {
            String output = "Los conjuntos ";
            for (String error : errors) {
                output += "'" + error + "', ";
            }
            output += " no se pudieron ingresar, :(";
            JOptionPane.showMessageDialog(null, output);
        }
        setsToolsTemp.getTextCon().setText("");
        setsToolsTemp.getTextCon().requestFocus();
    }

    public void addElement() {
        String set = setsToolsTemp.getDropSets().getSelectedItem().toString();
        String[] elements = setsToolsTemp.getTextEle().getText().toLowerCase().replaceAll("\\s+", "").split(",");
        ArrayList<String> errors = new ArrayList<String>();
        for (String element : elements) {
            char[] ch = element.toCharArray();
            Boolean add = true;
            for (Character c : ch) {
                if (!Character.isLetterOrDigit(c)) {
                    add = false;
                    break;
                }
            }
            if (add && !element.equals("")){
                if (!sSets.addElement(set, element))
                    errors.add(element);
            }
            else
                errors.add(element);
        }
        if (errors.size() != elements.length)
            chargeElements();
        if (errors.size() > 0) {
            String output = "Los elementos ";
            for (String error : errors) {
                output += "'" + error + "', ";
            }
            output += " no se pudieron ingresar al conjunto " + set + ", :(";
            JOptionPane.showMessageDialog(null, output);
        }
        setsToolsTemp.getTextEle().setText("");
        setsToolsTemp.getTextEle().requestFocus();
    }

    public void subSet() {
        String[] sets = setsToolsTemp.getTextCon().getText().toUpperCase().replaceAll("\\s+", "").split(",");
        ArrayList<String> errors = new ArrayList<String>();
        for (String name : sets) {
            if (!sSets.removeSet(name))
                errors.add(name);
        }
        if (errors.size() != sets.length) {
            updateSets();
            chargeElements();
        }
        if (errors.size() > 0) {
            String output = "Los conjuntos ";
            for (String error : errors) {
                output += "'" + error + "', ";
            }
            output += " no se pudieron eliminar, :(";
            JOptionPane.showMessageDialog(null, output);
        }
        setsToolsTemp.getTextCon().setText("");
        setsToolsTemp.getTextCon().requestFocus();
    }

    public void subElement() {
        String set = setsToolsTemp.getDropSets().getSelectedItem().toString();
        String[] elements = setsToolsTemp.getTextEle().getText().toLowerCase().replaceAll("\\s+", "").split(",");
        ArrayList<String> errors = new ArrayList<String>();
        for (String element : elements) {
            if(!sSets.removeElement(set, element))
                errors.add(element);
        }
        if (errors.size() != elements.length) {
            chargeElements();
        }
        if (errors.size() > 0) {
            String output = "Los elementos ";
            for (String error : errors) {
                output += "'" + error + "', ";
            }
            output += " no se pudieron eliminar del conjunto " + set + ", :(";
            JOptionPane.showMessageDialog(null, output);
        }
        setsToolsTemp.getTextEle().setText("");
        setsToolsTemp.getTextEle().requestFocus();
    }
    
    public SetsToolsTemp getSetsToolsTemp() {
        return this.setsToolsTemp;
    }
}
