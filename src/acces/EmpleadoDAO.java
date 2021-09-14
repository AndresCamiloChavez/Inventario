package acces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import models.Empleado;
import utils.MySQL;

/**
 * Clase que nos permite realizar todas las constulas de la DB hacia la tabla Bodega
 */
public class EmpleadoDAO {
    private Connection conexion;
    
    /**
     *Metodo que permite traer todos los datos de la tabla de producto con todos sus valores
     *@return lista de Producto con todas las caracteristicas de la tabla producto
     */
    public ArrayList<Empleado> obtenerEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList();
        try{
            conexion = MySQL.crearConexion();
            if( conexion != null){
                String query = "SELECT empleado.idEmpleado, empleado.idBodega, empleado.nombre, empleado.edad FROM empleado;";
                Statement statement = conexion.createStatement();
                ResultSet result = statement.executeQuery(query);
                while(result.next()){
                    Empleado empleado = new Empleado(result.getInt(1), result.getInt(2), result.getString(3), result.getInt(4));
                    empleados.add(empleado);
                }
            }  
        }catch(Exception e){
            System.out.println("LOG:(ERROR): Al momento de traer valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
        return empleados;
    }
    /**
     * Metodo que inserta valores hacia la tabla de Empleado el codigo del empleado no se envia ya que es auto_Increment en la BD
     * @param Empleado con todos los valores de idBodega para identificar donde trabaja, nombre y su edad
     * @return void
     */
     public void insertarEmpleado(Empleado empleado){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "INSERT INTO empleado (idBodega ,nombre ,edad) VALUES (?, ?, ?);";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,empleado.getIdBodega());
                statement.setString(2,empleado.getNombre());
                statement.setInt(3,empleado.getEdad());
                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de insertar valores --> " + e);
        }finally{
            MySQL.closeConnection(conexion);
        }
    }
      /**
     * metodo que permite eliminar empleados por medio de el id
     * @param idEmpleado permite identificar el Empleado por medio de el identicador es un entero
     * 
     */
     
     public void eliminarEmpleado(int idEmpleado){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "DELETE FROM empleado WHERE idEmpleado = ?";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,idEmpleado);                
                int rowsInserted = statement.executeUpdate();
                if(rowsInserted > 0) JOptionPane.showMessageDialog(null,"El registro fue eliminado correctamente!!", "Estado", JOptionPane.INFORMATION_MESSAGE);
               
            }
        } catch (Exception e) {
            System.out.println("LOG:(ERROR): Al momento de ELIMINAR VALORES");
            JOptionPane.showMessageDialog(null,"ERROR, El registro NO fue eliminado", "Estado", JOptionPane.ERROR_MESSAGE);
        }finally{
            MySQL.closeConnection(conexion);
        }  
    }
     /**
      * Metodo que realiza el query y lo envia hacia la BD para acutalizar el empleado
      * @param empleado 
      */
     public void actualizarEmpleado(Empleado empleado){
        try {
            conexion = MySQL.crearConexion();
            if (conexion != null) {
                String query = "UPDATE empleado SET idBodega = ?, nombre = ?, edad = ? WHERE idEmpleado = ?";
                PreparedStatement statement = conexion.prepareStatement(query);
                statement.setInt(1,empleado.getIdBodega());  
                statement.setString(2,empleado.getNombre()); 
                statement.setInt(3,empleado.getEdad());
                statement.setInt(4,empleado.getIdEmpleado());
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
}
