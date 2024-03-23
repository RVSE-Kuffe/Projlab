import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;

public abstract class Item {
    public void AcceptPutDown(Student s){}

    public boolean AcceptSP(Student s){}

    public boolean acceptGasProtect(Player p){}

    public void AcceptPickUp(Player p){}

    public boolean AcceptPairing(Player p, Transistor t){}

    public boolean AcceptPairing(Player p, Item i){}

    
}
