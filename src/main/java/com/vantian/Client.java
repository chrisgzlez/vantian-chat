package com.vantian;

import java.rmi.*;
import java.rmi.registry.*;

import com.vantian.core.IUserManager;
import com.vantian.core.Password;
import com.vantian.core.IUser;
import com.vantian.core.User;
import com.vantian.core.communication.IMessage;
import com.vantian.core.communication.TextMessage;
import com.vantian.gui.GUI;
import com.vantian.gui.MainWindow;

import java.io.*;

/**
 * Client
 */
public class Client {
    public static void main(String[] args) {
        String registryHost = "localhost", serviceName = "vantianchat";
        int registryPort =  5601;
        if (args.length >= 3) {
            registryHost = args[0];
            registryPort = Integer.parseInt(args[1]);
            serviceName = args[2];
        } else {
            System.out.println("No arguments were provided, using defaults...");
        }
        try {

            String registryURL = "rmi://" + registryHost + ":" + registryPort;
            System.out.println(registryURL + "/" + serviceName);
            IUserManager userManager = (IUserManager)Naming.lookup(registryURL + "/" + serviceName);

            System.out.println(" [*] Initializing GUI...");
            MainWindow gui = new MainWindow(userManager);


        } catch (Exception e) {
            System.out.println(" [x] Exception in Client. " + e);
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}

