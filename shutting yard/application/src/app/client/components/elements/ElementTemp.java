package app.client.components.elements;

import javax.swing.JPanel;
import javax.swing.JTextField;

import app.services.graphicServices.RecursosService;
import app.services.graphicServices.ObjGraficosService;

import java.awt.Dimension;
public class ElementTemp extends JPanel {
    private RecursosService sRecursos;
    private ObjGraficosService sObjGraficos;

    private JTextField name, elements;
    public ElementTemp(String name, int y, String elements){
        this.sRecursos = RecursosService.getService();
        this.sObjGraficos = ObjGraficosService.getService();
        createJTextFields(name, elements);
        this.setBounds(0, y, 500, 50);
        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }
    public void createJTextFields(String name,String elements){
      
        this.name= sObjGraficos.construirJTextField(
          name, 50, 5, 110, 30, 
          sRecursos.getFontserifJl(), null, 
          sRecursos.getColorcWhite(), 
          null, null, "l");
          this.name.setEditable(false);
          this.add(this.name);

          this.elements = sObjGraficos.construirJTextField(
            elements, 170, 5, 270, 30, 
            sRecursos.getFontserifJl(), null, 
            sRecursos.getColorcWhite(), 
            null, null, "l");
            this.elements.setEditable(false);
          this.add(this.elements);
    }
}
