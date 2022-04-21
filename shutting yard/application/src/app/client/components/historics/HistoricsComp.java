package app.client.components.historics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Dimension;
import java.util.ArrayList;

import app.client.view.tables.TablesComp;
import app.services.logicServices.TablesService;
import model.Table;

public class HistoricsComp implements ActionListener{
    private HistoricsTemp historicsTemp;
    private TablesService sTables;
    private TablesComp tablesComp;
    public HistoricsComp(TablesComp tablesComp, Dimension size){
        this.historicsTemp = new HistoricsTemp(this, size);
        this.tablesComp = tablesComp;
        sTables = TablesService.getService();
        chargeTables();
    }

    public HistoricsTemp getHistoricTemp(){
        return historicsTemp;
    }

    public void chargeTables(){
        historicsTemp.getList().removeAll();
        ArrayList<Table> tables = sTables.getTables();
        HistoricTemp aux;
        Dimension size = historicsTemp.getSize();
        int i = 20;
        for (Table table : tables) {
            String input = table.getInput();
            aux = new HistoricTemp(this,input, i,size);
            historicsTemp.getList().add(aux);
            i+=40;
        }
        historicsTemp.getList().repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String[] command = e.getActionCommand().split(",");
        String input = command[0];
        if(command.length>1){
            sTables.removeTable(command[1]);
            chargeTables();
            tablesComp.getTableComp().chargeTable();
        }
        ArrayList<Table> tables = sTables.getTables();
        for (Table table : tables) {
            if(table.getInput().equals(input)){
                tablesComp.getTableComp().setInput(table.getInput());
            }
        }
    }

}
