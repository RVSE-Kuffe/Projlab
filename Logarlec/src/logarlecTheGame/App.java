package logarlecTheGame;

import java.io.*;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import logarlecTheGame.Controller.CommandHandler;
import logarlecTheGame.Model.Board;
import logarlecTheGame.View.BoardPanel;
import logarlecTheGame.View.GameMenu;
import logarlecTheGame.View.View;

public class App {

    //static boolean toConsole = true;
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

    public static void main(String[] args) throws Exception {

        Board board = new Board();
        while(chooser != 'c'){
            System.out.println("Valassz modszert");
            System.out.println("a, Tesztek beolvasasa");
            System.out.println("b, Jatek vezerlese parancsokkal");

            chooser = scanner.next().charAt(0);
            scanner.nextLine();

            if(chooser == 'a'){
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
                    /*JFrame frame = new JFrame("Game by ripgyork");
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    frame.setSize(800, 600);
                    frame.add(new BoardPanel());
                    frame.setVisible(true);*/
                    GameMenu gm = new GameMenu();
                    gm.setVisible(true);
                }
            }
            else if(chooser == 'b'){
                String cmd = null;
                CommandHandler ch = new CommandHandler(null, board);
                while(!"quit".equals(cmd)){
                    cmd = scanner.nextLine();
                    ch.executeCommand(cmd);
                }
            }
        }
    }
}
