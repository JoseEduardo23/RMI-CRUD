package RMICliente.Cliente;

import rmiservidor.clase.Persona;
import rmiservidor.clase.Servidor;
import java.rmi.Naming;

public class Cliente {
    private static Servidor getServicio() throws Exception {
        String rmiObjectName = "rmi://localhost/Datos";
        return (Servidor) Naming.lookup(rmiObjectName);
    }

    public static String consultar(int id) throws Exception {
        return getServicio().consultar(id);
    }

    public static String crear(int id, String nombre, String correo, String cargo, Double sueldo) throws Exception {
        Persona p = new Persona(id, nombre, correo, cargo, sueldo);
        return getServicio().crear(p);
    }

    public static String actualizar(int id, String nombre, String correo, String cargo, Double sueldo) throws Exception {
        Persona p = new Persona(id, nombre, correo, cargo, sueldo);
        return getServicio().actualizar(p);
    }

    public static String eliminar(int id) throws Exception {
        return getServicio().eliminar(id);
    }
}