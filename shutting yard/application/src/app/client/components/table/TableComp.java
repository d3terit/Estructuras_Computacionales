package app.client.components.table;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import app.client.view.tables.TablesComp;
import app.services.logicServices.TablesService;
import model.Table;

public class TableComp {
    private TablesComp tablesComp;
    private TableTemp tableTemp;
    private TablesService sTables;
    private Dimension size;
    private String input;
    public TableComp(TablesComp tablesComp, Dimension size){
        this.tablesComp = tablesComp;
        this.size = size;
        sTables =TablesService.getService();
        tableTemp = new TableTemp(this, size);
        input = "";
        chargeTable();
    }

    public void setInput(String input){
        this.input = input;
        chargeTable();
    }

    public void chargeTable(){
        Table table = null;
        ArrayList<Table> tables = sTables.getTables();
        for (Table aux : tables) {
            if(aux.getInput().equals(input)){
                table = aux;
            }
        }
        tableTemp.setTable(table);
        if(table != null)mostrarRegistrosTabla(table);
    }

    public void mostrarRegistrosTabla(Table table){
        limpiarTabla();
        tableTemp.getTableP().setRowSorter(null);
        tableTemp.getTableP().setRowSorter(null);

        Vector<Character> aux;
        for(int i=0; i< table.getValues().length; i++){
            aux = new Vector<Character>();
            for (int j = 0; j < table.getValues()[i].length; j++) {
                if(table.getValues()[i][j] == 0) aux.add('F');
                else aux.add('V');
            }
            tableTemp.getModelP().addRow(aux);
        }
        for(int i=0; i< table.getResult().length; i++){
            aux = new Vector<Character>();
            for (int j = 0; j < table.getResult()[i].length; j++) {
                if(table.getResult()[i][j] == 0) aux.add('F');
                else aux.add('V');
            }
            tableTemp.getModelExp().addRow(aux);
        }
    }

    public void limpiarTabla(){
        int length = tableTemp.getModelP().getRowCount();
        for(int i=0; i< length; i++){
            tableTemp.getModelP().removeRow(0);
        }
        length = tableTemp.getModelExp().getRowCount();
        for(int i=0; i< length; i++){
            tableTemp.getModelExp().removeRow(0);
        }
    }
    
    public TableTemp getTableTemp(){
        return this.tableTemp;
    }
}
