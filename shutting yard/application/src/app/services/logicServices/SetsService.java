package app.services.logicServices;

import java.util.ArrayList;
import java.util.Collections;

import model.Set;

public class SetsService {
    private static SetsService service;
    private ArrayList<Set> sets;
    private String Universe;

    public SetsService() {
        Universe = "U";
        sets = new ArrayList<Set>();
        sets.add(new Set(Universe));
    }

    public Boolean addSet(String name) {
        for (Set set : sets) {
            if (set.getName().equals(name))
                return false;
        }
        return sets.add(new Set(name));
    }

    public ArrayList<Set> getSets() {
        return this.sets;
    }

    public Boolean contain(String name){
        for (Set set : sets) {
            if(set.getName().equals(name)) return true;
        }
        return false;
    }

    public ArrayList<String> getUniverse(){
        return getElements(Universe);
    }

    public ArrayList<String> getElements(String name){
        for (Set set : sets) {
            if(set.getName().equals(name)) return set.getElements();
        }
        return null;
    }

    public Boolean removeSet(String name) {
        for (Set set : sets) {
            if (set.getName().equals(name) && !name.equals(Universe))
                return sets.remove(set);
        }
        return false;
    }

    public Boolean addElement(String name, String element) {
        for (Set set : sets) {
            if (set.getName().equals(name))
                if (!set.getElements().contains(element)) {
                    if (!sets.get(0).getElements().contains(element) && this.Universe != set.getName()) {
                        sets.get(0).getElements().add(element);
                        Collections.sort(sets.get(0).getElements());
                    }
                    if (set.getElements().add(element)) {
                        Collections.sort(set.getElements());
                        return true;
                    }
                }
        }
        return false;
    }

    public Boolean removeElement(String name, String element) {
        Boolean p = false;
        if (name.equals(Universe)) {
            for (Set set : sets) {
                if (set.getName().equals(Universe))
                    p = set.getElements().remove(element);
                else
                    set.getElements().remove(element);
            }
        } else {
            for (Set set : sets) {
                if (set.getName().equals(name))
                    p = set.getElements().remove(element);
            }
        }
        return p;
    }

    public static SetsService getService() {
        if (service == null)
            service = new SetsService();
        return service;
    }
}
