package com.vantian.gui.windows.chatComponents;

import com.vantian.core.communication.IMessage;
import com.vantian.gui.tabbed.TabbedForm;
import com.vantian.gui.windows.swingitems.ScrollBar;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;


public class ChatBody extends JPanel {

    /**
     * Creates new form Main
     */
    public ChatBody() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.darkGray);
    }

    public void addItemLeft(IMessage text) {
        ChatLeft item = new ChatLeft();
        item.setText(text);
        body.add(item, "wrap, w ::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemRight(IMessage text) {
        ChatRight item = new ChatRight();
        item.setText(text);
        body.add(item, "wrap, al right, w ::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new JScrollPane();
        body = new JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new Color(54, 54, 54, 255));

        GroupLayout bodyLayout = new GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
                bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
                bodyLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel body;
    private JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
