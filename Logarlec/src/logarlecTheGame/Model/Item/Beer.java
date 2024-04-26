package logarlecTheGame.Model.Item;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Skeleton.*;

public class Beer extends Item implements CycleBased {
    private int durability;
    private boolean active;

    /**
     * Sörosztály konstruktora, inicializlja az attribútumait
     */
    public Beer(int durab) {
        durability=durab;
        active=false;
        isFake=false;

    }
    /**
     * A hallgatót próbálja megvédeni
     * @param s     a student aki használni akarja (visitor)
     * @return      Hamis, true ha sikerült megvédeni, hamis egyébként
     */
    @Override
     public boolean acceptSP(Student s){
        if(!isFake){
        //System.out.println(sk.names.get(this) + "acceptSP");
        if(s.protect(this))
            return true;
        }
        return false;   
    }
    /**
     * A tárgyat fel akarja venni játékos
     * speciális, mert felvételtől aktiválódik
     * @param p     a player aki fel akarja venni (visitor)
     */
    @Override
    public boolean acceptPickUp(Player p){
        if(p.pickUp(this)){
            return true;
        }
        System.out.println(sk.names.get(this) + "acceptPickUp");
        return false;
    }    

     /**
     * Aktiválja a tárgyat, ezesetben sört a játékos, 
     * azzal, hogy használja
     * aktívra állítja az attribútumát
     */
    public void activate(){
        this.active=true;
    }
    /**
     *Csökkenti a tárgy használhatóságát, 
     hogyha aktív és még lehet
     */
    private void csokkent(){
        if(durability>0&&this.active==true){
        durability-=1;
        if(durability<=0){
            isFake=true;
        }
    }
    }

    /**
     * Meghívja a csökkentés metódust, 
     * amikor iterál
     */
    public void iterate(){
        this.csokkent();
    }
    
}
