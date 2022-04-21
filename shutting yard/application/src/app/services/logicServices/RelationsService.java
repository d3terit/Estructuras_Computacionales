package app.services.logicServices;

import java.util.ArrayList;

import model.Set;

public class RelationsService {
    private static RelationsService service;
    private ArrayList<Set> relations;
    private OpSetsService sOpSets;
    public RelationsService() {
        relations = new ArrayList<Set>();
        sOpSets = OpSetsService.getService();
    }

    public Boolean addRelation(String name) {
        for (Set set : relations) {
            if (set.getName().equals(name))
                return false;
        }
        relations.add(new Set(name));
        calcELments(name);
        return true;
    }

    public void calcELments(String name){
        for (Set set : relations) {
            if(set.getName().equals(name)){
                set.setElements(sOpSets.calcElements(name));
            }
        }
    }

    public ArrayList<Set> getRelations() {
        for (Set set : relations) {
            set.setElements(sOpSets.calcElements(set.getName()));
        }
        return this.relations;
    }

    public Boolean updateRelation(String oldName, String newName){
        for (Set set : relations) {
            if(set.getName().equals(oldName)){
                set.setName(newName);
                calcELments(newName);
                return true;
            }
        }
        return false;
    }

    public Boolean removeRelation(String name) {
        for (Set set : relations) {
            if (set.getName().equals(name))
                return relations.remove(set);
        }
        return false;
    }

    public static RelationsService getService() {
        if (service == null)
            service = new RelationsService();
        return service;
    }
}
