package com.vantian.gui.manager;

import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.icons.FlatAnimatedIcon;
import com.vantian.gui.main.Application;

import javax.swing.*;
import java.awt.*;

public class Manager {
    private Application aplicacion;
    private static Manager manager;
    public static Manager getInstance(){
        if (manager == null){
            manager = new Manager();
        }
        return manager;
    }

    public void iniciarAplicacion(Application aplicacion){
        this.aplicacion=aplicacion;

    }
    public void mostrarRegister (JComponent register){
        EventQueue.invokeLater(()->{
            FlatAnimatedLafChange.showSnapshot();
            aplicacion.setContentPane(register);
            aplicacion.revalidate();
            aplicacion.repaint();
            FlatAnimatedLafChange.hideSnapshotWithAnimation();
        });
    }
}
