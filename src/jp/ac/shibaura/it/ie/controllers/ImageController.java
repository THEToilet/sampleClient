package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.domain.model.Image;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.image.ImageInterface;
import jp.ac.shibaura.it.ie.usecase.image.list.ImageListResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImageController implements ImageInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LogUtils logger;

    /**
     * @param session
     * @param categoryId
     * @return
     */
    @Override
    public List<Image> imageList(String session, String categoryId) {
        String URL = "http://localhost:8080/image/list?categoryId=" + categoryId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<ImageListResponseMessage> imageListResponseResponseEntity = null;
        try {
             imageListResponseResponseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, ImageListResponseMessage.class);
        }
        catch (HttpClientErrorException e) { // (1)
            logger.error("400系エラー発生");
            e.printStackTrace();
            return new ArrayList<Image>();
        }
        catch (HttpServerErrorException e) { // (2)
            logger.error("500系エラー発生");
            e.printStackTrace();
            return new ArrayList<Image>();
        }
        return imageListResponseResponseEntity.getBody().getImageList();
    }
}
