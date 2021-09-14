package controller;

import acces.ProductoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import models.Producto;

public class ControllerProducto {

    static ProductoDAO productoDAO = new ProductoDAO();
    /**
     * metodo que permite la conexion con el DAO de producto y carga los valores en una tabla
     * @param jTableEmpleados 
     */
    public static void cargarValores(JTable jTableEmpleados) {
        ArrayList<Producto> listaEmpleados = productoDAO.obtenerProductos();

        String valores[][] = new String[listaEmpleados.size()][4];
        for (int i = 0; i < listaEmpleados.size(); i++) {
            valores[i][0] = "   " + listaEmpleados.get(i).getIdProducto();
            valores[i][1] = listaEmpleados.get(i).getNombre();
            valores[i][2] = "" + listaEmpleados.get(i).getPrecio();
        }
        //no editar casillas
        jTableEmpleados.setDefaultEditor(Object.class, null);
        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Producto", "Nombre", "Precio"
                }
        ));

        //Dar tamaño a las columnas 
        TableColumnModel columnModel = jTableEmpleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(110);
        columnModel.getColumn(1).setPreferredWidth(200);
        columnModel.getColumn(2).setPreferredWidth(200);
    }
    /**
     * Metodo que realiza el llamado de DAO y verifica si existe el objeto
     * @param valor 
     */
    public static void eliminarRegistro(String valor) {
        ArrayList<Producto> listaProductos = productoDAO.obtenerProductos();
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Producto producto1 : listaProductos) {
                if (id == producto1.getIdProducto()) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                productoDAO.eliminarProducto(id);
            } else {
                throw new NullPointerException("No se Encontro");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe elemento (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }
    }
    /**
     * Metodo que permite verificar los datos recibidos de la vista y llama el metodo se insertar además de cargar los valores 
     * @param nombre
     * @param precio
     * @return 
     */
    public static boolean insertarRegistro(String nombre, String precio) {
        boolean agregar = false;
        try {
            float precioo = Float.parseFloat(precio);
            if (nombre.isEmpty()) {
                throw new NullPointerException("Campos vacios");
            }
            Producto producto = new Producto(nombre, precioo);
            productoDAO.insertarProducto(producto);
            agregar = true;
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR,Precio Incorrecto (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: precio incorrecto" );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios"+e);
        }
        return agregar;
    }
    public static boolean actualizarRegistro(String id,String nombre , String precio){
        boolean acualizar = false;
        try{
            int idProducto = Integer.parseInt(id);
            float precioo = Float.parseFloat(precio);
            if(nombre.isEmpty()){
                throw new NullPointerException( "Campos vacios" );
            }
            Producto producto = new Producto(idProducto,nombre, precioo);
            productoDAO.actualizarProducto(producto);
            acualizar = true;
        }catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ERROR,Precio Incorrecto (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: precio incorrecto" );

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return acualizar;
    }
    /**
     * Metodo que verifica la existencia del producto retorna verdadero si existe
     * @param valor
     * @return 
     */
    public static Producto existeRegistro(String valor){
        ArrayList<Producto> listaProductos = productoDAO.obtenerProductos();
        Producto producto = null;
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Producto p : listaProductos) {
                if( id == p.getIdProducto()){
                   producto = new Producto(p.getIdProducto(),p.getNombre(),p.getPrecio());
                    existe = true;
                    return producto;
                }
            }
            if (!existe) throw new NullPointerException( "No se Encontro" );
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe elemento (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }
        return null;
    }
}
