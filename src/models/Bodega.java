
package models;
/**
 * @autor Yurley
 * Clase Bodega nos sirve para crear objetos tipo Bodega
 *  @param idBodega valor unico que nos permite identificar la bodega
 *  @param nombre permite darle un nombre a la bodega
 *  @param direccion permite darle dirección a la bodega

 */
public class Bodega {
    private int idBodega;
    private String nombre;
    private String direccion;

    public Bodega(int idBodega, String nombre, String direccion) {
        this.idBodega = idBodega;
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Bodega(String  nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     *Metodo que sobreescribimos para mostrar información del objeto 
     * @return String de Bodega con sus caracteristicas
     */
    @Override
    public String toString(){
        return "Bodega: "+this.idBodega+" "+this.nombre+" "+this.direccion;
    }
}
