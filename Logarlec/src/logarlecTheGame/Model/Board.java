package logarlecTheGame.Model;

import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Interfaces.CycleBased;
import java.util.Random;

public class Board implements CycleBased{
    List<Room> roomList = new ArrayList<>();
    private Skeleton sk;
    private static Random random=new Random();
    List itemList = new ArrayList<>();

    /**
        *Board osztáy konstruktora
     */
    public Board(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    /**
        Pálya inicializálás függvény
     */

    public void init(){System.out.println(sk.names.get(this) + "init");}

    /**
        Játék végét jelző függvény
     */

    public void gameOver(){System.out.println(sk.names.get(this) + "gameOver");}

    /**
        *Tábla módosítását jelző függvény
     */

    public void modify(){System.out.println(sk.names.get(this) + "modify");}

    /**
         * Szoba hozzáadása a pályához,
         * pl. Splitnél
         * @param r az új szoba
     */

    public void addRoom(Room r1){
        roomList.add(r1);
        System.out.println(sk.names.get(this) + " addRoom(" + sk.names.get(r1) + ")");

        System.out.println(sk.names.get(this) + " addRoom returned");
    }

      /**
         * Szoba törlése a pályáról
         * pl. Mergenél
         * @param r1 az törlendő szoba
     */
    public void removeRoom(Room r1){
        roomList.remove(r1);
        System.out.println(sk.names.get(this) + "removeRoom");
    }

    /**
         * Szobák mergelése függvény
         * @param r1 az egyik szoba
         * @param r2 a másik szoba
         * mindenképp mergel két szobát
     */
    public boolean forceMerge(Room r1, Room r2){System.out.println(sk.names.get(this) + "forceMerge");return true;}

      /**
         * Szobák mergelése függvény
         * @param r a szoba amit feloszt
         * mindenképp splitel egy szobát, és létrehoz egy újat
     */
    public void forceSplit(Room r){
        System.out.println(sk.names.get(this) + "forceSplit(" + sk.names.get(r) + ")");
        Room newRoom= r.newRoom();
    
        addRoom(newRoom);
        System.out.println(sk.names.get(this) + " return forceSplit(" + sk.names.get(r) + ")");
    }

    /**
         *játékos általi játék megnyerését jelző függvény
     */

    public void win(){System.out.println(sk.names.get(this) + "win");}

    @Override
    public void iterate() {
        int choice= random.nextInt(3);
        switch(choice){
            case 0:
                break;
            case 1:
                int index1=random.nextInt(roomList.size());
                int index2=random.nextInt(roomList.size());
                while(index2==index1){
                    index2=random.nextInt(roomList.size());
                }
                forceMerge(roomList.get(index1), roomList.get(index2));
                break;
            case 2:
                int index=random.nextInt(roomList.size());
                forceSplit(roomList.get(index));
                break;
        }
        
    }

}
