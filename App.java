import controllers.ControladorEstacionamiento;
import models.AccionTicket;
import views.VistasEstacionamiento;

public class App {
    public static void main(String[] args) {
        VistasEstacionamiento vista = new VistasEstacionamiento();
        AccionTicket modelo = new AccionTicket();
        ControladorEstacionamiento controlador = new ControladorEstacionamiento(vista, modelo);
        controlador.init();
    }
}