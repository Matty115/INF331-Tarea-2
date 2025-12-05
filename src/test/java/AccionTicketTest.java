import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.AccionTicket;
import models.Ticket;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @Test
    public void calcularMontoNormalAuto() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 4, 8, 0),
            LocalDateTime.of(2025, 12, 4, 11, 0),
            "AUTO"
        );
        assertEquals(6 * 800, monto);
    }

    @Test
    public void calcularMontoNormalMoto() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 4, 8, 0),
            LocalDateTime.of(2025, 12, 4, 10, 30),
            "MOTO"
        );
        assertEquals(5 * 500, monto);
    }

    @Test
    public void calcularMontoNormalCamioneta() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 4, 8, 0),
            LocalDateTime.of(2025, 12, 4, 9, 15),
            "CAMIONETA"
        );
        assertEquals(3 * 1000, monto);
    }

    @Test
    public void calcularMonto0Minutos() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 4, 8, 0),
            LocalDateTime.of(2025, 12, 4, 8, 0),
            "AUTO"
        );
        assertEquals(0, monto);
    }

    @Test
    public void calcularMontoMaximo() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 4, 8, 0),
            LocalDateTime.of(2025, 12, 6, 20, 0),
            "AUTO"
        );
        assertEquals(15000, monto);
    }

    @Test
    public void calcularMontoDescuentoFinDeSemana() {
        AccionTicket accion = new AccionTicket();
        int monto = accion.calcularMonto(
            LocalDateTime.of(2025, 12, 6, 8, 0),  // Sábado
            LocalDateTime.of(2025, 12, 6, 12, 0),
            "MOTO"
        );
        assertEquals((8 * 500 * 9) / 10, monto);
    }

    @Test
    public void calcularDiaNormal() {
        AccionTicket accion = new AccionTicket();
        ArrayList<Ticket> cerrados = new ArrayList<>();

        Ticket ticket1 = new Ticket(1, "ABC123", "AUTO", LocalDateTime.of(2025, 12, 4, 8, 0));
        ticket1.setSalida(LocalDateTime.now(), 3200);
        cerrados.add(ticket1);

        Ticket ticket2 = new Ticket(2, "DEF456", "MOTO", LocalDateTime.of(2025, 12, 4, 9, 0));
        ticket2.setSalida(LocalDateTime.now(), 2500);
        cerrados.add(ticket2);

        Ticket ticket3 = new Ticket(3, "GHI789", "CAMIONETA", LocalDateTime.of(2025, 12, 4, 14, 0));
        ticket3.setSalida(LocalDateTime.of(2024, 12, 5, 16, 0), 15000); // otro día
        cerrados.add(ticket3);

        int total = accion.calcularDia(cerrados);
        assertEquals(3200 + 2500, total);
    }

    @Test
    public void calcularDiaSinTickets() {
        AccionTicket accion = new AccionTicket();
        int total = accion.calcularDia(new ArrayList<>());
        assertEquals(0, total);
    }

    @Test
    public void calcularDiaSinTicketsCerrados() {
        AccionTicket accion = new AccionTicket();
        ArrayList<Ticket> cerrados = new ArrayList<>();

        Ticket ticket = new Ticket(1, "GHI789", "CAMIONETA", LocalDateTime.of(2025, 12, 4, 14, 0));
        ticket.setSalida(LocalDateTime.of(2024, 12, 5, 16, 0), 15000); // otro día
        cerrados.add(ticket);

        int total = accion.calcularDia(cerrados);
        assertEquals(0, total);
    }

    @Test
    public void nuevaIDTest() {
        AccionTicket accion = new AccionTicket();
        int id1 = accion.nuevaID();
        int id2 = accion.nuevaID();
        assertEquals(id1 + 1, id2);
    }

    @Test
    public void nuevaIDFailTest() {
        AccionTicket accion = new AccionTicket();
        accion.nuevaID();
        int id1 = accion.nuevaID();
        accion.nuevaIDFail();
        int id2 = accion.nuevaID();
        assertEquals(id1, id2);
    }
}