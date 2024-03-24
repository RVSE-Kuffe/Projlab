package logarlecTheGame.Model.Item;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Room;
import logarlecTheGame.Model.Item.Item;


public class Transistor extends Item  {
    private Transistor pair=null;
    private Room location =null;
    private boolean active = false;
    private Skeleton sk;

  /**
   *Transistor osztály konstruktora,
   *inicializála az attribútumokat
     */
    public Transistor(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }
  /**
   * *Transistor osztály konstruktora,
   *inicializála az attribútumokat,
   *alapvetően beállít egy párt, csak teszthez kell
     */
    public Transistor(Skeleton s, String n, Transistor p) {//teszteléshez kell csak, hogy legyen alapvető párja, amúgy nem használandó, amúgy túl hosszú teszt lenne
        sk = s;
        sk.names.put(this, n);
        this.pair=p;
    }
   /**
   *megnézi egy adott transistornak ki a párja,
   ha van egyáltalán
     */
    public Transistor getPair(){
         System.out.println(sk.names.get(this) + "getPair");
        return pair;}

     /**
    *Tranistor párosító függvény
    *@param t2 a másik transistor, amivel párosítunk
    *@return igaz, ha sikeres,
    *hamis, ha nem
     */
    public boolean makePair(Transistor t2){
        System.out.println(sk.names.get(this) + "makePair");
        if((this.pair==null&&t2.getPair()==null)||(this.pair==null && t2.getPair().equals(this))){
            this.pair=t2;
         return true;
        }
         return false;

    }
/**
     * Aktiválja a tárgyat, ezesetben transistort a játékos, 
     * azzal, hogy használja
     * aktívra állítja az attribútumát
     */
    public void activate(){
        System.out.println(sk.names.get(this) + "activate");
        this.active=true;
    }

    /**
         * Tárgyletétel elfogadására
         * @param s a diák aki leteszi a tárgyat,
         * meghívja a student use függvényét, aki használja
     */

    @Override
    public void acceptPutDown(Student s){
        System.out.println(sk.names.get(this) + "acceptPutDown");
        s.use(this);
    }

      /**
         * Teleportolásra való függvény, transistor használat miatt
         * @param s a játékos aki teleportál
         * @return igaz ha a meghívott arrivingPlayer igazzal tér vissza,
         *  hamis ha nem
     */
    public boolean teleportPlayer(Student s){
        System.out.println(sk.names.get(this) + "teleportPlayer");
       return this.arrivingPlayer(s);
        
    }

      /**
         * Érkezőjátékos, hogy hova kerül
         * @param s a játékos aki érkezik
         * @return igaz, ha hozzá lehet adni, hamis, ha nem
     */
    public boolean arrivingPlayer(Student s){  
        return this.pair.location.addPlayer(s);
        
    }

      /**
         * Szobabeállító függvény
         * @param r a szoba amit beállítunk
     */
    public void setRoom(Room r){
        System.out.println(sk.names.get(this) + "setRoom");
        this.location=r;
    }
      /**
         * felüldefiniált transistor párosítás elfogadó
         * @param s a diák aki párosít
         * @param t transistor aminek a függvényét hívjuk (amivel párosítunk)
         * @return igaz, ha sikeres, hamis ha nem
         * meghívja a student pair függvényét, aki használja
     */
    @Override
    public boolean acceptPairing(Student s, Transistor t){
        System.out.println(sk.names.get(this) + "acceptPairing");
        return s.pair(this, t);
    }
  /**
         * felüldefiniált item párosítás elfogadó
         * @param s a diák aki párosít
         * @param i item aminek a függvényét hívjuk (amivel párosítunk)
         * @return igaz, ha sikeres, hamis ha nem
     */
    @Override
    public boolean acceptPairing(Student s, Item i){
        System.out.println(sk.names.get(this) + "acceptPairing");
        return true;
    }
    
}
