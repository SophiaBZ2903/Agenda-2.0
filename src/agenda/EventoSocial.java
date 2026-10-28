package agenda;

import java.time.LocalDate;

public class EventoSocial extends Evento{
    private String lugar;

    public EventoSocial(LocalDate fecha, String descripcion, String lugar) {
        super(fecha, descripcion);
        this.lugar = lugar;
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
        return "EventoSocial{" +
                "lugar='" + lugar + '\'' +
                "} " + super.toString();
    }
}
