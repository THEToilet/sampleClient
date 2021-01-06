package jp.ac.shibaura.it.ie.usecase.category;

import jp.ac.shibaura.it.ie.domain.model.Category;

import java.util.List;

public interface CategoryInterface {
    public List<Category> categoryList(String session);
    public String categoryJoin(String session);
}
