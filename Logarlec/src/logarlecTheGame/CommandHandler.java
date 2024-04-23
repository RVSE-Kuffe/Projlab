package logarlecTheGame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

import logarlecTheGame.Model.Board;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Item.Item;

public class CommandHandler {

    private Board board;
    private Map<String, Object> names;
    private static File outFile;

    public CommandHandler(String output){
        board = new Board(null, null);
        names = new HashMap<String, Object>();
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
                pickUpItem(cmd, dstFile);
                break;
            default:
                System.out.println("Ismeretlen parancs: " + commandType);
        }
    }

    private void pickUpItem(String[] cmd, String dstFile) {
        String item = cmd[1];
        String player = cmd[2];

        String outpuString = player + item;

        Item itemRef = (Item)names.get(item);
        Player playerRef = (Player)names.get(player);
		
		if(playerRef.pickUpItem(itemRef)){
            outpuString += "OK";
        }
        else{
            outpuString += "FAIL";
        }
		outWriter(outpuString);
    }
}
