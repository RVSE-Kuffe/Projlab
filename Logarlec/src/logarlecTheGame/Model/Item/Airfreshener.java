package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.Student;

public class Airfreshener extends Item{

    public Airfreshener(){
        isFake=false;
    }

    @Override
     public void acceptPutDown(Student s){
        s.use(this);
     }
    
}
