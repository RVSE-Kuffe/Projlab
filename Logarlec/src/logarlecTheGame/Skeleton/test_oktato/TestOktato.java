package logarlecTheGame.Skeleton.test_oktato;

import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;

public class TestOktato {
    public void testOkatoLep1(Skeleton s){
        Room r1 = new Room(s, "r1", 0, 1);
        Room r2 = new Room(s, "r2", 1, 0);
        Door d = new Door(s, "d", r1, r2);
        r1.addDoor(d);
        r2.addDoor(d);
        Teacher t = new Teacher(s, "t", 0, r1);

        t.changeR(r2);
    }    
}
