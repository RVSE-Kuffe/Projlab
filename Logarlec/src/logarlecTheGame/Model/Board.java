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

import logarlecTheGame.Controller.GameLogic;
import logarlecTheGame.Model.Interfaces.*;
import logarlecTheGame.Model.Item.*;

import java.util.Random;

import javax.swing.JOptionPane;

/**
 * A Board osztály a játék tábláját reprezentálja.
 * Kezeli a játék elemeinek hozzáadását és eltávolítását, 
 * valamint azok nevét és objektumokat tartalmazó "térképeket".
 * Körfüggő és szerializálható
 */
public class Board implements CycleBased, RoomPairing, Serializable{
    private List<Room> roomList = new ArrayList<>();
    private Random random=new Random();
    private List<CycleBased> cycleList = new ArrayList<>();
    private Map<String, Object> bObjects = new HashMap<>();
    private  Map<Object, String> bNames = new HashMap<>();
    private GameLogic gl;


     /**
     * Objektum hozzáadása a táblához névvel együtt.
     *
     * @param o   A hozzáadni kívánt objektum.
     * @param str Az objektumhoz tartozó név.
     */
    public void addToBoard(Object o, String str){
        bObjects.put(str, o);
        bNames.put(o, str);
    }


    /**
     * Objektum eltávolítása a tábláról a nevével együtt.
     *
     * @param o   Az eltávolítandó objektum.
     * @param str Az objektumhoz tartozó név.
     */
    public void deleteFromBoard(Object o, String str){
        bObjects.remove(str, o);
        bNames.remove(o, str);
    }


    /**
     * Név alapján objektum lekérése a tábláról.
     *
     * @param name Az objektum neve.
     * @return A keresett objektum.
     * @throws NullPointerException Ha az objektum nincs a térképen.
     */
    public Object stringToObject(String name){
        Object o = bObjects.get(name);
        if(o == null) throw new NullPointerException(name+" is not in the Map");
        return o;
    }
/**
     * Objektum alapján név lekérése a tábláról.
     *
     * @param o A keresett objektum.
     * @return Az objektumhoz tartozó név.
     * @throws NullPointerException Ha az objektum nincs a térképen.
     */
    public String objectToString(Object o){
        String name = bNames.get(o);
        if(name == null) throw new NullPointerException("Object is not in the Map");
        return name;
    }


     /**
     * Konstruktor a Board osztály példányosítására GameLogic paraméterrel.
     *
     * @param gl A játék logikáját kezelő GameLogic objektum.
     */
    public Board(GameLogic gl) { 
        this.gl=gl;
    }

    /**
     * Alapértelmezett konstruktor a Board osztály példányosítására.
     */
    public Board() { 
    }


    /**
     * Visszaadja a szobák listáját.
     *
     * @return A szobák listája.
     */
    public List<Room> rooms(){
        return roomList;
    }


    /**
     * Iterálható objektum hozzáadása a iterálható listához.
     *
     * @param c Az iterálható objektum.
     */
    public void addIterating(CycleBased c){
        cycleList.add(c);
    }

     /**
     * Szoba hozzáadása a pályához, pl. Splitnél.
     *
     * @param r1 Az új szoba.
     */
    public void addRoom(Room r1){
        roomList.add(r1);
    }

      /**
     * Szoba törlése a pályáról, pl. Mergenél.
     *
     * @param r1 Az törlendő szoba.
     */
    public void removeRoom(Room r1){
        roomList.remove(r1);
    }

    /**
     * Szobák mergelése függvény.
     *
     * @param r1 Az egyik szoba.
     * @param r2 A másik szoba.
     * @return true, ha a merge sikeres, egyébként false.
     */
    public boolean forceMerge(Room r1, Room r2){
        if(r1.getCapacity()>=r2.getCapacity() && (r1.getPlayerCount()+r2.getPlayerCount()<=r1.getCapacity())){
            //JOptionPane.showMessageDialog(null, "merge!"+ objectToString(r1)+ " másik: "+ objectToString(r2));
            //return r1.acceptPairing(this,r2);
            if((r1.acceptPairing(this,r2)==true)){
                JOptionPane.showMessageDialog(null, "Merge! "+ objectToString(r1)+ " with: "+ objectToString(r2));
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "There was an unsuccessful merge attempt");
                return false;
            }
        }
        if(r2.getCapacity()>r1.getCapacity() && (r1.getPlayerCount()+r2.getPlayerCount()<=r2.getCapacity())){
            //JOptionPane.showMessageDialog(null, "merge!"+ objectToString(r2)+ " másik: "+ objectToString(r1));
            //return r1.acceptPairing(this,r2);
            if((r2.acceptPairing(this,r1)==true)){
                JOptionPane.showMessageDialog(null, "Merge! "+ objectToString(r2)+ " with: "+ objectToString(r1));
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null, "There was an unsuccessful merge attempt");
                return false;
            }
           
        }
        JOptionPane.showMessageDialog(null, "There was an unsuccessful merge attempt");
        
        
        return false;
    }


    /**
     * Két szoba párosítása.
     *
     * @param r1 Az első szoba.
     * @param r2 A második szoba.
     * @return true, ha a párosítás sikeres.
     */
    public boolean pair(Room r1, Room r2){
        r2.mergeRoom(r1);
        this.removeRoom(r2);
        this.removeFromMap(r2);
        return true;
    }

    /**
    * Eltávolítja az objektumot a térképről.
    * @param o az objektum, amit el kell távolítani
     */
    public void removeFromMap(Object o){
        String name = objectToString(o);
        bObjects.remove(o);
        bNames.remove(name);
    }

    /**
    * Párosít egy szobát egy elátkozott szobával.
    * @param r1 az első szoba
    * @param r2 az elátkozott szoba
    * @return mindig false, mivel a párosítás nem megengedett
    */
    public boolean pair(Room r1, CursedRoom r2){
        return false;
    }

    /**
    * Párosít egy elátkozott szobát egy szobával.
    * @param r1 az elátkozott szoba
    * @param r2 a szoba
    * @return mindig false, mivel a párosítás nem megengedett
    */
    public boolean pair(CursedRoom r1, Room r2){
        return false;
    }

    /**
 * Párosít két elátkozott szobát.
 * @param r1 az első elátkozott szoba
 * @param r2 a második elátkozott szoba
 * @return mindig false, mivel a párosítás nem megengedett
 */
    public boolean pair(CursedRoom r1, CursedRoom r2){
        return false;
    }

      /**
 * Szobák felosztása függvény.
 * @param r a szoba, amit fel kell osztani
 * Mindig feloszt(split) egy szobát, és létrehoz egy újat.
 */
    public void forceSplit(Room r){
        JOptionPane.showMessageDialog(null, "Split!: "+objectToString(r));
        Room newRoom= r.newRoom();
        this.addRoom(newRoom);
       //return newRoom;
    }

   /**
 * Jelzi, hogy a játékos megnyerte a játékot.
 * Hívja a logika endGame függvényét
 */
    public void win(){
        gl.endGame(true);
    }


    /**
 * Az iterációs ciklusokat végrehajtó függvény.
 * Véletlenszerűen választ egy műveletet: 
 * szobák összevonása, szobák felosztása, vagy semmi. 
 * Ezután minden iteráció alapú objektum végrehajtja saját iterációs ciklusát.
 */
    @Override
    public void iterate() {
        int choice= random.nextInt(3);
        switch(choice){
            case 1:
                if(roomList.size()<=3){
                    break;
                }
                int index1=random.nextInt(roomList.size());
                int index2=random.nextInt(roomList.size());
                while(index2==index1){
                    index2=random.nextInt(roomList.size());
                }
                forceMerge(roomList.get(index1), roomList.get(index2));
                break;
            case 2:
                if(roomList.size()>=16){
                    break;
                }
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

    /**
 * Visszaadja az iterációs ciklusokat tartalmazó listát.
 * @return a ciklusok listája
 */

    public List<CycleBased> getCycles(){
        return cycleList;
    }

    /**
 * Listázza a szobákat.
 * @param withPlayers ha igaz, a szobákban lévő játékosokat is listázza
 * @param withItems ha igaz, a szobákban lévő tárgyakat is listázza
 * @return a szobák listája stringként
 */
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

    /**
 * Serializálja a táblát.
 */
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

    /**
 * Deserializálja a táblát.
 */
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

    
    /**
 * Inicializálja a szobákat, ajtókat és tárgyakat a játék kezdete előtt.
 * A megadott diákokat és egyéb játékosokat is elhelyezi a megfelelő szobákban.
 *
 * @param students A játékban résztvevő diákok listája.
 * @param otherPlayers Az egyéb játékosok listája.
 */

    public void init(List<Student> students, List<Player> otherPlayers){
    
     // Szobák inicializálása
    Room room1= new Room(1,students.size(), this);
    this.addRoom(room1);
    this.addToBoard(room1, "Room1");

    Room room2= new Room(2,4, this);
    this.addRoom(room2);
    this.addToBoard(room2, "Room2");

    Room room3= new Room(3,3, this);
    this.addRoom(room3);
    this.addToBoard(room3, "Room3");

    CursedRoom room4= new CursedRoom(4,2, this);
    this.addRoom(room4);
    this.addIterating(room4);
    this.addToBoard(room4, "Room4");

    Room room5= new Room(5,6, this);
    this.addRoom(room5);
    this.addToBoard(room5, "Room5");

    Room room6= new Room(6,3, this);
    this.addRoom(room6);
    this.addToBoard(room6, "Room6");
    
    CursedRoom room7= new CursedRoom(7,5, this);
    this.addRoom(room7);
    this.addIterating(room7);
    this.addToBoard(room7, "Room7");

    Room room8= new Room(8,4, this);
    this.addRoom(room8);
    this.addToBoard(room8, "Room8");
    
    Room room9= new Room(9,2, this);
    this.addRoom(room9);
    this.addToBoard(room9, "Room9");

    Room room10= new Room(10,otherPlayers.size()+2, this);
    this.addRoom(room10);
    this.addToBoard(room10, "Room10");

    Room room11= new Room(11,4, this);
    this.addRoom(room11);
    this.addToBoard(room11, "Room11");

    Room room12= new Room(12,2, this);
    this.addRoom(room12);
    this.addToBoard(room12, "Room12");
//---------------------------------------------------
//---------------------------------------------------
    
     // Ajtók inicializálása
    Door door12 = new Door(room1, room2, true, true);
    room1.addDoor(door12);
    room2.addDoor(door12);
    addToBoard(door12, "Door12");

    Door door13 = new Door(room1, room3, true, true);
    room1.addDoor(door13);
    room3.addDoor(door13);
    addToBoard(door13, "Door13");

    Door door17 = new Door(room1, room7, false, true);
    room1.addDoor(door17);
    room7.addDoor(door17);
    addToBoard(door17, "Door17");

    Door door112 = new Door(room1, room12, true, false);
    room1.addDoor(door112);
    room12.addDoor(door112);
    addToBoard(door112, "Door112");

    Door door24 = new Door(room2, room4, false, true);
    room2.addDoor(door24);
    room4.addDoor(door24);
    addToBoard(door24, "Door24");

    Door door29 = new Door(room2, room9, true, true);
    room2.addDoor(door29);
    room9.addDoor(door29);
    addToBoard(door29, "Door29");

    Door door210 = new Door(room2, room10, true, true);
    room2.addDoor(door210);
    room10.addDoor(door210);
    addToBoard(door210, "Door210");

    Door door36 = new Door(room3, room6, true, false);
    room3.addDoor(door36);
    room6.addDoor(door36);
    addToBoard(door36, "Door36");

    Door door37 = new Door(room3, room7, true, true);
    room3.addDoor(door37);
    room7.addDoor(door37);
    addToBoard(door37, "Door37");

    Door door45 = new Door(room4, room5, true, true);
    room4.addDoor(door45);
    room5.addDoor(door45);
    addToBoard(door45, "Door45");

    Door door48 = new Door(room4, room8, true, false);
    room4.addDoor(door48);
    room8.addDoor(door48);
    addToBoard(door48, "Door48");

    Door door510 = new Door(room5, room10, true, true);
    room5.addDoor(door510);
    room10.addDoor(door510);
    addToBoard(door510, "Door510");

    Door door511 = new Door(room5, room11, true, true);
    room5.addDoor(door511);
    room11.addDoor(door511);
    addToBoard(door511, "Door511");

    Door door512 = new Door(room5, room12, true, true);
    room5.addDoor(door512);
    room12.addDoor(door512);
    addToBoard(door512, "Door512");

    Door door69 = new Door(room6, room9, true, false);
    room6.addDoor(door69);
    room9.addDoor(door69);
    addToBoard(door69, "Door69");

    Door door610 = new Door(room6, room10, true, true);
    room6.addDoor(door610);
    room10.addDoor(door610);
    addToBoard(door610, "Door610");

    Door door78 = new Door(room7, room8, true, true);
    room7.addDoor(door78);
    room8.addDoor(door78);
    addToBoard(door78, "Door78");

    Door door79 = new Door(room7, room9, true, true);
    room7.addDoor(door79);
    room9.addDoor(door79);
    addToBoard(door79, "Door79");

    Door door811 = new Door(room8, room11, true, true);
    room8.addDoor(door811);
    room11.addDoor(door811);
    addToBoard(door811, "Door811");

    Door door1011 = new Door(room10, room11, true, true);
    room10.addDoor(door1011);
    room11.addDoor(door1011);
    addToBoard(door1011, "Door1011");
//---------------------------------------------------
//---------------------------------------------------

 // Tárgyak inicializálása, és helyükre tétele
    Transistor transistor1 =new Transistor();
    addToBoard(transistor1, "transistor1");
    room1.addItem(transistor1);

    Transistor transistor2 =new Transistor();
    addToBoard(transistor2, "transistor2");
    room4.addItem(transistor2);

    Transistor transistor3 =new Transistor();
    addToBoard(transistor3, "transistor3");
    room6.addItem(transistor3);

    Transistor transistor4 =new Transistor();
    addToBoard(transistor4, "transistor4");
    room12.addItem(transistor4);

    Tablatorlo tablatorlo1 =new Tablatorlo(6);
    addToBoard(tablatorlo1, "tablatorlo1");
    addIterating(tablatorlo1);
    room1.addItem(tablatorlo1);

    Tablatorlo tablatorlo2 =new Tablatorlo(7);
    addToBoard(tablatorlo2, "tablatorlo2");
    addIterating(tablatorlo2);
    room5.addItem(tablatorlo2);

    Tvsz tvsz1 = new Tvsz(3, false);
    addToBoard(tvsz1, "tvsz1");
    room2.addItem(tvsz1);

    Tvsz tvsz2 = new Tvsz(3, true);
    addToBoard(tvsz2, "tvsz2");
    room5.addItem(tvsz2);

    Tvsz tvsz3 = new Tvsz(4, false);
    addToBoard(tvsz3, "tvsz3");
    room9.addItem(tvsz3);

    Mask mask1 = new Mask(2, false);
    addToBoard(mask1,"mask1");
    room8.addItem(mask1);

    Mask mask2 = new Mask(2, true);
    addToBoard(mask2,"mask2");
    room2.addItem(mask2);

    Mask mask3 = new Mask(3, false);
    addToBoard(mask3,"mask3");
    room9.addItem(mask3);

    Beer beer1 = new Beer(4);
    addToBoard(beer1, "beer1");
    addIterating(beer1);
    room3.addItem(beer1);

    Beer beer2 = new Beer(5);
    addToBoard(beer2, "beer2");
    addIterating(beer2);
    room10.addItem(beer2);

    Camambert camambert1 = new Camambert();
    addToBoard(camambert1, "camambert1");
    room3.addItem(camambert1);

    Camambert camambert2 = new Camambert();
    addToBoard(camambert2, "camambert2");
    room7.addItem(camambert2);

    Airfreshener airfreshener1= new Airfreshener();
    addToBoard(airfreshener1, "airfreshener1");
    room10.addItem(airfreshener1);

    Airfreshener airfreshener2= new Airfreshener();
    addToBoard(airfreshener2, "airfreshener2");
    room6.addItem(airfreshener2);

    Logarlec logarlec1 = new Logarlec(false);
    addToBoard(logarlec1, "logarlec1");
    room11.addItem(logarlec1);

    Logarlec logarlec2 = new Logarlec(true);
    addToBoard(logarlec2, "logarlec2");
    room4.addItem(logarlec2);
//-------------------------------------------------------
//-------------------------------------------------------
//Játékosok szobákhoz adása
    for(int i =0; i<students.size();i++){
        room1.addPlayer(students.get(i));
    }

    for(int i =0; i<otherPlayers.size();i++){
        room10.addPlayer(otherPlayers.get(i));
    }

    Janitor janitor1= new Janitor(1, null);
    this.addToBoard(janitor1, "janitor1");
    gl.addToOtherPlayers(janitor1);
    room11.addPlayer(janitor1);

    }

    /**
 * Egy diák halálakor hívódik meg ez a függvény. A függvény értesíti a logikát
 * a diák haláláról.
 *
 * @param p A halott diák
 */
    public void studentDied(Player p) {
        gl.deadStudent(p);
    }
}
