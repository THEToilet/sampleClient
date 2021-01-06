package jp.ac.shibaura.it.ie.domain.model;

public class Image {
    private String imageUrl;
    private String fileName;
    private String fileExtension;

    public Image(String imageUrl, String fileName, String fileExtension) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
