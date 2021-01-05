package jp.ac.shibaura.it.ie.controllers;

import jp.ac.shibaura.it.ie.log.LogUtils;
import jp.ac.shibaura.it.ie.usecase.category.CategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class CategoryController implements CategoryInterface {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LogUtils logger;

    @Override
    public Optional<Category> categoryList() {
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
        return Optional.empty();
    }
}
