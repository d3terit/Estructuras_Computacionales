package model;

import java.util.ArrayList;

public class Set {
    private String name;
    private ArrayList<String> elements;

    public Set(String name) {
        this.name = name;
        elements = new ArrayList<String>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getElements() {
        return elements;
    }

    public void setElements(ArrayList<String> elements) {
        this.elements = elements;
    }

    public Boolean addElement(String element){
        if(this.elements.contains(element)) return false;
        this.elements.add(element);
        return true;
    }
}
