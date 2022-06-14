package edu.hunau.cxb21.chatroom.client;

import edu.hunau.cxb21.chatroom.client.view.ChatAllFrame;
import edu.hunau.cxb21.chatroom.utils.ChatRoomUtils;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Client {
    private Socket socket;
    private String username;
    private ChatAllFrame chatAllFrame;
    public Client() {
        try {
            socket = new Socket("localhost", 8088);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private List<String> messageQueue= Collections.synchronizedList(new LinkedList<String>());
    public Client(String username, String hostName, int port) {
        try {
            this.username = username;
            socket = new Socket(hostName, port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void addMessage(String message){
        messageQueue.add(message);
        System.out.printf("client-addmessage==>"+messageQueue);
    }

    public void setChatAllFrame(ChatAllFrame chatAllFrame){
        this.chatAllFrame=chatAllFrame;
    }
    public void start() {
        try {
           new Thread(new SendMessageHandler()).start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private class SendMessageHandler implements Runnable{
        @Override
        public void run() {
            try{
                OutputStream os= socket.getOutputStream();
                PrintWriter out= new PrintWriter(os,true,Charset.forName("utf-8"));
                out.println(username);
                while (true){
                    if(!messageQueue.isEmpty()&&messageQueue.size()>0){
                        System.out.printf("client-sendMessage==>"+messageQueue);
                        String message=messageQueue.remove(0);
                        chatAllFrame.getMessageShowArea().append("¡¾"+username+"¡¿£º\n"+ChatRoomUtils.showMessage(message));
                        out.println(message);
                    }
                }
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }
}
