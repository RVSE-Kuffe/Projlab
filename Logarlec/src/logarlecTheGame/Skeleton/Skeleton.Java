package logarlecTheGame.Skeleton;
import logarlecTheGame.Model.*;
import logarlecTheGame.Model.Item.*;
import java.util.HashMap;
import java.util.Map;

import logarlecTheGame.*;

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
        CursedRoom r1=new CursedRoom(this, "r");
        Room r2=new Room(this, "r");
        Door d=new Door(this, "d",r1,r2);
        r1.addDoor(d);
        r2.addDoor(d);
        r1.closeDoor();
        System.out.println();
    }
    public void testOpenDoor(){
        Room r=new Room(this, "r");
        Door d=new Door(this, "d");
    }
    public void testMakeGassed(){
        Room r=new Room(this, "r");
        Student s=new Student(this, "s",1,r);
        Camambert c=new Camambert(this,"c");
}
}