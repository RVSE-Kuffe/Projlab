package logarlecTheGame.Model.Interfaces;

import logarlecTheGame.Model.Item.*;;

public interface StudentProtection { 
    /**
     * Studentet megvédő tárgyak interfésze
     * implementálva az ezt megvalósító osztályban van (student)
     * csak a Tvsz és a sör képes ilyen védelemre
     */ 
    boolean protect(Item i);
    boolean protect(Tvsz tvsz);
    boolean protect(Beer b);
}
