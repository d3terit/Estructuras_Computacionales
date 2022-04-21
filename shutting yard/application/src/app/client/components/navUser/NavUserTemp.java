package app.client.components.navUser;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.Dimension;

import app.services.graphicServices.*;

public class NavUserTemp extends JPanel {

  private NavUserComp navegacionUsuarioComponent;
  private ObjGraficosService sObjGraficos;
  private RecursosService sRecursos;

  private JButton bSets, bProps;

  private Border bVacio;

  public NavUserTemp(NavUserComp navegacionUsuarioComponent, Dimension size) {
    this.navegacionUsuarioComponent = navegacionUsuarioComponent;
    this.sObjGraficos = ObjGraficosService.getService();
    this.sRecursos = RecursosService.getService();

    this.createObjetosDecoradores();
    this.createJButtons();
    this.setSize(size);
    this.setLayout(null);
    this.setBackground(sRecursos.getColorDark());
    this.setVisible(true);
  }

  public void createObjetosDecoradores() {
    this.bVacio = new EmptyBorder(0, 0, 0, 0);
  }

  public void createJButtons() {
    this.bSets = sObjGraficos.construirJButton(
        "C",
        0, 0, 50, 50,
        sRecursos.getCMano(),
        null,
        sRecursos.getFontserifBtn(),
        null,
        sRecursos.getColorcWhite(),
        this.bVacio,
        "c",
        false);
    this.bSets.addActionListener(navegacionUsuarioComponent);
    this.bSets.setActionCommand("sets");
    this.add(bSets);

    this.bProps = sObjGraficos.construirJButton(
        "P",
        0, 50, 50, 50,
        sRecursos.getCMano(),
        null,
        sRecursos.getFontserifBtn(),
        null,
        sRecursos.getColorcWhite(),
        this.bVacio,
        "c",
        false);
    this.bProps.addActionListener(navegacionUsuarioComponent);
    this.bProps.setActionCommand("props");
    this.add(bProps);
  }

}
