package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;

  /**
   * Ennek az osztálynak a függvényeit felüldefiniálja,
   *  akinek kell
     */
public abstract class Item {
    boolean isFake;

        /**
         * Tárgyletétel elfogadására
         * @param s a diák aki leteszi a tárgyat,
         * meghívja a student use függvényét, aki használja
     */
    public void acceptPutDown(Student s){
        s.use(this);
    }
  /**
         * Tárgyáltali megvédés elfogadása
         * @param s a diák aki leteszi a tárgyat,
         * @return igaz, ha sikeres, hamis ha nem
         * meghívja a student use függvényét, aki használja
     */
    public boolean acceptSP(Student s){
       return false;
    }
      /**
         * Tárgyletétel elfogadására (mask) és megvédés
         * @param s a diák aki leteszi a tárgyat,
         * @return igaz, ha sikeres, hamis ha nem
         * meghívja a student use függvényét, aki használja
     */
    public boolean acceptGasProtect(){
       return false;
    }
  /**
         * Tárgyfelvétel elfogadása
         * @param s a játékos aki felveszi a tárgyat,
       * meghívja a player tárgyfelvétel függvényét
         * meghívja a student use függvényét, aki használja
     */
    public boolean acceptPickUp(Player p){
        return (p.pickUp(this));
    }

      /**
         *transistor párosítás elfogadó
         * @param s a diák aki párosít
         * @param t transistor aminek a függvényét hívjuk (amivel párosítunk)
         * @return igaz, ha sikeres, hamis ha nem
         * meghívja a student use függvényét, aki használja
     */
    public boolean acceptPairing(Student s, Transistor t){
        s.pair(this, t);
        return true;
    }

/**
         * Párosítás elfogadó
         * @param s a diák aki párosít
         * @param i item amivel párosítani akarunk
         * @return hamis, mivel csak transistor párosodhat
     */
    public boolean acceptPairing(Student s, Item i){
        return false;
    }
/**
         *Párosítás csináló
         * @param i a diák aki párosít
         * @return hamis, mivel nem párosodhat más csak transistor
     */
    public boolean makePair(Item i){
        return false;
    }

    
}
