package edu.hunau.cxb21.chatroom.client;

import edu.hunau.cxb21.chatroom.client.view.ChatAllFrame;
import edu.hunau.cxb21.chatroom.utils.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Client {
    private Socket socket;
    private String username;
    private ChatAllFrame chatAllFrame;
    private SendThread sendThread;

    private ReceiveThread receiveThread;
    public void sendMsg(String msg){
       sendThread.setContent(msg);
    }
    public void showMsg(String msg){
        if(Objects.nonNull(chatAllFrame)){
            chatAllFrame.getMessageShowArea().append(ChatRoomUtils.showMessage(msg));
        }
    }

    private List<String> messageQueue= Collections.synchronizedList(new LinkedList<String>());
    public Client(String username, String hostName, int port) {
        this.username=username;
        try {
            socket = new Socket(hostName, port);
            //socket输出流即发送管道
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //socket输入流即接受管道
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //启动收消息和发消息线程

            sendThread = new SendThread(Client.this,oos,username);
            receiveThread = new ReceiveThread(Client.this,ois);
            new Thread(sendThread).start();
            new Thread(receiveThread).start();
        }catch (Exception e){

        }
    }

    public void setChatAllFrame(ChatAllFrame chatAllFrame){
        this.chatAllFrame=chatAllFrame;
    }



}
