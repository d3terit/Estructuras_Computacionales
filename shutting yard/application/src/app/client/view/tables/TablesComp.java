package app.client.view.tables;

import app.client.components.calculatorProp.CalculatorPropComp;
import app.client.components.historics.HistoricsComp;
import app.client.components.table.TableComp;
import app.client.view.vistaPrincipal.VistaPrincipalComponent;
import model.Table;

import java.awt.Dimension;

public class TablesComp {
    private TablesTemp tablesTemp;
    private VistaPrincipalComponent vComponent;
    private TableComp tableComp;
    private CalculatorPropComp calculatorPropComp;
    private HistoricsComp historicComp;

    public TablesComp(VistaPrincipalComponent vComponent, Dimension size){
        tablesTemp = new TablesTemp(this, size);
        this.vComponent = vComponent;
        charge();
    }

    public void charge(){
        Dimension size = tablesTemp.getTable().getSize();
        tableComp = new TableComp(this, size);
        tablesTemp.getTable().add(tableComp.getTableTemp());
        size = tablesTemp.getHistoric().getSize();
        historicComp = new HistoricsComp(this, size);
        tablesTemp.getHistoric().add(historicComp.getHistoricTemp());
        size = tablesTemp.getCalculatorProp().getSize();
        calculatorPropComp = new CalculatorPropComp(this, size);
        tablesTemp.getCalculatorProp().add(calculatorPropComp.getCalculatorTemp());
    }
    
    public TableComp getTableComp(){
        return tableComp;
    }

    public HistoricsComp getHistoricsComp(){
        return historicComp;
    }

    public TablesTemp getTablesTemp(){
        return tablesTemp;
    }
}
