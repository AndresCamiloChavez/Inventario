
package acces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Producto;
import utils.MySQL;

/**
 * clase que nos permite realizar todas las constulas de la DB hacia tabla Producto
 */
public class ProductoDAO {
    private Connection conexion;
    /**
     *Metodo que permite traer todos los datos de la tabla de producto con todos sus valores
     *@return lista de Producto con todas las caracteristicas de la tabla producto
     */
    public ArrayList<Producto> obtenerProductos(){
        ArrayList<Producto> productos = new ArrayList();
        try{
            conexion = MySQL.crearConexion();
            if( conexion != null){
                String query = "SELECT producto.idProducto, producto.nombre, producto.precio FROM producto;";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                
                while(result.next()){
                    Producto producto = new Producto(result.getInt(1), result.getString(2), result.getFloat(3));
                    productos.add(producto);
                }
            }
            
        }catch(Exception e){
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return productos;
    }
    /**
     * Metodo que inserta valores hacia la tabla de Producto el codigo no se envia ya que es auto_Increment en la BD
     * @param Producto con los valores de nombre y precio
     * @return void
     */
    public void insertarProducto(Producto producto){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "INSERT INTO producto (nombre,precio) VALUES (?, ?);";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1,producto.getNombre());
                statement.setFloat(2,producto.getPrecio());
                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
           

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
    }
    /**
     * Metodo que nos permite eliminar un producto con cierto id 
     * @param idProducto 
     */
     public void eliminarProducto(int idProducto){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                System.out.println("id" + idProducto);
                String query = "DELETE FROM producto WHERE idProducto ="+idProducto+";";
                PreparedStatement statement = conexion.prepareStatement(query);
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue eliminado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null,"ERROR,El producto no puede ser eliminado porque hay registros existentes en otras tablas", "Estado", JOptionPane.ERROR_MESSAGE);
        } 
        catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES valroes"+ e.getClass());
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue eliminado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
    }
     /**
      * Meotodo que nos permite acutalizar un producto realiza la consulta Update y conexión
      * @param producto 
      */
     public void actualizarProducto(Producto producto){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "UPDATE producto SET nombre = ?, precio = ? WHERE idProducto = ?;";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1,producto.getNombre());  
                statement.setFloat(2,producto.getPrecio());                
                statement.setFloat(3,producto.getIdProducto()); 
 
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue Actualizado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES valores");
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue actualizado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
    }
     /**
      * Busca la producto por id y retorna el nombre de la producto
      * @param idProducto
      * @return 
      */
      public String buscarProducto(int idProducto){
         boolean existe = false;
           try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT producto.nombre FROM producto WHERE idProducto = "+idProducto+";";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    return result.getString(1);
                }
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de valores de existir --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return "no existe";
     }
      /**
       * Metodo que pemite verificar si el producto existe y de ser asi retorna true
       * @param idProducto
       * @return 
       */
      public boolean existeProducto(int idProducto){
         boolean existe = false;
           try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT idProducto FROM producto WHERE idProducto = "+idProducto+";";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    existe = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de valores de existir --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return existe;
     }
    
}
