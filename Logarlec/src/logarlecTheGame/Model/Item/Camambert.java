package logarlecTheGame.Model.Item;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Item.Item;


public class Camambert extends Item {
    private Skeleton sk;

    /**
     * Camambert osztály konstruktora, 
     * inicializálja az attribútumokat
     */
    public Camambert(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
        isFake=false;;
    }
    /**
     * A tárgyat le akarja tenni(és használni) hallgató
     * speciális, mert letételre képessége van(gázosítás)
     * @param s     a student aki fel akarja venni (visitor)
     */
    @Override
     public void acceptPutDown(Student s){
        System.out.println(sk.names.get(this) + "acceptPutDown");
        s.use(this);
        System.out.println(sk.names.get(this) + "acceptPutDown returned with void");
     }
    
}
