package logarlecTheGame.Model.Interfaces;

import logarlecTheGame.Model.Item.*;

public interface Pairing {
    /**
     * Transistorok párosítását végző interfész
     */ 
    boolean pair(Item i1, Item i2);
    boolean pair(Transistor t1, Transistor t2);
}
