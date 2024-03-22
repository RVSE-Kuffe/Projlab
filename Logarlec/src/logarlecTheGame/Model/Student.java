package logarlecTheGame.Model;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
public
    /**
     * A hallgatót próbálja megölni
     * @param i Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
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
     * @return Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
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
     * @param i Az a tárgy amivel szeretnénk dolgozni
     */
    void PutDown(Item i){
        i.acceptPutDown(this);
    }

    
    boolean pairing(Transistor t1, Transistor t2){}
    boolean protect(Item i){}
}
