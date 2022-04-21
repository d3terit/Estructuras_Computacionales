package app.client.components.relations;

import javax.swing.JPanel;
import java.awt.Dimension;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class RelationsTemp  extends JPanel{
    private RelationsComp relationsComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    public RelationsTemp(RelationsComp relationsComp, Dimension size) {
        this.relationsComp = relationsComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.setSize(size);
        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }
}
