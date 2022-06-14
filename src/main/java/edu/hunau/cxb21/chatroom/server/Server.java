package edu.hunau.cxb21.chatroom.server;

import edu.hunau.cxb21.chatroom.server.view.ServerFrame;
import edu.hunau.cxb21.chatroom.utils.ChatRoomUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Vector;

public class Server implements Runnable{
    private ServerSocket serverSocket;
    private ServerFrame serverFrame;
    public Server(ServerFrame serverFrame) {
        try {
            serverSocket = new ServerSocket(8088);
            this.serverFrame = serverFrame;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Server() {
        try {
            serverSocket = new ServerSocket(8088);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {

        try {
            Socket clientSocket = serverSocket.accept();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void close(){
        if(Objects.nonNull(serverSocket)){
            try {
                serverSocket.close();
                JOptionPane.showMessageDialog(serverFrame,"服务器已关闭");
                serverFrame.getMessageArea().append(ChatRoomUtils.showMessage("服务器关闭"));
            }catch (IOException e){
                JOptionPane.showMessageDialog(serverFrame,"服务器繁忙");
            }
        }
    }
    public static void main(String[] args) {
        new Server().start();
    }


    public void run() {
        try {
            String hostName = InetAddress.getLocalHost().getHostAddress();
            int port = serverSocket.getLocalPort();
            System.out.println("服务器启动，等待客户端连接" + hostName + "，监听端口" + port);
            String message = "服务器启动成功:ip地址为:" + hostName + "，监听端口:" + port;

            JOptionPane.showMessageDialog(serverFrame, message);
            serverFrame.getMessageArea().append(ChatRoomUtils.showMessage(message));

            while(true){
                Socket ct = serverSocket.accept();
                ClientHandlerThread client = new ClientHandlerThread(ct);
                client.start();
            }
        } catch (IOException e) {
            System.out.printf("客户端断开链接");
        }
    }
}