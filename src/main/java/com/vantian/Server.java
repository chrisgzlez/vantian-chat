package com.vantian;
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

import com.vantian.core.*;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        String portNumber, registryURI;
        try {
            System.out.print(" [*] Enter the port number: ");
            portNumber = (buff.readLine()).trim();
            int RMIPortNumber = Integer.parseInt(portNumber);
            startRegistry(RMIPortNumber);

            // Monitoring obj = new Monitoring();
            IUserManager userManager = new UserManager();
            registryURI = "rmi://localhost:" + RMIPortNumber + "/callback";
            Naming.rebind(registryURI, userManager);
            System.out.println(" [v] Callback Server Ready on rmi://localhost:" + RMIPortNumber);

        } catch (Exception e) {
            System.out.println(" [x] Exception in Server. " + e);
        }
    }

    private static void startRegistry(int RMIPort) throws RemoteException {
        try {
            // Try and get registry
            Registry registry = LocateRegistry.getRegistry(RMIPort);
            // Throws exception if it doesnt exits
            registry.list();
            
        } catch (RemoteException e) {
            // Create registry in port if it doesnt exist
            Registry registry = LocateRegistry.createRegistry(RMIPort);
        }
    }
}

