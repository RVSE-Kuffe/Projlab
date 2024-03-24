package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class CursedRoom extends Room {

    /**
     * Átkozot szoba konstruktora
     * beállítja a megfelelő paramétereket
     */
    public CursedRoom(Skeleton s, String n,int i, int c) {
        super(s,n,i,c);
    }
    /**
     * ajtók bezárását végzi a szobában
     * minden ajtót bezár
     */
    public void closeDoor(){
        System.out.println(sk.names.get(this) + "closeDoor");
        for(Door d : doorList){
            d.acceptCloseDoor();
        }
        System.out.println(sk.names.get(this) + "closeDoor returned with void");
        return;
    }
    /**
     * ajtók kinyitását végzi a szobában
     * minden ajtót kinyit
     */
    public void openDoor(){
        System.out.println(sk.names.get(this) + "openDoor");
        for(Door d : doorList){
            d.acceptOpenDoor();
        }
        System.out.println(sk.names.get(this) + "openDoor returned with void");
        return;
    }

    /**
     * merge párosító függvénye
     * visitor pattern alapján elfogadja a párosítás próbálkozást
     */
    public boolean acceptPairing(Board b, Room r){System.out.println(sk.names.get(this) + "acceptPairing");
    return true;}

}
