package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class CursedRoom extends Room {
    private Skeleton sk;

    public CursedRoom(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }
public
    void closeDoor(){System.out.println(sk.names.get(this) + "closeDoor");}
    void openDoor(){System.out.println(sk.names.get(this) + "openDoor");}
    void acceptPairing(Board b, Room r){System.out.println(sk.names.get(this) + "acceptPairing");}

}
