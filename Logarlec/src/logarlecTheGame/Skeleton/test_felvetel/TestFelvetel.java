package logarlecTheGame.Skeleton.test_felvetel;


import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;

public class TestFelvetel {

    /**
     * logarléc felvétel testelése
     */ 
    public void targyfelvesz1(Skeleton s){
        Room r1 = new Room(s,"r1", 0, 2);
        Student stud = new Student(s, "s", 1, r1);
        Logarlec logar = new Logarlec(s, "logar", false);

        r1.addPlayer(stud);
        r1.addItem(logar);
        stud.setRoom(r1);

        stud.pickUpItem(logar);
    }

    /**
     * sima tárgy felvételének tesztelése
     */ 
    public void targyfelvesz2(Skeleton s){
        Room r1 = new Room(s,"r1", 0, 2);
        Student stud = new Student(s, "s", 1, r1);
        Tvsz t = new Tvsz(s, "tvsz", 3, false);

        r1.addPlayer(stud);
        r1.addItem(t);
        stud.setRoom(r1);

        stud.pickUpItem(t);
    }
    
}
