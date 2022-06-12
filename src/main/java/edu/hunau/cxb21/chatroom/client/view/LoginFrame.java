package edu.hunau.cxb21.chatroom.client.view;

import edu.hunau.gui.utils.BaseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends BaseFrame {
    public void init() {
        this.setTitle("登录");
//创建一个垂直排列的容器
        Box y_repeat = Box.createVerticalBox();
        y_repeat.add(Box.createVerticalStrut(15));
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("登录服务器");
        titleLabel.setFont(new Font("黑体", Font.BOLD, 30));
        titlePanel.add(titleLabel);
        y_repeat.add(titlePanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel userNamePanel = new JPanel();
        JLabel userNameLabel = new JLabel("用户名");
        JTextField userNameField = createTextField(20, 25);
        userNamePanel.add(userNameLabel);
        userNamePanel.add(userNameField);
        y_repeat.add(userNamePanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel hostPanel = new JPanel();
        JLabel hostLabel = new JLabel("主机名");
        JTextField hostField = createTextField(20, 25);
        hostField.setText("localhost");
        hostPanel.add(hostLabel);
        hostPanel.add(hostField);
        y_repeat.add(hostPanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel portPanel = new JPanel();
        JLabel portLabel = new JLabel("端口号");
        JTextField portField = createTextField(20, 25);
        portField.setText("8088");
        portPanel.add(portLabel);
        portPanel.add(portField);
        y_repeat.add(portPanel);
        y_repeat.add(Box.createVerticalStrut(5));
        JPanel btnPanel = new JPanel();
        JButton restBtn = new JButton("重置");
        JButton connBtn = new JButton("连接");//为connBtn绑定事件
        connBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被单击");// 用户名
                String username = userNameField.getText();//主机地址
                String hostName = hostField.getText();// 端口号
                String portInfo = portField.getText();//将字符串转成int类型
                int port = Integer.parseInt(portInfo.trim());
                if("".equals(username)){
                    username="匿名用户";
                }
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