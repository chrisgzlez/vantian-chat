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
        int RMIPortNumber = 8080;
        if (args.length > 0) {
            RMIPortNumber = Integer.parseInt(args[0]);
        } else {
            try {
                BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
                System.out.print(" [*] Enter the port number: ");
                String portNumber = (buff.readLine()).trim();
                RMIPortNumber = Integer.parseInt(portNumber);
            } catch (IOException e) {
                System.err.println("Error reading from terminal. " + e.getMessage());
                System.exit(1);
            }
        }

        String registryURI;
        try {
            startRegistry(RMIPortNumber);

            // Monitoring obj = new Monitoring();
            IUserManager userManager = new UserManager();
            registryURI = "rmi://localhost:" + RMIPortNumber + "/vantianchat";
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

