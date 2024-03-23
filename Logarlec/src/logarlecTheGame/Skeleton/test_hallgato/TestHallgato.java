package logarlecTheGame.Skeleton.test_hallgato;

import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;

public class TestHallgato {
    public static void testHallgatolep1(Skeleton s){
        Room r1 = new Room(s,"r1", 1);
        Room r2 = new Room(s, "r2",0);
        Student stud = new Student(s, "s", 1, r1);
        Door d = new Door(s, "d", r1, r2);

        r1.addDoor(d);
        r2.addDoor(d);

        r1.addPlayer(stud);

        stud.super.changeR(r2);
    }
}
