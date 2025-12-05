package models;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.Duration;

public class AccionTicket {
    private ArrayList<Ticket> abiertos = new ArrayList<>();
    private ArrayList<Ticket> cerrados = new ArrayList<>();
    private int nIDS = 0;

    public int nuevaID() {
        this.nIDS++;
        return this.nIDS;
    }

    public int nuevaIDFail() {
        this.nIDS--;
        return this.nIDS;
    }

    public boolean abrirTicket(Ticket nuevo) {

        for (Ticket ticket : this.abiertos) {
            if (ticket.getPatente().equals(nuevo.getPatente())) return false;
        }
        this.abiertos.add(nuevo);
        return true;
    }

    public boolean cerrarTicket(Ticket ticket) {
        if (!this.abiertos.remove(ticket)) return false;
        return this.cerrados.add(ticket);
    }

    public Ticket getTicket(int id, boolean abierto) {
        ArrayList<Ticket> listado;
        if (abierto) listado = this.abiertos;
        else listado = this.cerrados;
        for (Ticket ticket : listado) {
            if (ticket.getID() == id) return ticket;
        }
        return null;
    }

    public ArrayList<Ticket> getAbiertos() {
        return abiertos;
    }

    public ArrayList<Ticket> getCerrados() {
        return cerrados;
    }

    public int calcularMonto(LocalDateTime entrada, LocalDateTime salida, String tipo) {
        int monto;
        long montoI = 0;
        long duracion = Duration.between(entrada, salida).toMinutes();
        long bloques = (duracion - 1) / 30 + 1;

        if (duracion == 0) return 0;

        switch (tipo) {
            case "AUTO":
                montoI = 800 * bloques;
                break;
            case "MOTO":
                montoI = 500 * bloques;
                break;
            case "CAMIONETA":
                montoI = 1000 * bloques;
                break;
            default:
                return 0;
        }

        if (montoI > 15000) monto = 15000;
        else monto = (int) montoI;

        if (entrada.getDayOfWeek().getValue() >= 6) monto = 9 * monto / 10;

        return monto;
    }

    public int calcularDia(ArrayList<Ticket> cerrados) {
        if (cerrados.size() == 0) return 0;
        int total = 0;
        for (Ticket ticket : cerrados) {
            if (ticket.getFechaHoraSalida().toLocalDate().equals(LocalDate.now()))
                total += ticket.getMonto();
        }
        return total;
    }
}
