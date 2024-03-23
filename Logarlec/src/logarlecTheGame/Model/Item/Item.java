import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;

public abstract class Item {
    public void acceptPutDown(Student s){}

    public boolean acceptSP(Student s){}

    public boolean acceptGasProtect(Player p){}

    public void acceptPickUp(Player p){}

    public boolean acceptPairing(Player p, Transistor t){}

    public boolean acceptPairing(Player p, Item i){}

    
}
