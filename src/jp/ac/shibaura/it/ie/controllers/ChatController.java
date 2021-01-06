package jp.ac.shibaura.it.ie.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.chat.ChatInterface;
import jp.ac.shibaura.it.ie.usecase.chat.message.post.ChatMessagePostRequestMessage;
import jp.ac.shibaura.it.ie.usecase.chat.message.update.ChatMessageUpdateResponseMessage;
import jp.ac.shibaura.it.ie.usecase.chat.stamp.post.ChatStampPostRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ChatController implements ChatInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;

    /**
     * @param session
     * @param roomId
     * @param chatMessagePostRequestMessage
     * @return
     */
    @Override
    public boolean chatMessagePost(String session, String roomId, ChatMessagePostRequestMessage chatMessagePostRequestMessage) {
        String URL = "http://localhost:8081/chat/" + roomId + "/message/post";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = null;
        try {
            body = new ObjectMapper().writeValueAsString(chatMessagePostRequestMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> chatMessageEntity = null;
        try {
            chatMessageEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
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

    /**
     * @param session
     * @param roomId
     * @return
     */
    @Override
    public ChatMessageUpdateResponseMessage chatMessageUpdate(String session, String roomId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/message/update";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<ChatMessageUpdateResponseMessage> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, ChatMessageUpdateResponseMessage.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
        }
        return responseEntity.getBody();
    }

    /**
     * @param session
     * @param roomId
     * @return
     */
    @Override
    public boolean chatExit(String session, String roomId) {
        String URL = "http://localhost:8081/chat/" + roomId + "/exit";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> chatExitResponseEntity = null;
        try {
            chatExitResponseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
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

    /**
     * @param session
     * @param roomId
     * @param messageId
     * @param chatStampPostRequestMessage
     * @return
     */
    @Override
    public boolean chatStampPost(String session, String roomId, String messageId, ChatStampPostRequestMessage chatStampPostRequestMessage) {
        String URL = "http://localhost:8081/chat/" + roomId + "/" + messageId + "/stamp/post";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = null;
        try {
            body = new ObjectMapper().writeValueAsString(chatStampPostRequestMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate.exchange(URL, HttpMethod.POST, httpEntity, String.class);
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
