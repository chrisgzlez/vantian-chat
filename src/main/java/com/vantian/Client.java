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
        try {
            BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(" [*] Enter the host name of the registry: ");
            String registryHost = buff.readLine().trim();
            System.out.print(" [*] Enter the port of the registry: ");
            int registryPort = Integer.parseInt(buff.readLine().trim());
            String registryURL = "rmi://" + registryHost + ":" + registryPort;

            Registry registry = LocateRegistry.getRegistry(registryHost, registryPort);
            String[] svcs = registry.list();
            System.out.println(" [*] Available Services: ");
            for (String svc : svcs) {
                System.out.println("\t- " + svc);
                
            }
            System.out.print(" [*] Choose a service: ");
            String serviceName = buff.readLine().trim();

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
            while(true);

        } catch (Exception e) {
            System.out.println(" [x] Exception in Client. " + e);
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }
}

