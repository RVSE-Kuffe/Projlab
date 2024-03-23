package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.Skeleton;

import logarlecTheGame.Model.Item.*;

import logarlecTheGame.Model.Interfaces.*;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
    public Student(Skeleton s, String oName){
        s.names.put(this,oName);
    }

    /**
     * A hallgatót próbálja megölni
     * @param i     Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    public boolean die(){
        for (Item item : itemList) {
            if(item.acceptSP(this))
                return false;
        }
        return true;
    }

    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    public boolean stun(){
        for (Item item : itemList) {
            if(item.acceptGasProtect(this))
                return true;
        }
        return false;
    }

    /**
     * Két tranzisztor párosítását kezdeményezi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pairing(Transistor t1, Transistor t2){
        if(t1.acceptPairing(this, t2))
            return true;
        return false;
    }

    public boolean pair(Item i1, Item i2) {
        if(i2.makePair(i1)){
            if(i1.makePair(i2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Két tranzisztor párosítását végzi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pair(Transistor t1, Transistor t2) {
        if(t2.makePair(t1)){
            if(t1.makePair(t2)){
                return true;
            }
        }
        return false;
    }

    /**
     * Tárgyak letételét/használatát kezdeményezi
     * @param i A tárgy amit használunk
     */
    void putDown(Item i){
        i.AcceptPutDown(this);
    }

    /**
     * Tárgyak használatát végzi
     * @param i     Az a tárgy amivel szeretnénk dolgozni
     */
    public void use(Item i) {
        dropItem(i);
        this.location.addItem(i);
    }

    public void use(Transistor i) {
        dropItem(i);
        this.location.removePlayer(this);
        i.getPair().teleportPlayer(this);
    }

    public void use(Camambert i) {
        dropItem(i);
        this.location.makeGassed();
    }

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


    public boolean protect(Tvsz tvsz) {
        if(i.durabminus())
            return true;
        return false;
    }

    public boolean protect(Beer b) {
        if(i.durabminus())
            return true;
        return false;
    }
}
