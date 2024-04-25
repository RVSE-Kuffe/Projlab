package logarlecTheGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Door;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Room;
import logarlecTheGame.Model.Student;
import logarlecTheGame.Model.Interfaces.CycleBased;
import logarlecTheGame.Model.Item.Beer;
import logarlecTheGame.Model.Item.Item;
import logarlecTheGame.Model.Item.Mask;

public class CommandHandler {

    private Board board;
    private File outFile;
    boolean isRandom;

    public CommandHandler(String output){
        board = new Board(null, null);
        if(output != null){ //Minden CMDHandlernek van egy file-ja amibe adott esetben írni kell
            outFile = new File(output); //Ennek a tartalmát törli, minden létrehozáskor.
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                writer.write("");
                writer.close();
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
        
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outFile, true));
            writer.write(output + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ha console akkor null a dstFile
    public void executeCommand(String command, String dstFile) {
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
                iterateAll(cmd);
                break;
            case "beerIterate":
                beerIterate(cmd);
                break;
            case "pair":
                pair(cmd);
                break; 
            case "listPlayers":
                listPlayers(cmd);
                break;
            case "listRoom":
                listRoom(cmd);
                break;
            case "listAllRoom":
                listAllRoom(cmd);
                break;
            case "listItem":
                listItem(cmd);
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

        String outputString = player + item;

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

    public void putDown(String[] cmd){
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
        outputString += " : ";
        outputString += board.objectToString(playerRef.getLocation());
        outputString += " ";
        outputString += item;
        outputString += " putDown";

        outWriter(outputString);
    }

    public void useDoor(String[] cmd){
        String outputString = "";
        if(cmd.length < 3){
            outWriter("invalid arguments");
            return;
        }
        String door = cmd[1];
        String player = cmd[2];

        Door doorRef = (Door)board.stringToObject(door);
        Player playerRef = (Player)board.stringToObject(player);

        playerRef.changeR(doorRef);

        outputString += "door";
        outputString += "player";
        if(playerRef.changeR(doorRef)){
            outputString += "OK";
        }
        else{
            outputString += "FAIL";
        }
        outWriter(outputString);
    }

    public void randomSwitch(boolean b){
        if(b){
            outWriter("random on");
        }
        else{
            outWriter("random off");
        }
        return;
    }

    public void split(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        if(isRandom){
            String outputString="";
            String room = cmd[1];

            Room roomRef = (Room)board.stringToObject(room);
            board.forceSplit(roomRef);

            outputString += room;
            outputString += "split, uj szoba: ";
            outputString += "splitted";
            outWriter(outputString);
        }
    }

    public void iterateAll(String[] cmd){
        String outString = "iterated: ";
        board.iterate();
        for (CycleBased c : board.getCycles()) {
            outString += board.objectToString(c);
            outString += "\n";
        }
        outWriter(outString);
    }

    public void beerIterate(String[] cmd){
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

    public void pair(String[] cmd){
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
        outputString += "párosítás : " + item1 + ", " + item2;
        outWriter(outputString);
    }

    public void listPlayers(String[] cmd){
        String outoutString = board.listRooms(true, false);
        outWriter(outoutString);
    }

    public void listRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
        }
            String room = cmd[1];
        String outputString = "";

        Room roomRef = (Room)board.stringToObject(room);
        outputString += roomRef.listMe(board, false, false, false);

        outWriter(outputString);
    }

    public void listAllRoom(String[] cmd){
        String outputString = board.listRooms(false, false);
        outWriter(outputString);
    }

    public void listItem(String [] cmd){
        String outputString = board.listRooms(false, false);
        outWriter(outputString);
    }

    public void listPlayerItem(String[] cmd){
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

    public void listPlayerAttribs(String cmd[]){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String player = cmd[1];
        Player playerRef = (Player)board.stringToObject(player);
        String outputString = playerRef.listMe(board, false, true);
        outWriter(outputString);
    }

    public void listRoomAttribs(String cmd[]){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room);
        String outputString = roomRef.listMe(board, false, false, true);
        outWriter(outputString);
    }

    public void gasRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.makeGassed();
    }

    public void cleanRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.makeClean();
    }

    public void stickyRoom(String[] cmd){
        if(cmd.length < 2){
            outWriter("invalid arguments");
            return;
        }
        String room = cmd[1];
        Room roomRef = (Room)board.stringToObject(room); 
        roomRef.make
    }
}
