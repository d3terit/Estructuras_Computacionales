package app.client.view.tables;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;

import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

public class TablesTemp extends JPanel {
    private TablesComp tablesComp;

    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;

    private JPanel table, calculatorProp, historic, header;
    private JLabel title;

    public JPanel getTable() {
        return table;
    }

    public JPanel getHistoric() {
        return historic;
    }

    public JPanel getCalculatorProp() {
        return calculatorProp;
    }

    public TablesTemp(TablesComp tablesComp, Dimension size) {
        this.tablesComp= tablesComp;
        sObjGraficos = ObjGraficosService.getService();
        sRecursos = RecursosService.getService();
        this.setSize(size);
        createJPanels();
        createJLabels();
        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void createJPanels() {
        header = sObjGraficos.construirJPanel(
                0, 0, getWidth(),
                (int) (getHeight() * 0.1),
                sRecursos.getColorPrimary(), null);
        add(header);

        table = sObjGraficos.construirJPanel(
                0, (int) (getHeight() * 0.1),
                 (int) (getWidth() * 0.6),
                (int) (getHeight() * 0.7),
                null, null);
        add(table);

        historic = sObjGraficos.construirJPanel(
                (int) (getWidth() * 0.6), 
                (int) (getHeight() * 0.1),
                (int) (getWidth() * 0.4),
                (int) (getHeight() * 0.7),
                null, null);
        add(historic);

        calculatorProp = sObjGraficos.construirJPanel(
                0, (int) (getHeight() * 0.8),
                getWidth(),
                (int) (getHeight() * 0.2),
                null, null);
        add(calculatorProp);
    }

    public void createJLabels() {
        title = sObjGraficos.construirJLabel(
                "Tablas de verdad",
                0, (int)(header.getHeight()-15)/2,
                header.getWidth(), 30,
                null, null,
                sRecursos.getFontLigera(),
                null,
                sRecursos.getColorcWhite(),
                null,
                "c");
        header.add(title);

    }

}
