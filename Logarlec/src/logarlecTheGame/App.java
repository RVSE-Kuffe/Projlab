package logarlecTheGame;
import logarlecTheGame.Skeleton.*;

import java.io.*;
import java.util.*;

public class App {

    //static boolean toConsole = true;
    static char chooser = 'd';
    static Scanner scanner = new Scanner(System.in);


    static void writeToFile(File outputFile, List<String> output){  //Paraméterként egy file-t kap, amibe írnia kell és egy string listát, melynek elemeit külön sorokba írja ki
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            for(String s : output){
                writer.write(s + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        while(chooser != 'c'){
            System.out.println("Valassz modszert");
            System.out.println("a, Tesztek beolvasasa");
            System.out.println("b, Jatek vezerlese parancsokkal");

            chooser = scanner.next().charAt(0);

            if(chooser == 'a'){
                String testFile = "proba.txt";              //Ennek a stringnek az értékét kell majd megváltoztatni a kiválasztott teszt függvénéyben
                String outFile= "out.txt";
                System.out.println("Valassz teszteket");    //Itt kilistázza a választható teszteket
                for(int i = 1; i<16+1;i++)
                    System.out.println("1. Teszteset"+i+'\n');

                chooser = scanner.next().charAt(0);
                testFile = "Test"+chooser+".txt";
                outFile = "Assert"+chooser+".txt";
                                                            
                File inputFile = new File(testFile);        //File objektum létrehozása a kiválasztott file-val
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));  //Reader létrehozása
                List<String> commands = new ArrayList<>();  //A parancsokat tartalmazó Lista, ezeket a parancsokat tovább kell majd még parse-olni
                String command;
                while((command = reader.readLine()) != null){
                    commands.add(command);
                }
                reader.close();

                CommandHandler ch = new CommandHandler(outFile);

                for(String s : commands){
                    ch.executeCommand(s, outFile);         //A stringek parseolását és végrehajtását meghívja az összes kiolvasott parancsra
                }
            }
            else if(chooser == 'b'){
                
                System.out.println("Parancsolvaso es parseolo logika helye");
            }
        }
    }
}
