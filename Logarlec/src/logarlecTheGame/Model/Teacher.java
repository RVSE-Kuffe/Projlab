package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;

import java.util.Random;

import logarlecTheGame.*;
import logarlecTheGame.Model.Item.*;

public class Teacher extends Player {
    /**
     * Teacher osztály konstruktora
     * inicializálja az attribútumokat
     */
    public Teacher(Skeleton s, String n, int i, Room l){
        super(s, n, i, l);
    }


    //Tanar random mozgasa
    public boolean RandomTeacherMove(){

        Random random=new Random();
        int a=random.nextInt(2);
        if(a==0){
            int doorcounter=0;
            for(Door d:this.location.doorList ){
                doorcounter++;
            }
            int chosendoor=random.nextInt(doorcounter);
            return this.changeR(this.location.doorList.get(chosendoor));
        }
        else{
                int itemcounter=0;
                for(Item i:this.location.itemList ){
                    itemcounter++;
                }
                int chosenitem=random.nextInt(itemcounter);
                if(this.itemList.size()==5){
                    return false;
                }
                return this.pickUpItem(this.location.itemList.get(chosenitem));
        }
    }

    /**
     * Tanár emberölő függvénye
     * meghívja a megölni kívánt ember die() függvényét
     * @param p a player akit meg akar ölni
     */
    @Override
    public void kill(Player p){
        System.out.println(sk.names.get(this) + " kill(" + sk.names.get(p) + ")");
        p.die();
        System.out.println(sk.names.get(this) + " return kill(" + sk.names.get(p) + ")");
        return;
    }


    /**
     * Lestunnolja az oktatót
     * Ezután ő elejti minden tárgyát
     * Mivel ez ellen nem tud védekezni
     */
    @Override
    public boolean stunTeacher(){
        System.out.println(sk.names.get(this) + " stunTeacher");
        stunned = true;
        System.out.println(sk.names.get(this) + " return stunTeacher");
        return true;
    }
    /**
     * Teacher logarléc felvétele
     * Ő nem tudja felvenni, tehát semmi sem történik
     * @param l a logarlec amit fel akarna venni
     */
    @Override
    public boolean pickUp(Logarlec l){
        System.out.println(sk.names.get(this) + " pickUp(" + sk.names.get(l) + ")");
        System.out.println(sk.names.get(this) + " return pickup(" + sk.names.get(l) + ")");
        return false;
    }
}
