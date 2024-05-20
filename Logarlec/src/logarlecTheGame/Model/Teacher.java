package logarlecTheGame.Model;

import java.util.Random;
import logarlecTheGame.Model.Item.*;

/**
     * Tanárt reprezentáló osztály, aki ugyan úgy játékos
     */
public class Teacher extends Player {
    Random random=new Random();

    /**
     * Teacher osztály konstruktora
     * inicializálja az attribútumokat
     */
    public Teacher(int i, Room l){
        super(i, l);
    }


    /**
 * Véletlenszerű cselekvés végrehajtása
 * Ha a tanár nem stunnolt, véletlenszerűen választ a lehetőségek között.
 * Ha a véletlenszerűen választott cselekvés ajtón átlépés,
 * akkor megpróbálja megváltoztatni a szobát.
 * Ha az ajtón nem lehet átmenni vagy nincs ajtó a szobában, akkor nem hajt végre semmilyen cselekvést.
 * Ha a véletlenszerűen választott cselekvés tárgy felvétele,
 * akkor megpróbálja felvenni az adott szobában található tárgyat.
 * Ha a tanárnak már 5 tárgya van, akkor nem vesz fel további tárgyat.
 * 
 * @return Igaz, ha a tanár véletlenszerű cselekvést hajtott végre, különben Hamis.
 */
    @Override
    public boolean randomAction(){
        if(!stunned){
        int a=random.nextInt(2);
        if(a==0){
            int doorcounter=this.location.doors().size();
            int chosendoor=random.nextInt(doorcounter);
            return this.changeR(this.location.doors().get(chosendoor));
        }
        else{
            int itemcounter=this.location.items().size();
            if(itemcounter==0)return false;
            int chosenitem=random.nextInt( itemcounter);
            if(this.itemList.size()>=5){
                return false;
            }
            return this.pickUpItem(this.location.itemList.get(chosenitem));
        }
    }
        return false;
    }

    /**
     * Tanár emberölő függvénye
     * meghívja a megölni kívánt ember die() függvényét
     * @param p a player akit meg akar ölni
     */
    @Override
    public void kill(Player p){
        if(!stunned) p.die();
    }


    /**
     * Lestunnolja az oktatót
     * Ezután ő elejti minden tárgyát
     * Mivel ez ellen nem tud védekezni
     */
    @Override
    public boolean stunTeacher(){
        stunned = true;
        return true;
    }
    /**
     * Teacher logarléc felvétele
     * Ő nem tudja felvenni, tehát semmi sem történik
     * @param l a logarlec amit fel akarna venni
     */
    @Override
    public boolean pickUp(Logarlec l){
        return false;
    }
}
