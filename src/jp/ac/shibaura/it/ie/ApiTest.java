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
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public ApiTest() {
    }

    /**
     * APIのテストをしています。
     *
     * @param context
     */
    public void start(ConfigurableApplicationContext context) throws Exception {
        String session = "";

        String example = UUID.randomUUID().toString();
        // ユーザ登録
        authInterface.authEntry(new AuthEntryRequestMessage(example, example, example));

        // ユーザログイン
        Optional<String> sessionOptional = authInterface.authLogin(new AuthLoginRequestMessage(example, example));
        if (!sessionOptional.isPresent()) {
            System.out.println("エラー出力");
        } else {
            session = sessionOptional.get();
        }

        // ユーザ情報変更(名前)
        userInterface.userUpdate(session, new UserUpdateRequestMessage(example, "unko"));


        // カテゴリ指定した画像一覧取得
        String categoryId = "1";
        List<Image> imageList = imageInterface.imageList(session, categoryId);

        // カテゴリ一覧取得
        List<Category> categoryList = categoryInterface.categoryList(session);


        String roomId = "";
        // 部屋参加
        Optional<String> roomIdOptional = categoryInterface.categoryJoin(session, categoryId);
        if (!roomIdOptional.isPresent()) {
            System.out.println("エラー出力");
        } else {
            roomId = roomIdOptional.get();
        }

        // ルーム待機
        roomInterface.roomWait(session, roomId);
        // ルーム退出
        roomInterface.roomExit(session, roomId);

        // メッセージ（画像）送信
        chatInterface.chatMessagePost(session, roomId, new ChatMessagePostRequestMessage("base64化した画像ソース", "cat", "png"));
        // メッセージ一覧取得
        chatInterface.chatMessageUpdate(session, roomId);

        String massageId = "";
        // スタンプ送信
        chatInterface.chatStampPost(session, roomId, massageId, new ChatStampPostRequestMessage("example", "1"));
        // チャット退出
        chatInterface.chatExit(session, roomId);

        // ユーザログアウト
        authInterface.authLogout(session);

    }
}
