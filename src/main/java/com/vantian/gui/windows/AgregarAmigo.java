package com.vantian.gui.windows;

import com.formdev.flatlaf.FlatDarkLaf;
import com.vantian.gui.MainWindow;
import com.vantian.gui.tabbed.TabbedForm;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import raven.alerts.MessageAlerts;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class AgregarAmigo extends TabbedForm {
    private DefaultComboBoxModel<String> userModel;
    private JComboBox<String> userComboBox;

    public AgregarAmigo() {
        // Configurar FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Configuración básica del JFram
        setSize(300, 150); // Ajustar el tamaño de la ventana


        // Crear el modelo del JComboBox
        userModel = new DefaultComboBoxModel<>();

        try {
            String userName = MainWindow.user.getUserName();
            for (String user : MainWindow.userManager.getUsersRegistered()) {
                if (! user.equals(userName)) {
                    userModel.addElement(user);
                }
            }
        } catch (Exception e) {
            System.err.println(" [x] COuld not retrieve list of friends. " + e.getMessage());
            e.printStackTrace(System.err);
            return;
        }

        // Crear el JComboBox con el modelo
        userComboBox = new JComboBox<>(userModel);
        userComboBox.setEditable(true); // Permitir edición

        // Configurar autocompletado con SwingX
        AutoCompleteDecorator.decorate(userComboBox);

        // Crear etiquetas para el título y la descripción
        JLabel titleLabel = new JLabel("Agregar Amigo...");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel descriptionLabel = new JLabel("Seleccione un usuario de la lista:");

        // Crear un contenedor y agregar los componentes usando MigLayout
        setLayout(new MigLayout("wrap 2, alignx center", "[right][grow]", "[]15[]"));

        // Agregar componentes al contenedor
        add(titleLabel, "span 2, center, wrap");
        add(descriptionLabel, "span 2, center, wrap");
        add(userComboBox, "span 2, grow");

        // Manejar eventos del ratón para abrir la lista desplegable cuando se hace clic en la caja de texto
        userComboBox.getEditor().getEditorComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userComboBox.showPopup();
            }
        });

        // Agregar un botón
        JButton miBoton = new JButton("Solicitud de Amistad");
        miBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //comprobacion de solicitud
                try {
                    MainWindow.userManager.sendFriendRequest(MainWindow.user.getUserName(), (String)userComboBox.getSelectedItem());

                    MessageAlerts.getInstance().showMessage("Solicitud Correcta", "Ha sido enviada su solicitud de amistad",
                    MessageAlerts.MessageType.SUCCESS);
                } catch (Exception ex) {
                    System.out.println(" [x] Exception in Client. " + ex.getMessage());
                    ex.printStackTrace(System.err);
                }

            }
        });
        add(miBoton, "span 2, center");

    }


}



