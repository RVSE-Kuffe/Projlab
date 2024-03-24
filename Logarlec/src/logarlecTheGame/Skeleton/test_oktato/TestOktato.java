package logarlecTheGame.Skeleton.test_oktato;

import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Skeleton.*;

public class TestOktato {
    public void testOkatoLep1(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 1);
        Door d = new Door(s, "d", r1, r2);
        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);
        r1.addPlayer(t);

        t.changeR(d);
    }   
    
    public void testOktatoLep7(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 0);
        Door d = new Door(s, "d", r1, r2);
        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);
        r1.addPlayer(t);

        t.changeR(d);
    }

    public void testOktatoLep2(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 2);
        Door d = new Door(s, "d", r1, r2);
        Student st = new Student(s, "st", 0, r2);

        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);

        r1.addPlayer(t);
        r2.addPlayer(st);

        t.changeR(d);
    }

    public void testOktatoLep3(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 2);
        Door d = new Door(s, "d", r1, r2);
        Tvsz tv = new Tvsz(s, "tv", 3);
        Student st = new Student(s, "st", 0, r2);

        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);
        st.addItem(tv);

        r1.addPlayer(t);
        r2.addPlayer(st);

        t.changeR(d);
    }

    public void testOktatoLep4(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);

        r1.addPlayer(t);
        r2.makeGassed();

        t.changeR(d);
    }

    public void testOktatoLep5(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 1);
        Door d = new Door(s, "d", r1, r2);
        Mask m = new Mask(s, "m", 3);

        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);
        t.addItem(m);

        r1.addPlayer(t);
        r2.makeGassed();

        t.changeR(d);
    }

    public void testOktatoLep6(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);

        r1.addPlayer(t);
        r2.makeClean();

        t.changeR(d);
    }
}
