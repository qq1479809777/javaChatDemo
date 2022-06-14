package edu.hunau.cxb21.chatroom.utils;

import edu.hunau.cxb21.chatroom.client.Client;
import edu.hunau.cxb21.chatroom.client.view.ChatAllFrame;

import java.io.IOException;
import java.io.ObjectOutputStream;


public class SendThread implements Runnable{
    private ObjectOutputStream oos;
    private String username;
    private String content;
    private Boolean flat=true;

    private Client client;

    public void setContent(String content) {
        this.content = content;
    }

    public SendThread(Client client,ObjectOutputStream oos, String username) {
        super();
        this.oos = oos;
        this.username = username;
        this.client=client;
    }


    public void run(){
        try {
            oos.writeObject(new Message(Code.LOGIN, username, "上线了"));
            while(flat){
                if(!"".equals(content)){
                    Message msg = new Message(Code.CHAT, username, content);
                    content="";
//                    发完之后清空发送的内容，避免持续发送
                    oos.writeObject(msg);
                    content="";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFlat(Boolean flat) {
        this.flat = flat;
    }
}
