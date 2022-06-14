import edu.hunau.cxb21.chatroom.client.view.LoginFrame;
import edu.hunau.cxb21.chatroom.server.view.ServerFrame;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerFrame().init();
            }
        });
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().init();
                System.out.printf("你好啊");
            }
        });
    }
}
