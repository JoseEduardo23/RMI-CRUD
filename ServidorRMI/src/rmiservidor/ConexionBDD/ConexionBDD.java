package rmiservidor.ConexionBDD;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBDD {
    public static Connection getConnection() throws Exception {

        File directorio = new File("basedatos");
        if (!directorio.exists()){
            directorio.mkdirs();
        }

        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:basedatos/empleados.db");
    }
}