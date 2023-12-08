package com.vantian.gui.windows;

import com.formdev.flatlaf.FlatDarkLaf;
import com.vantian.gui.tabbed.TabbedForm;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolicitudesAmistad extends TabbedForm {
    private DefaultListModel<String> requestListModel;
    private JList<String> requestList;
    private JButton acceptButton;
    private JButton declineButton;

    public SolicitudesAmistad() {
        FlatDarkLaf.install(); // Instala el tema FlatLaf

        // Configura el diseño con MigLayout
        setLayout(new MigLayout("wrap 3", "[grow][grow][grow]", "[][grow][]"));

        // Inicializa componentes
        requestListModel = new DefaultListModel<>();
        requestList = new JList<>(requestListModel);
        acceptButton = new JButton("Aceptar");
        declineButton = new JButton("Declinar");


        // Agrega componentes al panel con MigLayout

        add(new JLabel("Solicitudes de amistad enviadas"), "span 3, center, wrap");
        add(new JScrollPane(requestList), "span 3, grow, push, wrap");
        add(acceptButton, "grow");
        add(declineButton, "grow");

        // Agrega oyentes de eventos a los botones
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = requestList.getSelectedValue();
                // Implementa la lógica de aceptación aquí

                // Elimina la solicitud aceptada del modelo
                requestListModel.removeElement(selectedRequest);
            }
        });

        declineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRequest = requestList.getSelectedValue();
                // Implementa la lógica de declinación aquí

                // Elimina la solicitud aceptada del modelo
                requestListModel.removeElement(selectedRequest);
            }
        });
        // Establece una fuente más grande para la JList
        requestList.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Ajusta la fuente según tus preferencias
    }


    public void addFriendRequest(String request) {
        requestListModel.addElement(request);
    }
}
