package agenda;

public class ContactoNegocios extends Contacto{
    private String correoElectronico;

    public ContactoNegocios(Persona laPersona, String telefono, int cedula, String correoElectronico) {
        super(laPersona, telefono, cedula);
        this.correoElectronico = correoElectronico;
    }

    public ContactoNegocios(String nombre, int edad, int genero, String telefono, int cedula, String correoElectronico) {
        super(nombre, edad, genero, telefono, cedula);
        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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
        return "ContactoNegocios{" +
                "correoElectronico='" + correoElectronico + '\'' +
                "} " + super.toString();
    }
}
