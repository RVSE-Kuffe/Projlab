package logarlecTheGame.Model;

import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Model.Interfaces.*;
import java.util.Random;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
    Random random=new Random();
    
    /**
     * Student osztály konstruktora
     * inicializálja az attribútumokat
     */
    public Student(int i, Room r){
        super(i,r);
    }

    /**
     * A hallgatót próbálja megölni
     * @param i     Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean die(){
        for (Item item : this.itemList) {
            if(item.acceptSP(this)){
                return false;
            }
        }
        location.killPlayer(this);
        return true;
    }
    
    @Override
    public void kill(Player p){/*This shouldn't do enything at all */}
    
    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean stun() {
        // Ellenőrizzük, hogy valamelyik tárgy meg tudja-e védeni
        for (Item i : itemList) {
            if (i.acceptGasProtect(this)) {
                return false; // Ha van olyan tárgy, ami meg tud védeni, akkor kilépünk
            }
        }
    
        // Ha egyik tárgy sem tud védeni, akkor eldobhatjuk őket
        for (int i = 0; i < itemList.size(); i++) {
            dropItem(itemList.get(i)); // Az "i" indexű elemet eldobjuk
        }
        this.stunned =true;
        // Az "itemList" üres lesz a ciklusok végeztével
    
        // Visszatérünk, hogy sikeres volt-e a stun
        return true;
    }

    /**
     * Két tranzisztor párosítását kezdeményezi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    @Override
    public boolean pairing(Transistor t1, Transistor t2){
        return (t1.acceptPairing(this, t2));
    }

    /**
     * Két Item párosítását kezdeményezi. Hamis, mivel csak tranziszto esetén van értelme
     * @param i1    Első Item
     * @param i2    Második Item
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pairing(Item i1, Item i2) {
        return false;
    }

    /**
     * Két tranzisztor párosítását végzi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pair(Transistor t1, Transistor t2) {
        return (t2.makePair(t1) && (t1.makePair(t2)));
    }

    /**
     * Két item párosítását végzi. Hamis, mert csak tranzisztoral van értelme
     * @param t1    Első item
     * @param t2    Második item
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pair(Item t1, Item t2) {return false;}

    /**
     * Tárgyak letételét/használatát kezdeményezi
     * @param i A tárgy amit használunk
     */
    public void putDown(Item i){
        i.acceptPutDown(this);
    }

    /**
     * Tárgyak használatát végzi
     * @param i     Az a tárgy amivel szeretnénk dolgozni
     */
    public void use(Item i) {
        dropItem(i);
    }

    /**
     * Tranzisztor használatát végzi
     * @param i     Tranzisztor
     */
    public void use(Transistor i) {
        dropItem(i);
        i.setRoom(this.location);
        i.activate();
        Room temp = this.location;
        if(i.teleportPlayer(this)){
            //this.location.removePlayer(this);
            temp.removePlayer(this);
            return;
        }
    }

    /**
     * Camambertek használatát végzi
     * @param i     Sajt
     */
    public void use(Camambert i) {
        dropItem(i);
        this.location.makeGassed();
    }

    

/**
 * A légfrissítő használata.
 * A karakter ledobja a légfrissítőt, és a jelenlegi szoba gáztalan lesz.
 * Emellett hamissá teszi a légfrissítőt.
 * 
 * @param a A használt légfrissítő objektum.
 */
    public void use(Airfreshener a) {
        dropItem(a);
        this.location.makeUnGassed();
        a.makeFake();

    }

    /**
 * Hamis légfrissítő használata.
 * A karakter ledobja a hamis légfrissítőt.
 * 
 * @param a A használt hamis légfrissítő objektum.
 */
    public void useFake(Airfreshener a) {
        dropItem(a);
    }

    /**
     * Tablatorlok használatát végzi
     * @param i     Tablatorlo
     */
    public void use(Tablatorlo i) {
        dropItem(i);
        this.location.makeClean();
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni
     * @return      Igaz, ha sikerült a tárgynak megvédenie a játékost, különben hamis.
     */
    public boolean protect(Item i){
        return false;
    }

    /**
     * Nem csinál semmit a hallgatóval, 
     * mivel ez csak a tanár stunolását végzi atblatorlo miatt
     * hallgatót nem érinti
     */
    @Override
    public boolean stunTeacher(){
        return false;
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni, itt TVSZ
     * @return      Igaz, ha sikerült a tárgynak megvédenie a játékost, különben hamis.
     */
    public boolean protect(Tvsz i) {
        return (i.durabminus());
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni, itt Sör
     * @return      Minden esetben igaz, köszönhetően a sör tulajdonságainak.
     */
    public boolean protect(Beer i) {
        if(!itemList.isEmpty()){
            int index=random.nextInt(itemList.size());
            Item selectedItem=itemList.get(index);
            this.dropItem(selectedItem);
        }
        return true;
    }
}
