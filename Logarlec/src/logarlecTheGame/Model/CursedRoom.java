package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class CursedRoom extends Room {
    private Skeleton sk;

    public CursedRoom(Skeleton s, String n,int i, int c) {
        super(s,n,i,c);
    }
public
    
    void closeDoor(){
        System.out.println(sk.names.get(this) + "closeDoor");
        for(Door d : doorlist){
            d.acceptCloseDoor();
        }
        System.out.println(sk.names.get(this) + "closeDoor returned with void");
        return;
    }
    void openDoor(){
        System.out.println(sk.names.get(this) + "openDoor");
        for(Door d : doorlist){
            d.acceptOpenDoor();
        }
        System.out.println(sk.names.get(this) + "openDoor returned with void");
        return;
    }
    boolean acceptPairing(Board b, Room r){System.out.println(sk.names.get(this) + "acceptPairing");
    return true;}

}
