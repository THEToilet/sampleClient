package jp.ac.shibaura.it.ie.usecase.auth.login;

public class AuthLoginRequestMessage {
    private String userId;
    private String userPassword;
    public AuthLoginRequestMessage(String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }
}
