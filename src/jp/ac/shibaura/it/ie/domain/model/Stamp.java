package jp.ac.shibaura.it.ie.domain.model;

public class Stamp {
    private String pushedUserName;
    private String stampId;

    public Stamp(String pushedUserName, String stampId) {
        this.pushedUserName = pushedUserName;
        this.stampId = stampId;
    }

    public String getPushedUserName() {
        return pushedUserName;
    }

    public void setPushedUserName(String pushedUserName) {
        this.pushedUserName = pushedUserName;
    }

    public String getStampId() {
        return stampId;
    }

    public void setStampId(String stampId) {
        this.stampId = stampId;
    }
}
