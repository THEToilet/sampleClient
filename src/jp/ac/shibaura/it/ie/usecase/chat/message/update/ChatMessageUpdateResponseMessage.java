package jp.ac.shibaura.it.ie.usecase.chat.message.update;

import jp.ac.shibaura.it.ie.domain.model.Message;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatMessageUpdateResponseMessage {
    private Map<String, Message> messageList = new LinkedHashMap<String, Message>();

    public LinkedHashMap<String, Message> getMessageList() {
        return (LinkedHashMap<String, Message>) messageList;
    }

    public void setMessageList(String messageId, Message message) {
        this.messageList.put(messageId, message);
    }
}
