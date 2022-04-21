package app.client.components.navUser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import app.client.view.vistaPrincipal.VistaPrincipalComponent;

public class NavUserComp implements ActionListener {

    private NavUserTemp navUserTempl;
    private VistaPrincipalComponent vistaPrincipalComponent;
    

    public NavUserComp(VistaPrincipalComponent vistaPrincipalComponent, Dimension size) {
        this.vistaPrincipalComponent = vistaPrincipalComponent;
        this.navUserTempl =  new NavUserTemp(this, size);
    }

    public NavUserTemp getNavUserTempl() {
        return this.navUserTempl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.vistaPrincipalComponent.mostrarComponente(e.getActionCommand().trim());
    }
    
}
