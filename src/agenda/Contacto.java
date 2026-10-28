package agenda;

public abstract class Contacto extends ElementoAgenda{
    private Persona laPersona;
    private String telefono;
    private int cedula;

    public Contacto(Persona laPersona, String telefono, int cedula) {
        this.laPersona = laPersona;
        this.telefono = telefono;
        this.cedula = cedula;
    }

    public Contacto(String nombre, int edad, int genero, String telefono, int cedula) {
        this.laPersona = new Persona();
        this.laPersona.setNombre(nombre);
        this.laPersona.setEdad(edad);
        this.laPersona.setGenero(genero);
        //this.laPersona = new Persona(nombre, edad, genero);
        this.telefono = telefono;
        this.cedula = cedula;
    }

    public Persona getLaPersona() {
        return laPersona;
    }

    public void setLaPersona(Persona laPersona) {
        this.laPersona = laPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "laPersona=" + laPersona +
                ", telefono='" + telefono + '\'' +
                ", cedula=" + cedula +
                '}';
    }
}
