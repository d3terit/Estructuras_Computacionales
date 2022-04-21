package app.client.view.vistaPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;

import app.services.graphicServices.*;

import app.services.graphicServices.RecursosService;

public class VistaPrincipalTemplate extends JFrame {
    private static final long serialVersionUID = 1L;

    private ObjGraficosService sObjGraficos;

    private VistaPrincipalComponent vistaPrincipalComponent;

    private JPanel pNavegacion, pPrincipal;

    private RecursosService sRecursos;

    private int width, height;

    public VistaPrincipalTemplate(VistaPrincipalComponent vistaPrincipalComponent) {

        sObjGraficos = ObjGraficosService.getService();
        sRecursos = RecursosService.getService();

        this.vistaPrincipalComponent = vistaPrincipalComponent;
        width = 1200;
        height = 700;
        this.createJPanels();

        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Proposiciones");
        setIconImage(sRecursos.getIIcon().getImage());
        setSize(width, height);
        //setResizable(false);
        setLocationRelativeTo(this);
        setVisible(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                width = getWidth();
                height = getHeight() - 34;
                pNavegacion.setSize(50, height);
                pPrincipal.setSize(width-50, height);
                vistaPrincipalComponent.charge();
                pNavegacion.repaint();
                pPrincipal.repaint();
            }
        });
        
    }

    public void createJPanels() {
        int w = 50;
        pNavegacion = sObjGraficos.construirJPanel(
                0, 0, w, this.height,
                null, null);
        this.add(pNavegacion);
        w = this.width - w;
        pPrincipal = sObjGraficos.construirJPanel(
                pNavegacion.getWidth(), 0, w, this.height,
                sRecursos.getColorDark(), null);
        this.add(pPrincipal);
    }

    public JPanel getPNavegacion() {
        return this.pNavegacion;
    }

    public JPanel getPPrincipal() {
        return this.pPrincipal;
    }
}
