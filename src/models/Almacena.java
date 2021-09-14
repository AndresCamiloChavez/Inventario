
package models;
/**
 * @autor Yurley
 * Clase Almacena nos sirve para crear objetos tipo Almacena
 *  @param idProducto valor entero que permite identificar el prodcuto
 *  @param idBodega valor entero que permite identificar la bodega
 *  @param cantidad permite identificar la cantidad de productos en una bodega

 */
public class Almacena {
    private int idBodega;
    private int idProducto;
    private int cantidad;

    public Almacena(int idBodega, int idProducto, int cantidad) {
        this.idBodega = idBodega;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
      /**
     * Metodo que sobreescribimos para mostrar información del objeto 
     * @return String de Almacena con sus caracteristicas
     */
    @Override
    public String toString(){
        return "Almacena "+this.idBodega+" "+this.idProducto+" "+this.cantidad;
    }
}
