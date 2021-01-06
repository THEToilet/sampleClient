package jp.ac.shibaura.it.ie.usecase.image.list;

import jp.ac.shibaura.it.ie.domain.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageListResponseMessage {
    private List<Image> imageList = new ArrayList<>();

    public ImageListResponseMessage(List<Image> imageList) {
        this.imageList = imageList;
    }

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }
}
