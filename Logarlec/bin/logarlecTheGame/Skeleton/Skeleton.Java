package logarlecTheGame.Skeleton;
import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.*;
import logarlecTheGame.Skeleton.test_hallgato.TestHallgato;

import java.util.HashMap;
import java.util.Map;

public class Skeleton {
    public Map<Object, String> names = new HashMap<>();

    public void mainMenu(){
        //kilistázza az öt tesztbeli menüpontot
        //meghívja a kiválasztott menüponthoz tartozó listázó függvényt.
        //Ezt lehet a mainbe kéne megvalósítani
    }

    public void listStudentMoves(){
        //kilisázza a hallgató lépéseivel kapcsolatos teszteket
        //elinditja a felhasznál által kiválasztott tesztet (beolvas egy betűt, stb.)
        //Tesztjeim meghívására példa:
        hallgatolep1(this);
    }

    public void listTeacherMoves(){

    }

    public void listRoomChange(){
        System.out.println("a: testclosedoor");


        bekérbetű
        switchcase
        testCloseDoor()
    }

    public void listItemUse(){

    }

    public void listItemPickUp(){

    }

    public void studentMove1(){
       
    }
    public void testCloseDoor(){
        CursedRoom r1=new CursedRoom(this, "r",2,4);
        Room r2=new Room(this, "r",1,4);
        Door d=new Door(this, "d",r1,r2);
        r1.addDoor(d);
        r2.addDoor(d);
        r1.closeDoor();
        System.out.println();
    }
    public void testOpenDoor(){
        CursedRoom r1=new CursedRoom(this, "r",2,4);
        Room r2=new Room(this, "r",1,4);
        Door d=new Door(this, "d",r1,r2);
        r1.addDoor(d);
        r2.addDoor(d);
        r1.closeDoor();
        System.out.println();
    }
    public void testMakeGassed(){
        Room r=new Room(this, "r",3,5);
        Student s=new Student(this, "s",1,r);
        Camambert c=new Camambert(this,"c");
<<<<<<< HEAD
        s.putDown(c);
    }
    public void testMakeClean(){
        Room r=new Room(this, "r",3,5);
        Student s=new Student(this, "s",1,r);
        Tablatorlo t=new Tablatorlo(this,"t",6);
        s.putDown(t);
=======
>>>>>>> e07a787602d64e731d83e800e29ad961445b214f
    }
}