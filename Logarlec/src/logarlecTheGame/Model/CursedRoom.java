package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class CursedRoom extends Room {

    public CursedRoom(Skeleton s, String n,int i, int c) {
        super(s,n,i,c);
    }
    
    public void closeDoor(){
        System.out.println(sk.names.get(this) + "closeDoor");
        for(Door d : doorList){
            d.acceptCloseDoor();
        }
        System.out.println(sk.names.get(this) + "closeDoor returned with void");
        return;
    }
    public void openDoor(){
        System.out.println(sk.names.get(this) + "openDoor");
        for(Door d : doorList){
            d.acceptOpenDoor();
        }
        System.out.println(sk.names.get(this) + "openDoor returned with void");
        return;
    }
    public boolean acceptPairing(Board b, Room r){System.out.println(sk.names.get(this) + "acceptPairing");
    return true;}

}
