package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Interfaces.*;

public class Tablatorlo extends Item implements CycleBased {
    private int durability;

    /**
    *Tablatorlo konstruktor, inicializálja az attribútumokat
     */
    public Tablatorlo(int durab) {
        durability=durab;
    }
    
    /**
         *Felüldefiniált letétel elfogadó
         * @param s a diák aki leteszi a tárgyat
     */
    @Override
    public void acceptPutDown(Student s){
        s.use(this);
    } 
    /**
     *Csökkenti a tárgy használhatóságát, 
     hogyha aktív és még lehet
     */
    private void csokkent(){
        if(durability>0) {durability-=1;}
    }
/**
     * Meghívja a csökkentés metódust, 
     * amikor iterál
     */
    public void iterate(){
        this.csokkent();
    }
    
}
