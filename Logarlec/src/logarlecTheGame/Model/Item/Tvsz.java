package logarlecTheGame.Model.Item;

import logarlecTheGame.Skeleton.*;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;
import logarlecTheGame.Model.Item.Item;


public class Tvsz extends Item {
    private int durability;
    private Skeleton sk;

    public Tvsz(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
    }

    /**
     * A hallgatót próbálja megvédeni
     * @param s     a student aki használni akarja (visitor)
     * @return      Hamis, true ha sikerült megvédeni, hamis egyébként
     */
   @Override
    public boolean acceptSP(Student s){
        System.out.println(sk.names.get(this) + "acceptSP");
        if(s.protect(this))return true;
        return false;
    }

    /**
     * Használatonként 1-el csökken a durability
     * @return true ha sikerül, false egyébként
     */
    public boolean durabminus(){
        System.out.println(sk.names.get(this) + "durabminus");
        if(this.durability>0){
            durability-=1;
            System.out.println(sk.names.get(this) + "durabminus return true");
            return true;
        }
        System.out.println(sk.names.get(this) + "durabminus return false");
        return false;
    }

    
}