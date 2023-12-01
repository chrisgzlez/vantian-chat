package com.vantian.gui.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.vantian.gui.Login.Login;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public Application(){
        init();
    }

    private void init(){
        setTitle("VantianChat Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,700));
        setLocationRelativeTo(null);
        setContentPane(new Login());

    }

    public static void main(String[] args){
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY,Font.PLAIN,13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(()-> new Application().setVisible(true));
    }
}
