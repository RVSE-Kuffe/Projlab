package logarlecTheGame.Model;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Az ajtókat reprezentáló osztály, melyek két szoba közötti átjárók.
 */
public class Door implements Serializable {
    boolean validTo = true;
    Room whereTo;
    Room whereFrom;
    boolean validFrom = true;
    boolean closed=false;

/**
     * Konstruktor az ajtó létrehozásához.
     * 
     * @param r1    "Ahonnan"" szoba.
     * @param r2    "Ahova" szoba.
     * @param vf    Az ajtó érvényessége az induló szobából.
     * @param vt    Az ajtó érvényessége a cél szobába.
    */

    public Door(Room r1, Room r2, boolean vf, boolean vt) {
        whereFrom=r1;
        whereTo=r2;
        validFrom = vf;
        validTo = vt;
    }
     /**
     * Az ajtó bezárását végző metódus.
     */
    void acceptCloseDoor(){
        this.closed=true;
    }

   /**
     * Az ajtón átlépést végző metódus, amely eltávolítja a játékost az előző szobából
     * és hozzáadja a következő szobához.
     * @param p     a karakter, akit át kell léptetni 
     * @param from  "Ahonnan"" szoba.
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
    /**
     * Szobák cseréjét végző metódus, amelynek során egy szobát egy másikra cserél ki az ajtó.
     * 
     * @param r1    Az új szoba, amire kell állítani.
     * @param r2    Az a szoba, amelyről az ajtó át van állítva.
     */
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
            r1.removeDoor(this);
            return;
        }
        r1.addDoor(this);
    }

    /**
     * Az ajtó kinyitását elfogadó metódus.
     */
    public void acceptOpenDoor(){
        this.closed=false;
    }

    /**
     * Az ajtó állapotát váltó metódus, amely lehetővé teszi az ajtó nyitását vagy zárását.
     * 
     * @param toOpen    Ha igaz, az ajtó nyitva lesz, ha hamis, akkor zárva.
     */
    public void switchMe(boolean toOpen){
        if(toOpen){
            closed = false;
        }
        else{
            closed = true;
        }
    }

}
