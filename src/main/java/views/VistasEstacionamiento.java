package views;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import models.Ticket;

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
                System.out.println("======================\n");
                System.out.print("Seleccione una opción: ");
                opcion = sc.nextInt();
                if (opcion <= 0 || opcion >= 7) System.out.println("Opción no válida. Intente de nuevo.");
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

    public int solicitarID(ArrayList<Ticket> tickets, boolean exit) {
        if (tickets.size() == 0) {
            System.out.println("No hay tickets abiertos de momento.");
            return 0;
        }
        int id = 0;
        boolean valid = false;
        if (exit) System.out.println("=== Cierre de Tickets ===");
        else System.out.println("=== Lista de Tickets ===");
        for (Ticket ticket : tickets) {
            System.out.println("ID: " + ticket.getID() + "  |  Patente: " + ticket.getPatente());
        }
        if (exit) System.out.println("=========================");
        else System.out.println("========================");

        while (!valid) {
            try {
                System.out.println("\nIngrese 0 para anular la operación.");
                if (exit) System.out.print("Ingrese ID del ticket del vehículo saliente: ");
                else System.out.print("Ingrese ID del ticket para revisar su detalle: ");

                id = sc.nextInt();
                if (id >= 0) valid = true;
                else System.out.println("Opción no válida. Intente de nuevo.");
            } catch (InputMismatchException e) {
                System.out.println("Opción no válida. Intente de nuevo.");
            }
            sc.nextLine();
            System.out.println();
        }

        return id;
    }

    public int mostrarDetalles(Ticket ticket, boolean abierto) {
        int op = 0;
        System.out.println("=== Detalle de Ticket ===");
        System.out.println("ID: " + ticket.getID());
        System.out.println("Patente: " + ticket.getPatente());
        System.out.println("Tipo de vehículo: " +ticket.getTipoVehiculo());
        System.out.println("Fecha y hora de acceso: " + ticket.getFechaHoraEntrada());
        if (abierto) System.out.println("Estado: Abierto");
        else {
            System.out.println("Fecha y hora de salida: " + ticket.getFechaHoraSalida());
            System.out.println("Monto cobrado: $" + ticket.getMonto());
            System.out.println("Estado: Cerrado");
        }
        System.out.println("=========================\n");

        try {
            System.out.println("Ingrese 0 para volver al menú.");
            System.out.println("Ingrese cualquier otra tecla si desea volver al listado.");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();
        } catch (InputMismatchException e) {
            op = -1;
        }
        sc.nextLine();
        System.out.println();
        return op;

    }
}
