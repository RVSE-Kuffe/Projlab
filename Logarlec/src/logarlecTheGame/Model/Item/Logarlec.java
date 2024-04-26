import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.Item;


public class Logarlec extends Item {
    private Skeleton sk;
/**
 * Logarléc konstruktor
     */
    public Logarlec(Skeleton s, String n, boolean fake) {
        sk = s;
        sk.names.put(this, n);
        isFake=fake;
    }

    /**
     * A tárgyat fel akarja venni játékos
     * speciális, mert felvételével megnyeri a játékot a hallgató
     * oktató nem veheti fel
     * @param p     a player aki fel akarja venni (visitor)
     */
    @Override
    public boolean acceptPickUp(Player p){
        if(!isFake){
            if(p.pickUp(this)){
                return true;
            }
            return false;
        }
        return false;
        //System.out.println(sk.names.get(this) + "acceptPickUp");
    }
    
}
