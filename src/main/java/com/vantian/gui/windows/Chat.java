package com.vantian.gui.windows;

import com.vantian.gui.tabbed.TabbedForm;
import com.vantian.gui.windows.chatComponents.ChatBody;
import com.vantian.gui.windows.chatComponents.ChatBottom;
import com.vantian.gui.windows.chatComponents.event.EventChat;
import com.vantian.gui.windows.chatComponents.event.PublicEvent;
import net.miginfocom.swing.MigLayout;

public class Chat extends TabbedForm {

    public Chat() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[100%, bottom]0[shrink 0]0"));
        ChatBody chatBody = new ChatBody();
        ChatBottom chatBottom = new ChatBottom();
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(String text) {
                chatBody.addItemRight(text);
            }
        });

        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(54, 54, 54));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
