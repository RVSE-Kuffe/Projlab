package logarlecTheGame.Model;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Item.*;

public class Teacher extends Player {
    public Teacher(Skeleton s, String n, int i, Room l){
        super(s, n, i, l);
    }

    @Override
    public void kill(Player p){
        System.out.println(sk.names.get(this) + " kill(" + sk.names.get(p) + ")");
        p.die();
        System.out.println(sk.names.get(this) + " return kill(" + sk.names.get(p) + ")");
        return;
    }


    /**
     * Lestunnolja az oktatót, aki elejti minden tárgyát
     */
    @Override
    public void stunTeacher(){
        System.out.println(sk.names.get(this) + " stunTeacher");

        stunned = true;
        for(Item i : itemList){
            dropItem(i);
        }
        System.out.println(sk.names.get(this) + " return stunTeacher");
    }

    @Override
    public void pickUp(Logarlec l){
        System.out.println(sk.names.get(this) + " pickUp(" + sk.names.get(l) + ")");
        System.out.println(sk.names.get(this) + " return pickup(" + sk.names.get(l) + ")");
    }
}
