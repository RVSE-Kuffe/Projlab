package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.Student;
/**
 * Az Airfreshener (légfrissítő) tárgyat reprezentáló osztály.
 */
public class Airfreshener extends Item{

    /**
     * Az Airfreshener osztály konstruktora.
     * Az isFake attribútumot hamisra állítja.
     */
    public Airfreshener(){
        isFake=false;
    }

    /**
     * Az Airfreshener elfogadja a letétet.
     * Ha a tárgy nem hamis, a játékos használja, 
     * egyébként hamis tárgyat tesz csak le.
     *
     * @param s A játékos, aki leteszi a tárgyat
     */
    @Override
     public void acceptPutDown(Student s){
        if(isFake){
            s.useFake(this);
        }
        s.use(this);
     }

     /**
     * Hamissá teszi az Airfreshener tárgyat.
     * Ha elhasználódott hamis lesz
     */
     public void makeFake(){
        isFake=true;
    }
    
}
