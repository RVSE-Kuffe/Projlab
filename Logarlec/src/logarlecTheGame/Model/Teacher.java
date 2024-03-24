package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.*;

public class Teacher extends Player {
    private Skeleton sk;

    public Teacher(Skeleton s, String n){
        sk = s;
        sk.names.put(this, n);
    }

    public void move(){
        System.out.println(sk.names.get(this) + " move");

        System.out.println(sk.names.get(this) + " return move");
        return;
    }

    public void kill(Player p){
        System.out.println(sk.names.get(this) + " kill(" + sk.names.get(p) + ")");
        p.die();
        System.out.println(sk.names.get(this) + " return kill(" + sk.names.get(p) + ")");
        return;
    }

    public void stunTeacher(){
        System.out.println(sk.names.get(this) + " stunTeacher");

        System.out.println(sk.names.get(this) + " return stunTeacher");
        return;
    }

    public void pickUp(Logarlec l){
        System.out.println(sk.names.get(this) + " pickUp(" + sk.names.get(l) + ")");
        System.out.println(sk.names.get(this) + " return pickup(" + sk.names.get(l) + ")");
    }
}
