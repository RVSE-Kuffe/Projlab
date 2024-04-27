package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;


public class Transistor extends Item  {
    private Transistor pair=null;
    private Room location =null;
    private boolean active = false;

  /**
   *Transistor osztály konstruktora,
   *inicializála az attribútumokat
     */
    public Transistor() {
        isFake=false;
    }

   /**
   *megnézi egy adott transistornak ki a párja,
   ha van egyáltalán
     */
    public Transistor getPair(){
        return pair;
    }

     /**
    *Tranistor párosító függvény
    *@param t2 a másik transistor, amivel párosítunk
    *@return igaz, ha sikeres,
    *hamis, ha nem
     */
    public boolean makePair(Transistor t2){
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
        this.active=true;
    }

    /**
         * Tárgyletétel elfogadására
         * @param s a diák aki leteszi a tárgyat,
         * meghívja a student use függvényét, aki használja
     */

    @Override
    public void acceptPutDown(Student s){
        s.use(this);
    }

      /**
         * Teleportolásra való függvény, transistor használat miatt
         * @param s a játékos aki teleportál
         * @return igaz ha a meghívott arrivingPlayer igazzal tér vissza,
         *  hamis ha nem
     */
    public boolean teleportPlayer(Student s){
       if(this.pair.arrivingPlayer(s)){
            this.active=false;
            return true;
       }
       return false;
        
    }

      /**
         * Érkezőjátékos, hogy hova kerül
         * @param s a játékos aki érkezik
         * @return igaz, ha hozzá lehet adni, hamis, ha nem
     */
    public boolean arrivingPlayer(Student s){  
        if(active&&this.location.addPlayer(s)){
            this.active =false;
            return true;
        }
        return false;
    }

      /**
         * Szobabeállító függvény
         * @param r a szoba amit beállítunk
     */
    public void setRoom(Room r){
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
        return true;
    }
    
}
