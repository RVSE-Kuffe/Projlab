package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;


public class Tvsz extends Item {
    private int durability;

    /**
         *TVSZ konstruktor, inicializálja az attribútumokat
     */
    public Tvsz(int durab, boolean fake) {
        durability=durab;
        isFake=fake;
    }

    /**
     * A hallgatót próbálja megvédeni
     * @param s     a student aki használni akarja (visitor)
     * @return      Hamis, true ha sikerült megvédeni, hamis egyébként
     */
   @Override
    public boolean acceptSP(Student s){
        return (!isFake&& s.protect(this));
    }

    /**
     * Használatonként 1-el csökken a durability
     * @return true ha sikerül, false egyébként
     */
    public boolean durabminus(){
        if(this.durability>0){
            durability-=1;
            return true;
        }
        else{
            isFake=true;
            return false;
        }
        
    }

    
}