package logarlecTheGame.Model;
import java.util.logging.Logger;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Model.Interfaces.*;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
    Logger logger = Logger.getLogger(getClass().getName());
    
    
    public Student(Skeleton s, String n, int i, Room r){
        super(s,n,i,r);
        System.out.println(n + ".ctor()");
    }

    /**
     * A hallgatót próbálja megölni
     * @param i     Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean die(){
        System.out.println(sk.names.get(this) + ".die()");
        for (Item item : this.itemList) {
            if(item.acceptSP(this)){
                System.out.println(sk.names.get(this) + ".die() returned False");
                return false;
            }
        }
        System.out.println(sk.names.get(this) + ".die() returned True");
        return true;
    } 
    
    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean stun(){
        System.out.println(sk.names.get(this) + ".stun()");
        for (Item item : itemList) {
            if(item.acceptGasProtect(this)){
                System.out.println(sk.names.get(this) + ".stun() returned True");
                return true;
            }
        }
        System.out.println(sk.names.get(this) + ".stun() returned False");
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
        System.out.println(sk.names.get(this) + ".pairing("+ sk.names.get(t1)+", " +sk.names.get(t2)+")");
        if(t1.acceptPairing(this, t2)){
            System.out.println(sk.names.get(this) + ".pairing("+ sk.names.get(t1)+", " +sk.names.get(t2)+") returned True");
            return true;
        }else{
            System.out.println(sk.names.get(this) + ".pairing("+ sk.names.get(t1)+", " +sk.names.get(t2)+") returned False");
            return false;
        }
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
        System.out.println(sk.names.get(this) + ".pair("+ sk.names.get(t1)+", " +sk.names.get(t2)+")");
        if(t2.makePair(t1) && (t1.makePair(t2))){
            System.out.println(sk.names.get(this) + ".pair("+ sk.names.get(t1)+", " +sk.names.get(t2)+") returned True");
            return true;
        }else{
            System.out.println(sk.names.get(this) + ".pair("+ sk.names.get(t1)+", " +sk.names.get(t2)+") returned False");
            return false;
        }
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
        System.out.println(sk.names.get(this) + ".putDown("+sk.names.get(i)+")");
        i.acceptPutDown(this);
        System.out.println(sk.names.get(this) + ".putDown("+sk.names.get(i)+") returned");
    }

    /**
     * Tárgyak használatát végzi
     * @param i     Az a tárgy amivel szeretnénk dolgozni
     */
    public void use(Item i) {
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+")\n");
        dropItem(i);
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+") returned");
    }

    /**
     * Tranzisztor használatát végzi
     * @param i     Tranzisztor
     */
    public void use(Transistor i) {
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+")\n");
        if(i.teleportPlayer(this)){
            this.location.removePlayer(this);
            dropItem(i);
        }
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+") returned");
    }

    /**
     * Camambertek használatát végzi
     * @param i     Sajt
     */
    public void use(Camambert i) {
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+")");
        dropItem(i);
        this.location.makeGassed();
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+") returned");
    }

    /**
     * Tablatorlok használatát végzi
     * @param i     Tablatorlo
     */
    public void use(Tablatorlo i) {
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+")");
        dropItem(i);
        this.location.makeClean();
        System.out.println(sk.names.get(this) + ".use("+sk.names.get(i)+") returned ");
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni
     * @return      Igaz, ha sikerült a tárgynak megvédenie a játékost, különben hamis.
     */
    public boolean protect(Item i){
        System.out.println(sk.names.get(this) + ".protect("+sk.names.get(i)+")");
        System.out.println(sk.names.get(this) + ".protect("+sk.names.get(i)+") returned False");
        return false;
    }

    /**
     * Nem csinál semmit a hallgatóval
     */
    @Override
    public void stunTeacher(){
        return;
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni, itt TVSZ
     * @return      Igaz, ha sikerült a tárgynak megvédenie a játékost, különben hamis.
     */
    public boolean protect(Tvsz i) {
        System.out.println(sk.names.get(this) + ".protect("+sk.names.get(i)+")");
        if(i.durabminus()){
            System.out.println(sk.names.get(this) + ".protect("+sk.names.get(i)+") returned True");
            return true;
        }
        System.out.println(sk.names.get(this) + ".protect("+sk.names.get(i)+") returned False");
        return false;
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni, itt Sör
     * @return      Minden esetben igaz, köszönhetően a sör tulajdonságainak.
     */
    public boolean protect(Beer i) {
        return true;
    }
}
