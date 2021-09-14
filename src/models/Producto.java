package models;
/**
 * @autor Yurley
 * Clase Producto nos sirve para crear objetos tipo Producto
 *  @param idProducto valor unico que nos permite identificar el prodcuto
 *  @param nombre permite darle un nombre al producto
 *  @param precio permite darle precio al Producto

 */
public class Producto {
    private int idProducto;
    private String nombre;
    private float precio;

    public Producto(int idProducto, String nombre, float precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    /**
     * Metodo que sobreescribimos para mostrar información del objeto 
     * @return String de Producto con sus caracteristicas
     */
    @Override
    public String toString(){
        return "Producto: "+this.idProducto+" "+this.nombre+" "+this.precio;
    }
}
