package com.vantian.gui;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.*;

import com.vantian.gui.drawer.DrawerBuilder;
import raven.drawer.Drawer;

import com.vantian.core.IPassword;
import com.vantian.core.IUser;
import com.vantian.core.IUserManager;
import com.vantian.gui.Login.Login;
import raven.popup.GlassPanePopup;
import com.vantian.gui.tabbed.WindowsTabbed;
import raven.toast.Notifications;


public class MainWindow extends javax.swing.JFrame {

    public static MainWindow mainWindow;
    public static IUserManager userManager;
    public static IUser user = null;
    public static IPassword userPassword;
    private Login loginForm;

    /**
     * Creates new form Main
     */
    public MainWindow(IUserManager userManager) {
        if (userManager == null) {
            System.err.println(" [x] Null User Manager. Exiting...");
            System.exit(1);
        }
        MainWindow.userManager = userManager;
        
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            initComponents();
            init();
            MainWindow.mainWindow = this;
            MainWindow.mainWindow.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    try {
                        if (MainWindow.user == null) {
                            System.exit(1);
                        }
                        
                        MainWindow.userManager.logOut(MainWindow.user, MainWindow.userPassword);
                    } catch (RemoteException ex) {
                        System.err.println(" [x] Error en logout. " + ex.getMessage());
                        System.exit(1);
                    }
                }
            });
            MainWindow.mainWindow.setVisible(true);
        });
    }

    private void init() {
        GlassPanePopup.install(this);
        Notifications.getInstance().setJFrame(this);
        WindowsTabbed.getInstance().install(this, body);
        login();
    }
    

    public void login() {
        if (loginForm == null) {
            loginForm = new Login();
        }
        WindowsTabbed.getInstance().showTabbed(false);
        loginForm.applyComponentOrientation(getComponentOrientation());
        setContentPane(loginForm);
        revalidate();
        repaint();
    }

    public void showMainForm() {
        DrawerBuilder myDrawerBuilder = new DrawerBuilder();
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        WindowsTabbed.getInstance().showTabbed(true);
        setContentPane(body);
        revalidate();
        repaint();
    }

    public void mostrarRegister (JComponent register){
        EventQueue.invokeLater(()->{
            FlatAnimatedLafChange.showSnapshot();
            setContentPane(register);
            revalidate();
            repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        //////////////////
            // Puedes agregar un WindowListener para realizar acciones cuando la ventana se cierre
        

        //////////////////

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
