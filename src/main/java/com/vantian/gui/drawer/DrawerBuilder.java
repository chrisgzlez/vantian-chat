package com.vantian.gui.drawer;

import com.vantian.gui.tabbed.WindowsTabbed;
import com.vantian.gui.windows.Chat;
import com.vantian.gui.windows.TestForm;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.MenuValidation;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.swing.AvatarIcon;

public class DrawerBuilder extends SimpleDrawerBuilder {
    @Override
    public SimpleHeaderData getSimpleHeaderData() {
            //no pone la fotito
            return new SimpleHeaderData()
                            .setTitle("Ivan");




    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        String menus[][] = {
                {"~MAIN~"},
                {"Dashboard"},
                {"~WEB APP~"},
                {"Email", "Inbox", "Read", "Compost"},
                {"Chat"},
                {"Calendar"},
                {"~COMPONENT~"},
                {"Advanced UI", "Cropper", "Owl Carousel", "Sweet Alert"},
                {"Forms", "Basic Elements", "Advanced Elements", "SEditors", "Wizard"},
                {"~OTHER~"},
                {"Charts", "Apex", "Flot", "Sparkline"},
                {"Icons", "Feather Icons", "Flag Icons", "Mdi Icons"},
                {"Special Pages", "Blank page", "Faq", "Invoice", "Profile", "Pricing", "Timeline"},
                {"Logout"}};


        return new SimpleMenuOption()
                .setMenus(menus)
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int index, int subindex) {
                        if (index==0){
                            //WindowsTabbed.getInstance().addTab("Test Form",new TestForm());
                            WindowsTabbed.getInstance().addTab("Chat",new Chat());
                        //if (index==1){
                           // WindowsTabbed.getInstance().addTab("Chat",new Chat());
                       // }
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
