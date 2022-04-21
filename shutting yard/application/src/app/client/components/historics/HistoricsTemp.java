package app.client.components.historics;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class HistoricsTemp extends JPanel {
    private HistoricsComp relationsComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private JPanel top, list;
    private JLabel title;

    public HistoricsTemp(HistoricsComp relationsComp, Dimension size) {
        this.relationsComp = relationsComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();

        this.setSize(size);
        createJPanels();
        createJLabels();

        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void createJPanels() {
        top = sObjGraficos.construirJPanel(
                0, 0, getWidth(), 50,
                null, null);
        add(top);

        list = sObjGraficos.construirJPanel(
                0, 50, getWidth(), getHeight()-50,
                null, null);
        add(list);
    }

    public void createJLabels() {
        title = sObjGraficos.construirJLabel(
                "Historico:", 00, 20, top.getWidth(), 25,
                null, null, sRecursos.getFontserifJl(), null,
                sRecursos.getColorcWhite(), null, "c");
        top.add(title);
    }

    public JPanel getList(){
        return list;
    }
}
