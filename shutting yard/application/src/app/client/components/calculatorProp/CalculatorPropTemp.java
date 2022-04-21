package app.client.components.calculatorProp;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

import app.services.graphicServices.GraficosAvanzadosService;
import app.services.graphicServices.ObjGraficosService;
import app.services.graphicServices.RecursosService;

import java.awt.Color;
import java.awt.Dimension;

public class CalculatorPropTemp extends JPanel{
    private CalculatorPropComp calculatorComp;
    private ObjGraficosService sObjGraficos;
    private RecursosService sRecursos;
    private GraficosAvanzadosService sGraphics;
    private JPanel edit, tools;

    private JTextField input;

    private JButton calc, conjuncion, disyuncion, exclusiva, condiconal, bicondiconal, negacion, open, close ;
    
    public CalculatorPropTemp(CalculatorPropComp calculatorComp, Dimension size){
        this.calculatorComp = calculatorComp;
        this.sObjGraficos = ObjGraficosService.getService();
        this.sRecursos = RecursosService.getService();
        sGraphics = GraficosAvanzadosService.getService();
    
        this.setSize(size);
        createJPanels();
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

        conjuncion = sObjGraficos.construirJButton(
            "∧", (int)(tools.getWidth()-490)/2, 0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        conjuncion.addActionListener(calculatorComp);
        conjuncion.setActionCommand("∧");
        tools.add(conjuncion);

        disyuncion= sObjGraficos.construirJButton(
            "∨", (int)(tools.getWidth()-490)/2 + 50, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        disyuncion.addActionListener(calculatorComp);
        disyuncion.setActionCommand("∨");
        tools.add(disyuncion);

        exclusiva = sObjGraficos.construirJButton(
            "⊻", (int)(tools.getWidth()-490)/2 + 100, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        exclusiva.addActionListener(calculatorComp);
        exclusiva.setActionCommand("⊻");
        tools.add(exclusiva);

        negacion = sObjGraficos.construirJButton( //∁ ᶜ
            "¬", (int)(tools.getWidth()-490)/2 + 150, 
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        negacion.addActionListener(calculatorComp);
        negacion.setActionCommand("¬");
        tools.add(negacion);

        condiconal = sObjGraficos.construirJButton(
            "⮕", (int)(tools.getWidth()-490)/2 + 200,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        condiconal.addActionListener(calculatorComp);
        condiconal.setActionCommand("⮕");
        tools.add(condiconal);

        bicondiconal = sObjGraficos.construirJButton(
            "⟷", (int)(tools.getWidth()-490)/2 + 250,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        bicondiconal.addActionListener(calculatorComp);
        bicondiconal.setActionCommand("⟷");
        tools.add(bicondiconal);

        open = sObjGraficos.construirJButton(
            "(", (int)(tools.getWidth()-490)/2 + 300,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        open.addActionListener(calculatorComp);
        open.setActionCommand("(");
        tools.add(open);

        close = sObjGraficos.construirJButton(
            ")", (int)(tools.getWidth()-490)/2 + 350,
            0, 40, edit.getHeight(), 
            sRecursos.getCMano(), null, sRecursos.getFontserifJl(), 
            sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), 
            null, "c", true);
        close.addActionListener(calculatorComp);
        close.setActionCommand(")");
        tools.add(close);
    }

    public JTextField getInput() {
        return input;
    }

    
}
