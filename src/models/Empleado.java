package models;
/**
 * @autor Yurley
 * Clase Empleado  nos sirve para crear objetos tipo Empleado
 *  @param idBodega valor unico que nos permite identificar al empleado de la Bodega
 *  @param idEmpleado valor unico que nos permite identificar al empleado 
 *  @param nombre permite darle nombre al Empleado
 *  @param eda permite asignar edad al Empleado
 */
public class Empleado {
  private int idBodega;
  private int idEmpleado;
  private String nombre;
  private int edad;

    public Empleado( int idEmpleado, int idBodega, String nombre, int edad) {
        this.idBodega = idBodega;
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.edad = edad;
    }

    public Empleado(int idBodega, String nombre, int edad) {
        this.idBodega = idBodega;
        this.nombre = nombre;
        this.edad = edad;
    }
    

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
      /**
     *Metodo que sobreescribimos para mostrar información del objeto 
     * @return String de Bodega con sus caracteristicas
     */
  @Override
    public String toString(){
        return "Empleado "+this.idBodega+" "+this.idEmpleado+" "+this.nombre+" "+this.edad;
    }
  
}
