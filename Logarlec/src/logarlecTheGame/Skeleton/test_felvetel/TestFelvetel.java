package logarlecTheGame.Skeleton.test_felvetel;


import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;

public class TestFelvetel {

    public static void targyfelvesz1(Skeleton s){
        Room r1 = new Room(s,"r1", 2);
        Student stud = new Student(s, "s", 1, r1);
        Logarlec logar = new Logarlec(s, "logar");

        r1.addPlayer(stud);
        r1.addItem(logar);
        stud.setRoom(r1);

        stud.pickUpItem(logar);
    }

    public static void targyfelvesz2(Skeleton s){
        Room r1 = new Room(s,"r1", 2);
        Student stud = new Student(s, "s", 1, r1);
        Tvsz t = new Tvsz(s, "tvsz");

        r1.addPlayer(stud);
        r1.addItem(t);
        stud.setRoom(r1);

        stud.pickUpItem(t);
    }

    public static void testSplit(Skeleton s){
        Board b = new Board(s, "board");
        Room r1 = new Room(s,"r1", 2);
        b.addRoom(r1);
        b.forceSplit(r1);
    }
    
}