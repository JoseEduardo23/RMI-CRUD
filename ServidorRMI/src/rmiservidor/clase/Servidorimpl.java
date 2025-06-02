package rmiservidor.clase;

import rmiservidor.ConexionBDD.ConexionBDD;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Servidorimpl extends UnicastRemoteObject implements Servidor {
    public Servidorimpl() throws Exception {
        super();
        inicializarBD();
    }

    private void inicializarBD() {
        try (Connection con = ConexionBDD.getConnection()) {
            String sql = """
                    CREATE TABLE IF NOT EXISTS persona(
                    clave INTEGER PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    correo TEXT NOT NULL,
                    cargo TEXT NOT NULL,
                    sueldo REAL NOT NULL
                    );
                    """;
            con.createStatement().execute(sql);
            System.out.println("Base de datos SQLite inicializada");
        } catch (Exception e) {
            System.out.println("Error con la generacion de la tabla" + e.getMessage());
        }
    }

    @Override
    public String consultar(int id) throws Exception {
        try (Connection con = ConexionBDD.getConnection()) {
            String sql = "SELECT * FROM persona WHERE clave = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "Nombre: " + rs.getString("nombre") + '\n'
                        + "Correo: " + rs.getString("correo") + '\n'
                        + "Cargo: " + rs.getString("cargo") + '\n'
                        + "Sueldo: " + rs.getString("sueldo") + '\n';
            } else {
                return "Empleado con ID" + id + "no encontrado";
            }
        } catch (Exception e) {
            return "Error al consultar: " + e.getMessage();
        }
    }

    @Override
    public String crear(Persona p) throws Exception {
        try (Connection con = ConexionBDD.getConnection()) {
            String sql = "INSERT INTO persona (clave, nombre, correo, cargo, sueldo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getClave());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getCorreo());
            stmt.setString(4, p.getCargo());
            stmt.setDouble(5, p.getSueldo());
            stmt.executeUpdate();
            return "Empleado creado exitosamente";
        } catch (Exception e) {
            return "Error al crear: " + e.getMessage();
        }
    }

    @Override
    public String actualizar(Persona p) throws Exception {
        try (Connection con = ConexionBDD.getConnection()) {
            String sql = "UPDATE persona SET nombre = ?, correo = ?, cargo = ?, sueldo = ? WHERE clave = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCorreo());
            stmt.setString(3, p.getCargo());
            stmt.setDouble(4, p.getSueldo());
            stmt.setInt(5, p.getClave());
            int rows = stmt.executeUpdate();
            return rows > 0 ? "Empleado actualizado correctamente" : "Empleado no encontrado para actualizar";
        } catch (Exception e) {
            return "Error al actualizar: " + e.getMessage();
        }
    }

    @Override
    public String eliminar(int id) throws Exception {
        try (Connection con = ConexionBDD.getConnection()) {
            String sql = "DELETE FROM persona WHERE clave = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            return rows > 0 ? "Empleado eliminado correctamente" : "Empleado no encontrado";
        } catch (Exception e) {
            return "Error al eliminar: " + e.getMessage();
        }
    }
}