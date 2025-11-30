package controllers;

import views.VistasEstacionamiento;
import models.*;

import java.time.LocalDateTime;
import java.time.Duration;

public class ControladorEstacionamiento {
    private int nIDS = 0;
    private final VistasEstacionamiento vista;
    private final AccionTicket accion;
    
    public ControladorEstacionamiento(VistasEstacionamiento vista, AccionTicket accion) {
        this.vista = vista;
        this.accion = accion;
    }

    public int calcularMonto(LocalDateTime entrada, LocalDateTime salida, String tipo) {
        int monto;
        long montoI = 0;
        long duracion = Duration.between(entrada, salida).toMinutes();
        long bloques = (duracion - 1) / 30 + 1;

        switch (tipo) {
            case "AUTO":
                montoI = 800 * bloques;
                break;
            case "MOTO":
                montoI = 500 * bloques;
            
            case "CAMIONETA":
                montoI = 1000 * bloques;
        }

        if (montoI > 15000) monto = 15000;
        else monto = (int) montoI;

        if (entrada.getDayOfWeek().getValue() >= 6) monto = 9 * monto / 10;

        return monto;
    }

    public void init() {
        int opcion = 0;
        do {
            opcion = vista.menu();
            switch (opcion) {
                case 1: {
                    String patente = vista.solicitarPatente();
                    if (patente.equals("0")) break;
                    String tipoVehiculo = vista.solicitarTipo();
                    if (tipoVehiculo.equals("0")) break;
                    this.nIDS++;
                    LocalDateTime fechaHoraEntrada = LocalDateTime.now();
                    Ticket ticket = new Ticket(nIDS, patente, tipoVehiculo, fechaHoraEntrada);
                    if (accion.abrirTicket(ticket)) System.out.println("Ticket creado para el vehículo " + patente + ".\n");
                    else System.out.println("No se pudo crear ticket porque no se ha registrado la selida del vehículo " + patente + ".\n");
                    break;
                }

                case 2: {
                    int id;
                    Ticket ticket;
                    do {
                        id = vista.solicitarID();
                        ticket = accion.getTicket(id);
                    } while (ticket == null && id != 0);
                    if (id == 0) break;
                    LocalDateTime fechaHoraSalida = LocalDateTime.now();
                    LocalDateTime fechaHoraEntrada = ticket.getFechaHoraEntrada();
                    String tipoVehiculo = ticket.getTipoVehiculo();
                    int monto = calcularMonto(fechaHoraEntrada, fechaHoraSalida, tipoVehiculo);
                    ticket.setSalida(fechaHoraSalida, monto);
                    accion.cerrarTicket(ticket);

                }
                default:
                    break;
            }
        } while (opcion != 6);
    }
    
}
