package jp.ac.shibaura.it.ie.usecase.room.wait;

public class RoomWaitResponseMessage {
    private int numberOfWaitUser;
    private boolean start;

    public int getNumberOfWaitUser() {
        return numberOfWaitUser;
    }

    public void setNumberOfWaitUser(int numberOfWaitUser) {
        this.numberOfWaitUser = numberOfWaitUser;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
