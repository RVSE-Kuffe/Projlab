package logarlecTheGame.Model.Item;
import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Interfaces.*;

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
        return (!isFake && s.protect(this));
    }
    /**
     * A tárgyat fel akarja venni játékos
     * speciális, mert felvételtől aktiválódik
     * @param p     a player aki fel akarja venni (visitor)
     */
    @Override
    public boolean acceptPickUp(Player p){
        return (p.pickUp(this));
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
        if(durability>0&&this.active)
            durability-=1;
        if(durability<=0){
            isFake=true;
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
