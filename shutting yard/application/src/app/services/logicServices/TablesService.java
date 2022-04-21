package app.services.logicServices;

import java.util.ArrayList;

import model.Table;

public class TablesService {
    private static TablesService service;
    private ArrayList<Table> tables;

    public TablesService() {
        tables = new ArrayList<Table>();
    }

    public ArrayList<Table> getTables(){
        return tables;
    }

    public Boolean addTable(Table input){
        for (Table table : tables) {
            if(table.getInput().equals(input.getInput()))
                return false;
        }
        return tables.add(input);
    }

    public Boolean removeTable(String input){
        for (Table table : tables) {
            if(table.getInput().equals(input))
                return tables.remove(table);
        }
        return false;
    }

    public static TablesService getService() {
        if (service == null)
            service = new TablesService();
        return service;
    }
}
