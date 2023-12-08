package com.vantian.gui.windows;

import com.formdev.flatlaf.FlatClientProperties;
import com.vantian.gui.Login.Login;
import com.vantian.gui.MainWindow;
import com.vantian.gui.tabbed.TabbedForm;
import net.miginfocom.swing.MigLayout;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;

import javax.swing.*;
import java.awt.*;

public class CambiarCredenciales extends TabbedForm {

    public CambiarCredenciales(){
        init();
    }

    public void init(){
        setLayout(new MigLayout("fill,insets 20","[center]","[center]"));
        username = new JTextField();
        password = new JPasswordField();
        newpassword = new JPasswordField();
        checknewPasswd = new JPasswordField();
        changePassword = new JButton("Change Password");

        JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45","[fill,360]"));
        panel.putClientProperty(FlatClientProperties.STYLE,
                "" + "arc:20;"+"[light]background:darken(@background,3%);"+
                        "[dark]background:lighten(@background,3%)");

        //placeholders para los campos


        username.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su nombre de usuario");
        password.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su antigua contraseña");
        newpassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca su nueva contraseña");
        checknewPasswd.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Introduzca la nueva contraseña chequeado");
        changePassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Repita la contraseña");

        //visualizar la contrasena
        password.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");
        newpassword.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");
        checknewPasswd.putClientProperty(FlatClientProperties.STYLE,""+"showRevealButton:true");


        JLabel labeltitle  = new JLabel("Cambiar credenciales");
        JLabel descripcion  = new JLabel("Cubra el formulario para poder cambiar su contraseña");

        //propiedades
        labeltitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +13");

        descripcion.putClientProperty(FlatClientProperties.STYLE, "" + "[light]foreground:darken(@foreground,30%);" + "[dark]foreground:lighten(@foreground,10%)" );


        //configuaramos el boton de login
        changePassword.putClientProperty(FlatClientProperties.STYLE,"" +
                // "[light]background:darken(@background,3%);"+
                // "[dark]background:lighten(@background,3%);"+
                "margin:4,6,4,6;"+
                "borderWidth:0;"+
                "focusWidth:0;" +
                "innerFocusWidth:0");

        changePassword.addActionListener((e -> {
            //llamamos a la gui de register
            //control de que el usuario este verificado, si no alerta

            //error alerta de error
            //MessageAlerts.getInstance().showMessage("Sing Up Incorrecto", "Los parametros proprocionados a la hora de realizar el registro no son correctos, vuelva a intentarlo",
            //MessageAlerts.MessageType.ERROR);

            //error alerta de error
            MessageAlerts.getInstance().showMessage("Cambio de contraseña", "Los parametros proprocionados a la hora de realizar el cambio de contraseña no son correctos, vuelva a intentarlo",
            MessageAlerts.MessageType.ERROR);


        }));


        panel.add(labeltitle);
        panel.add(descripcion);
        panel.add(new JSeparator(),"gapy 5 5");
        panel.add(new JLabel("Username"));
        panel.add(username);
        panel.add(new JLabel("Antigua Contraseña"),"gapy 8");
        panel.add(password);
        panel.add(new JLabel("Nueva Contraseña"),"gapy 8");
        panel.add(newpassword);
        panel.add(new JLabel("Confirmar nueva Contraseña"),"gapy 8");
        panel.add(checknewPasswd);
        panel.add(changePassword, "gapy 20");
        add(panel);

    }


    private JTextField username;
    private JPasswordField password;
    private JPasswordField newpassword;
    private JPasswordField checknewPasswd;

    private JButton changePassword;




}
