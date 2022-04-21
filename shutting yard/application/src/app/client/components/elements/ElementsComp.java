package app.client.components.elements;
import java.util.ArrayList;

import java.awt.Dimension;

import app.client.view.setsRelations.SetsRelationsComp;
import app.services.logicServices.SetsService;
import model.Set;

public class ElementsComp {
    private ElementsTemp elementsTemp;
    private SetsRelationsComp setsRelationsComp;
    private SetsService sSets;

    public ElementsComp(SetsRelationsComp setsRelationsComp, Dimension size){
        this.setsRelationsComp = setsRelationsComp;
        this.elementsTemp = new ElementsTemp(this, size);
        sSets = SetsService.getService();
        chargeSets();
    }

    public ElementsTemp getElementsTemp(){
        return this.elementsTemp;
    }

    public void chargeSets(){
        this.elementsTemp.removeAll();
        ArrayList<Set> sets = sSets.getSets();
        ElementTemp aux;
        int i = 20;
        for (Set set : sets) {
            String sElements = "{ ";
            ArrayList<String> elements = set.getElements();
            for (int j = 0; j < elements.size(); j++) {
                sElements += elements.get(j);
                if(j<elements.size()-1) sElements += ", ";
            }
            sElements += " }";
            aux = new ElementTemp(set.getName()+":", i, sElements);
            this.elementsTemp.add(aux);
            i+=40;
        }
        this.elementsTemp.repaint();
    }
    
}
