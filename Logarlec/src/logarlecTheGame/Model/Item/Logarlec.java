package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;


public class Logarlec extends Item {
/**
 * Logarléc konstruktor
     */
    public Logarlec(boolean fake) {
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
        return (!isFake&&p.pickUp(this));
    }
    
}
