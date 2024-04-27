package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;


public class Mask extends Item {
    private int durability;

    /**
      *Mask konstruktor, inicializálja az attribútumokat
     */
    public Mask(int durab, boolean fake) {
        durability=durab;
        isFake=fake;
    }

    /**
     * A payert próbálja megvédeni
     * @param p     a player aki használni akarja (visitor)
     * @return      True ha sikerült megvédeni, false egyébként
     */
    @Override   
    public boolean acceptGasProtect(Player p){
        return (!isFake&&p.maskProtect(this));
    }

    /**
     * Használatonként 1-el csökken a durability
     * @return true ha sikerül, false egyébként
     */
    public boolean durabminus(){
        if(this.durability>0){
            durability-=1;
            if(durability==0){this.isFake=true;}
            return true;
        }
        return false;
    }

    
}