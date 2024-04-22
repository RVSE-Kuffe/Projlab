package logarlecTheGame;
import logarlecTheGame.Skeleton.*;
import java.util.Scanner;

import java.io.*;
import java.util.*;

public class App {

    static boolean toConsole = true;
    static char chooser = 'd';
    static Scanner scanner = new Scanner(System.in);

    static void testfv(){
        if(toConsole){
            System.out.println("A konzolra kell irnom");
        }
        else{
            //fileba kell irnia
        }
    }

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
                CommandHandler ch= new CommandHandler();
                toConsole = false;
                String testFile = "proba.txt";              //Ennek a stringnek az értékét kell majd megváltoztatni a kiválasztott teszt függvénéyben
                String outFile= "out.txt";
                System.out.println("Valassz teszteket");    //Itt kilistázza a választható teszteket
                System.out.println("...tesztek");
                                                            //Ide kell majd egy switchcase, ami nevet ad a file-nak
                File inputFile = new File(testFile);        //File objektum létrehozása a kiválasztott file-val
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));  //Reader létrehozása
                List<String> commands = new ArrayList<>();  //A parancsokat tartalmazó Lista, ezeket a parancsokat tovább kell majd még parse-olni
                String command;
                while((command = reader.readLine()) != null){
                    commands.add(command);
                }
                reader.close();

                for(String s : commands){
                    ch.executeCommand(s, outFile);         //A stringek parseolását és végrehajtását meghívja az összes kiolvasott parancsra
                }
            }
            else if(chooser == 'b'){
                toConsole = true;
                System.out.println("Parancsolvaso es parseolo logika helye");
            }
        }
    }
}
