package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.CycleBased;

public class CycleUsage {
    private int cycleNumber;
    private List<CycleBased> cbList;
    /**
    * CycleUsage konstruktor, 
    *inicializálja az attribútumokat
     */
    public CycleUsage(){
        cycleNumber = 0;
        cbList = new ArrayList<>();
    }

    /**
    * Megsemmisíti az adott tárgyat ami CycleBased-t valósít meg
    *@param c a CycleBased tárgy, vagy elem
     */
    public void destroy(CycleBased c){
        /*TODO*/
    }

     /**
    *Hozzáad egy tárgyat ami CycleBased-t valósít meg
    *@param c a CycleBased tárgy, vagy elem
     */
    public void add(CycleUsage c){
        /*TODO */
    }
}
