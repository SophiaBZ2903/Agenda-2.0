package agenda;

import java.time.LocalDate;

public class EventoReunion extends Evento{
    private int cantidadPersonas;

    public EventoReunion(LocalDate fecha, String descripcion, int cantidadPersonas) {
        super(fecha, descripcion);
        this.cantidadPersonas = cantidadPersonas;
    }

    @Override
    public String convertToXml() {
        return "";
    }

    @Override
    public String convertToJSON() {
        return "";
    }

    @Override
    public String toString() {
        return "EventoReunion{" +
                "cantidadPersonas=" + cantidadPersonas +
                "} " + super.toString();
    }
}
