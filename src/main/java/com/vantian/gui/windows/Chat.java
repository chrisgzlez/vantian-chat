package com.vantian.gui.windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import com.sun.source.util.TaskEvent;
import com.vantian.core.IUser;
import com.vantian.core.User;
import com.vantian.core.communication.IMessage;
import com.vantian.core.communication.TextMessage;
import com.vantian.gui.MainWindow;
import com.vantian.gui.tabbed.TabbedForm;
import com.vantian.gui.windows.chatComponents.ChatBody;
import com.vantian.gui.windows.chatComponents.ChatBottom;
import com.vantian.gui.windows.chatComponents.event.EventChat;
import com.vantian.gui.windows.chatComponents.event.PublicEvent;
import net.miginfocom.swing.MigLayout;

public class Chat extends TabbedForm {
    private IUser destUser;
    private Timer timer;

    public Chat(IUser destUser) {
        try {
            this.destUser = destUser;
            System.out.println("--------------------------");
            System.out.println("My User: " + MainWindow.user);
            System.out.println("Remote User: " + this.destUser);
            System.out.println("--------------------------");
        } catch (Exception e) {
            return;
        }
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[100%, bottom]0[shrink 0]0"));
        ChatBody chatBody = new ChatBody();
        ChatBottom chatBottom = new ChatBottom();
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(IMessage mssg) {
                try {
                    chatBody.addItemRight(mssg.get());
                    destUser.send(MainWindow.user, mssg);
                } catch (Exception e) {
                    System.err.println("Exception sending mssg: " + e.getMessage());
                    e.printStackTrace(System.err);
                    return;
                }
            }

            @Override
            public void receiveMessage() {
                try {
                    for(IMessage mssg = MainWindow.user.receive(destUser); mssg != null; mssg = MainWindow.user.receive(destUser)) {
                        System.out.println("Reading message: " + mssg.get());
                        chatBody.addItemLeft(mssg.get());
                    }
                } catch (Exception e) {
                    System.err.println("Exception getting messages");
                }
            }
        });

        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");
        // Create a Timer with a 1000 ms (1 second) delay
        this.timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PublicEvent.getInstance().getEventChat().receiveMessage();
                } catch (Exception ex) {
                    System.out.println("Exception in action performmed: " + ex.getMessage());
                    ex.printStackTrace(System.err);
                    return;
                }
            }
        });

        // Start the timer
        this.timer.start();
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
