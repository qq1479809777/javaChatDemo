package edu.hunau.cxb21.chatroom.utils;

import edu.hunau.cxb21.chatroom.client.Client;
import edu.hunau.cxb21.chatroom.client.view.ChatAllFrame;

import java.io.ObjectInputStream;
import java.util.Objects;


public class ReceiveThread implements Runnable{
    private ObjectInputStream ois;
    private volatile boolean flag = true;
    private String content;

    private ChatAllFrame chatAllFrame;
    private Client client;

    public ReceiveThread(Client client,ObjectInputStream ois) {
        super();
        this.ois = ois;
        this.client=client;
    }
    private void showMsg(String msg){
        if (Objects.nonNull(client)){
            client.showMsg(msg);
        }
    }


    public void run(){
        try {
            while(flag){
                Message msg = (Message) ois.readObject();
                if(Objects.nonNull(msg.getContent())){
                    content=msg.getUsername() + ":" + msg.getContent();
                    this.showMsg(content);
                }
            }
        } catch (Exception e) {
            this.showMsg("【系统提示】：请重新登录");
        }
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}