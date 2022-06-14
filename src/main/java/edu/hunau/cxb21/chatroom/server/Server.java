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

public class Server implements Runnable {
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
                JOptionPane.showMessageDialog(serverFrame,"�������ѹر�");
                serverFrame.getMessageArea().append(ChatRoomUtils.showMessage("�������ر�"));
            }catch (IOException e){
                JOptionPane.showMessageDialog(serverFrame,"��������æ");
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
            System.out.println("�������������ȴ��ͻ�������" + hostName + "�������˿�" + port);
            String message = "�����������ɹ�:ip��ַΪ:" + hostName + "�������˿�:" + port;

            JOptionPane.showMessageDialog(serverFrame, message);
            serverFrame.getMessageArea().append(ChatRoomUtils.showMessage(message));
            // ����пͻ������ӣ�����-scoketʵ��
            Socket clientSocket = serverSocket.accept();
            String clientIP=clientSocket.getLocalAddress().getHostAddress();
            System.out.printf("����ˣ���ÿͻ��˵�����");
            serverFrame.getMessageArea().append(ChatRoomUtils.showMessage("��ÿͻ��˵����ӣ�ip��ַΪ��"+clientIP));
//            ��ȡ�ͻ��˵�����
            InputStream is =clientSocket.getInputStream();
            InputStreamReader isr=new InputStreamReader(is, Charset.forName("utf-8"));
            BufferedReader br =new BufferedReader(isr);
            String data = br.readLine();
            serverFrame.getMessageArea().append(ChatRoomUtils.showMessage(data+"��¼����"));

            Vector vector=new Vector(0);
            vector.add(data);
            serverFrame.getClientTableModel().addRow(vector);
            while (true){
                String info = br.readLine();
                System.out.printf("���յ���������"+info);
                if(Objects.nonNull(info)&&!info.equals("")){
                    serverFrame.getMessageArea().append("��"+data+"����\n"+ChatRoomUtils.showMessage(info));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}