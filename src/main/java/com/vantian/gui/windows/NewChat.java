package com.vantian.gui.windows;

import com.formdev.flatlaf.FlatDarkLaf;
import com.vantian.gui.tabbed.TabbedForm;
import com.vantian.gui.tabbed.WindowsTabbed;
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

public class NewChat extends TabbedForm {
    private DefaultComboBoxModel<String> userModel;
    private JComboBox<String> userComboBox;

    public NewChat() {
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
        userModel.addElement("Usuario1");
        userModel.addElement("Usuario2");
        userModel.addElement("Usuario3");

        // Crear el JComboBox con el modelo
        userComboBox = new JComboBox<>(userModel);
        userComboBox.setEditable(true); // Permitir edición

        // Configurar autocompletado con SwingX
        AutoCompleteDecorator.decorate(userComboBox);

        // Crear etiquetas para el título y la descripción
        JLabel titleLabel = new JLabel("Comience una conversacion");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel descriptionLabel = new JLabel("Seleccione un usuario de su lista de amigos:");

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
        JButton miBoton = new JButton("Chatear");
        miBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el valor seleccionado del JComboBox
                String usuarioSeleccionado = (String) userComboBox.getSelectedItem();

                WindowsTabbed.getInstance().addTab(usuarioSeleccionado,new Chat());

            }
        });
        add(miBoton, "span 2, center");

    }


}
