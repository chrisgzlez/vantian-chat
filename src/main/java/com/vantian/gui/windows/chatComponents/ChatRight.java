package com.vantian.gui.windows.chatComponents;

import java.awt.Color;

import com.vantian.core.communication.IMessage;


public class ChatRight extends javax.swing.JLayeredPane {

    public ChatRight() {
        initComponents();
        txt.setBackground(new Color(58, 188, 253));
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

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.vantian.gui.windows.chatComponents.ChatItem txt;
    // End of variables declaration//GEN-END:variables
}
