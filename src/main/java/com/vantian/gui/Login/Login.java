package com.vantian.gui.Login;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import com.vantian.gui.MainWindow;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel {

    private JTextField username;
    private JPasswordField password;
    private JCheckBox rememberMe;
    private JButton login;

    public Login() {
        init();
    }

    private void init(){
        setLayout(new MigLayout("fill,insets 20","[center]","[center]"));
        username = new JTextField();
        password = new JPasswordField();
        rememberMe = new JCheckBox("Remember me");
        login = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","fill,250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,
            "" + "arc:20;"+"[light]background:darken(@background,3%);"+"[dark]background:lighten(@background,3%)"
        );

        password.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");

        //configuaramos el boton de login
        login.putClientProperty(FlatClientProperties.STYLE,"" +
            // "[light]background:darken(@background,3%);"+
            // "[dark]background:lighten(@background,3%);"+
            "margin:4,6,4,6;"+
            "borderWidth:0;"+
            "focusWidth:0;" +
            "innerFocusWidth:0"
        );

        login.addActionListener((e -> {
            //control de que el usuario este verificado, si no alerta
            //error alerta de error
            //MessageAlerts.getInstance().showMessage("Login Incorrecto", "No se ha encontrado un usuario con los datos proporcionados, vuelva a intentarlo",
                    //MessageAlerts.MessageType.ERROR);

            //acierto en la base de datos
            MessageAlerts.getInstance().showMessage("Login Correcto", "Disfrute de su experiencia",
                MessageAlerts.MessageType.SUCCESS, MessageAlerts.OK_CANCEL_OPTION, new PopupCallbackAction() {
                    @Override
                    public void action(PopupController popupController, int i) {
                        if(i==MessageAlerts.OK_OPTION){
                            MainWindow.mainWindow.showMainForm();
                        }
                    }
                }
            );
        }));

        //placeholders para los campos
        username.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su nombre de usuario");
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su contraseña");

        JLabel labeltitle  = new JLabel("Welcome back!");
        JLabel descripcion  = new JLabel("Haga login para acceder a su cuenta");

        //propiedades
        labeltitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +13");
        descripcion.putClientProperty(FlatClientProperties.STYLE, "" + "[light]foreground:darken(@foreground,30%);" + "[dark]foreground:lighten(@foreground,10%)" );

        panel.add(labeltitle);
        panel.add(descripcion);
        panel.add(new JLabel("Username"),"gapy 8");
        panel.add(username);
        panel.add(new JLabel("Password"),"gapy 8");
        panel.add(password);
        panel.add(rememberMe,"grow 0");
        panel.add(login,"gapy 10");
        //metemos el signup
        panel.add(SignUp(),"gapy 10");

        add(panel);

    }

    //Jpanel para el signup
    private Component SignUp(){
        JPanel panel= new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
        JButton register = new JButton("<html><a href=\"#\">Sign up</a></html>");
        register.putClientProperty(FlatClientProperties.STYLE, "" + "border:3,3,3,3");

        //cursor
        register.setContentAreaFilled(false);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Accion que queramos hacer al llmar al register
        register.addActionListener(regbutton -> {
            //llamamos a la gui de register
            MainWindow.mainWindow.mostrarRegister(new Register());
        });
        JLabel cuenta =new JLabel("No tienes una cuenta?");
        cuenta.putClientProperty(FlatClientProperties.STYLE, "" + "[light]foreground:darken(@foreground,30%);" + "[dark]foreground:lighten(@foreground,30%)" );

        panel.add(cuenta);
        panel.add(register);
        return panel;
    }
}
