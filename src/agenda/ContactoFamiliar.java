package agenda;

public class ContactoFamiliar extends Contacto{
    private String parentesco;

    public ContactoFamiliar(Persona laPersona, String telefono, int cedula, String parentesco) {
        super(laPersona, telefono, cedula);
        this.parentesco = parentesco;
    }

    public ContactoFamiliar(String nombre, int edad, int genero, String telefono, int cedula, String parentesco) {
        super(nombre, edad, genero, telefono, cedula);
        this.parentesco = parentesco;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
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
        return "ContactoFamiliar{" +
                "parentesco='" + parentesco + '\'' +
                "} " + super.toString();
    }
}
