package edu.hunau.cxb21.chatroom.client.view;

import edu.hunau.cxb21.chatroom.client.Client;
import edu.hunau.gui.utils.BaseFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Vector;

public class ChatAllFrame extends BaseFrame {
    private String username;
    private JTextArea messageSendArea;
    private JTextArea messageShowArea;
    private Client client;

    public JTextArea getMessageShowArea() {
        return messageShowArea;
    }

    public ChatAllFrame(){

    }
    public ChatAllFrame(String username, Client client){
        this.username=username;
        this.client=client;
    }
    public void init() {
        if(Objects.nonNull(username)&&!username.equals("")){
            this.setTitle(username);
        }else{
            this.setTitle("匿名");
        }
        this.setSize(550, 450);
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setBorder(new EmptyBorder(5, 10, 5, 10));
        jSplitPane.setDividerLocation(150);
        jSplitPane.setDividerSize(5);
        jSplitPane.setEnabled(false);
        jSplitPane.setLeftComponent(leftPanel());
        jSplitPane.setRightComponent(righPanel());
        this.add(jSplitPane);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel righPanel() {
        JPanel jPanel = new JPanel(new BorderLayout(0, 5));
        JScrollPane messageShowPane = new JScrollPane();
        messageShowPane.setPreferredSize(new Dimension(0, 260));
        JTextArea messageShowArea = new JTextArea();
        this.messageShowArea=messageShowArea;
        messageShowPane.setViewportView(messageShowArea);
        JScrollPane messageSendPane = new JScrollPane();
        JTextArea messageSendArea = new JTextArea();
        messageSendPane.setViewportView(messageSendArea);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton sendBtn = new JButton("发送");
        sendBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message =messageSendArea.getText();
                client.sendMsg(message);
                messageSendArea.setText("");
            }
        });
        btnPanel.add(sendBtn);
        jPanel.add(messageShowPane, BorderLayout.NORTH);
        jPanel.add(messageSendPane);
        jPanel.add(btnPanel, BorderLayout.SOUTH);
        return jPanel;
    }

    private JScrollPane leftPanel() {
        JScrollPane jScrollPane = new JScrollPane();
        DefaultTableModel userInfoModel = new DefaultTableModel(new String[]{"在线用户"}, 0);
        Vector vector= new Vector(0);
        vector.add(username);
        userInfoModel.addRow(vector);
        JTable userTable = new JTable(userInfoModel);
        jScrollPane.setViewportView(userTable);
        return jScrollPane;
    }

    public static void main(String[] args) {
        new ChatAllFrame().init();
    }
}