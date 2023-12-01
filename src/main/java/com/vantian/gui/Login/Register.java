package com.vantian.gui.Login;

import com.formdev.flatlaf.FlatClientProperties;
import com.vantian.gui.manager.Manager;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class Register extends JPanel {

    public Register(){
        init();
    }

    public void init(){
        setLayout(new MigLayout("fill,insets 20","[center]","[center]"));
        nombre = new JTextField();
        apellidos = new JTextField();
        username = new JTextField();
        password = new JPasswordField();
        checkPasswd = new JPasswordField();
        registerButton = new JButton("Sing up");

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;"+"[light]background:darken(@background,3%);"+
                        "[dark]background:lighten(@background,3%)");

        //placeholders para los campos

        nombre.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su nombre");
        apellidos.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca sus apellidos");
        username.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su nombre de usuario");
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su contraseña");
        checkPasswd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Repita la contraseña");

        //visualizar la contrasena
        password.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");
        checkPasswd.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");



        JLabel labeltitle  = new JLabel("Bienvenido a VantianChat!");
        JLabel descripcion  = new JLabel("Registrese para acceder chatear con sus amigos");

        //propiedades
        labeltitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +13");

        descripcion.putClientProperty(FlatClientProperties.STYLE, "" + "[light]foreground:darken(@foreground,30%);" + "[dark]foreground:lighten(@foreground,10%)" );

        panel.add(labeltitle);
        panel.add(descripcion);
        panel.add(new JLabel("Nombre Completo"),"gapy 10");
        panel.add(nombre,"split 2");
        panel.add(apellidos);
        panel.add(new JLabel("Genero"),"gapy 8");
        panel.add(crearPanelGenero());
        panel.add(new JSeparator(),"gapy 5 5");
        panel.add(new JLabel("Username"));
        panel.add(username);
        panel.add(new JLabel("Contraseña"),"gapy 8");
        panel.add(password);
        panel.add(new JLabel("Confirmar Contraseña"),"gapy 8");
        panel.add(checkPasswd);
        panel.add(registerButton, "gapy 20");
        //metemos el LogIn
        panel.add(LogIn(),"gapy 10");

        add(panel);

    }

    private Component LogIn(){
        JPanel panel= new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JButton login = new JButton("<html><a href=\"#\">Log In</a></html>");
        login.putClientProperty(FlatClientProperties.STYLE, "" + "border:3,3,3,3");

        //cursor
        login.setContentAreaFilled(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Accion que queramos hacer al llmar al register
        login.addActionListener(regbutton -> {
            //llamamos a la gui de register
            Manager.getInstance().mostrarRegister(new Login());
        });
        JLabel cuenta =new JLabel("Ya registrado?");
        cuenta.putClientProperty(FlatClientProperties.STYLE, "" + "[light]foreground:darken(@foreground,30%);" + "[dark]foreground:lighten(@foreground,30%)" );

        panel.add(cuenta);
        panel.add(login);
        return panel;
    }

   private Component crearPanelGenero(){
        JPanel panel = new JPanel(new MigLayout("insets 0"));
        panel.putClientProperty(FlatClientProperties.STYLE,""+
                "background:null");
        mujer = new JRadioButton("Mujer");
        hombre = new JRadioButton("Hombre");
        groupGenero = new ButtonGroup();
        groupGenero.add(mujer);
        groupGenero.add(hombre);
        hombre.setSelected(true);
        panel.add(hombre);
        panel.add(mujer);

        return panel;
   }

    private JTextField nombre;
    private JTextField apellidos;
    private JRadioButton mujer;
    private JRadioButton hombre;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField checkPasswd;

    private ButtonGroup groupGenero;
    private JButton registerButton;




}
