package agenda;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Agenda {
    private LinkedList<ElementoAgenda> listaElementosAgenda;

    public Agenda() {
        this.listaElementosAgenda = new LinkedList<>();
    }

    public void agregarEvento(Evento e){
        this.listaElementosAgenda.add(e);
    }

    public void agregarContacto(Contacto c){
        this.listaElementosAgenda.add(c);
    }

    public Contacto buscarContacto(int cedula){
        for (ElementoAgenda e : this.listaElementosAgenda){
            if (e instanceof Contacto) {
                if (((Contacto) e).getCedula() == cedula)
                    return ((Contacto) e);
            }
        }
        return null;
    }

    public void eliminarContacto(int cedula){
        Contacto c = this.buscarContacto(cedula);
        if (c!= null)
            this.listaElementosAgenda.removeFirstOccurrence(c);
        else
            System.out.println("La cedula a borrar no pertenece a ningún contacto");
    }

    public void modificarContacto(int cedula, String nombre, int edad,
                                  int genero, String telefono) {

        Contacto contacto = buscarContacto(cedula);

        if (contacto != null) {
            contacto.getLaPersona().setNombre(nombre);
            contacto.getLaPersona().setEdad(edad);
            contacto.getLaPersona().setGenero(genero);
            contacto.setTelefono(telefono);
        }
        else
            System.out.println("ERROR: La cédula no pertenece a ningún contacto en la agenda!");
    }

    public void modificarContactoFamiliar(int cedula, String nombre, int edad,
                                  int genero, String telefono,
                                  String parentesco) {

        Contacto contacto = buscarContacto(cedula);

        if (contacto != null) {
            if (contacto instanceof ContactoFamiliar) {
                ContactoFamiliar familiar = (ContactoFamiliar) contacto;

                familiar.getLaPersona().setNombre(nombre);
                familiar.getLaPersona().setEdad(edad);
                familiar.getLaPersona().setGenero(genero);
                familiar.setTelefono(telefono);
                familiar.setParentesco(parentesco);
            }
            else
                System.out.println("ERROR: El Contacto que se desea modificar no es un Contacto Familiar ");
        }
        else
            System.out.println("ERROR: La cédula no pertenece a ningún contacto en la agenda!");
    }

    public void modificarContactoNegocios(int cedula, String nombre, int edad,
                                          int genero, String telefono,
                                          String correoElectronico) {

        Contacto contacto = buscarContacto(cedula);

        if (contacto != null) {
            if (contacto instanceof ContactoNegocios) {
                ContactoNegocios negocios = (ContactoNegocios) contacto;

                negocios.getLaPersona().setNombre(nombre);
                negocios.getLaPersona().setEdad(edad);
                negocios.getLaPersona().setGenero(genero);
                negocios.setTelefono(telefono);
                negocios.setCorreoElectronico(correoElectronico);
            }
            else
                System.out.println("ERROR: El Contacto que se desea modificar no es un Contacto de Negocios ");
        }
        else
            System.out.println("ERROR: La cédula no pertenece a ningún contacto en la agenda!");
    }

    @Override
    public String toString() {
        return this.listaElementosAgenda.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

}
