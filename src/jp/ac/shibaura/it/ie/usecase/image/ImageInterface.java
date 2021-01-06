package jp.ac.shibaura.it.ie.usecase.image;

import jp.ac.shibaura.it.ie.domain.model.Image;

import java.util.List;

public interface ImageInterface {
    public List<Image> imageList(String session, String categoryId);
}
