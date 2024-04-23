package logarlecTheGame.Skeleton;
import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Skeleton.test_felvetel.TestFelvetel;
import logarlecTheGame.Skeleton.test_hallgato.TestHallgato;
import logarlecTheGame.Skeleton.test_oktato.TestOktato;
import logarlecTheGame.Skeleton.test_targyletesz.TestTargyLetesz;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Skeleton {
    public Map<Object, String> names = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void mainMenu(){
        char chooser = 'a';

        while(chooser != 'x'){
            System.out.println("Válassz egy tesztcsoportot");
            System.out.println("");
            System.out.println("a: Tesztek a Student lépéseire");
            System.out.println("b: Tesztek a Teacher lépéseire");
            System.out.println("c: Tesztek a szobák változására");
            System.out.println("d: Tesztek a tárgyak felvételére");
            System.out.println("e: Tesztek a tárgyak letételére");
            System.out.println("x: kilépés");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): listStudentMoves();
                    break;
                case('b'): listTeacherMoves();
                    break;
                case('c'): listRoomChange();
                    break;
                case('d'): listItemPickUp();
                    break;
                case('e'): listItemPutDown();
                    break;
                case('x'): scanner.close(); 
                    break;
                default: break;
            }
        }
    }

    public void listStudentMoves(){
        char chooser = 'a';
        TestHallgato test = new TestHallgato();

        while(chooser != 'x'){
            System.out.println("Válassz egy tesztet");
            System.out.println("");
            System.out.println("a: Hallgato olyan szobaba lep, ahol nem fer el");
            System.out.println("b: Hallgato olyan szobaba lep, ami gazos es van maszkja");
            System.out.println("c: Hallgato olyan szobaba lep, ami gazos es nincs maszkja");
            System.out.println("d: Vedett hallgato olyan szobaba lep, ahol van egy oktato");
            System.out.println("e: Vedtelen hallgato olyan szobaba lep, ahol van oktato");
            System.out.println("f: Hallgatonak van vedelme, de nem eleg sokszor");
            System.out.println("g: Hallgatonak van vedelme eleg sokszor");
            System.out.println("x: kilépés");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): test.hallgatolep1(this);
                    break;
                case('b'): test.hallgatolep2(this);
                    break;
                case('c'): test.hallgatolep3(this);
                    break;
                case('d'): test.hallgatolep4(this);
                    break;
                case('e'): test.hallgatolep5(this);
                    break;
                case('f'): test.hallgatolep6(this);
                    break;
                case('g'): test.hallgatolep7(this);
                    break;
                case('x'): break;
                default: break;
            }
        }
    }

    public void listTeacherMoves(){
        char chooser = 'a';
        TestOktato test = new TestOktato();

        while(chooser != 'x'){
            System.out.println("Valassz egy tesztet");
            System.out.println("");
            System.out.println("a: Oktato olyan szobaba lep, ahol elfer");
            System.out.println("b: Oktato olyan szobaba lep, ahol van Hallgato vedelem nelkul");
            System.out.println("c: Oktato olyan szobaba lep, ahol van Hallgato vedelemmel");
            System.out.println("d: Oktato ures, gazos szobaba lep maszk nelkul");
            System.out.println("e: Oktato ures, gazos szobaba lep maszkkal");
            System.out.println("f: Oktato tablatorlos szobaba lep");
            System.out.println("g: Oktato olyan szobaba lep, ahol nem fer el");
            System.out.println("x: kilepes");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): test.testOkatoLep1(this);
                    break;
                case('b'): test.testOktatoLep2(this);
                    break;
                case('c'): test.testOktatoLep3(this);
                    break;
                case('d'): test.testOktatoLep4(this);
                    break;
                case('e'): test.testOktatoLep5(this);
                    break;
                case('f'): test.testOktatoLep6(this);
                    break;
                case('g'): test.testOktatoLep7(this);
                    break;
                case('x'): break;
                default: break;
            }
        }
    }

    public void listRoomChange(){
        char chooser = 'a';

        while(chooser != 'x'){
            System.out.println("Valassz egy tesztet");
            System.out.println("");
            System.out.println("a: Teszt egy ajto becsukasara");
            System.out.println("b: Teszt egy ajto nyitasara");
            System.out.println("c: Hallgato lerak egy camambert egy ures szobaban");
            System.out.println("d: Hallgato lerak egy tablatorlot egy ures szobaban");
            System.out.println("e: Szoba splitelodik");
            System.out.println("x: kilepes");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): testCloseDoor();
                    break;
                case('b'): testOpenDoor();
                    break;
                case('c'): testMakeGassed();
                    break;
                case('d'): testMakeClean();
                    break;
                case('e'): testSplit();
                    break;
                case('x'): break;
                default: break;
            }
        }

    }

    public void listItemPutDown(){
        char chooser = 'a';
        TestTargyLetesz test = new TestTargyLetesz();

        while(chooser != 'x'){
            System.out.println("Valassz egy tesztet");
            System.out.println("");
            System.out.println("a: Hallgato letesz egy sort");
            System.out.println("b: Hallgato osszeparosit 2 tranzisztort");
            System.out.println("x: kilepes");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): test.testTargyLetesz(this);
                    break;
                case('b'): test.testTransistorPairing(this);
                    break;
                case('x'): break;
                default: break;
            }
        }
    }

    public void listItemPickUp(){
        char chooser = 'a';
        TestFelvetel test = new TestFelvetel();

        while(chooser != 'x'){
            System.out.println("Valassz egy tesztet");
            System.out.println("");
            System.out.println("a: Hallgato felveszi a logarlecet");
            System.out.println("b: Hallgato felvesz egy TVSZ-t");
            System.out.println("x: kilepes");

            chooser = scanner.next().charAt(0);

            switch(chooser){
                case('a'): test.targyfelvesz1(this);
                    break;
                case('b'): test.targyfelvesz2(this);
                    break;
                case('x'): break;
                default: break;
            }
        }
    }

    
     /**
     * ajtók bezáródását vizsgáló függvény
     * Átkozott szoba ajtói tudnak csak záródni
     */ 

    public void testCloseDoor(){
        CursedRoom r1=new CursedRoom(this, "r",2,4);
        Room r2=new Room(this, "r",1,4);
        Door d=new Door(this, "d",r1,r2);
        r1.addDoor(d);
        r2.addDoor(d);
        r1.closeDoor();
        System.out.println();
    }

    /**
     * ajtók nyílását vizsgáló függvény
     * Átkozott szoba ajtói tudnak csak záródni és ezután nyílni
     */ 
    public void testOpenDoor(){
        CursedRoom r1=new CursedRoom(this, "r",2,4);
        Room r2=new Room(this, "r",1,4);
        Door d=new Door(this, "d",r1,r2);
        r1.addDoor(d);
        r2.addDoor(d);
        r1.openDoor();
        System.out.println();
    }

    /**
     * szoba gázosodását tesztelő függvény
     * Camambert használattal valósulhat meg
     */ 
    public void testMakeGassed(){
        Room r=new Room(this, "r",3,5);
        Student s=new Student(this, "s",1,r);
        Camambert c=new Camambert(this,"c");
        s.addItem(c);
        s.putDown(c);
    }

    /**
     * szoba tablatörlőssé válását tesztelő függvény
     * Tablatorlo használattal valósulhat meg
     */ 
    public void testMakeClean(){
        Room r=new Room(this, "r",3,5);
        Student s=new Student(this, "s",1,r);
        Tablatorlo t=new Tablatorlo(this,"t",6);
        s.addItem(t);
        s.putDown(t);
    }

    /**
     * szoba splitelődését tesztelő függvény
     */ 
    public void testSplit(){
        Board b = new Board(this, "board");
        Room r1 = new Room(this,"r1", 0, 2);
        b.addRoom(r1);
        b.forceSplit(r1);
    }
}