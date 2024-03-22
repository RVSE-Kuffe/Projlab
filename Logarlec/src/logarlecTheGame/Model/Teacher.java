package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

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
        System.out.println(sk.names.get(this) + " kill");

        System.out.println(sk.names.get(this) + " return kill");
        return;
    }
}
