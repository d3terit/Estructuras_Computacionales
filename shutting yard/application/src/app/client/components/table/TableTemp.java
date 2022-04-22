package app.client.components.table;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import app.services.graphicServices.RecursosService;
import model.Table;
import app.services.graphicServices.GraficosAvanzadosService;
import app.services.graphicServices.ObjGraficosService;

public class TableTemp extends JPanel {
    private TableComp elementsComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraficosAvanzados;

    private JPanel top, conTable;
    private JTextField input;
    private Table modelTable;

    // ----------------------------
    private JScrollPane sTableP, sTableExp;
    private JPanel cornerP, cornerExp;
    private JTable tableP, tableExp;
    private JTableHeader headerP, headerExp;
    private DefaultTableModel modelP, modelExp;

    public TableTemp(TableComp elementsComp, Dimension size) {
        this.elementsComp = elementsComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
        sGraficosAvanzados = GraficosAvanzadosService.getService();
        this.setSize(size);

        createJPanels();

        this.setBackground(sRecursos.getColorBlueDark());
        this.setLayout(null);
        this.setVisible(true);
    }

    public void setTable(Table table) {
        modelTable = table;
        charge();
    }

    public void charge() {
        top.removeAll();
        conTable.removeAll();
        if (modelTable != null) {
            createJTextField();
            createJTable();
        }
        top.repaint();
        conTable.repaint();
    }

    public void createJPanels() {
        top = sObjGraficos.construirJPanel(
                0, 0, getWidth(), 50,
                null, null);
        add(top);

        conTable = sObjGraficos.construirJPanel(
                0, 70, getWidth(), getHeight() - 70,
                null, null);
        add(conTable);
    }

    public void createJTextField() {
        input = sObjGraficos.construirJTextField(
                modelTable.getOutput(), (int) (top.getWidth() * 0.1),
                25, (int) (top.getWidth() * 0.8), 25,
                sRecursos.getFontserifJl(), null, sRecursos.getColorcWhite(),
                null, null, "c");
        top.add(input);

    }

    public void createJTable() {
        String aux = "";
        Vector<String> output = new Vector<String>();
        char[] chx = modelTable.getInput().toCharArray();
        for (int i = 0; i < chx.length; i++) {
                if(chx[i] == '(' || (i< chx.length-1 && chx[i+1] == ')')) aux += chx[i];
                else if(chx[i] == ')' || (i< chx.length-1 && chx[i+1] != ')')){
                        aux += chx[i];
                        output.add(aux);
                        aux = "";
                }
                else if(i == chx.length-1){
                        aux += chx[i];
                        output.add(aux);
                        aux = "";
                }
        }
        modelP = new DefaultTableModel();
        modelP.setColumnIdentifiers(modelTable.getP().toArray());

        modelExp = new DefaultTableModel();
        modelExp.setColumnIdentifiers(output);

        tableP = new JTable();
        tableP.setModel(modelP);
        tableP.setRowHeight(30);
        tableP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        tableExp = new JTable();
        tableExp.setModel(modelExp);
        tableExp.setRowHeight(30);
        tableExp.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableExp.setShowHorizontalLines(false);
        tableExp.setShowVerticalLines(false);

        headerP = tableP.getTableHeader();
        headerP.setPreferredSize(new Dimension(modelTable.getP().size() * 30, 30));

        headerExp = tableExp.getTableHeader();
        headerExp.setPreferredSize(new Dimension(modelTable.getExp().size() * 30, 30));

        sTableP = sObjGraficos.construirPanelBarra(
                tableP, (int) (conTable.getWidth() * 0.08), 0, (int) (conTable.getWidth() * 0.22),
                conTable.getHeight() - 20, sRecursos.getColorBlueDark(),
                new EmptyBorder(0, 0, 0, 0));
        sTableP.getHorizontalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        5, 10, sRecursos.getColorBlueDark(),
                        sRecursos.getColorPrimary(), sRecursos.getColorSecondary()));

        sTableP.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        5, 10, sRecursos.getColorBlueDark(),
                        sRecursos.getColorPrimary(), sRecursos.getColorSecondary()));

        sTableExp = sObjGraficos.construirPanelBarra(
                tableExp, (int) (conTable.getWidth() * 0.35), 0, (int) (conTable.getWidth() * 0.57),
                conTable.getHeight() - 20, sRecursos.getColorBlueDark(),
                new EmptyBorder(0, 0, 0, 0));
        sTableExp.getHorizontalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        5, 10, sRecursos.getColorBlueDark(),
                        sRecursos.getColorPrimary(), sRecursos.getColorSecondary()));

        sTableExp.getVerticalScrollBar().setUI(
                sGraficosAvanzados.devolverScrollPersonalizado(
                        5, 10, sRecursos.getColorBlueDark(),
                        sRecursos.getColorPrimary(), sRecursos.getColorSecondary()));

        cornerP = new JPanel();
        cornerP.setBackground(sRecursos.getColorPrimary());

        cornerExp = new JPanel();
        cornerExp.setBackground(sRecursos.getColorPrimary());

        sTableP.setCorner(JScrollPane.UPPER_RIGHT_CORNER, cornerP);
        sTableExp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, cornerExp);

        headerP.setDefaultRenderer(
                sGraficosAvanzados.devolverTablaPersonalizada(
                        sRecursos.getColorPrimary(),
                        null, null,
                        sRecursos.getColorcWhite(),
                        sRecursos.getFontserifJl(),-1));
        tableP.setDefaultRenderer(Object.class, sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorBlueDark(),sRecursos.getColorBlueDark(),sRecursos.getColorBlueDark(),
                sRecursos.getColorcWhite(), sRecursos.getFontserifJl(),-1));
        tableP.getTableHeader().setBackground(sRecursos.getColorBlueDark());
        headerExp.setDefaultRenderer(
                sGraficosAvanzados.devolverTablaPersonalizada(
                        sRecursos.getColorPrimary(),
                        sRecursos.getColorBlue(), null,
                        sRecursos.getColorcWhite(),
                        sRecursos.getFontserifJl(), modelTable.getRta()));
        tableExp.setDefaultRenderer(Object.class, sGraficosAvanzados.devolverTablaPersonalizada(
                sRecursos.getColorBlueDark(),sRecursos.getColorBlueDark(),sRecursos.getColorBlueDark(),
                sRecursos.getColorcWhite(), sRecursos.getFontserifJl(), -1));
        tableExp.getTableHeader().setBackground(sRecursos.getColorBlueDark());
        conTable.add(sTableP);
        conTable.add(sTableExp);
    }


    public JTable getTableP(){
        return tableP;
    }

    public JTable getTableExp(){
        return tableExp;
    }


    public DefaultTableModel getModelP(){
        return modelP;
    }

    public DefaultTableModel getModelExp(){
        return modelExp;
    }
}
