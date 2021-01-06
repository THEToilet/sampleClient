package jp.ac.shibaura.it.ie.usecase.user;

import jp.ac.shibaura.it.ie.usecase.user.update.UserUpdateRequestMessage;

public interface UserInterface {
    public boolean userUpdate(String session, UserUpdateRequestMessage userUpdateRequestMessage);
}
