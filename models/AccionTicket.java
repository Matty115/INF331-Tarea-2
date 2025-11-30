package models;

import java.util.ArrayList;

public class AccionTicket {
    private ArrayList<Ticket> abiertos = new ArrayList<>();
    private ArrayList<Ticket> cerrados = new ArrayList<>();

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

    public Ticket getTicket(int id) {
        for (Ticket ticket : this.abiertos) {
            if (ticket.getID() == id) return ticket;
        }
        return null;
    }
}
