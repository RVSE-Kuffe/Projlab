package logarlecTheGame.Model;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
public
    /**
     * A hallgatót próbálja megölni
     * @param i     Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    boolean die(){
        for (Item item : ItemList) {
            if(item.acceptSP(this))
                return false;
        }
        return true;
    }

    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    boolean stun(){
        for (Item item : ItemList) {
            if(item.acceptGasProtect(this))
                return true;
        }
        return false
    }

    /**
     * Tárgyak letételét/használatát kezdeményezi
     * @param i     Az a tárgy amivel szeretnénk dolgozni
     */
    void PutDown(Item i){
        i.acceptPutDown(this);
    }

    /**
     * Két tranziszto párosítását kezdeményezi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    boolean pairing(Transistor t1, Transistor t2){
        if(t1.acceptPairing(this, t2))
            return true;
        return false;
    }

    /**
     * Két tranzisztor párosítását végzi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    boolean pair(Transistor t1, Transistor t2){
        if(t2.makePair(t1)){
            if(t1.makePair(t2)){
                return true;
            }
        }
        return false;
    }

    boolean protect(Item i){}
}
