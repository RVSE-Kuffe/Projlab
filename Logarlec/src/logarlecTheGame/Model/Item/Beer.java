package logarlecTheGame.Model.Item;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Interfaces.*;


public class Beer extends Item implements CycleBased {
    private int durability;
    private boolean active;
    private Skeleton sk;

    public Beer(Skeleton s, String n, int durab) {
    public Beer(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
        active=false;
        active=false;
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
     * A tárgyat fel akarja venni játékos
     * speciális, mert felvételtől aktiválódik
     * @param p     a player aki fel akarja venni (visitor)
     */
    @Override
    public void acceptPickUp(Player p){
        p.pickUp(this);
        System.out.println(sk.names.get(this) + "acceptPickUp");
    }    

    public void activate(){
        this.active=true;
    }

    private void csokkent(){
<<<<<<< HEAD
        if(durability>0)
        durability-=1;
=======
        if(durability>0 && this.active){
            this.durability-=1;
        }
>>>>>>> 3aba82ecbcb062eac659f7f672c4c3b9fdb614b4
    }

    public void iterate(){
        this.csokkent();
    }
    
}
