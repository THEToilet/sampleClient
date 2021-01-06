package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.auth.AuthInterface;
import jp.ac.shibaura.it.ie.usecase.auth.entry.AuthEntryRequestMessage;
import jp.ac.shibaura.it.ie.usecase.auth.login.AuthLoginRequestMessage;
import jp.ac.shibaura.it.ie.usecase.auth.login.AuthLoginResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 *  認証周りのAPIを叩きます
 */
@Component
public class AuthController implements AuthInterface {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LogUtils logger;

    /**
     * @param authLoginRequestMessage ログインするユーザ情報
     * @return session情報
     */
    @Override
    public Optional<String> authLogin(AuthLoginRequestMessage authLoginRequestMessage) {
        String URL = "http://localhost:8000/login";
        String session = null;
        try {
            ResponseEntity<AuthLoginResponseMessage> responseEntity = restTemplate.postForEntity(URL, authLoginRequestMessage, AuthLoginResponseMessage.class);
            logger.info(responseEntity.toString());
            session = responseEntity.getBody().getSession();
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
            return Optional.ofNullable(session);
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
            return Optional.ofNullable(session);
        }
        return Optional.ofNullable(session);
    }

    /**
     * @param authEntryRequestMessage
     * @return
     */
    @Override
    public boolean authEntry(AuthEntryRequestMessage authEntryRequestMessage) {
        String URL = "http://localhost:8080/entry";
        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL, authEntryRequestMessage, String.class);
            logger.info(responseEntity.toString());
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
     * @return
     */
    @Override
    public boolean authLogout(String session) {
        String URL = "http://localhost:8080/logout";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            return false;
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            return false;
        }
        return true;
    }
}
