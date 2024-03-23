import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;
import logarlecTheGame.Model.Item.Item;


public class Mask extends Item {
    private int durability;
    private Skeleton sk;

    public Mask(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
    }

    /**
     * A payert próbálja megvédeni
     * @param p     a player aki használni akarja (visitor)
     * @return      True ha sikerült megvédeni, false egyébként
     */
    @Override   
    public boolean acceptGasProtect(Player p){
        System.out.println(sk.names.get(this) + "acceptGasProtect");
        if(p.maskProtect(this)) return true;
        return false;
    }

    /**
     * Használatonként 1-el csökken a durability
     * @return true ha sikerül, false egyébként
     */
    boolean durabminus(){
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