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
            oos.writeObject(new Message(Code.LOGIN, username, "������"));
            while(flat){
                if(!"".equals(content)){
                    Message msg = new Message(Code.CHAT, username, content);
                    content="";
//                    ����֮����շ��͵����ݣ������������
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
