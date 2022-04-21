package app.client.components.elements;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import app.services.graphicServices.RecursosService;

import app.services.graphicServices.ObjGraficosService;

public class ElementsTemp extends JPanel {
    private ElementsComp elementsComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    public ElementsTemp(ElementsComp elementsComp, Dimension size) {
        this.elementsComp = elementsComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.setSize(size);
        this.setBackground((new Color(10,10,30)));
        this.setLayout(null);
        this.setVisible(true);
    }


    
}
