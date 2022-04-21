package app.client.components.setsTools;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.ComboBoxUI;

import java.awt.Dimension;
import app.services.graphicServices.*;

public class SetsToolsTemp extends JPanel {

  private SetsToolsComp setsToolsComp;
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;
  private GraficosAvanzadosService sGraphics;

  private JPanel sets, elements;
  private JLabel lSets, lElements, lDrop;
  private JTextField textCon, textEle;
  private JButton addSets, addElement, subSets, subElement;
  private ComboBoxUI dropUI;
  private JComboBox<String> dropSets;

  public SetsToolsTemp(SetsToolsComp setsToolsComp, Dimension size) {
    this.setsToolsComp = setsToolsComp;
    this.sObjGraficos = ObjGraficosService.getService();
    this.sGraphics = GraficosAvanzadosService.getService();
    this.sRecursos = RecursosService.getService();
    this.setSize(size);
    this.createJPanels();
    this.createJInputs();
    this.createJLabels();
    this.createJButtons();
    this.createJComboBox();
    this.setBackground(sRecursos.getColorPrimary());
    this.setLayout(null);
    this.setVisible(true);
  }

  public void createJPanels() {
    int w = (int)((getWidth()*0.7-310)/3)+100;
    this.sets = sObjGraficos.construirJPanel(
        (int)(getWidth()*.1),
        (int)((getHeight()-100)/2), w, 100,
        null,
        null);
    this.add(sets);
    this.elements = sObjGraficos.construirJPanel(
        (int)(getWidth()*.2+w),
        (int)((getHeight()-100)/2),
        (int)(getWidth()*.7-w), 100,
        null,
        null);
    this.add(elements);
  }

  public void createJLabels() {
    this.lSets = sObjGraficos.construirJLabel(
      "Añadir conjunto", 
      0, 15, sets.getWidth(), 20, 
      null, null,
      sRecursos.getFontLigera(),
      null, 
      sRecursos.getColorcWhite(), 
      null, 
      "c");
    this.sets.add(lSets);

    this.lElements= sObjGraficos.construirJLabel(
      "Añadir elemento", 0, 15, elements.getWidth(), 20, 
      null, null,
      sRecursos.getFontLigera(),
      null, 
      sRecursos.getColorcWhite(), 
      null, 
      "c");
    this.elements.add(lElements);
    this.lDrop= sObjGraficos.construirJLabel(
      "Conjunto:", 0, 50, 90, 40, 
      null, null,
      sRecursos.getFontserifJl(),
      null, 
      sRecursos.getColorcWhite(), 
      null, 
      "l");
    this.elements.add(lDrop);
  }

  public void createJInputs() {
    this.textCon = sObjGraficos.construirJTextField(
      "", 0, 50, sets.getWidth()-100, 40, 
      sRecursos.getFontserifJl(), null, sRecursos.getColorcWhite(), 
      sRecursos.getColorcWhite(), sRecursos.getBborderLbl(), "c");
    this.sets.add(textCon);

    this.textEle = sObjGraficos.construirJTextField(
      "", elements.getWidth()-sets.getWidth(), 
      50, sets.getWidth()-100, 40, 
      sRecursos.getFontserifJl(), null, sRecursos.getColorcWhite(), 
      sRecursos.getColorcWhite(), sRecursos.getBborderLbl(), "c");
    this.elements.add(textEle);
  }

  public void createJButtons() {
    this.addSets = sObjGraficos.construirJButton(
      "+",
      sets.getWidth()-90, 50, 40, 40,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontserifBtn(),
      sRecursos.getColorSecondary(),
      sRecursos.getColorcWhite(),
      null,
      "c",
      true);
  this.addSets.addActionListener(setsToolsComp);
  this.addSets.setActionCommand("addSet");
  this.sets.add(this.addSets);

  this.subSets = sObjGraficos.construirJButton(
      "‒",
      sets.getWidth()-40, 50, 40, 40,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontserifBtn(),
      sRecursos.getColorSecondary(),
      sRecursos.getColorcWhite(),
      null,
      "c",
      true);
  this.subSets.addActionListener(setsToolsComp);
  this.subSets.setActionCommand("subSet");
  this.sets.add(this.subSets);

  this.addElement= sObjGraficos.construirJButton(
      "+",
      elements.getWidth()-90, 50, 40, 40,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontserifBtn(),
      sRecursos.getColorSecondary(),
      sRecursos.getColorcWhite(),
      null,
      "c",
      true);
  this.addElement.addActionListener(setsToolsComp);
  this.addElement.setActionCommand("addElement");
  this.elements.add(this.addElement);

  this.subElement= sObjGraficos.construirJButton(
      "‒",
      elements.getWidth()-40, 50, 40, 40,
      sRecursos.getCMano(),
      null,
      sRecursos.getFontserifBtn(),
      sRecursos.getColorSecondary(),
      sRecursos.getColorcWhite(),
      null,
      "c",
      true);
  this.subElement.addActionListener(setsToolsComp);
  this.subElement.setActionCommand("subElement");
  this.elements.add(this.subElement);
  }

  public void createJComboBox(){
    this.dropSets = sObjGraficos.construirJComboBox(
      100, 50, sets.getWidth()-100, 40, 
      sRecursos.getColorPrimary(), sRecursos.getColorcWhite(), null, 
      "l");
    //this.dropSets.setFocusable(false);
    this.dropSets.addActionListener(setsToolsComp);
    this.dropUI = sGraphics.devolverJComboBoxPersonalizado(
      null, sRecursos.getColorPrimary(), 
      sRecursos.getColorSecondary(),
      sRecursos.getIArrowDown(), false);
    this.dropSets.setUI(dropUI);
    this.elements.add(this.dropSets);
  }

  public JTextField getTextCon() {
    return textCon;
  }

  public JTextField getTextEle() {
    return textEle;
  }

  public JComboBox<String> getDropSets() {
    return dropSets;
  }
}