package com.vantian.gui.windows.chatComponents;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.vantian.core.communication.TextMessage;
import com.vantian.gui.windows.chatComponents.event.PublicEvent;
import com.vantian.gui.windows.swingitems.JIMSendTextPane;
import com.vantian.gui.windows.swingitems.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

public class ChatBottom extends JPanel {

    public ChatBottom() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setHintText("Write Message Here ...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.darkGray);
        JButton cmd = getjButton(txt);
        panel.add(cmd);
        add(panel);
    }

    private JButton getjButton(JIMSendTextPane txt) {
        JButton cmd = new JButton(new FlatSVGIcon("/gui/svg/close.svg", 0.8f));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //cmd.setIcon(new ImageIcon(getClass().getResource("/com/vantian/gui/windows/chatComponents/images/send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String text = txt.getText().trim();
                if (!text.equals("")) {
                    try {
                        PublicEvent.getInstance().getEventChat().sendMessage(new TextMessage(text));
                    } catch (Exception e) {
                        System.err.println("Exception sending mssg: " + e.getMessage());
                        e.printStackTrace(System.err);
                        return;
                    }
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
            }
        });
        return cmd;
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new Color(54, 54, 54));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

