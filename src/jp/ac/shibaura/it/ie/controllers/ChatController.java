package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.chat.ChatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class ChatController implements ChatInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;

    @Override
    public boolean chatMessagePost(String session, String roomId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/message/post";
        ResponseEntity<String> chateMessageEntity = null;
        try {
            chateMessageEntity = restTemplate.exchange(URL, HttpMethod.POST, String.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
            return false;
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public MeesageData chatMessageUpdate(String session, String roomId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/message/update";
        try {
            restTemplate.exchange(URL,HttpMethod.GET, TestResponseResource.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            throw e;
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            throw e;
        }
        return null;
    }

    @Override
    public boolean chatExit(String session, String roomId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/exit";
        ResponseEntity<String> chatExitResponseEntity = null;
        try {
            chatExitResponseEntity = restTemplate.exchange(URL,HttpMethod.GET, TestResponseResource.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
            return false;
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean chatStampPost(String session, String roomId, String messageId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/" + messageId + "/stamp/post";
        try {
            return restTemplate.getForObject(URL, TestResponseResource.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
            return false;
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
