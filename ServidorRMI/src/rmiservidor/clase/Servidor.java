package rmiservidor.clase;

import java.rmi.Remote;

public interface Servidor extends Remote {
    // consultar el id empleado
    public String consultar(int id) throws Exception;
    public String crear(Persona p) throws Exception;
    public String actualizar(Persona p) throws Exception;
    public String eliminar(int id) throws Exception;

}
