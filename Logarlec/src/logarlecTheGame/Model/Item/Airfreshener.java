package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.Student;

public class Airfreshener extends Item{

    public Airfreshener(){
        isFake=false;
    }

    @Override
     public void acceptPutDown(Student s){
        //System.out.println(sk.names.get(this) + "acceptPutDown");
        s.use(this);
        //System.out.println(sk.names.get(this) + "acceptPutDown returned with void");
     }
    
}
