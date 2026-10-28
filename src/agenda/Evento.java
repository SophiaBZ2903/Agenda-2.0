package agenda;

import java.time.LocalDate;

public abstract class Evento extends ElementoAgenda{
    private LocalDate fecha;
    private String descripcion;

    public Evento(LocalDate fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }


    @Override
    public String toString() {
        return "Evento{" +
                "fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
