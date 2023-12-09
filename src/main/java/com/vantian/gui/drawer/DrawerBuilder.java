package com.vantian.gui.drawer;

import com.vantian.gui.MainWindow;
import com.vantian.gui.tabbed.WindowsTabbed;
import com.vantian.gui.windows.AgregarAmigo;
import com.vantian.gui.windows.CambiarCredenciales;
import com.vantian.gui.windows.NewChat;
import com.vantian.gui.windows.SolicitudesAmistad;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;

public class DrawerBuilder extends SimpleDrawerBuilder {

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        String userName = "User";
        try {
            userName  = MainWindow.user.getUserName();
            
        } catch (Exception e) {
            userName = "User";
        }

        return new SimpleHeaderData()
            .setTitle(userName);
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        String menus[][] = {
                {"~MAIN~"},
                {"New Chat"},
                {"Agregar Amigos"},
                {"Solicitudes de Amistad"},
                {"Cambiar configuracion"},
        };


        return new SimpleMenuOption()
                .setMenus(menus)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int index, int subindex) {
                        if (index==0){
                            WindowsTabbed.getInstance().addTab("Seleccione Chat",new NewChat());
                        }
                        if (index==1){
                            WindowsTabbed.getInstance().addTab("Agregar Amigos",new AgregarAmigo());
                        }
                        if (index==2){
                            SolicitudesAmistad solicitudes = new SolicitudesAmistad();
                            solicitudes.addFriendRequest("Usuario1");
                            solicitudes.addFriendRequest("Usuario2");
                            WindowsTabbed.getInstance().addTab("Solicitudes de Amistad",solicitudes);
                        }
                        if (index==3){
                            WindowsTabbed.getInstance().addTab("Cambiar Contrasena",new CambiarCredenciales());
                        }

                    }
                });
        //Menu validation
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {

        return new SimpleFooterData()
                .setTitle("Vantian Chat")
                .setDescription("Version 1.0");
    }
}
