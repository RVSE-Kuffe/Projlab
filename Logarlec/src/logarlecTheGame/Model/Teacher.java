package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
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
    public void stunTeacher(){
        System.out.println(sk.names.get(this) + " stunTeacher");
        stunned = true;
        System.out.println(sk.names.get(this) + " return stunTeacher");
    }
    /**
     * Teacher logarléc felvétele
     * Ő nem tudja felvenni, tehát semmi sem történik
     * @param l a logarlec amit fel akarna venni
     */
    @Override
    public void pickUp(Logarlec l){
        System.out.println(sk.names.get(this) + " pickUp(" + sk.names.get(l) + ")");
        System.out.println(sk.names.get(this) + " return pickup(" + sk.names.get(l) + ")");
    }
}
