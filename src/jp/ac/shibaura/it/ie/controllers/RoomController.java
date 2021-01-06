package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.room.RoomInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class RoomController implements RoomInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;
    @Override
    public RoomWait roomWait(String session, String roomId) {
        String URL = "http://localhost:8080/room/" + roomId + "/wait";
        try {
            return restTemplate.getForObject(URL, TestResponseResource.class);
        }
        catch (HttpClientErrorException e) { // (1)
            logger.error("400系エラー発生");
            throw e;
        }
        catch (HttpServerErrorException e) { // (2)
            logger.error("500系エラー発生");
            throw e;
        }
        return null;
    }

    @Override
    public boolean roomExit(String session, String roomId) {
        String URL = "http://localhost:8080/room" + roomId + "/wait";
        ResponseEntity<String> roomExitEntity = null;
        try {
            return restTemplate.exchange(URL, TestResponseResource.class);
        }
        catch (HttpClientErrorException e) { // (1)
            logger.error("400系エラー発生");
            throw e;
        }
        catch (HttpServerErrorException e) { // (2)
            logger.error("500系エラー発生");
            throw e;
        }
        return false;
    }
}
