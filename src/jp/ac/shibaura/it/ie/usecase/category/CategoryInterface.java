package jp.ac.shibaura.it.ie.usecase.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.shibaura.it.ie.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryInterface {
    public List<Category> categoryList(String session);
    public Optional<String> categoryJoin(String session, String categoryId);
}
