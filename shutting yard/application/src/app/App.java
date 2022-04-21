package app;

import javax.swing.SwingUtilities;

import app.client.view.vistaPrincipal.VistaPrincipalComponent;

public class App {
    public static void main(String[] args) throws Exception {
        Runnable runApplication = new Runnable () {
            public void run(){
                new VistaPrincipalComponent();
            }
        };
        SwingUtilities.invokeLater(runApplication);
    }
}

//simbolos
//⊕ ∩ ∪ ∆ ⬅ ⮕ ⟵ ⟶ ← ↔ → ⟷ ⌧ ⌫ ∖  ᶜ ∁ ∅  ¬ ∧  ∨ ⊻ ⮕ ⟷