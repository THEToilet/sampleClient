package jp.ac.shibaura.it.ie.usecase.chat.message.post;

public class ChatMessagePostRequestMessage {
    private String imageSource;
    private String fileName;
    private String fileExtension;

    public ChatMessagePostRequestMessage(String imageSource, String fileName, String fileExtension) {
        this.imageSource = imageSource;
        this.fileName = fileName;
        this.fileExtension = fileExtension;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
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
