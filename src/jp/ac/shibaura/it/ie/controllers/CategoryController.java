package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.domain.model.Category;
import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.category.CategoryInterface;
import jp.ac.shibaura.it.ie.usecase.category.join.CategoryJoinResponseMessage;
import jp.ac.shibaura.it.ie.usecase.category.list.CategoryListResponseMessage;
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

import java.util.List;
import java.util.Optional;

@Component
public class CategoryController implements CategoryInterface {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LogUtils logger;

    /**
     * @param session
     * @return
     */
    @Override
    public List<Category> categoryList(String session) {
        String URL = "http://localhost:8080/category/list";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<CategoryListResponseMessage> categoryResponse = null;
        try {
            categoryResponse = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, CategoryListResponseMessage.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
        }
        return categoryResponse.getBody().getCategoryList();
    }

    /**
     * @param session
     * @param categoryId
     * @return
     */
    @Override
    public Optional<String> categoryJoin(String session, String categoryId) {
        String URL = "http://localhost:8080/category/" + categoryId + "/join";
        HttpHeaders headers = new HttpHeaders();
        headers.add("session", session);
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        ResponseEntity<CategoryJoinResponseMessage> categoryResponse = null;
        try {
            categoryResponse = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, CategoryJoinResponseMessage.class);
        } catch (HttpClientErrorException e) {
            logger.error("400系エラー発生");
            e.printStackTrace();
        } catch (HttpServerErrorException e) {
            logger.error("500系エラー発生");
            e.printStackTrace();
        }
        return Optional.ofNullable(categoryResponse.getBody().getRoomId());
    }
}
