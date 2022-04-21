package app.client.components.calculator;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;

import app.services.graphicServices.GraficosAvanzadosService;
import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

import java.awt.Color;
import java.awt.Dimension;

public class CalculatorTemp extends JPanel{
    private CalculatorComp calculatorComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraphics;
    private JPanel edit, tools;

    private JLabel text;

    private JTextField input;

    private JButton calc, addSet, union, interseccion, diferencia, simetrica, complemento, open, close ;

    private ComboBoxUI dropUiSets;
    private JComboBox<String> dropSets;
    
    public CalculatorTemp(CalculatorComp calculatorComp, Dimension size){
        this.calculatorComp = calculatorComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
        sGraphics = GraficosAvanzadosService.getService();
    
        this.setSize(size);
        createJPanels();
        createJComboBox();
        createJButtons();
        createJInputs();
        createJLabels();
    
        this.setBackground(new Color(10,10,30));
        this.setBorder(sRecursos.getBTopBorder());
        this.setLayout(null);
        this.setVisible(true);

    }

    public void createJPanels(){
        edit = sObjGraficos.construirJPanel(
            (int)(getWidth()*.05), 
            (int)((getHeight()-100)/2), 
            (int)(getWidth()*.9), 40, 
            null, null);
        add(edit);

        tools = sObjGraficos.construirJPanel(
            (int)(getWidth()*.05), 
            (int)((getHeight()-100)/2)+60, 
            (int)(getWidth()*.9), 40, 
            null, null);
        add(tools);
    }

    public void createJInputs(){
        input = sObjGraficos.construirJTextField(
            "", 0, 0, edit.getWidth(), 
            edit.getHeight(), sRecursos.getFontserifJl(), 
            sRecursos.getColorDark(), sRecursos.getColorcWhite(), 
            sRecursos.getColorcWhite(), sRecursos.getBborderLbl(), "l");
        edit.add(input);
    }

    public void createJLabels(){

    }

    public void createJButtons(){
        calc = sObjGraficos.construirJButton(
            "Calcular", tools.getWidth()-90, 0, 90, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        calc.addActionListener(calculatorComp);
        calc.setActionCommand("calc");
        tools.add(calc);

        addSet = sObjGraficos.construirJButton(
            "+", dropSets.getWidth()+10, 0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        addSet.addActionListener(calculatorComp);
        addSet.setActionCommand("addSet");
        tools.add(addSet);

        union= sObjGraficos.construirJButton(
            "∪", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 50, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        union.addActionListener(calculatorComp);
        union.setActionCommand("union");
        tools.add(union);

        interseccion = sObjGraficos.construirJButton(
            "∩", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 100, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        interseccion.addActionListener(calculatorComp);
        interseccion.setActionCommand("interseccion");
        tools.add(interseccion);

        complemento = sObjGraficos.construirJButton( //∁ ᶜ
            "∁", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 150, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        complemento.addActionListener(calculatorComp);
        complemento.setActionCommand("complemento");
        tools.add(complemento);

        diferencia = sObjGraficos.construirJButton(
            "∖", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 200,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        diferencia.addActionListener(calculatorComp);
        diferencia.setActionCommand("diferencia");
        tools.add(diferencia);

        simetrica = sObjGraficos.construirJButton(
            "⊕", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 250,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        simetrica.addActionListener(calculatorComp);
        simetrica.setActionCommand("simetrica");
        tools.add(simetrica);

        open = sObjGraficos.construirJButton(
            "(", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 300,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        open.addActionListener(calculatorComp);
        open.setActionCommand("open");
        tools.add(open);

        close = sObjGraficos.construirJButton(
            ")", (int)(tools.getWidth()-570)/2+ dropSets.getWidth() + 350,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        close.addActionListener(calculatorComp);
        close.setActionCommand("close");
        tools.add(close);
    }

    public void createJComboBox(){
        dropSets = sObjGraficos.construirJComboBox(
        0, 0, 120, edit.getHeight(), 
        new Color(10,10,30), sRecursos.getColorcWhite(), null, 
        "c");
        dropUiSets = sGraphics.devolverJComboBoxPersonalizado(
        null, sRecursos.getColorPrimary(), 
        sRecursos.getColorSecondary(),
        sRecursos.getIArrowDown(), false);
        dropSets.setUI(dropUiSets);
        tools.add(dropSets);
    }

    public JComboBox<String> getDropSets(){
        return dropSets;
    }

    public JTextField getInput() {
        return input;
    }

    
}
