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
    private static File outFile;
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

    static void outWriter(String output){  //Paraméterként egy file-t kap, amibe írnia kell és egy string listát, melynek elemeit külön sorokba írja ki
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
            default:
                System.out.println("Ismeretlen parancs: " + commandType);
        }
    }

    private void pickUpItem(String[] cmd) {
        if(cmd.length < 3){
            outWriter("invalid arguments");
            return;
        }
        String item = cmd[1];
        String player = cmd[2];

        String outputString = player + item;

        Item itemRef = (Item)board.bObjects.get(item);
        Player playerRef = (Player)board.bObjects.get(player);
		
		if(playerRef.pickUpItem(itemRef)){
            outputString += "OK";
        }
        else{
            outputString += "FAIL";
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

        Item itemRef = (Item)board.bObjects.get(item);
        Student playerRef = (Student)board.bObjects.get(student);
        
        playerRef.putDown(itemRef);

        outputString += student;
        outputString += " : ";
        outputString += board.bNames.get(playerRef.getLocation());
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

        Door doorRef = (Door)board.bObjects.get(door);
        Player playerRef = (Player)board.bObjects.get(player);

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

            Room roomRef = (Room)board.bObjects.get(room);
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
            outString += board.bNames.get(c);
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

        Beer beerRef = (Beer)board.bObjects.get(beer);
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
        
        Player playerRef = (Player)board.bObjects.get(player);
        Item itemRef1 = (Item)board.bObjects.get(item1);
        Item itemRef2 = (Item)board.bObjects.get(item2);

        if(itemRef1.makePair(itemRef2)){
            outputString += "Sikeres ";
        }
        else{
            outputString += "Sikertelen ";
        }
        outputString += "párosítás : " + item1 + ", " + item2;
        outWriter(outputString);
    }
}
