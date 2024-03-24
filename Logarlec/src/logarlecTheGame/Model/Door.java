package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;

public class Door {
    private Skeleton sk;
    boolean validTo = true;
    Room whereTo;
    Room whereFrom;
    boolean validFrom = true;
    boolean closed=false;
    public Door(Skeleton s, String n,Room r1,Room r2) {
        sk = s;
        whereFrom=r1;
        whereTo=r2;
        sk.names.put(this, n);
    }
    
    void acceptCloseDoor(){
        System.out.println(sk.names.get(this) + "acceptCloseDoor");
        this.closed=true;
        System.out.println(sk.names.get(this) + "acceptCloseDoor returned with void");
        return;
    }

    /**
     * Intézi a játékos eltávolítását az előző és hozzáadását a következő szobához
     * @param p     a karakter, akit át kell léptetni 
     * @return      Igaz, ha a whereTo szobába van elég hely, tehát át lehet lépni. Különben Hamis
     */
    public boolean transferPlayer(Player p){
        System.out.println(sk.names.get(this) + "transferPlayer");

        if(! closed){
            if(whereTo.addPlayer(p)){

                whereFrom.removePlayer(p);

                whereTo.killAll(p);
                whereTo.pvp(p);

                System.out.println(sk.names.get(this) + " transferplayer returned true");
                return true;
            }
        }       
        System.out.println(sk.names.get(this) + " transferplayer returned false");
        return false;
    }
    void changeVisibility(){System.out.println(sk.names.get(this) + "changeVisibility");}
    void changeRoom(){System.out.println(sk.names.get(this) + "changeRoom");}
    void acceptOpenDoor(){
        System.out.println(sk.names.get(this) + "acceptOpenDoor");
        this.closed=false;
        System.out.println(sk.names.get(this) + "acceptOpenDoor returned with void");
        return;
    }

}
