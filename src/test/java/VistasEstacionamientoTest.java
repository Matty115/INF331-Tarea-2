import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import views.VistasEstacionamiento;
import models.Ticket;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class VistasEstacionamientoTest {
	@Test
	public void menuOpcionVálida() {
		String input = "1\n"; // usuario escribe 1 y Enter
		InputStream in = new ByteArrayInputStream(input.getBytes());

		VistasEstacionamiento vista = new VistasEstacionamiento(in);

		int opcion = vista.menu();

		assertEquals(1, opcion);
	}

    @Test
    public void menuOpcionEnteroMayorInválidoVálido() {
        String input = "8\n2\n"; // usuario escribe 'a' (inválido), luego 2 (válido)
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        int opcion = vista.menu();

        assertEquals(2, opcion);
    }

    @Test
    public void menuOpcionEnteroMenorInválidoVálido() {
        String input = "0\n4\n"; // usuario escribe 'a' (inválido), luego 2 (válido)
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        int opcion = vista.menu();

        assertEquals(4, opcion);
    }

    @Test
    public void menuOpcionNoEnteroInválidoVálido() {
        String input = "q\n3\n"; // usuario escribe 'a' (inválido), luego 2 (válido)
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        int opcion = vista.menu();

        assertEquals(3, opcion);
    }

    @Test
    public void patenteEntradaCorrecta1() {
        String input = "AB1234\n"; // usuario escribe una patente con forma LLDDDD
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String patente = vista.solicitarPatente();

        assertEquals("AB1234", patente);
    }

    @Test
    public void patenteEntradaCorrecta2() {
        String input = "ABCD11\n"; // usuario escribe una patente válida LLLLDD
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String patente = vista.solicitarPatente();

        assertEquals("ABCD11", patente);
    }

    @Test
    public void patenteAnularOperacion() {
        String input = "0\n"; // usuario escribe 0 para anular
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String patente = vista.solicitarPatente();

        assertEquals("0", patente);
    }

    @Test
    public void patenteEntradaInválidaVálida() {
        String input = "123ABC\nAB1234\n"; // usuario escribe una patente inválida, luego una válida
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String patente = vista.solicitarPatente();

        assertEquals("AB1234", patente);
    }

    @Test
    public void tipoAutoCorrecto() {
        String input = "AUTo\n"; // input resiste mayúsculas y minúsculas
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String tipo = vista.solicitarTipo();

        assertEquals("AUTO", tipo);
    }

    @Test
    public void tipoMotoCorrecto() {
        String input = "MotO\n"; // input resiste mayúsculas y minúsculas
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String tipo = vista.solicitarTipo();

        assertEquals("MOTO", tipo);
    }

    @Test
    public void tipoCamionetaCorrecto() {
        String input = "caMIONETA\n"; // input resiste mayúsculas y minúsculas
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String tipo = vista.solicitarTipo();

        assertEquals("CAMIONETA", tipo);
    }

    @Test
    public void tipoAnularOperacion() {
        String input = "0\n"; // usuario escribe 0 para anular
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String tipo = vista.solicitarTipo();

        assertEquals("0", tipo);
    }

    @Test
    public void tipoEntradaInválidaVálida() {
        String input = "BICICLETA\nAUTO\n"; // usuario escribe una patente inválida, luego una válida
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);

        String tipo = vista.solicitarTipo();

        assertEquals("AUTO", tipo);
    }

    @Test
    public void idListaVacia() {

        VistasEstacionamiento vista = new VistasEstacionamiento();
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        int id = vista.solicitarID(tickets, true);

        assertEquals(0, id);
    }

    @Test
    public void idRetroceder() {
        String input = "0\n"; // usuario escribe 0 para anular
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();

        tickets.add(new Ticket(1, "AB1234", "AUTO", null));
        tickets.add(new Ticket(2, "CD5678", "MOTO", null));
        tickets.add(new Ticket(3, "EF9012", "CAMIONETA", null));

        int id = vista.solicitarID(tickets, false);

        assertEquals(0, id);
    }

    @Test
    public void idAbiertoCorrecto() {
        String input = "2\n"; // usuario escribe 2 y Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(1, "AB1234", "AUTO", null));
        tickets.add(new Ticket(2, "CD5678", "MOTO", null));
        tickets.add(new Ticket(3, "EF9012", "CAMIONETA", null));

        int id = vista.solicitarID(tickets, false);

        assertEquals(2, id);
    }

    @Test
    public void idCerradoCorrecto() {
        String input = "3\n"; // usuario escribe 3 y Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Ticket ticket1 = new Ticket(1, "AB1234", "AUTO", null);
        ticket1.setSalida(java.time.LocalDateTime.now(), 5000);
        tickets.add(ticket1);

        Ticket ticket2 = new Ticket(2, "CD5678", "MOTO", null);
        ticket2.setSalida(java.time.LocalDateTime.now(), 3000);
        tickets.add(ticket2);

        Ticket ticket3 = new Ticket(3, "EF9012", "CAMIONETA", null);
        ticket3.setSalida(java.time.LocalDateTime.now(), 7000);
        tickets.add(ticket3);

        int id = vista.solicitarID(tickets, true);

        assertEquals(3, id);
    }

    @Test
    public void idEntradaInválidaNoEnteraVálida() {
        String input = "k\n2\n"; // usuario escribe -1 (inválido), luego 2 (válido). OJO: no importa si no está en la lista,
                                  // el control de existencia se hace en el controlador
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(4, "GH3456", "AUTO", null));
        tickets.add(new Ticket(5, "IJ7890", "MOTO", null));
        tickets.add(new Ticket(6, "KL1234", "CAMIONETA", null));

        int id = vista.solicitarID(tickets, false);

        assertEquals(2, id);
    }

    @Test
    public void idEntradaInválidaEnteraVálida() {
        String input = "-1\n3\n"; // usuario escribe -1 (inválido), luego 2 (válido). OJO: no importa si no está en la lista,
                                  // el control de existencia se hace en el controlador
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(4, "GH3456", "AUTO", null));
        tickets.add(new Ticket(5, "IJ7890", "MOTO", null));
        tickets.add(new Ticket(6, "KL1234", "CAMIONETA", null));

        int id = vista.solicitarID(tickets, false);

        assertEquals(3, id);
    }

    @Test
    public void detalleTicketMostrarAbierto() {
        String input = "0\n"; // usuario escribe 1 y Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        Ticket ticket = new Ticket(1, "AB1234", "AUTO", null);

        // Solo verificamos que no lance excepciones
        vista.mostrarDetalles(ticket, true);
        assertTrue(true);
    }

    @Test
    public void detalleTicketMostrarCerrado() {
        String input = "0\n"; // usuario escribe 1 y Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());
        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        Ticket ticket = new Ticket(1, "AB1234", "AUTO", null);
        ticket.setSalida(java.time.LocalDateTime.now(), 5000);

        // Solo verificamos que no lance excepciones
        vista.mostrarDetalles(ticket, false);
        assertTrue(true);
    }

    @Test
    public void detalleMenu() {
        String input = "0\n"; // usuario escribe 1 y Enter
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        Ticket ticket = new Ticket(1, "AB1234", "AUTO", null);

        int opcion = vista.mostrarDetalles(ticket, false);

        assertEquals(0, opcion);
    }

    @Test
    public void detalleVolver() {
        String input = "PPPdamkl\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        VistasEstacionamiento vista = new VistasEstacionamiento(in);
        Ticket ticket = new Ticket(1, "AB1234", "AUTO", null);

        int opcion = vista.mostrarDetalles(ticket, true);

        assertEquals(-1, opcion); // Cualquier entrada distinta de 0 retorna -1
    }
    

}