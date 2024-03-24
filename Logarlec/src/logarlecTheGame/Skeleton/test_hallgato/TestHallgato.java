package logarlecTheGame.Skeleton.test_hallgato;

import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;

public class TestHallgato{

    public static void hallgatolep1(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",0);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        stud.changeR(r2);
    }

    public static void hallgatolep2(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",1);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        r2.makeGassed();

        Mask mask = new Mask(s, "m", 1);    
        
        stud.addItem(mask);

        stud.changeR(r2);
    }

    public static void hallgatolep3(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",1);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        r2.makeGassed();

        stud.changeR(r2);
    }
    
    public static void hallgatolep4(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",2);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        Tvsz tvsz = new Tvsz(s, "tvsz", 1);

        Teacher t = new Teacher(s, "t", 1, r2);

        stud.addItem(tvsz);

        r2.addPlayer(t);

        stud.changeR(r2);
    }

    public static void hallgatolep5(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",0);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        Teacher t = new Teacher(s, "t", 1, r2);

        r2.addPlayer(t);

        stud.changeR(r2);
    }

    public static void hallgatolep6(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",2);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        Tvsz tvsz = new Tvsz(s, "tvsz", 1);

        Teacher t1 = new Teacher(s, "t1", 1, r2);
        Teacher t2 = new Teacher(s, "t2", 1, r2);

        stud.addItem(tvsz);

        r2.addPlayer(t1);
        r2.addPlayer(t2);

        stud.changeR(r2);
    }

    public static void hallgatolep7(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",2);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        Tvsz tvsz = new Tvsz(s, "tvsz", 2);

        Teacher t1 = new Teacher(s, "t1", 1, r2);
        Teacher t2 = new Teacher(s, "t2", 1, r2);

        stud.addItem(tvsz);

        r2.addPlayer(t1);
        r2.addPlayer(t2);

        stud.changeR(r2);
    }
}
