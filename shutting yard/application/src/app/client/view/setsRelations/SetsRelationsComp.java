package app.client.view.setsRelations;

import app.client.components.calculator.CalculatorComp;
import app.client.components.elements.ElementsComp;
import app.client.components.relations.RelationsComp;
import app.client.components.setsTools.SetsToolsComp;
import app.client.view.vistaPrincipal.VistaPrincipalComponent;

import java.awt.Dimension;

public class SetsRelationsComp {
    private SetsRelationsTemp setsRelationsTemp;
    private VistaPrincipalComponent vComponent;
    private SetsToolsComp setsToolsComp;
    private ElementsComp elementsComp;
    private CalculatorComp calculatorComp;
    private RelationsComp relationsComp;

    public SetsRelationsComp(VistaPrincipalComponent vComponent, Dimension size){
        setsRelationsTemp = new SetsRelationsTemp(this, size);
        this.vComponent = vComponent;
        charge();
    }

    public void charge(){
        Dimension size = setsRelationsTemp.getCalculator().getSize();
        calculatorComp = new CalculatorComp(this,size);
        setsRelationsTemp.getCalculator().add(calculatorComp.getCalculatorTemp());

        size = setsRelationsTemp.getTools().getSize();
        setsToolsComp = new SetsToolsComp(this, size);
        setsRelationsTemp.getTools().add(setsToolsComp.getSetsToolsTemp());

        size = setsRelationsTemp.getSets().getSize();
        elementsComp = new ElementsComp(this,size);
        setsRelationsTemp.getSets().add(elementsComp.getElementsTemp());

        size = setsRelationsTemp.getRelations().getSize();
        relationsComp = new RelationsComp(this,size);
        setsRelationsTemp.getRelations().add(relationsComp.getRelationsTemp());
    }

    public ElementsComp getElementsComp(){
        return elementsComp;
    }

    public CalculatorComp getCalculatorComp(){
        return calculatorComp;
    }

    public RelationsComp getRelationsComp(){
        return relationsComp;
    }

    public SetsRelationsTemp getSetsRelationsTemp(){
        return setsRelationsTemp;
    }
}
