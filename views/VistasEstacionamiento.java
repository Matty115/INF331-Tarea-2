package views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VistasEstacionamiento {

    private final Scanner sc = new Scanner(System.in);

    public int menu() {

        int opcion = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("=== Menú principal ===");
                System.out.println("1. Registrar entrada");
                System.out.println("2. Registrar salida");
                System.out.println("3. Lista de tickets abiertos");
                System.out.println("4. Lista de tickets cerrados");
                System.out.println("5. Recaudación del día");
                System.out.println("6. Salir");
                System.out.println("======================");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                if (opcion < 1 || opcion > 6) System.out.println("Opción no válida. Intente de nuevo.");
                else valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
            sc.nextLine(); // Limpia el buffer
            System.out.println();
        }
        
        return opcion;
    }

    public String solicitarPatente() {
        String patente = "";
        boolean valid = false;

        while (!valid) {
            System.out.println("Formato esperado: LLDDDD o LLLLDD, L es letra, D es dígito.");
            System.out.println("Ejemplos: JD3289 o FEUW28.");
            System.out.println("Ingrese 0 para anular la operación.");
            System.out.print("Ingrese patente del vehículo entrante: ");
            patente = sc.nextLine().toUpperCase();
            if (patente.matches("^[A-Z]{4}\\d{2}$") || 
                patente.matches("^[A-Z]{2}\\d{4}$") || patente.equals("0")) valid = true;
            else System.out.println("Opción no válida. Intente de nuevo.");
            System.out.println();
        }

        return patente;
    }

    public String solicitarTipo() {
        String tipo = "";
        boolean valid = false;

        while (!valid) {
            System.out.print("Ingrese tipo de vehículo (auto/moto/camioneta): ");
            tipo = sc.nextLine().toUpperCase();
            if (tipo.equals("AUTO") || tipo.equals("MOTO") || 
                tipo.equals("CAMIONETA") || tipo.equals("0")) valid = true;
            else System.out.println("Opción no válida. Intente de nuevo.");
            System.out.println();
        }

        return tipo;
    }

    public int solicitarID() {
        int id = 0;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.print("Ingrese ID del ticket del vehículo: ");
                id = sc.nextInt();
                if (id > 0) valid = true;
                else System.out.println("Opción no válida. Intente de nuevo.");
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
            sc.nextLine();
            System.out.println();
        }

        return id;
    }
}
