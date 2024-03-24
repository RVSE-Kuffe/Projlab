package logarlecTheGame.Model.Item;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Skeleton.*;

public class Tablatorlo extends Item implements CycleBased {
    private int durability;
    private Skeleton sk;

    public Tablatorlo(Skeleton s, String n, int durab) {
        sk = s;
        sk.names.put(this, n);
        durability=durab;
    }
    
    @Override
    public void acceptPutDown(Student s){
        System.out.println(sk.names.get(this) + "acceptPutDown");
        s.use(this);
        System.out.println(sk.names.get(this) + "acceptPutDown returned with void");
    }    

    private void csokkent(){
        if(durability>0) {durability-=1;}
    }

    public void iterate(){
        this.csokkent();
    }
    
}
