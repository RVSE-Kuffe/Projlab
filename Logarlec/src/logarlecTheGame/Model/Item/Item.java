package logarlecTheGame.Model.Item;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;

public abstract class Item {
    public void acceptPutDown(Student s){
        s.use(this);
    }

    public boolean acceptSP(Student s){
       return false;
    }

    public boolean acceptGasProtect(Player p){
       return false;
    }

    public void acceptPickUp(Player p){
        p.pickUp(this);
    }

    public boolean acceptPairing(Student s, Transistor t){
        s.pair(this, t);
        return true;
    }

    public boolean acceptPairing(Student s, Item i){
        return false;
    }

    public boolean makePair(Item i){}

    
}
