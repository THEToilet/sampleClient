package jp.ac.shibaura.it.ie.usecase.chat;

import jp.ac.shibaura.it.ie.usecase.chat.message.post.ChatMessagePostRequestMessage;
import jp.ac.shibaura.it.ie.usecase.chat.message.update.ChatMessageUpdateResponseMessage;
import jp.ac.shibaura.it.ie.usecase.chat.stamp.post.ChatStampPostRequestMessage;

public interface ChatInterface {
    public boolean chatMessagePost(String session, String roomId, ChatMessagePostRequestMessage chatMessagePostRequestMessage);
    public ChatMessageUpdateResponseMessage chatMessageUpdate(String session, String roomId);
    public boolean chatExit(String session, String roomId);
    public boolean chatStampPost(String session, String roomId, String messageId, ChatStampPostRequestMessage chatStampPostRequestMessage);
}
