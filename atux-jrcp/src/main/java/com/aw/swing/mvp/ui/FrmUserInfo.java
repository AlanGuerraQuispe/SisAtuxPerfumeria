package com.aw.swing.mvp.ui;

import javax.swing.*;
import java.awt.*;

/**
 * User: Juan Carlos Vergara
 * Date: 23-nov-2007
 * Time: 18:15:00
 */
public class FrmUserInfo {
    public JPanel pnlMain;
    private JPanel pnlHeader;
    public JPanel pnlUserInfo;

    private void createUIComponents() {
        pnlUserInfo = new UserInfoPanel();
        pnlUserInfo.setSize(300, 200);
        pnlMain = new JPanel();
        pnlMain.setOpaque(false);
        pnlHeader = new JPanel();
        pnlHeader.setOpaque(false);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        pnlMain.setLayout(new BorderLayout(0, 0));
        pnlHeader.setLayout(new BorderLayout(0, 0));
        pnlHeader.setPreferredSize(new Dimension(0, 110));
        pnlMain.add(pnlHeader, BorderLayout.NORTH);
        pnlUserInfo.setPreferredSize(new Dimension(350, 100));
        pnlHeader.add(pnlUserInfo, BorderLayout.EAST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return pnlMain;
    }
}
