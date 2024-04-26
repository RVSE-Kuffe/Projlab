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
    public Door(Skeleton s, String n,Room r1,Room r2, boolean vf, boolean vt) {
        sk = s;
        whereFrom=r1;
        whereTo=r2;
        sk.names.put(this, n);
        validFrom = vf;
        validTo = vt;
    }
    /**
     * ajtók bezárását végzi
     */
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
    public boolean transferPlayer(Player p, Room from){
        System.out.println(sk.names.get(this) + "transferPlayer");

        if(! closed){
            if(whereTo!=from&&validTo){
                if(whereTo.addPlayer(p)){

                whereFrom.removePlayer(p);

                whereTo.killAll(p);
                whereTo.pvp(p);

                System.out.println(sk.names.get(this) + " transferplayer returned true");
                return true;
                 }

             }
             else if(whereTo==from&&validFrom){
                if(whereFrom.addPlayer(p)){

                    whereTo.removePlayer(p);
    
                    whereFrom.killAll(p);
                    whereFrom.pvp(p);
    
                    System.out.println(sk.names.get(this) + " transferplayer returned true");
                    return true;
                }
            } 
        }         
        System.out.println(sk.names.get(this) + " transferplayer returned false");
        return false;
    
    }
    //elso parameter hogy mire valtoztatunk, masodik hogy melyiket--mergenel r1 volt a nagyobb
    public void changeRoom(Room r1,Room r2){
        System.out.println(sk.names.get(this) + "changeRoom");
        if(r2==whereTo){
            whereTo=r1;
        }
        if(r2==whereFrom){
            whereFrom=r1;
        }
        if(whereFrom==whereTo){
            whereTo=null;
            whereFrom=null;
        }
    }

    /**
     * ajtók kinyitását végzi
     */
    void acceptOpenDoor(){
        System.out.println(sk.names.get(this) + "acceptOpenDoor");
        this.closed=false;
        System.out.println(sk.names.get(this) + "acceptOpenDoor returned with void");
        return;
    }

    public void switchMe(boolean toOpen){
        if(toOpen){
            closed = false;
        }
        else{
            closed = true;
        }
    }

}
