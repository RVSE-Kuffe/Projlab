package logarlecTheGame.Model.Item;

import logarlecTheGame.Model.Student;

public class Airfreshener extends Item{

    public Airfreshener(){
        isFake=false;
    }

    @Override
     public void acceptPutDown(Student s){
        if(isFake){
            s.useFake(this);
        }
        s.use(this);
     }

     public void makeFake(){
        isFake=true;
    }
    
}
