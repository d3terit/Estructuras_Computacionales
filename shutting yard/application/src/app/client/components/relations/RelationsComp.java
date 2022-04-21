package app.client.components.relations;

import java.awt.Dimension;
import java.util.ArrayList;

import app.client.view.setsRelations.SetsRelationsComp;
import app.services.logicServices.RelationsService;
import model.Set;

public class RelationsComp {
    private RelationsTemp relationsTemp;
    private SetsRelationsComp setsRelationsComp;
    private RelationsService sRelations;

    public RelationsComp(SetsRelationsComp setsRelationsComp, Dimension size) {
        this.setsRelationsComp = setsRelationsComp;
        this.relationsTemp = new RelationsTemp(this, size);
        sRelations = RelationsService.getService();
        chargeRelations();
    }

    public RelationsTemp getRelationsTemp() {
        return relationsTemp;
    }

    public void chargeRelations() {
        relationsTemp.removeAll();
        ArrayList<Set> relations = sRelations.getRelations();
        RelationTemp aux;
        Dimension size = relationsTemp.getSize();
        int i = 20;
        for (Set set : relations) {
            String sElements = "{ ";
            ArrayList<String> elements = set.getElements();
            if (elements != null)
                for (int j = 0; j < elements.size(); j++) {
                    sElements += elements.get(j);
                    if (j < elements.size() - 1)
                        sElements += ", ";
                }
            sElements += " }";
            aux = new RelationTemp(set.getName() + ":", i, sElements, size);
            relationsTemp.add(aux);
            i += 90;
        }
        relationsTemp.repaint();
    }

}
