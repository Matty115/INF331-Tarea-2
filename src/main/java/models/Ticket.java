package models;

import java.time.LocalDateTime;

public class Ticket {
    private int idTicket;
    private String patente;
    private String tipoVehiculo;
    private LocalDateTime fechaHoraEntrada;
    private LocalDateTime fechaHoraSalida;
    private int montoCobrado;

    public Ticket(int idTicket, String patente, String tipoVehiculo, LocalDateTime fechaHoraEntrada) {
        this.idTicket = idTicket;
        this.patente = patente;
        this.tipoVehiculo = tipoVehiculo;
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public int getID() {
        return this.idTicket;
    }

    public String getPatente() {
        return this.patente;
    }

    public String getTipoVehiculo() {
        return this.tipoVehiculo;
    }

    public LocalDateTime getFechaHoraEntrada() {
        return this.fechaHoraEntrada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return this.fechaHoraSalida;
    }

    public int getMonto() {
        return this.montoCobrado;
    }

    public void setSalida(LocalDateTime fechaHoraSalida, int montoCobrado) {
        this.fechaHoraSalida = fechaHoraSalida;
        this.montoCobrado = montoCobrado;
    }
}
