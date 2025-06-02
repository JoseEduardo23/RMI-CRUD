package rmiservidor.clase;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private int clave;
    @Getter @Setter private String nombre;
    @Getter @Setter private String correo;
    @Getter @Setter private String cargo;
    @Getter @Setter private double sueldo;

    public Persona(int clave, String nombre, String correo, String cargo, double sueldo) {
        this.clave = clave;
        this.nombre = nombre;
        this.correo = correo;
        this.cargo = cargo;
        this.sueldo = sueldo;
    }
}