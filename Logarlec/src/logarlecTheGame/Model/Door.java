package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class Door {
    private Skeleton sk;
    boolean validTo;
    Room whereTo;
    Room whereFrom;
    boolean validFrom;
    boolean closed=false;
    public Door(Skeleton s, String n,Room r1,Room r2) {
        sk = s;
        whereTo=r1;
        whereFrom=r2;
        sk.names.put(this, n);
    }
    
public
    void acceptCloseDoor(){
        System.out.println(sk.names.get(this) + "acceptCloseDoor");
        this.closed=true;
        System.out.println(sk.names.get(this) + "acceptCloseDoor returned with void");
        return;
    }
    boolean transferPlayer(){System.out.println(sk.names.get(this) + "transferPlayer");return true;}
    void changeVisibility(){System.out.println(sk.names.get(this) + "changeVisibility");}
    void changeRoom(){System.out.println(sk.names.get(this) + "changeRoom");}
    void acceptOpenDoor(){System.out.println(sk.names.get(this) + "acceptOpenDoor");}

}
