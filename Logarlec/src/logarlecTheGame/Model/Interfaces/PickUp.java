package logarlecTheGame.Model.Interfaces;
import logarlecTheGame.Model.Item.Logarlec;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Item.Beer;


public interface PickUp {
    void pickUp(Logarlec l);

    void pickUp(Beer b);

    void pickUp(Item i);
}
