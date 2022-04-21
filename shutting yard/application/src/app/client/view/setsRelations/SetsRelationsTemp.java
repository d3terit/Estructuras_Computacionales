package app.client.view.setsRelations;

import javax.swing.JPanel;
import java.awt.Dimension;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class SetsRelationsTemp extends JPanel {
    private SetsRelationsComp setsRelationsComp;

    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    
    private JPanel tools, sets, relations, calculator;

    public JPanel getTools() {
        return tools;
    }

    public JPanel getSets() {
        return sets;
    }

    public JPanel getRelations() {
        return relations;
    }

    public JPanel getCalculator() {
        return calculator;
    }

    public SetsRelationsTemp(SetsRelationsComp setsRelationsComp, Dimension size){
        this.setsRelationsComp = setsRelationsComp;
        sObjGraficos = ObjGraficosService.getService();
        sRecursos = RecursosService.getService();
        this.setSize(size);
        createJPanels();
        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void  createJPanels(){
        tools = sObjGraficos.construirJPanel(
            0, 0, getWidth(), 
            (int)(getHeight()*0.2), 
            null, null);
        add(tools);

        sets = sObjGraficos.construirJPanel(
            0, (int)(getHeight()*0.2), 
            (int)(getWidth()*0.4), 
            (int)(getHeight()*0.6), 
            null, null);
        add(sets);

        relations = sObjGraficos.construirJPanel(
            (int)(getWidth()*0.4),
            (int)(getHeight()*0.2), 
            (int)(getWidth()*0.6), 
            (int)(getHeight()*0.6), 
            null, null);
        add(relations);

        calculator = sObjGraficos.construirJPanel(
            0, (int)(getHeight()*0.8), 
            getWidth(), 
            (int)(getHeight()*0.2), 
            null, null);
        add(calculator);
    }
}
