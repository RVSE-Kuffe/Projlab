package logarlecTheGame.Model.Interfaces;
import logarlecTheGame.Model.Item.*;

public interface GasProtect {
    /**
     * Tárgyak letételét és használatához szükséges interfész
     * Csak a student tud letenni/használni tárgyakat
     * csak a Transistornak, a Camambert-nek és a Tablatorlonek van letételre hatása
     */ 
    boolean maskProtect(Mask m);
    boolean maskProtect(Item i);
}
