package app.client.components.historics;

import javax.swing.JButton;
import javax.swing.JPanel;

import app.services.graphicServices.RecursosService;
import app.services.graphicServices.ObjGraficosService;

import java.awt.Dimension;

public class HistoricTemp extends JPanel {
  private RecursosService sRecursos;
  private ObjGraficosService sObjGraficos;
  private HistoricsComp historicsComp;
  private JButton input, delete;

  public HistoricTemp(HistoricsComp historicsComp, String input, int y, Dimension size) {
    this.sRecursos = RecursosService.getService();
    this.sObjGraficos = ObjGraficosService.getService();
    this.historicsComp = historicsComp;
    setSize((int) size.getWidth(), 30);
    createJButtons(input);
    setLocation(0, y);
    this.setBackground(null);
    this.setLayout(null);
    this.setVisible(true);
  }

  public void createJButtons(String input) {

    this.input = sObjGraficos.construirJButton(
        input, (int) (getWidth() * 0.1), 0,
        (int) (getWidth() * 0.8 - 50), 30, sRecursos.getCMano(),
        null, sRecursos.getFontserifJl(), sRecursos.getColorPrimary(),
        sRecursos.getColorcWhite(), null, "c", true);
    this.input.addActionListener(historicsComp);
    this.input.setActionCommand(input);
    add(this.input);

    delete = sObjGraficos.construirJButton(
        "‒",
        (int)(getWidth() - 30 - getWidth()*0.1), 0, 30, 30,
        sRecursos.getCMano(),
        null,
        sRecursos.getFontserifBtn(),
        sRecursos.getColorPrimary(),
        sRecursos.getColorcWhite(),
        null,
        "c",
        true);
    delete.addActionListener(historicsComp);
    delete.setActionCommand("delete,"+input);
    add(delete);
  }

  public JButton getBDelete(){
    return delete;
  }
}
