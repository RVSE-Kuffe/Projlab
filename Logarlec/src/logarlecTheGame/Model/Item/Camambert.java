package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.*;


public class Camambert extends Item {
    /**
     * Camambert osztály konstruktora, 
     * inicializálja az attribútumokat
     */
    public Camambert() {
        isFake=false;
    }
    /**
     * A tárgyat le akarja tenni(és használni) hallgató
     * speciális, mert letételre képessége van(gázosítás)
     * @param s     a student aki fel akarja venni (visitor)
     */
    @Override
     public void acceptPutDown(Student s){
        s.use(this);
     }
    
}
