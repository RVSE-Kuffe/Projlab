package logarlecTheGame.Model;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;

import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Skeleton.*;

public class CycleUsage {
    private int cycleNumber;
    private List<CycleBased> cbList;
    private Skeleton sk;
/**
  * CycleUsage konstruktor, 
  *inicializálja az attribútumokat
     */
    public CycleUsage(Skeleton s, String n){
        sk = s;
        sk.names.put(this, n);
        cycleNumber = 0;
        cbList = new ArrayList<>();
    }
    /**
    * Megsemmisíti az adott tárgyat ami CycleBased-t valósít meg
    *@param c a CycleBased tárgy, vagy elem
     */
    public void destroy(CycleBased c){
        System.out.println(sk.names.get(this) + " destroy(" + sk.names.get(c) + ")" );

        System.out.println(sk.names.get(this) + " return destroy");
        return;
    }

     /**
    *Hozzáad egy tárgyat ami CycleBased-t valósít meg
    *@param c a CycleBased tárgy, vagy elem
     */
    public void add(CycleUsage c){
        System.out.println(sk.names.get(this) + " add(" + sk.names.get(c) + ")");

        System.out.println(sk.names.get(this) + " return add");
        return;
    }
}
