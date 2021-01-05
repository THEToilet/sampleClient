package jp.ac.shibaura.it.ie.usecase.chat;

public interface ChatInterface {
    public boolean chatMessagePost();
    public MeesageData chatMessageUpdate();
    public boolean chatExit();
    public boolean chatStampPost();
}
