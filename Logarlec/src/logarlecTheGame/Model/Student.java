package logarlecTheGame.Model;

import java.rmi.server.Skeleton;
import java.util.logging.Logger;

import logarlecTheGame.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Model.Interfaces.*;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
    Logger logger = Logger.getLogger(getClass().getName());
    
    public Student(Skeleton s, String n, int i, Room r){
        super(s,n,i,r);
        logger.info(s.names.get(this) + ".ctor()\n");
    }

    /**
     * A hallgatót próbálja megölni
     * @param i     Az az item, amire megpróbálja meghívni a függvény a protectSP() függvényt
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean die(){
        logger.info(s.names.get(this) + ".die()\n");
        for (Item item : this.itemList) {
            if(item.acceptSP(this)){
                logger.info(s.names.get(this) + ".die() returned with False\n");
                return false;
            }
        }
        logger.info(s.names.get(this) + ".die() returned with True\n");
        return true;
    }

    /**
     * A hallgatót próbálja stunnolni
     * @return      Hamis, ha sikerült valamelyik tárgynak megvédenie a hallgatót, ellenkező esetben igaz
     */
    @Override
    public boolean stun(){
        logger.info(s.names.get(this) + ".stun()\n");
        for (Item item : itemList) {
            if(item.acceptGasProtect(this)){
                logger.info(s.names.get(this) + ".stun() returned with True\n");
                return true;
            }
        }
        logger.info(s.names.get(this) + ".stun() returned with False\n");
        return false;
    }

    /**
     * Két tranzisztor párosítását kezdeményezi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pairing(Transistor t1, Transistor t2){
        logger.info(s.names.get(this) + ".pairing()\n");
        if(t1.acceptPairing(this, t2)){
            logger.info(s.names.get(this) + ".pairing() returned with True\n");
            return true;
        }else{
            logger.info(s.names.get(this) + ".pairing() returned with False\n");
            return false;
        }
        //return t1.acceptPairing(this, t2);
    }

    public boolean pair(Item i1, Item i2) {
        return (i2.makePair(i1) && (i1.makePair(i2)));
    }

    /**
     * Két tranzisztor párosítását végzi
     * @param t1    Első tranzisztor
     * @param t2    Második tranzisztor
     * @return      Ha sikerült párosítani, akkor igazzal tér vissza, különben hamis.
     */
    public boolean pair(Transistor t1, Transistor t2) {
        logger.info(s.names.get(this) + ".pair()\n");
        if(t2.makePair(t1) && (t1.makePair(t2))){
            logger.info(s.names.get(this) + ".pairing() returned with True\n");
            return true;
        }else{
            logger.info(s.names.get(this) + ".pairing() returned with False\n");
            return false;
        }
    }

    /**
     * Tárgyak letételét/használatát kezdeményezi
     * @param i A tárgy amit használunk
     */
    void putDown(Item i){
        i.acceptPutDown(this);
        logger.info(s.names.get(this) + ".putDown()\n");
    }

    /**
     * Tárgyak használatát végzi
     * @param i     Az a tárgy amivel szeretnénk dolgozni
     */
    public void use(Item i) {
        logger.info(s.names.get(this) + ".use()\n");
        dropItem(i);
        this.location.addItem(i);
    }

    public void use(Transistor i) {
        logger.info(s.names.get(this) + ".use()\n");
        dropItem(i);
        this.location.removePlayer(this);
        if(!i.getPair().teleportPlayer(this))
            this.location.addPlayer(this);
    }

    public void use(Camambert i) {
        logger.info(s.names.get(this) + ".use()\n");
        dropItem(i);
        this.location.makeGassed();
    }

    public void use(Tablatorlo i) {
        logger.info(s.names.get(this) + ".use()\n");
        dropItem(i);
        this.location.makeClean();
    }

    /**
     * A hallgató védelmét kezdeményezi
     * @param i     A tárgy, amivel megpróbálja magát megvédeni
     * @return      Igaz, ha sikerült a tárgynak megvédenie a játékost, különben hamis.
     */
    public boolean protect(Item i){
        logger.info(s.names.get(this) + ".protect() returned with False\n");
        return false;
    }


    public boolean protect(Tvsz i) {
        logger.info(s.names.get(this) + ".protect()\n");
        if(i.durabminus()){
            logger.info(s.names.get(this) + ".protect() returned with True\n");
            return true;
        }
        logger.info(s.names.get(this) + ".protect() returned with False\n");
        return false;
    }

    public boolean protect(Beer i) {
        logger.info(s.names.get(this) + ".protect()\n");
        if(i.durabminus()){
            logger.info(s.names.get(this) + ".protect() returned with True\n");
            return true;
        }
        logger.info(s.names.get(this) + ".protect() returned with False\n");
        return false;
    }
}
