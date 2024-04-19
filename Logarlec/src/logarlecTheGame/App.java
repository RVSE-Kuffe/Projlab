package logarlecTheGame;
import logarlecTheGame.Skeleton.*;
import java.util.Scanner;

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

    public static void main(String[] args) throws Exception {
        while(chooser != 'c'){
            System.out.println("Valassz modszert");
            System.out.println("Tesztek beolvasasa");
            System.out.println("Jatek vezerlese parancsokkal");

            chooser = scanner.next().charAt(0);

            if(chooser == 'a'){
                toConsole = false;
                System.out.println("Valassz teszteket");
                System.out.println("...tesztek");
            }
            else if(chooser == 'b'){
                toConsole = true;
                System.out.println("Parancsolvaso es parseolo logika helye");
            }
        }
    }
}
