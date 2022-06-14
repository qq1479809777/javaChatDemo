package edu.hunau.cxb21.chatroom.server.view;

import edu.hunau.cxb21.chatroom.server.Server;
import edu.hunau.cxb21.chatroom.utils.ChatRoomUtils;
import edu.hunau.gui.utils.BaseFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ServerFrame extends BaseFrame {
    private JTextArea messageArea;
    private DefaultTableModel clientTableModel;
    private Server server;

    public JTextArea getMessageArea() {
        return messageArea;
    }
    public DefaultTableModel getClientTableModel(){
        return clientTableModel;
    }
    public void init() {
        this.setSize(800, 450);
        this.setTitle("服务端");
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.setBorder(new EmptyBorder(7, 10, 7, 10));
        jPanel.add(topPanel(), BorderLayout.NORTH);
        jPanel.add(middlePanel());
        jPanel.add(bottomPanel(), BorderLayout.SOUTH);
        this.add(jPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JPanel bottomPanel() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField messageField = new JTextField(15);
        messageField.setPreferredSize(new Dimension(0, 28));
        JButton sendBtn = new JButton("发送");
        sendBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message =messageField.getText();
                messageArea.append("【系统管理员】:\n"+ ChatRoomUtils.showMessage(message));
                messageField.setText("");
            }
        });
        jPanel.add(messageField);
        jPanel.add(sendBtn);
        return jPanel;
    }

    private JSplitPane middlePanel() {
// 获得分割组件为水平摆放的切分面板
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setDividerSize(5);
        jSplitPane.setDividerLocation(150);// 设置切分位置不可移动
        jSplitPane.setEnabled(false);
        JScrollPane leftPanel = new JScrollPane();//表格中的数据// 提供字段名称
        DefaultTableModel clientInfoTableModel = new DefaultTableModel(new String[]{"客户端列表"}, 0);
        clientTableModel=clientInfoTableModel;
        JTable clientTable = new JTable(clientInfoTableModel);
        leftPanel.setViewportView(clientTable);
        jSplitPane.setLeftComponent(leftPanel);
        JScrollPane rightPanel = new JScrollPane();
        messageArea = new JTextArea();
        rightPanel.setViewportView(messageArea);
        jSplitPane.setRightComponent(rightPanel);
        return jSplitPane;
    }

    private JPanel topPanel() {
        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("服务端口");
        JTextField portField = new JTextField();
        portField.setPreferredSize(new Dimension(50, 28));
        // 设置内容在文本框中居中
        portField.setHorizontalAlignment(JTextField.CENTER);// 设置默认值
        portField.setText("8088");
        // 设置按钮的图标
        JButton executeBtn = new JButton("开启服务");// TODO:启动服务器
        executeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String btnStatus = executeBtn.getText();
                if (btnStatus.startsWith("开启")) {
                   server= new Server(ServerFrame.this);
                    new Thread(server).start();
// 设置图标
                    executeBtn.setText("关闭服务");
                } else {
//关闭服务器
                    if (Objects.nonNull(server)) {
                        server.close();
                    }
                    executeBtn.setText("开启服务");;
                }
            }
        });
        jPanel.add(jLabel);
        jPanel.add(portField);
        jPanel.add(executeBtn);
        return jPanel;
    }

    public static void main(String[] args) {

    }
}