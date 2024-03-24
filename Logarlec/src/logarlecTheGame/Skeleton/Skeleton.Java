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
    }

    public void listTeacherMoves(){

    }

    public void listRoomChange(){
        System.out.println("a: testclosedoor");
        System.out.println("b: testopendoor");
        System.out.println("c: testmakegassed");
        System.out.println("d: testmakeclean");

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
        s.addItem(c);
        s.putDown(c);
    }
    public void testMakeClean(){
        Room r=new Room(this, "r",3,5);
        Student s=new Student(this, "s",1,r);
        Tablatorlo t=new Tablatorlo(this,"t",6);
        s.addItem(t);
        s.putDown(t);
    }
}