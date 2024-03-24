package logarlecTheGame.Skeleton.test_targyletesz;

import java.rmi.server.Skeleton;

import logarlecTheGame.Model.*;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.Model.Item.*;


  /**
     *Különböző esetek tesztelésére lévő osztály
     */
public class TestTargyLetesz {
      /**
       *Teszt tárgyak letételére
       *inicializálja a mindenképp kellő attribútumokat, objektumokat,
       *majd ezekkel végigfuttatja a tesztet
     */

    public void testTargyLetesz(Skeleton s){
        Room r1 = new Room(s,"r1", 0, 2);
        Student stud = new Student(s, "s", 1, r1);
        Beer beer = new Beer(s, "beer", 3);

        r1.addPlayer(stud);
        stud.addItem(beer);
        stud.setRoom(r1);

      stud.putDown(beer);
    }

  /**
       *Teszt a transistor párosítás tesztelésére,
       *inicializálja a mindenképp kellő attribútumokat, objektumokat,
       *majd ezekkel végigfuttatja a tesztet
     */
    public void testTransistorPairing(Skeleton s){
        Room r1 = new Room(s,"r1", 0, 2);
        Student stud = new Student(s, "s", 1, r1);
        Transistor t1 = new Transistor(s, "transistor1");
        Transistor t2 = new Transistor(s, "transistor2");


        r1.addPlayer(stud);
        stud.addItem(t1);
        stud.addItem(t2);
        stud.setRoom(r1);
        stud.pair(t1,t2);
     

    }
      /**
       *Teszt a transistor használat tesztelésére,
       *inicializálja a mindenképp kellő attribútumokat, objektumokat,
       *majd ezekkel végigfuttatja a tesztet
     */
    public void testTransistorUsage(Skeleton s){
        Room r1 = new Room(s,"r1", 0, 2);
        Room r2 = new Room(s,"r2", 1, 2);

        Student stud = new Student(s, "s", 1, r1);
        Transistor t2 = new Transistor(s, null, null);
        Transistor t1 = new Transistor(s, "transistor1",t2);
        t2 = new Transistor(s, "transistor2",t1);


        r1.addPlayer(stud);
        stud.addItem(t1);
        //stud.addItem(t2);
        stud.pair(t1, t2);
        stud.setRoom(r1);
        t2.setRoom(r2);
        stud.putDown(t1);

    }
}