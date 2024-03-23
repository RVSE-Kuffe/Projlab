package logarlecTheGame.Model.Item;
import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Item;


public class Logarlec extends Item {
    private Skeleton sk;

    public Logarlec(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    /**
     * A tárgyat fel akarja venni játékos
     * speciális, mert felvételével megnyeri a játékot a hallgató
     * oktató nem veheti fel
     * @param p     a player aki fel akarja venni (visitor)
     */
    @Override
    public void acceptPickUp(Player p){
        p.pickUp(this);
        System.out.println(sk.names.get(this) + "acceptPickUp");
    }
    
}
