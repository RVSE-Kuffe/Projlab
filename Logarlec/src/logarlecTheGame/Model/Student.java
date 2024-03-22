package logarlecTheGame.Model;

public class Student extends Player implements StudentProtection, PutDown, Pairing {
public:
    boolean die(){}
    boolean stun(){}
    void PutDown(Item i){}
    boolean pairing(Transistor t1, Transistor t2){}
    boolean protect(Item i){}
}
