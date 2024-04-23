package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Interfaces.CycleBased;
import java.util.Random;

public class CursedRoom extends Room implements CycleBased {
    private static Random random=new Random();
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
    @Override
    public void iterate() {
        int choice= random.nextInt(3);
        switch(choice){
            case 0:
                break;
            case 1:
               for(Door d:doorList){
                this.closeDoor();
               }
            case 2:
            for(Door d:doorList){
                this.openDoor(); 
               }
        }
        
    }

}
