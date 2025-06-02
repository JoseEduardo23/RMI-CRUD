package rmiservidor.test;

import rmiservidor.clase.Servidor;
import rmiservidor.clase.Servidorimpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Testservidor {
    public static void main (String[] args) throws Exception {
        LocateRegistry.createRegistry(1099);
        Servidorimpl servidor = new Servidorimpl();
        String rmiObjectName = "rmi://localhost/Datos";
        Naming.rebind(rmiObjectName, servidor);
        System.out.println("Servidor remoto corriendo");
    }
}
