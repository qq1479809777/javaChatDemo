package edu.hunau.cxb21.chatroom.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatRoomUtils {
    public static String showMessage(String message) {
        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return time + ":" + message + "\n";

    }
}
