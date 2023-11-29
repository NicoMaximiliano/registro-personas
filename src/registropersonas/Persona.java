package registropersonas;


public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;

    public Persona(String nombre, String apellido, String dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    public String[] datosPersona() {
        String[] datos = {nombre, apellido, dni, fechaNacimiento};
        return datos;
    }


}
