package jp.ac.shibaura.it.ie.usecase.category.list;

import jp.ac.shibaura.it.ie.domain.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryListResponseMessage {
    private List<Category> categoryList = new ArrayList<>();

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
