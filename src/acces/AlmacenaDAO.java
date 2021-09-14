
package acces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Almacena;
import utils.MySQL;

/**
 * clase que nos permite realizar a todas las constulas de la DB en la tabla Almacena
 */
public class AlmacenaDAO {
    private Connection conexion;    
    /**
     *Metodo que permite traer todos los datos de la tabla de almacena con todos sus valores
     *@return lista de objetos de tipo almacena con todas las caracteristicas de la tabla Almacena
     */
    public ArrayList<Almacena> obtenerAlmacenamiento() {
        ArrayList<Almacena> almacenamiento = new ArrayList();
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT almacena.idBodega, almacena.idProducto, almacena.cantidad FROM almacena;";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    Almacena almacena = new Almacena(result.getInt(1),result.getInt(2), result.getInt(3));
                    almacenamiento.add(almacena);
                }
            }

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return almacenamiento;
    }
    
    /**
     * Meotodo que conecta y genera la consulta que permite traer informacion hacia la base de datos
     * con un parametro que permite ordenar segun el contenido
     * @param propiedad
     * @return 
     */
    public ArrayList<Almacena> obtenerAlmacenamientoBodegas(String propiedad) {
        ArrayList<Almacena> almacenamiento = new ArrayList();
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "SELECT almacena.idBodega, almacena.idProducto, almacena.cantidad FROM almacena ORDER BY("+propiedad+");";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while (result.next()) {
                    Almacena almacena = new Almacena(result.getInt(1),result.getInt(2), result.getInt(3));
                    almacenamiento.add(almacena);
                }
            }

        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return almacenamiento;
    }
    /**
     * Metodo que inserta valores hacia la tabla de Almacena en la BD
     * @param Almacena con los valores de idBodega, idProducto y cantidad
     * @return void
     */
    public boolean insertarAlmacenamiento(Almacena almacena){
        boolean insertar = false;
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "INSERT INTO almacena(idBodega,idProducto, cantidad) VALUES (?, ?, ?);";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,almacena.getIdBodega());
                statement.setInt(2,almacena.getIdProducto());
                statement.setInt(3,almacena.getCantidad());

                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
                insertar = true;
            }
        }catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return insertar;
    }
    /**
     * Metodo que ejecuta la consulta de Delete y realiza conexion frente a la base de datos
     * @param idBodega
     * @param idProducto 
     */
    public void eliminarBodega(int idBodega, int idProducto){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "DELETE FROM almacena WHERE idBodega = ? AND idProducto = ?;";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,idBodega);      
                statement.setInt(2,idProducto);
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
     * Metodo que permite realizar la consulta de Update y la conexio hacia la base de datos
     * @param almacena
     * @return 
     */
    public boolean actualizarProducto(Almacena almacena){
        boolean actualizar = false;
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "UPDATE almacena SET idBodega = ?, idProducto = ?, cantidad = ? WHERE idProducto = ? AND idBodega = ?;";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,almacena.getIdBodega());  
                statement.setInt(2,almacena.getIdProducto());  
                statement.setInt(3,almacena.getCantidad());  
                statement.setInt(4,almacena.getIdProducto());
                statement.setInt(5,almacena.getIdBodega());  
                int rowsInserted = statement.executeUpdate();
                System.out.println(rowsInserted);

                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue Actualizado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
                actualizar = true;
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES valores");
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue actualizado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
        return actualizar;
    }
}
