package jp.ac.shibaura.it.ie.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.room.RoomInterface;
import jp.ac.shibaura.it.ie.usecase.room.wait.RoomWaitResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class RoomController implements RoomInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;

    /**
     * @param session
     * @param roomId
     * @return
     */
    @Override
    public RoomWaitResponseMessage roomWait(String session, String roomId) {
        String URL = "http://localhost:8080/room/" + roomId + "/wait";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<RoomWaitResponseMessage> responseEntity = null;
        try {
            restTemplate.exchange(URL, HttpMethod.GET, httpEntity, RoomWaitResponseMessage.class);
        } catch (HttpClientErrorException e) { // (1)
            logger.error("400系エラー発生");
            e.printStackTrace();
        } catch (HttpServerErrorException e) { // (2)
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
    public boolean roomExit(String session, String roomId) {
        String URL = "http://localhost:8080/room" + roomId + "/wait";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<String> roomExitEntity = null;
        try {
            roomExitEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) { // (1)
            logger.error("400系エラー発生");
            e.printStackTrace();
            return false;
        } catch (HttpServerErrorException e) { // (2)
            logger.error("500系エラー発生");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
