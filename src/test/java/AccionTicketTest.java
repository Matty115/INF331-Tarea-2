import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.AccionTicket;

public class AccionTicketTest {

    @Test
    //  Abrir un ticket correctamente
    public void abrirTicketSuccess() {
        AccionTicket accion = new AccionTicket();
        boolean resultado = accion.abrirTicket(
            new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now())
        );
        assertTrue(resultado);
    }

    @Test
    //  Intentar abrir un ticket con una patente ya registrada
    public void abrirTicketFail() {
        AccionTicket accion = new AccionTicket();
        accion.abrirTicket(
            new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now())
        );
        boolean resultado = accion.abrirTicket(
            new models.Ticket(2, "ABC123", "MOTO", java.time.LocalDateTime.now())
        );
        assertTrue(!resultado);
    }

    @Test
    //  Intentar abrir un ticket con una patente ya registrada
    public void cerrarTicketSuccess() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket);
        boolean resultado = accion.cerrarTicket(ticket);
        assertTrue(resultado);
    }

    @Test
    //  Intentar cerrar un ticket que no existe
    public void cerrarTicketFail() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        boolean resultado = accion.cerrarTicket(ticket);
        assertTrue(!resultado);
    }

    @Test
    //  Consultar por un ticket abierto existente
    public void getTicketAbiertoSuccess() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket);
        models.Ticket resultado = accion.getTicket(1, true);
        assertEquals(ticket, resultado);
    }

    @Test
    //  Consultar por un ticket abierto inexistente
    public void getTicketAbiertoFail() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket);
        models.Ticket resultado = accion.getTicket(2, true);
        assertNull(resultado);
    }

    @Test
    //  Consultar por un ticket cerrado existente
    public void getTicketCerradoSuccess() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket);
        accion.cerrarTicket(ticket);
        models.Ticket resultado = accion.getTicket(1, false);
        assertEquals(ticket, resultado);
    }

    @Test
    //  Consultar por un ticket cerrado inexistente
    public void getTicketCerradoFail() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket);
        accion.cerrarTicket(ticket);
        models.Ticket resultado = accion.getTicket(2, false);
        assertNull(resultado);
    }

    @Test
    //  Verificar que la lista de tickets abiertos se actualiza correctamente luego de cerrar un ticket
    public void getAbiertos() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket1 = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        models.Ticket ticket2 = new models.Ticket(2, "DEF456", "MOTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket1);
        accion.abrirTicket(ticket2);
        assertEquals(2, accion.getAbiertos().size());
        accion.cerrarTicket(ticket1);
        assertEquals(1, accion.getAbiertos().size());
    }

    @Test
    //  Verificar que la lista de tickets cerrados se actualiza correctamente luego de cerrar un ticket
    public void getCerrados() {
        AccionTicket accion = new AccionTicket();
        models.Ticket ticket1 = new models.Ticket(1, "ABC123", "AUTO", java.time.LocalDateTime.now());
        models.Ticket ticket2 = new models.Ticket(2, "DEF456", "MOTO", java.time.LocalDateTime.now());
        accion.abrirTicket(ticket1);
        accion.abrirTicket(ticket2);
        assertEquals(0, accion.getCerrados().size());
        accion.cerrarTicket(ticket1);
        assertEquals(1, accion.getCerrados().size());
    }
}