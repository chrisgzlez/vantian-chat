package com.vantian.gui.windows;

import com.formdev.flatlaf.FlatClientProperties;
import com.vantian.gui.Login.Login;
import com.vantian.core.IPassword;
import com.vantian.core.Password;
import com.vantian.core.User;
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
            IPassword oldpasswd = null;
            try {
                oldpasswd = new Password(new String(password.getPassword()));
                boolean loggedIn = MainWindow.userManager.logIn(new User(username.getText()), oldpasswd);

                if (!loggedIn) {
                    return;
                }
            } catch (Exception ex) {
                System.out.println(" [x] Exception in Client. " + e);
                ex.printStackTrace(System.err);
                return;
            }

            if(newpassword.getPassword().length == 0) {
                MessageAlerts.getInstance().showMessage(
                    "Sign Up Incorrecto", "Debe de introducir una contraseña valida",
                    MessageAlerts.MessageType.ERROR
                );
                return;
            }
            String passwd = new String(newpassword.getPassword());
            String checkPassword = new String(checknewPasswd.getPassword());

            if (! passwd.equals(checkPassword)) {
                MessageAlerts.getInstance().showMessage(
                    "Sign Up Incorrecto", "Las contraseñas deben de coincidir",
                    MessageAlerts.MessageType.ERROR
                );
                return;
            }
            try {
                
                MainWindow.userManager.updatePassword(new User(username.getText()), oldpasswd, new Password(passwd));
            } catch (Exception ex) {
                System.out.println(" [x] Exception in Client. " + e);
                ex.printStackTrace(System.err);
                return;
            }

            //error alerta de error
            MessageAlerts.getInstance().showMessage("Cambio de contraseña Correcto", "",
            MessageAlerts.MessageType.SUCCESS);


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
