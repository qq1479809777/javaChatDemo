package edu.hunau.gui.utils;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public void setVisible(boolean b) {
        center(this);
        super.setVisible(b);
    }

    public void center(Window win) {
        /**
         * 获取屏幕的大小
         */
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screen = toolkit.getScreenSize();
        int w = win.getWidth();
        int h = win.getHeight();
        int x = (screen.width - w) / 2;
        int y = (screen.height - h) / 2;
        win.setLocation(x, y);

    }
}
