package logarlecTheGame;

import java.io.*;
import java.util.*;

import logarlecTheGame.Model.Board;

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

            if(chooser == 'a'){
                String testFile;             //Ennek a stringnek az értékét kell majd megváltoztatni a kiválasztott teszt függvénéyben
                String outFile;
                System.out.println("Valassz teszteket");    //Itt kilistázza a választható teszteket
                for(int i = 1; i<16+1;i++)
                    System.out.println("1. Teszteset"+i+'\n');

                chooser = scanner.next().charAt(0);
                testFile = "Test"+chooser+".txt";
                outFile = "Assert"+chooser+".txt";
                                                            
                File inputFile = new File(testFile);        //File objektum létrehozása a kiválasztott file-val
                try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))){  //Reader létrehozása
                    List<String> commands = new ArrayList<>();  //A parancsokat tartalmazó Lista, ezeket a parancsokat tovább kell majd még parse-olni
                    String command;
                    while((command = reader.readLine()) != null){
                        commands.add(command);
                    }
                    CommandHandler ch = new CommandHandler(outFile, board);
                    for(String s : commands){
                        ch.executeCommand(s, outFile);         //A stringek parseolását és végrehajtását meghívja az összes kiolvasott parancsra
                    }
                }catch(IOException e){
                    e.getStackTrace();
                }
            }
            else if(chooser == 'b'){
                
                System.out.println("Parancsolvaso es parseolo logika helye");
            }
        }
    }
}
