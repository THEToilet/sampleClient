package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.chat.ChatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class ChatController implements ChatInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;
    @Override
    public boolean chatMessagePost() {
        return false;
    }

    @Override
    public MeesageData chatMessageUpdate() {
        return null;
    }

    @Override
    public boolean chatExit() {
        return false;
    }

    @Override
    public boolean chatStampPost() {
        return false;
    }
}
