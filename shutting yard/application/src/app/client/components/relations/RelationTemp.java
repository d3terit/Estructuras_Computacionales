package app.client.components.relations;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.services.graphicServices.RecursosService;
import app.services.graphicServices.ObjGraficosService;

import java.awt.Color;
import java.awt.Dimension;
public class RelationTemp extends JPanel{
    private RecursosService sRecursos;
    private ObjGraficosService sObjGraficos;

    private JTextField name, elements;
    public RelationTemp(String name, int y, String elements, Dimension size){
        this.sRecursos = RecursosService.getService();
        this.sObjGraficos = ObjGraficosService.getService();
        setSize((int)size.getWidth(), 70);
        createJTextFields(name, elements);
        setLocation(0, y);
        this.setBackground(null);
        this.setLayout(null);
        this.setVisible(true);
    }
    public void createJTextFields(String name,String elements){
      
        this.name= sObjGraficos.construirJTextField(
          name, (int)(getWidth()*0.1), 0, (int)(getWidth()*0.8), 30, 
          sRecursos.getFontserifJl(), null, 
          sRecursos.getColorcWhite(), 
          null, null, "c");
          this.name.setEditable(false);
          this.add(this.name);

          this.elements = sObjGraficos.construirJTextField(
            elements, (int)(getWidth()*0.1), 40, (int)(getWidth()*0.8), 30, 
            sRecursos.getFontserifJl(), new Color(10,10,30), 
            sRecursos.getColorcWhite(), 
            null, null, "c");
            this.elements.setEditable(false);
          this.add(this.elements);
    }
}
