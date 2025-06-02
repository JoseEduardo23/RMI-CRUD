package RMICliente.Test;

import RMICliente.Cliente.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIRmi extends JFrame {
    private JTextField ID;
    private JButton Busqueda;
    private JLabel text;
    private JPanel panel1;
    private JTextField Nombre;
    private JTextField Correo;
    private JTextField Cargo;
    private JTextField Sueldo;
    private JButton Crear;
    private JButton Actualizar;
    private JButton Eliminar;
    private JLabel ntext;
    private JLabel ctext;
    private JLabel catext;
    private JLabel stext;

    public GUIRmi() {
        setTitle("Búsqueda de empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);

        add(panel1);
        panel1.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));



        Busqueda.addActionListener((ActionEvent e) -> {
            String IDcliente = ID.getText().trim();

            if (IDcliente.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(IDcliente);
                String mensaje = Cliente.consultar(id);
                JOptionPane.showMessageDialog(null, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "El ID debe ser un número entero", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al conectar con el servidor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        Crear.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(ID.getText().trim());
                String nombre = Nombre.getText().trim();
                String correo = Correo.getText().trim();
                String cargo = Cargo.getText().trim();
                double sueldo = Double.parseDouble(Sueldo.getText().trim());

                String respuesta = Cliente.crear(clave, nombre, correo, cargo, sueldo);
                JOptionPane.showMessageDialog(null, respuesta);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al crear: " + ex.getMessage());
            }
        });

        Actualizar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(ID.getText().trim());
                String nombre = Nombre.getText().trim();
                String correo = Correo.getText().trim();
                String cargo = Cargo.getText().trim();
                double sueldo = Double.parseDouble(Sueldo.getText().trim());

                String respuesta = Cliente.actualizar(clave, nombre, correo, cargo, sueldo);
                JOptionPane.showMessageDialog(null, respuesta);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar: " + ex.getMessage());
            }
        });

        Eliminar.addActionListener(e -> {
            try {
                int clave = Integer.parseInt(ID.getText().trim());
                String respuesta = Cliente.eliminar(clave);
                JOptionPane.showMessageDialog(null, respuesta);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUIRmi().setVisible(true));
    }
}