package logarlecTheGame.Model;

import logarlecTheGame.Model.Interfaces.CycleBased;
import java.util.Random;
  /**
     * Átkozot szobaát reprezentáló osztály, 
     * mely a Szoba osztály leszármazottja
     */
public class CursedRoom extends Room implements CycleBased {
    private static Random random=new Random();
    /**
     * Átkozot szoba konstruktora
     * beállítja a megfelelő paramétereket
     */
    public CursedRoom(int i, int c, Board b) {
        super(i,c, b);
    }
    /**
     * ajtók bezárását végzi a szobában
     * minden ajtót bezár
     */
    public void closeDoor(){
        for(Door d : doorList){
            d.acceptCloseDoor();
        }
    }
    /**
     * ajtók kinyitását végzi a szobában
     * minden ajtót kinyit
     */
    public void openDoor(){
        for(Door d : doorList){
            d.acceptOpenDoor();
        }
    }

    /**
     * merge párosító függvénye
     * visitor pattern alapján elfogadja a párosítás próbálkozást
     */
    @Override
    public boolean acceptPairing(Board b, Room r){
        return true;
    }

    /**
 * A szobában történő iteráció során végrehajtott műveleteket definiáló metódus.
 * A szoba véletlenszerűen választ egy műveletet a három lehetőség közül:
 * 1. Ajtó bezárása.
 * 2. Ajtó kinyitása.
 * 3. Semmi
 */
    @Override
    public void iterate() {
        int choice= random.nextInt(3);
        switch(choice){
            case 1:
                this.closeDoor();
               break;
            case 2:
                    this.openDoor(); 
                break;
            default:    //case 0
                break;
        } 
    }

}
