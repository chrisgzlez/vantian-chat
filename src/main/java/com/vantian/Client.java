package com.vantian;

import java.rmi.*;
import java.rmi.registry.*;

import com.vantian.core.IUserManager;
import com.vantian.core.Password;
import com.vantian.core.IUser;
import com.vantian.core.User;
import com.vantian.gui.GUI;

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
            GUI gui = new GUI(userManager);

            IUser user = new User("christian");
            boolean check = userManager.signIn(user, new Password("test"));
            if(check) {
                System.out.println("Succesfully signed in");
            } else {
                System.out.println("Unsuccesfully signed in");
            }

            System.out.println(" [*] Attempting login with correct password");
            userManager.logIn(user, new Password("test"));

            System.out.println(" [*] Attempting login with wrong password");
            userManager.logIn(user, new Password("fail"));

            IUser user1 = new User("ivan");
            userManager.signIn(user1, new Password("test"));
            userManager.logIn(user1, new Password("test"));

            IUser user2 = new User("adrian");
            userManager.signIn(user2, new Password("test"));
            userManager.logIn(user2, new Password("test"));

            IUser user3 = new User("loly");
            userManager.signIn(user3, new Password("test"));
            userManager.logIn(user3, new Password("test"));

            IUser user4 = new User("pepe");
            userManager.signIn(user4, new Password("test"));
            userManager.logIn(user4, new Password("test"));
        } catch (Exception e) {
            System.out.println(" [x] Exception in Client. " + e);
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}

