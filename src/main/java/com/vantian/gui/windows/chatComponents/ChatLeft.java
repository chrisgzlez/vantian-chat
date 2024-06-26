package com.vantian.gui.windows.chatComponents;

import java.awt.Color;

import com.vantian.core.communication.IMessage;

public class ChatLeft extends javax.swing.JLayeredPane {

    public ChatLeft() {
        initComponents();
        txt.setBackground(new Color(132, 150, 183));
    }

    public void setText(IMessage text) {
        try {
            txt.setText(text.get());
            txt.setTime(text.getTime());    //  Testing
        } catch (Exception e) {
            System.out.println(" [x] Exception in Client. " + e);
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new com.vantian.gui.windows.chatComponents.ChatItem();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.vantian.gui.windows.chatComponents.ChatItem txt;
    // End of variables declaration//GEN-END:variables
}
