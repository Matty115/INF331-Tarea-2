package controllers;

import views.VistasEstacionamiento;
import models.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorEstacionamiento {
    private final VistasEstacionamiento vista;
    private final AccionTicket accion;
    
    public ControladorEstacionamiento(VistasEstacionamiento vista, AccionTicket accion) {
        this.vista = vista;
        this.accion = accion;
    }

    public void init() {
        int opcion = 0;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1: {
                    String patente = vista.solicitarPatente();
                    if (patente.equals("0")) {
                        System.out.println("Operación anulada.\n");
                        break;
                    }

                    String tipoVehiculo = vista.solicitarTipo();
                    if (tipoVehiculo.equals("0")) {
                        System.out.println("Operación anulada.\n");
                        break;
                    }

                    int id = accion.nuevaID();
                    LocalDateTime fechaHoraEntrada = LocalDateTime.now();
                    Ticket ticket = new Ticket(id, patente, tipoVehiculo, fechaHoraEntrada);
                    if (accion.abrirTicket(ticket)) System.out.println("Ticket creado para el vehículo " + patente + ".\n");
                    else {
                        System.out.println("No se pudo crear ticket porque no se ha registrado la salida del vehículo " + patente + ".\n");
                        accion.nuevaIDFail();
                    }
                    break;
                }

                case 2: {
                    int id;
                    Ticket ticket;
                    do {
                        ArrayList<Ticket> abiertos = accion.getAbiertos();
                        id = vista.solicitarID(abiertos, true);
                        ticket = accion.getTicket(id, true);
                        if (ticket == null) System.out.println("Opción no válida. Intente de nuevo.\n");
                    } while (ticket == null && id != 0);

                    if (id == 0) {
                        System.out.println("Operación anulada.\n");
                        break;
                    }

                    LocalDateTime fechaHoraSalida = LocalDateTime.now();
                    LocalDateTime fechaHoraEntrada = ticket.getFechaHoraEntrada();
                    String tipoVehiculo = ticket.getTipoVehiculo();
                    int monto = accion.calcularMonto(fechaHoraEntrada, fechaHoraSalida, tipoVehiculo);
                    ticket.setSalida(fechaHoraSalida, monto);

                    if (!accion.cerrarTicket(ticket)) System.out.println("Algo salió mal. Inténtelo de nuevo.\n");
                    else System.out.println("Monto cobrado: $" + ticket.getMonto() + "\n");
                    break;

                }

                case 3: {
                    int id;
                    Ticket ticket;
                    do {
                        ArrayList<Ticket> abiertos = accion.getAbiertos();
                        id = vista.solicitarID(abiertos, false);
                        ticket = accion.getTicket(id, true);
                        if (ticket == null && id != 0) System.out.println("Opción no válida. Intente de nuevo.\n");
                        else if (ticket != null) {
                            id = vista.mostrarDetalles(ticket, true);
                            ticket = null;
                        }
                    } while (ticket == null && id != 0);
                    break;
                }

                case 4: {
                    int id;
                    Ticket ticket;
                    do {
                        ArrayList<Ticket> cerrados = accion.getCerrados();
                        id = vista.solicitarID(cerrados, false);
                        ticket = accion.getTicket(id, false);
                        if (ticket == null && id != 0) System.out.println("Opción no válida. Intente de nuevo.\n");
                        else if (ticket != null) {
                            id = vista.mostrarDetalles(ticket, false);
                            ticket = null;
                        }
                    } while (ticket == null && id != 0);
                    break;
                }

                case 5: {
                    ArrayList<Ticket> cerrados = accion.getCerrados();
                    int total = accion.calcularDia(cerrados);
                    System.out.println("Total recaudado hoy (" + LocalDate.now() + "): $" + total + ".\n");
                }

                default: break;
            }

        } while (opcion != 6);
    }
    
}
