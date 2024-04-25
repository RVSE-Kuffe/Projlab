package logarlecTheGame.Model.Interfaces;

import logarlecTheGame.Model.*;

public interface RoomPairing {
    public boolean pair(Room r1, Room r2);
    public boolean pair(Room r1, CursedRoom r2);
    public boolean pair(CursedRoom r1, Room r2);
    public boolean pair(CursedRoom r1, CursedRoom r2);
}
