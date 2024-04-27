package logarlecTheGame;

import java.io.*;

import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Model.Item.*;

public class CommandHandler {

    private Board board;
    private File outFile;
    boolean isRandom;
    int roomIds = 0;
    int playerIds = 0;

    public CommandHandler(String output, Board b){
        board = b;
        if(output != null){ //Minden CMDHandlernek van egy file-ja amibe adott esetben írni kell
            outFile = new File(output); //Ennek a tartalmát törli, minden létrehozáskor.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            outFile = null;
        }
    }

    public void outWriter(String output){  //Paraméterként egy file-t kap, amibe írnia kell és egy string listát, melynek elemeit külön sorokba írja ki
        if(outFile == null){ //Ha a file, amit kapott null értékű, akkor csak a konzolra írja ki a stringet
            System.out.println(output);
            return;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile, true))){
            writer.write(output + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ha console akkor null a dstFile
    public void executeCommand(String command) {
        String[] cmd = command.split(" ");
        String commandType = cmd[0];
        
        switch (cmd[0]) {
            case "pickUp":
                pickUpItem(cmd);
                break;
            case "putDown":
                putDown(cmd);
                break;
            case "useDoor":
                useDoor(cmd);
                break;
            case "randomOff":
                randomSwitch(true);
                break;
            case "randomOn":
                randomSwitch(false);
                break;
            case "split":
                split(cmd);
                break;
            case "iterateAll":
                iterateAll();
                break;
            case "beerIterate":
                beerIterate(cmd);
                break;
            case "pair":
                pair(cmd);
                break; 
            case "listPlayers":
                listPlayers();
                break;
            case "listRoom":
                listRoom(cmd);
                break;
            case "listAllRoom":
                listAllRoom();
                break;
            case "listItem":
                listItem();
                break;
            case "listPlayerItem":
                listPlayerItem(cmd);
                break;
            case "listPlayerAttribs":
                listPlayerAttribs(cmd);
                break;
            case "listRoomAttribs":
                listRoomAttribs(cmd);
                break;
            case "gasRoom":
                gasRoom(cmd);
                break;
            case "cleanRoom":
                cleanRoom(cmd);
                break;
            case "stickyRoom":
                stickyRoom(cmd);
                break;
            case "closeDoor":
                closeDoor(cmd);
                break;
            case "openDoor":
                openDoor(cmd);
                break;
            case "addDoor":
                addDoor(cmd);
                break;
            case "addRoom":
                addRoom(cmd);
                break;
            case "addPlayerToRoom":
                addPlayerToRoom(cmd);
                break;
            case "addItemToRoom":
                addItemToRoom(cmd);
                break;
            case "addItemToPlayer":
                addItemToPlayer(cmd);
                break;
            case "save":
                save();
                break;
            case "load":
                load();
                break;
            default:
                System.out.println("Ismeretlen parancs: " + commandType);
        }
    }

    private void pickUpItem(String[] cmd) {
        if(cmd.length < 3){
            outWriter("invalid arguments");
            throw new IllegalArgumentException("Too many arguments");
        }
        String item = cmd[1];
        String player = cmd[2];

        String outputString = player+": "+item;

        Item itemRef = (Item)board.stringToObject(item);
        Player playerRef = (Player)board.stringToObject(player);
		
		if(playerRef.pickUpItem(itemRef)){
            outputString += " - OK";
        }
        else{
            outputString += " - FAIL";
        }
		outWriter(outputString);
    }

    private void putDown(String[] cmd){
        if(cmd.length < 3){
            outWriter("invalid arguments");
            return;
        }
        String item = cmd[1];
        String student = cmd[2];
        String outputString = "";

        Item itemRef = (Item)board.stringToObject(item);
        Student playerRef = (Student)board.stringToObject(student);
        
        playerRef.putDown(itemRef);

        outputString += student;
        outputString += ": ";
        outputString += board.objectToString(playerRef.getLocation());
        outputString += " ";
        outputString += item;
        outputString += " putDown";

        outWriter(outputString);
    }

    private void useDoor(String[] cmd){
        String outputString = "";
        if(cmd.length < 3){
            outWriter("invalid arguments");
            return;
        }
        String door = cmd[1];
        String player = cmd[2];

        Door doorRef = (Door)board.stringToObject(door);
        Player playerRef = (Player)board.stringToObject(player);

        outputString = door +": "+player;
        if(playerRef.changeR(doorRef)){
            outputString += " - OK";
        }
        else{
            outputString += " - FAIL";
        }
        outWriter(outputString);
    }

    private void randomSwitch(boolean b){
        if(b){
            outWriter("random on");
        }
        else{
            outWriter("random off");
        }
    }

    private void split(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        if(isRandom){
            String outputString="";
            String room = cmd[1];

            Room roomRef = (Room)board.stringToObject(room);
            Room newRoom = board.forceSplit(roomRef);
            String roomName = ++roomIds +"";
            board.addToBoard(newRoom, roomName);

            outputString += room;
            outputString += " split, uj szoba: ";
            outputString += board.objectToString(newRoom);

            outWriter(outputString);
        }
    }

    private void iterateAll(){
        StringBuilder outString = new StringBuilder("iterated: ");
        board.iterate();
        for (CycleBased c : board.getCycles()) {
            outString.append(board.objectToString(c)+'\n');
        }
        outWriter(outString.toString());
    }

    private void beerIterate(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String beer = cmd[1];
        String outputString = "";
        outputString += beer;

        Beer beerRef = (Beer)board.stringToObject(beer);
        beerRef.iterate();

        outputString += "iterated";
        outWriter(outputString);
    }

    private void pair(String[] cmd){
        if(cmd.length < 4){
            outWriter("invalid arguments");
            return;
        }
        String outputString = "";
        String player = cmd[1];
        String item1 = cmd[2];
        String item2 = cmd[3];
        
        Player playerRef = (Player)board.stringToObject(player);
        Item itemRef1 = (Item)board.stringToObject(item1);
        Item itemRef2 = (Item)board.stringToObject(item2);

        if(itemRef1.makePair(itemRef2)){
            outputString += "Sikeres ";
        }
        else{
            outputString += "Sikertelen ";
        }
        outputString += "párosítás: " + item1 + ":" + item2;
        outWriter(outputString);
    }

    private void listPlayers(){
        String outoutString = board.listRooms(true, false);
        outWriter(outoutString);
    }

    private void listRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
        }
            String room = cmd[1];
        String outputString = "";

        Room roomRef = (Room)board.stringToObject(room);
        outputString += roomRef.listMe(board, false, false, false);

        outWriter(outputString);
    }

    private void listAllRoom(){
        String outputString = board.listRooms(false, false);
        outWriter(outputString);
    }

    private void listItem(){
        String outputString = board.listRooms(false, true);
        outWriter(outputString);
    }

    private void listPlayerItem(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String player = cmd[1];
        String outputStream = "";

        Player playerRef = (Player)board.stringToObject(player);
        outputStream += playerRef.listMe(board, true, false);
        outWriter(outputStream);
    }

    private void listPlayerAttribs(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String player = cmd[1];
        Player playerRef = (Player)board.stringToObject(player);
        String outputString = playerRef.listMe(board, false, true);
        outWriter(outputString);
    }

    private void listRoomAttribs(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room);
        String outputString = roomRef.listMe(board, false, false, true);
        outWriter(outputString);
    }

    private void gasRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.makeGassed();
    }

    private void cleanRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.makeClean();
    }

    private void stickyRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.makeSticky();
    }

    private void closeDoor(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String door = cmd[1];
        Door doorRef = (Door)board.stringToObject(door);
        doorRef.switchMe(false); 
    }

    private void openDoor(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String door = cmd[1];
        Door doorRef = (Door)board.stringToObject(door);
        doorRef.switchMe(true); 
    }

    private void addDoor(String[] cmd){
        if(cmd.length < 6){
            outWriter("invalid arguments");
            return;
        }
        String doorName = cmd[1];
        String room1 = cmd[2];
        String room2 = cmd[3];
        boolean validFrom;
        boolean validTo;
        if(cmd[4].equals("true")){
            validFrom = true;
        }
        else if(cmd[4].equals("false")){
            validFrom = false;
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        if(cmd[5].equals("true")){
            validTo = true;
        }
        else if(cmd[5].equals("false")){
            validTo = false;
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        Room roomRef1 = (Room)board.stringToObject(room1);
        Room roomRef2 = (Room)board.stringToObject(room2);
        Door d = new Door(roomRef1 , roomRef2, validFrom, validTo);
        board.addToBoard(d,doorName);
    }

    private void addRoom(String[] cmd){
        if(cmd.length != 4){
            outWriter("invalid arguments");
            return;
        }
        String roomName = cmd[2];
        int capacity = Integer.parseInt(cmd[3]);
        Room r;
        if(cmd[1].equals("cursedRoom")){
            r = new CursedRoom(++roomIds, capacity);
        }
        else if(cmd[1].equals("room")){
            r = new Room(++roomIds, capacity);
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        board.addToBoard(r, roomName);
        System.out.println(board.objectToString(r));
    }

    private void addPlayerToRoom(String[] cmd){
        if(cmd.length < 4){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[3];
        String player = cmd[2];
        Room roomRef = (Room)board.stringToObject(room);
        Player p;
        if(cmd[1].equals("student")){
            p = new Student(playerIds++, roomRef);
        }
        else if(cmd[1].equals("teacher")){
            p = new Teacher(playerIds++, roomRef);
        }
        else if(cmd[1].equals("janitor")){
            p = new Janitor(playerIds++, roomRef);
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        board.addToBoard(p, player);
    }

    private void addItemToRoom(String[] cmd){
        if(cmd.length < 6){
            outWriter("invalid arguments");
            return;
        }
        boolean fake;
        if(cmd[2].equals("false")){
            fake = false;
        }
        else if(cmd[2].equals("true")){
            fake = true;
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        String itemName = cmd[3];
        int durab = Integer.parseInt(cmd[4]);
        String roomName = cmd[5];
        Room roomRef = (Room)board.stringToObject(roomName);
        Item i;
        String itemType = cmd[1];
        if(itemType.equals("airfreshener")){
            i = new Airfreshener();
        }
        else if(itemType.equals("beer")){
            i = new Beer(durab);
        }
        else if(itemType.equals("camambert")){
            i = new Camambert();
        }
        else if(itemType.equals("logarlec")){
            i = new Logarlec(fake);
        }
        else if(itemType.equals("mask")){
            i = new Mask(durab, fake);
        }
        else if(itemType.equals("tablatorlo")){
            i = new Tablatorlo(durab);
        }
        else if(itemType.equals("transistor")){
            i = new Transistor();
        }
        else if(itemType.equals("TVSZ")){
            i = new Tvsz(durab, fake);
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        roomRef.addItem(i);
        board.addToBoard(i,itemName);
    }
    
    private void addItemToPlayer(String[] cmd){
        if(cmd.length < 5){
            outWriter("invalid arguments");
            return;
        }
        String itemType = cmd[1];
        String itemName = cmd[2];
        int durab = Integer.parseInt(cmd[3]);
        String player = cmd[4];
        Player playerRef = (Player)board.stringToObject(player);
        boolean fake = false;
        Item i;

        if(itemType.equals("airfreshener")){
            i = new Airfreshener();
        }
        else if(itemType.equals("beer")){
            i = new Beer(durab);
        }
        else if(itemType.equals("camambert")){
            i = new Camambert();
        }
        else if(itemType.equals("logarlec")){
            i = new Logarlec(fake);
        }
        else if(itemType.equals("mask")){
            i = new Mask(durab, fake);
        }
        else if(itemType.equals("tablatorlo")){
            i = new Tablatorlo(durab);
        }
        else if(itemType.equals("transistor")){
            i = new Transistor();
        }
        else if(itemType.equals("TVSZ")){
            i = new Tvsz(durab, fake);
        }
        else{
            outWriter("invalid arguments");
            return;
        }
        playerRef.addItem(i);
        board.addToBoard(i, itemName);
    }

    public void save(){
        board.serialize();
    }

    public void load(){
        board.deserialize();
    }
}
