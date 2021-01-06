package jp.ac.shibaura.it.ie.usecase.chat;

import jp.ac.shibaura.it.ie.usecase.chat.message.update.ChatMessageUpdateResponseMessage;

public interface ChatInterface {
    public boolean chatMessagePost(String session, String roomId);
    public ChatMessageUpdateResponseMessage chatMessageUpdate(String session, String roomId);
    public boolean chatExit(String session, String roomId);
    public boolean chatStampPost(String session, String roomId, String messageId);
}
