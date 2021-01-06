package jp.ac.shibaura.it.ie.usecase.auth;

import jp.ac.shibaura.it.ie.usecase.auth.entry.AuthEntryRequestMessage;
import jp.ac.shibaura.it.ie.usecase.auth.login.AuthLoginRequestMessage;

import java.util.Optional;

public interface AuthInterface {
    public Optional<String> authLogin(AuthLoginRequestMessage authLoginRequestMessage);
    public boolean authEntry(AuthEntryRequestMessage authEntryRequestMessage);
    public boolean authLogout(String session);
}
