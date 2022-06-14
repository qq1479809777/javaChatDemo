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
        this.setTitle("�����");
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
        JButton sendBtn = new JButton("����");
        sendBtn.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message =messageField.getText();
                messageArea.append("��ϵͳ����Ա��:\n"+ ChatRoomUtils.showMessage(message));
                messageField.setText("");
            }
        });
        jPanel.add(messageField);
        jPanel.add(sendBtn);
        return jPanel;
    }

    private JSplitPane middlePanel() {
// ��÷ָ����Ϊˮƽ�ڷŵ��з����
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        jSplitPane.setDividerSize(5);
        jSplitPane.setDividerLocation(150);// �����з�λ�ò����ƶ�
        jSplitPane.setEnabled(false);
        JScrollPane leftPanel = new JScrollPane();//����е�����// �ṩ�ֶ�����
        DefaultTableModel clientInfoTableModel = new DefaultTableModel(new String[]{"�ͻ����б�"}, 0);
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
        JLabel jLabel = new JLabel("����˿�");
        JTextField portField = new JTextField();
        portField.setPreferredSize(new Dimension(50, 28));
        // �����������ı����о���
        portField.setHorizontalAlignment(JTextField.CENTER);// ����Ĭ��ֵ
        portField.setText("8088");
        // ���ð�ť��ͼ��
        JButton executeBtn = new JButton("��������");// TODO:����������
        executeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String btnStatus = executeBtn.getText();
                if (btnStatus.startsWith("����")) {
                   server= new Server(ServerFrame.this);
                    new Thread(server).start();
// ����ͼ��
                    executeBtn.setText("�رշ���");
                } else {
//�رշ�����
                    if (Objects.nonNull(server)) {
                        server.close();
                    }
                    executeBtn.setText("��������");;
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