package jp.ac.shibaura.it.ie.usecase.chat.stamp.post;

public class ChatStampPostRequestMessage {
    private String userName;
    private String stampId;

    public ChatStampPostRequestMessage(String userName, String stampId) {
        this.userName = userName;
        this.stampId = stampId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStampId() {
        return stampId;
    }

    public void setStampId(String stampId) {
        this.stampId = stampId;
    }
}
