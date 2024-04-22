package logarlecTheGame.Model;
import java.util.logging.Logger;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Model.Interfaces.*;

public class Janitor extends Player {
    
     /**
     * Tárgyak felvételét kezdeményezi
     * @param i     Felvett tárgy
     */
    @Override
    public void pickUpItem(Item i){
        return;
    }

    /**
     * Tárgyak felvételét valósítja meg
     * @param i     A felvenni kívánt tárgy
     */
    @Override
    public void pickUp(Item i){
        return;
    }

    /**
     * A Logarléc felvételét valósítja meg
     * felvételével meg van nyerve a játék
     * Teacher felüldefiniálja
     * @param i     Logarléc
     */
    @Override
    public void pickUp(Logarlec i){
        return;
    }

    /**
     * A sör felvételét kezeli, mivel ez egy speckó eset
     * @param i Sör
     */
    @Override
    public void pickUp(Beer i){
      return;
    }


    /**
     * A Hallgatók bénítását végzi (stunned = true és itemek eldobása)
     * @return  Hamissal tér vissza, ha a Hallgatót egy tárgya megvédte, különben Igaz
     */
    @Override
    public boolean stun(){
        return false;
    }

    /**
     * Az Oktatók bénítását végzi
     * Teacher felüldefiniálja, mert ez csak rá van hatással 
     */
    @Override
    public void stunTeacher(){
        return;
    }

    /**
     * A tárgyak eldobását végzi
     * magától kiveszi a player és leteszi az adott szobába
     * @param i     Az eldobott tárgy
     */
    @Override
    public void dropItem(Item i){
        return;
    }

    /**
     * Játékosok megölésével próbálkozik, kezdeményezi azt
     * @param p     A játékos, akit próbálunk megölni
     * @return      Igazzal tér vissza, ha a játékos meghalt, különben hamis
     */
    
    @Override
    public void kill(Player p){
        return;
    }

    /**
     * A játékosok megölését végzi. A student ezt felüldefiniálja, így ez csak az oktatókra hívódik, ők pedig nem tudnak meghalni
     * @return  Hamis, ha a játékosnak sikerült magát megvédenie, különben Igaz
     */
    @Override
    public boolean die(){
        return false;
    }

    /**
     * A játékos szobaváltását kezdeményezi
     * @param r     A szoba, ahová lépünk
     * @return      Igaz, ha az 'r' szobába van elég hely, tehát át lehet lépni. Különben Hamis
     */
    @Override
    public boolean changeR(Door d){
        //System.out.println(sk.names.get(this) + " changeR(" + sk.names.get(d) + ")");
        if(this.location.changeRoom(this, d)){
           // System.out.println(sk.names.get(this) + ".changeR(" + sk.names.get(d) + ") returned True");
            return true;
        }
       // System.out.println(sk.names.get(this) + ".changeR(" + sk.names.get(d) + ") returned False");
        return false;
    }


    /**
     * A játékos kábítását oldja fel
     */
    @Override
    public void heal(){
        //System.out.println(sk.names.get(this) + ".heal()");
        stunned = false;
        //System.out.println(sk.names.get(this) + ".heal() returned");
    }


    /**
     * A játékost próbálja védeni a gáztól
     * @param m     A maszk amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    @Override
    public boolean maskProtect(Mask m){
        return false;
    }

     /**
     * A játékost próbálja védeni a gáztól
     * @param i     Az item (ami nem maszk), amivel védekezik
     * @return      Ha sikerült a védés, Igazzal, ha nem, akkor Hamissal tér vssza
     */
    @Override
    public boolean maskProtect(Item i){
        return false;
    }

    /**
     * Beállítja a játékos szobáját
     * @param r     A játékos új lokációja
     */
    @Override
    public void setRoom(Room r){
        this.location=r;
    }

    public void cleanRoom(){
        this.location.makeUnGassed();
       // this.location.sendOut(this);
    }

    //public void sendPlayersOut(){
      //  this.location.sendOut(this);
    //}

}
