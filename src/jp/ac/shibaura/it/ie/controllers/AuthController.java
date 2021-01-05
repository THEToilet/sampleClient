package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.auth.AuthInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthController implements AuthInterface {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LogUtils logger;

    @Override
    public boolean authLogin() {
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
        return false;
    }

    @Override
    public boolean authEntry() {
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
        return false;
    }

    @Override
    public boolean authLogout() {
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
        return false;
    }
}
