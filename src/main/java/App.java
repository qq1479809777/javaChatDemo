import edu.hunau.cxb21.chatroom.client.view.LoginFrame;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().init();
            }
        });
    }
}
