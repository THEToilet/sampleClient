package jp.ac.shibaura.it.ie;

import jp.ac.shibaura.it.ie.domain.model.Category;
import jp.ac.shibaura.it.ie.domain.model.Image;
import jp.ac.shibaura.it.ie.usecase.auth.AuthInterface;
import jp.ac.shibaura.it.ie.usecase.auth.entry.AuthEntryRequestMessage;
import jp.ac.shibaura.it.ie.usecase.auth.login.AuthLoginRequestMessage;
import jp.ac.shibaura.it.ie.usecase.category.CategoryInterface;
import jp.ac.shibaura.it.ie.usecase.chat.ChatInterface;
import jp.ac.shibaura.it.ie.usecase.chat.message.post.ChatMessagePostRequestMessage;
import jp.ac.shibaura.it.ie.usecase.chat.stamp.post.ChatStampPostRequestMessage;
import jp.ac.shibaura.it.ie.usecase.image.ImageInterface;
import jp.ac.shibaura.it.ie.usecase.room.RoomInterface;
import jp.ac.shibaura.it.ie.usecase.user.UserInterface;
import jp.ac.shibaura.it.ie.usecase.user.update.UserUpdateRequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@Component
public class ApiTest {
    @Autowired
    private AuthInterface authInterface;
    @Autowired
    private CategoryInterface categoryInterface;
    @Autowired
    private ChatInterface chatInterface;
    @Autowired
    private ImageInterface imageInterface;
    @Autowired
    private RoomInterface roomInterface;
    @Autowired
    private UserInterface userInterface;

    public ApiTest() { }

    /**
     *  APIのテストをしています。
     * @param context
     */
    public void start(ConfigurableApplicationContext context) throws Exception{
        String session = "";

        // ユーザ登録
        authInterface.authEntry(new AuthEntryRequestMessage("unko", "unko", "unko"));

        // ユーザログイン
        Optional<String> sessionOptional = authInterface.authLogin(new AuthLoginRequestMessage("unko", "unko"));
        if (!sessionOptional.isPresent()){
            System.out.println("unko");
        }else {
            session = sessionOptional.get();
        }

        // ユーザ情報変更(名前)
        userInterface.userUpdate(session, new UserUpdateRequestMessage("unko","unko"));


        // カテゴリ指定した画像一覧取得
        String categoryId = "1";
        List<Image> imageList = imageInterface.imageList(session, categoryId);

        // カテゴリ一覧取得
        List<Category> categoryList = categoryInterface.categoryList(session);

        // 部屋参加
        Optional<String> roomIdOptional = categoryInterface.categoryJoin(session, categoryId);
        String roomId = roomIdOptional.get();

        // ルーム待機
        roomInterface.roomWait(session, roomId);
        // ルーム退出
        roomInterface.roomExit(session, roomId);


        // メッセージ（画像）送信
        chatInterface.chatMessagePost(session, roomId, new ChatMessagePostRequestMessage("df","d","df"));
        // メッセージ一覧取得
        chatInterface.chatMessageUpdate(session, roomId);

        String massageId = "";
        // スタンプ送信
        chatInterface.chatStampPost(session, roomId, massageId, new ChatStampPostRequestMessage("d", "d"));
        // チャット退出
        chatInterface.chatExit(session, roomId);


        // ユーザログアウト
        authInterface.authLogout(session);

    }
}
