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
}
