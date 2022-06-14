package edu.hunau.cxb21.chatroom.utils;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String username;
    private String content;
    public Message(int code, String username, String content) {
        super();
        this.code = code;
        this.username = username;
        this.content = content;
    }

    public Message() {
        super();
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}