package jp.ac.shibaura.it.ie.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.user.UserInterface;
import jp.ac.shibaura.it.ie.usecase.user.update.UserUpdateRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class UserController implements UserInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;

    @Override
    public boolean userUpdate(String session, UserUpdateRequestMessage userUpdateRequestMessage) {
        String URL = "http://localhost:8080/user/update";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = null;
        try {
            body = new ObjectMapper().writeValueAsString(userUpdateRequestMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        HttpEntity<String> httpEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> userUpdateResponse = null;
        try {
            userUpdateResponse = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
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
