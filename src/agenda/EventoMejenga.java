package agenda;

import java.time.LocalDate;
import java.util.LinkedList;

public class EventoMejenga extends  Evento{

    private LinkedList<String> convocados;
    private LocalDate fecha;
    private String lugar;

    public EventoMejenga(LocalDate fecha, String descripcion, LinkedList<String> convocados, LocalDate fecha1, String lugar) {
        super(fecha, descripcion);
        this.convocados = convocados;
        this.fecha = fecha1;
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
}
