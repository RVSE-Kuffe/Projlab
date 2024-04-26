package logarlecTheGame.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Model.Interfaces.RoomPairing;
import logarlecTheGame.Model.Item.Item;

import java.util.Random;

public class Board implements CycleBased, RoomPairing, Serializable{
    private List<Room> roomList = new ArrayList<>();
    private Skeleton sk;
    private static Random random=new Random();
    private List<CycleBased> cycleList = new ArrayList<>();
    private Map<String, Object> bObjects = new HashMap<>();
    private Map<Object, String> bNames = new HashMap<>();

    public Object stringToObject(String name){
        Object o = bObjects.get(name);
        if(o == null) throw new NullPointerException("Object is not in the Map");
        return o;
    }

    public String objectToString(Object o){
        String name = bNames.get(o);
        if(name == null) throw new NullPointerException("Object is not in the Map");
        return name;
    }

    /**
        *Board osztáy konstruktora
     */
    public Board(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

    public void addIterating(CycleBased c){
        cycleList.add(c);
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
    public boolean forceMerge(Room r1, Room r2){
        System.out.println(sk.names.get(this) + "forceMerge");
        if(r1.getCapacity()>=r2.getCapacity() && (r1.getPlayerCount()+r2.getPlayerCount()<=r1.getCapacity())){
            return r1.acceptPairing(this,r2);
        }
        if(r2.getCapacity()>=r1.getCapacity() && (r1.getPlayerCount()+r2.getPlayerCount()<=r2.getCapacity())){
            return r1.acceptPairing(this,r2);
        }
        return false;
    }
    public boolean pair(Room r1, Room r2){
        r2.mergeRoom(r1);
        this.removeRoom(r2);
        return true;
    }
    public boolean pair(Room r1, CursedRoom r2){
        return false;
    }
    public boolean pair(CursedRoom r1, Room r2){
        return false;
    }
    public boolean pair(CursedRoom r1, CursedRoom r2){
        return false;
    }

      /**
         * Szobák mergelése függvény
         * @param r a szoba amit feloszt
         * mindenképp splitel egy szobát, és létrehoz egy újat
     */
    public void forceSplit(Room r){
        System.out.println(sk.names.get(this) + "forceSplit(" + sk.names.get(r) + ")");
        Room newRoom= r.newRoom();
        bObjects.put("split", newRoom);
        bNames.put(newRoom, "split");
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
        for (CycleBased cycleBased : cycleList) {
            cycleBased.iterate();
        }
        
    }

    public List<CycleBased> getCycles(){
        return cycleList;
    }

    public String listRooms(boolean withPlayers, boolean withItems){
        String returnString = "";
        if(withPlayers){
        for (Room r : roomList) {
                returnString += r.listMe(this, true, false, false) + ", ";
            }
            return returnString;
        }
        if(withItems){
            for (Room r : roomList) {
                returnString += r.listMe(this, false, true, false);
            }
            return returnString;
        }
        
        for(Room r : roomList){
            returnString += objectToString(r) + ", ";
        }
        return returnString;
    }

    public void addToObjects(String s, Object o){
        bObjects.put(s, o);
    }

    public void addToStrings(String s, Object o){
        bNames.put(o,s);
    }

    public void serialize(){
        try{
            FileOutputStream fileOut = new FileOutputStream("C:/Bálint/HF-k/projlab/Projlab/Logarlec/src/logarlecTheGame/board.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(){
        try {
            FileInputStream fileIn = new FileInputStream("C:/Bálint/HF-k/projlab/Projlab/Logarlec/src/logarlecTheGame/board.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this = (Board)in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
