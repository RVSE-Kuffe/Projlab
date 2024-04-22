package logarlecTheGame.Model.Interfaces;

import logarlecTheGame.Model.Item.*;

public interface PutDown {
    /**
     * Tárgyak letételét és használatához szükséges interfész
     * Csak a student tud letenni/használni tárgyakat
     * csak a Transistornak, a Camambert-nek és a Tablatorlonek van letételre hatása
     */ 
    void use(Item i);
    void use(Transistor i);
    void use(Camambert i);
    void use(Tablatorlo i);
    void use(Airfreshener a);
}
