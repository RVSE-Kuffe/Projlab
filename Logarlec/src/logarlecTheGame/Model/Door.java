package logarlecTheGame.Model;

import java.io.Serializable;

public class Door implements Serializable {
    boolean validTo = true;
    Room whereTo;
    Room whereFrom;
    boolean validFrom = true;
    boolean closed=false;

    public Door(Room r1, Room r2, boolean vf, boolean vt) {
        whereFrom=r1;
        whereTo=r2;
        validFrom = vf;
        validTo = vt;
    }
    /**
     * ajtók bezárását végzi
     */
    void acceptCloseDoor(){
        this.closed=true;
    }

    /**
     * Intézi a játékos eltávolítását az előző és hozzáadását a következő szobához
     * @param p     a karakter, akit át kell léptetni 
     * @return      Igaz, ha a whereTo szobába van elég hely, tehát át lehet lépni. Különben Hamis
     */
    public boolean transferPlayer(Player p, Room from){
        if(! closed){
            if(whereTo!=from&&validTo){
                if(whereTo.addPlayer(p)){

                whereFrom.removePlayer(p);

                whereTo.killAll(p);
                whereTo.pvp(p);

                return true;
                }
             }
             else if(whereTo==from&&validFrom && whereFrom.addPlayer(p)){
                    whereTo.removePlayer(p);
                    whereFrom.killAll(p);
                    whereFrom.pvp(p);
                    return true;
            }
        }         
        return false;
    
    }
    //elso parameter hogy mire valtoztatunk, masodik hogy melyiket--mergenel r1 volt a nagyobb
    public void changeRoom(Room r1,Room r2){
        if(r2==whereTo){
            whereTo=r1;
        }
        if(r2==whereFrom){
            whereFrom=r1;
        }
        if(whereFrom==whereTo){
            whereTo=null;
            whereFrom=null;
            return;
        }
        r1.addDoor(this);
    }

    /**
     * ajtók kinyitását végzi
     */
    public void acceptOpenDoor(){
        this.closed=false;
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
