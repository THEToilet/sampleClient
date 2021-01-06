package jp.ac.shibaura.it.ie.usecase.room;

import jp.ac.shibaura.it.ie.usecase.room.wait.RoomWaitResponseMessage;

public interface RoomInterface {
    public RoomWaitResponseMessage roomWait(String session, String roomId);
    public boolean roomExit(String session, String roomID);
}
