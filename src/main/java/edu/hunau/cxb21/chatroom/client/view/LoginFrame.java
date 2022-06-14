package edu.hunau.cxb21.chatroom.client.view;

import edu.hunau.cxb21.chatroom.client.Client;
import edu.hunau.gui.utils.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseFrame {
    public void init() {
        this.setTitle("��¼");
//����һ����ֱ���е�����
        Box y_repeat = Box.createVerticalBox();
        y_repeat.add(Box.createVerticalStrut(15));
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("��¼������");
        titleLabel.setFont(new Font("����", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        y_repeat.add(titlePanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel userNamePanel = new JPanel();
        JLabel userNameLabel = new JLabel("�û���");
        JTextField userNameField = createTextField(20, 25);
        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        y_repeat.add(userNamePanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel hostPanel = new JPanel();
        JLabel hostLabel = new JLabel("������");
        JTextField hostField = createTextField(20, 25);
        hostField.setText("localhost");
        hostPanel.add(hostLabel);
        hostPanel.add(hostField);
        y_repeat.add(hostPanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel portPanel = new JPanel();
        JLabel portLabel = new JLabel("�˿ں�");
        JTextField portField = createTextField(20, 25);
        portField.setText("8088");
        portPanel.add(portLabel);
        portPanel.add(portField);
        y_repeat.add(portPanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel btnPanel = new JPanel();
        JButton restBtn = new JButton("����");
        JButton connBtn = new JButton("����");//ΪconnBtn���¼�
        connBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("��ť������");// �û���
                String username = userNameField.getText();//������ַ
                String hostName = hostField.getText();// �˿ں�
                String portInfo = portField.getText();//���ַ���ת��int����
                int port = Integer.parseInt(portInfo.trim());
                if ("".equals(username)) {
                    username = "�����û�";
                }
                Client client = new Client(username, hostName, port);
                client.start();
                LoginFrame.this.dispose();
                ChatAllFrame chatAllFrame = new ChatAllFrame(username, client);
                client.setChatAllFrame(chatAllFrame);
                chatAllFrame.init();
            }
        });
        btnPanel.add(restBtn);
        btnPanel.add(connBtn);
        y_repeat.add(btnPanel);
        y_repeat.add(Box.createVerticalStrut(5));
        this.setSize(450, 250);
        this.add(y_repeat);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JTextField createTextField(int cols, int height) {
        JTextField jTextField = new JTextField(cols);
        jTextField.setPreferredSize(new Dimension(0, height));
        return jTextField;
    }
}