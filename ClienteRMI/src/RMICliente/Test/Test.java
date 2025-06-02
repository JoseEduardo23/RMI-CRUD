package RMICliente.Test;


import RMICliente.Cliente.Cliente;

import java.util.Scanner;

public class Test {
    public static void main (String[] args) throws Exception{
        String op = null;
        int id = 1;
        Scanner scanner=null;
        do{
            scanner = new Scanner(System.in);
            System.out.println("Buscar datos del empleado");
            id = scanner.nextInt();
            System.out.println(Cliente.consultar(id));
            System.out.println("Desea salir? Si(s) / No(n)");
            op = scanner.next();
        } while (op.equals("si") || op.equals("s"));
        scanner.close();
    }
}
