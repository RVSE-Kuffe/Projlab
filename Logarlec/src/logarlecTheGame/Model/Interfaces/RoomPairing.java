package logarlecTheGame.Model.Interfaces;

import logarlecTheGame.Model.*;
/**
 * Az interfész, amely definiálja a szobapárosítást a játékban.
 */
public interface RoomPairing {

     /**
     * Két normál szoba párosítása.
     *
     * @param r1 Az egyik szoba
     * @param r2 A másik szoba
     * @return Igaz, ha a párosítás sikeres, egyébként hamis
     */
    public boolean pair(Room r1, Room r2);

     /**
     * Egy normál szoba és egy átkozott szoba párosítása.
     *
     * @param r1 A normál szoba
     * @param r2 Az átkozott szoba
     * @return Mindig hamis
     */
    public boolean pair(Room r1, CursedRoom r2);

     /**
     * Egy átkozott szoba és egy normál szoba párosítása.
     *
     * @param r1 Az átkozott szoba
     * @param r2 A normál szoba
     * @return Mindig hamis
     */
    public boolean pair(CursedRoom r1, Room r2);

    /**
     * Két átkozott szoba párosítása.
     *
     * @param r1 Az egyik átkozott szoba
     * @param r2 A másik átkozott szoba
     * @return Mindig hamis
     */
    public boolean pair(CursedRoom r1, CursedRoom r2);
}
