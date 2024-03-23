import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Transistor;

public class Mask extends Item {
    private int durability;
    private Skeleton sk;

    public Mask(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
    }

    public boolean AcceptGasProtect(Player p){}

    boolean durabminus(){}

    
}