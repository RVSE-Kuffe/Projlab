import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;
import logarlecTheGame.Skeleton.*;
import logarlecTheGame.*;
import logarlecTheGame.Model.Player;
import logarlecTheGame.Model.Student;

public class Camambert {

    public Camambert(Skeleton s, String n) {
        sk = s;
        sk.names.put(this, n);
    }

     public void AcceptPutDown(Student s){}
    
}
