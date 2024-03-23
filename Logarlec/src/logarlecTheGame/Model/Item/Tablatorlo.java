package logarlecTheGame.Model.Item;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.Skeleton;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;

public class Tablatorlo implements CycleBased {
    private int durability;
    private Skeleton sk;

    public Tablatorlo(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
    }

    public void AcceptPutDown(Student s){}    

    boolean durabminus(){}
    
}
