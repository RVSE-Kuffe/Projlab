package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class Door {
    private Skeleton sk;

    public Door(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }
    boolean validTo;
    Room whereTo;
    Room whereFrom;
    boolean validFrom;
public
    void acceptCloseDoor(){System.out.println(sk.names.get(this) + "acceptCloseDoor");}
    boolean transferPlayer(){System.out.println(sk.names.get(this) + "transferPlayer");return true;}
    void changeVisibility(){System.out.println(sk.names.get(this) + "changeVisibility");}
    void changeRoom(){System.out.println(sk.names.get(this) + "changeRoom");}
    void acceptOpenDoor(){System.out.println(sk.names.get(this) + "acceptOpenDoor");}

}
