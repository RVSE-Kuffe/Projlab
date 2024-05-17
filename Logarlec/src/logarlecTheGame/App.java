package logarlecTheGame;

import java.io.*;
import java.util.*;

import logarlecTheGame.Controller.CommandHandler;
import logarlecTheGame.Model.Board;
import logarlecTheGame.View.GameMenu;

public class App {

    static char chooser = 'd';
    static Scanner scanner = new Scanner(System.in);


    static void writeToFile(File outputFile, List<String> output){  //Paraméterként egy file-t kap, amibe írnia kell és egy string listát, melynek elemeit külön sorokba írja ki
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {  
            for(String s : output){
                writer.write(s + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void runTests(){
        Board board = new Board();
        String testFile;             //Ennek a stringnek az értékét kell majd megváltoztatni a kiválasztott teszt függvénéyben
        String outFile;
        System.out.println("Valassz teszteket");    //Itt kilistázza a választható teszteket
        for(int i = 1; i<16+1;i++)
            System.out.println(i+" - Teszteset"+i);
        System.out.println("17. Grafikus felület");
        System.out.println("Add meg a teszteset számát: ");
        String testnum = scanner.nextLine();

        if(!testnum.equals("17")){
            testFile = "Logarlec/src/TestFiles/Test"+testnum+".txt";
            outFile = "out.txt";
            
            File inputFile = new File(testFile);        //File objektum létrehozása a kiválasztott file-val
            try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){  //Reader létrehozása
                List<String> commands = new ArrayList<>();  //A parancsokat tartalmazó Lista, ezeket a parancsokat tovább kell majd még parse-olni
                String command;
                while((command = reader.readLine()) != null){
                    System.out.println(command);
                    commands.add(command);
                }
                CommandHandler ch = new CommandHandler(outFile, board);
                for(String s : commands){
                    ch.executeCommand(s);         //A stringek parseolását és végrehajtását meghívja az összes kiolvasott parancsr
                }
            }catch(IOException e){
                e.getStackTrace();
                System.err.println(e.getMessage());
            }
        }else{
            GameMenu gm = new GameMenu();
            gm.setVisible(true);
        }
    }

    public static void runConsoleGame(){
        Board board = new Board();
        String cmd = null;
        CommandHandler ch = new CommandHandler(null, board);
        while(!"quit".equals(cmd)){
            cmd = scanner.nextLine();
            ch.executeCommand(cmd);
        }
    }

    public static void runGraphicalGame(){
        GameMenu gm = new GameMenu();
        gm.setVisible(true);
    }

    public static void main(String[] args) throws Exception {

        if(args.length != 1){
            throw new IllegalArgumentException("Exactly 1 argument needed!");
        }

        switch (args[0]) {
            case "-play":
                runGraphicalGame();
                break;
            case "-tests":
                runTests();
                break;
            case "-console":
                runConsoleGame();
                break;

            default:
                throw new IllegalArgumentException("No matching arguments. You should use [-play], [-test] or [-console]");
                //break;
        }
    }
}
