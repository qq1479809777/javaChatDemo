package edu.hunau.cxb21.chatroom.server;

import edu.hunau.cxb21.chatroom.utils.Code;
import edu.hunau.cxb21.chatroom.utils.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;


public class ClientHandlerThread extends Thread{
    public static Set<ObjectOutputStream> online = Collections.synchronizedSet(new HashSet<ObjectOutputStream>());
    public static Vector vector=new Vector<>();
    private Socket socket;
    private String username;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ClientHandlerThread(Socket socket) {
        super();
        this.socket = socket;
    }

    public void run(){
        Message message = null;
        try{
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

            //��������
            while (true) {
                message = (Message) ois.readObject();

                if(message.getCode() == Code.LOGIN){
                    //����ǵ�¼������֤�û�������
                        oos.writeObject(message);

                        //�������û���ӵ�������Ա������
                        online.add(oos);
                        vector.add(message.getUsername());

                        message.setCode(Code.CHAT);
                        message.setContent("������");
                        //֪ͨ�����ˣ�xx������
                        sendToOther(message);
                }else if(message.getCode() == Code.CHAT){
                    //�����������Ϣ������Ϣת�����������߿ͻ���
                    sendToOther(message);
                }else if(message.getCode() == Code.LOGOUT){
                    //֪ͨ�����ˣ�xx������
                    message.setContent("������");
                    sendToOther(message);
                    break;
                }
            }
        }catch(Exception  e){
            //֪ͨ�����ˣ�������
            if(message!=null && username!=null){
                message.setCode(Code.LOGOUT);
                message.setContent("������");
                sendToOther(message);
            }
        }finally{
            //��������Ա���Ƴ����Ͽ���ǰ�ͻ���
            try {
                online.remove(oos);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendToOther(Message message) {
        try {
            ArrayList<ObjectOutputStream> offline = new ArrayList<ObjectOutputStream>();
            for (ObjectOutputStream on : online) {
//                if(!on.equals(oos)){
                    try {
                        on.writeObject(message);
                    } catch (IOException e) {
//                    offline.add(on);
                    }
//                }
            }

//        for (ObjectOutputStream off : offline) {
//            online.remove(off);
//        }
        }catch (Exception e){

        }

    }
}
