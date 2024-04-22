package logarlecTheGame;

import java.io.File;

public class CommandHandler {

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
        
		//ide kell, hogy a hashmap vagy valami ilyesmi alapján megtalálni a névhez a referenciát
        itemRef=...
		player.pickUpItem(itemRef);
		if(dstFile==null){
		System.out.println(item + ": " + player + " - OK pickup");
		}else {
		dstFile.NewLine();
		dst. Append();
		}
    }
}
