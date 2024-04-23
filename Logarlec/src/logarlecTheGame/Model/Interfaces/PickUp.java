package logarlecTheGame.Model.Interfaces;
import logarlecTheGame.Model.Item.Logarlec;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Item.Beer;


public interface PickUp {
    /**
     * Tárgyak felvételéhez szükséges interfész
     * bárki vehet fel tárgyat
     * csak a logarlec és a Beer felvétele speciális
     */ 
    boolean pickUp(Logarlec l);

    boolean pickUp(Beer b);

    boolean pickUp(Item i);
}
