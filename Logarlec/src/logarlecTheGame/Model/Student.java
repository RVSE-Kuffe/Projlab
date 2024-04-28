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
    
    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean stun(){
        for (Item item : itemList) {
            if(item.acceptGasProtect(this)){
                return true;
            }
        }
        stunned =true;
        return false;
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
        if(i.teleportPlayer(this)){
            this.location.removePlayer(this);
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

    public void use(Airfreshener a) {
        dropItem(a);
        this.location.makeUnGassed();
        a.makeFake();

    }

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
