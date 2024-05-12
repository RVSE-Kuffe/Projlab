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

import logarlecTheGame.Model.Interfaces.*;
import java.util.Random;

public class Board implements CycleBased, RoomPairing, Serializable{
    private List<Room> roomList = new ArrayList<>();
    private Random random=new Random();
    private List<CycleBased> cycleList = new ArrayList<>();
    private Map<String, Object> bObjects = new HashMap<>();
    private Map<Object, String> bNames = new HashMap<>();

    public void addToBoard(Object o, String str){
        bObjects.put(str, o);
        bNames.put(o, str);
    }

    public void deleteFromBoard(Object o, String str){
        bObjects.remove(str, o);
        bNames.remove(o, str);
    }

    public Object stringToObject(String name){
        Object o = bObjects.get(name);
        if(o == null) throw new NullPointerException(name+" is not in the Map");
        return o;
    }

    public String objectToString(Object o){
        String name = bNames.get(o);
        if(name == null) throw new NullPointerException("Object is not in the Map");
        return name;
    }

    public Board() { /* Board osztáy konstruktora */ }

    public void addIterating(CycleBased c){
        cycleList.add(c);
    }

    /**
        Pálya inicializálás függvény
     */

    public void init(){/*TODO*/}

    /**
        Játék végét jelző függvény
     */

    public void gameOver(){/*TODO*/}

    /**
        *Tábla módosítását jelző függvény
     */

    public void modify(){/*TODO*/}

    /**
         * Szoba hozzáadása a pályához,
         * pl. Splitnél
         * @param r az új szoba
     */

    public void addRoom(Room r1){
        roomList.add(r1);
    }

      /**
         * Szoba törlése a pályáról
         * pl. Mergenél
         * @param r1 az törlendő szoba
     */
    public void removeRoom(Room r1){
        roomList.remove(r1);
    }

    /**
         * Szobák mergelése függvény
         * @param r1 az egyik szoba
         * @param r2 a másik szoba
         * mindenképp mergel két szobát
     */
    public boolean forceMerge(Room r1, Room r2){
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
        this.removeFromMap(r2);
        return true;
    }

    public void removeFromMap(Object o){
        String name = objectToString(o);
        bObjects.remove(0);
        bNames.remove(name);
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
    public Room forceSplit(Room r){
        Room newRoom= r.newRoom();
        addRoom(newRoom);
        return newRoom;
    }

    /**
         *játékos általi játék megnyerését jelző függvény
     */

    public void win(){/*TODO*/}

    @Override
    public void iterate() {
        int choice= random.nextInt(3);
        switch(choice){
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
            default: //case 0
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
        StringBuilder sb = new StringBuilder("");
        if(withPlayers){
        for (Room r : roomList) {
                sb.append(r.listMe(this, true, false, false) + ", ");
            }
            return sb.toString();
        }
        if(withItems){
            for (Room r : roomList) {
                sb.append(r.listMe(this, false, true, false));
            }
            return sb.toString();
        }
        
        for(Room r : roomList){
            sb.append(objectToString(r) + ", ");
        }
        return sb.toString();
    }

    public void serialize(){
        try(FileOutputStream fileOut = new FileOutputStream("Logarlec/board.ser");){
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void deserialize(){
        try (FileInputStream fileIn = new FileInputStream("Logarlec/board.ser");){
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Board temp = (Board)in.readObject();
            this.roomList = temp.roomList;
            this.random = temp.random;
            this.cycleList = temp.cycleList;
            this.bObjects = temp.bObjects;
            this.bNames = temp.bNames;
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
