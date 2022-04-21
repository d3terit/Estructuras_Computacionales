package app.client.view.vistaPrincipal;

import java.awt.Dimension;

import app.client.components.navUser.NavUserComp;
import app.client.view.setsRelations.SetsRelationsComp;
import app.client.view.tables.TablesComp;

public class VistaPrincipalComponent {
    private VistaPrincipalTemplate vistaPrincipalTemplate;
    private NavUserComp navUserComp;
    private SetsRelationsComp setsRelationsComp;
    private TablesComp propositionsComp;
    private String environment;

    public VistaPrincipalComponent() {
        vistaPrincipalTemplate = new VistaPrincipalTemplate(this);
        environment = "sets";
        charge();
    }

    public void charge() {
        Dimension size = vistaPrincipalTemplate.getPNavegacion().getSize();
        navUserComp = new NavUserComp(this, size);
        vistaPrincipalTemplate.getPNavegacion().add(navUserComp.getNavUserTempl());
        mostrarComponente(environment);
    }

    public void mostrarComponente(String comando) {
        environment = comando;
        Dimension size = vistaPrincipalTemplate.getPPrincipal().getSize();
        vistaPrincipalTemplate.getPPrincipal().removeAll();
        switch (comando) {
            case "sets":
                //if (setsRelationsComp == null)
                    setsRelationsComp = new SetsRelationsComp(this, size);
                vistaPrincipalTemplate.getPPrincipal().add(setsRelationsComp.getSetsRelationsTemp());
                break;
            case "props":
                    propositionsComp = new TablesComp(this, size);
                vistaPrincipalTemplate.getPPrincipal().add(propositionsComp.getTablesTemp());
                break;
            default: 
                break;
        }
        vistaPrincipalTemplate.getPPrincipal().repaint();
    }

    public VistaPrincipalTemplate getVistaPrincipalTemplate() {
        return vistaPrincipalTemplate;
    }
}
