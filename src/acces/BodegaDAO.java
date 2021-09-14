package acces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Bodega;
import utils.MySQL;

/**
 * clase que nos permite realizar todas las constulas de la DB hacia tabla Bodega
 */
public class BodegaDAO {

    private Connection conexion;

    /**
     *Metodo que permite traer todos los datos de la tabla de bodega con todos sus valores
     *@return lista de bodegas con todas las caracteristicas de la tabla bodega
     */
    public ArrayList<Bodega> obtenerBodegas() {
        ArrayList<Bodega> bodegas = new ArrayList();

        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT bodega.idBodega, bodega.nombre, bodega.direccion FROM bodega;";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    Bodega bodega = new Bodega(result.getInt(1),result.getString(2), result.getString(3) );
                    bodegas.add(bodega);
                }
            }

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }

        return bodegas;
    }
    
    /**
     * Metodo que inserta valores hacia la tabla de Bodega el codigo no se envia ya que es auto_Increment en la BD
     * @param Bodega con los valores de nombre y direccion de la bodega
     * @return void
     */
    public void insertarBodega(Bodega bodega) {
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "INSERT INTO bodega (nombre,direccion) VALUES (?, ?);";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1,bodega.getNombre());
                statement.setString(2,bodega.getDireccion());
                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
           

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valroes");
        }finally{
            MySQL.closeConnection(conexion);
        }
    }
    /**
     * metodo que permite eliminar bodegas por medio de el id
     * @param idBodega permite identificar la bodega por medio de el identicador es un entero
     * 
     */
    public void eliminarBodega(int idBodega){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "DELETE FROM bodega WHERE idBodega = ?";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,idBodega);                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue eliminado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
               
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES valroes");
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue eliminado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
    }
    /**
     * Metodo que permite realizar la consulta de Update de bodega y conexion hacia la base datos (Acutaliza la bodega)
     * @param bodega 
     */
     public void actualizarBodega(Bodega bodega){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "UPDATE bodega SET nombre = ?, direccion = ? WHERE idBodega = ?";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setString(1,bodega.getNombre());  
                statement.setString(2,bodega.getDireccion()); 
                statement.setInt(3,bodega.getIdBodega());                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue Actualizado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
                
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES valroes");
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue actualizado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
    }
     /**
      * Metodo que permite filtrar por nombre de bodega y devolver la lista de bodegas que tengan cierta letras iguales
      * @param nombre
      * @return 
      */
     public  ArrayList<Bodega> filtroBodegas(String nombre){
          ArrayList<Bodega> bodegas = new ArrayList();

        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT bodega.idBodega, bodega.nombre, bodega.direccion FROM bodega WHERE nombre LIKE '%"+nombre+"%';";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    Bodega bodega = new Bodega(result.getInt(1),result.getString(2), result.getString(3) );
                    bodegas.add(bodega);
                }
            }

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return bodegas;

     }
     /**
      * Metodo que verifica si la bodega existe por medio de una consulta de ser asi retorna true
      * @param idBodega
      * @return 
      */
     public boolean existeBodega(int idBodega){
         boolean existe = false;
           try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT bodega.idBodega FROM bodega WHERE idBodega = "+idBodega+";";
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
     /**
      * Busca la bodega por id y retorna el nombre de la bodega
      * @param idBodega
      * @return 
      */
     public String buscarBodega(int idBodega){
         boolean existe = false;
           try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT bodega.nombre FROM bodega WHERE idBodega = "+idBodega+";";
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

}   
