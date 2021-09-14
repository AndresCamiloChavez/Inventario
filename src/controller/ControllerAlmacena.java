package controller;

import acces.AlmacenaDAO;
import acces.BodegaDAO;
import acces.ProductoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import models.Almacena;

public class ControllerAlmacena {

    static AlmacenaDAO almacenaDAO = new AlmacenaDAO();
    static BodegaDAO bodegaDAO = new BodegaDAO();
    static ProductoDAO productoDAO = new ProductoDAO();
    
    /**
     * Metodo que llama la consulta del DAO y carga los valores y los coloca en una Tabla
     * @param jTableEmpleados 
     */
    public static void cargarValores(JTable jTableEmpleados) {
        ArrayList<Almacena> listaAlmacenamiento = almacenaDAO.obtenerAlmacenamiento();

        String valores[][] = new String[listaAlmacenamiento.size()][5];
        for (int i = 0; i < listaAlmacenamiento.size(); i++) {
            valores[i][0] = "   " + listaAlmacenamiento.get(i).getIdBodega();

            //obtener el nombre de la bodega
            valores[i][1] = bodegaDAO.buscarBodega(listaAlmacenamiento.get(i).getIdBodega());

            valores[i][2] = "   " + listaAlmacenamiento.get(i).getIdProducto();

            valores[i][3] = productoDAO.buscarProducto(listaAlmacenamiento.get(i).getIdProducto());
            //obtener el nombre del producto
            valores[i][4] = "   " + listaAlmacenamiento.get(i).getCantidad();

        }
        //no editar casillas
        jTableEmpleados.setDefaultEditor(Object.class, null);
        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Bodega", "Nombre", "id Producto", "Nombre", "Cantidad"
                }
        ));

        //Dar tamaño a las columnas 
        TableColumnModel columnModel = jTableEmpleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(110);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(3).setPreferredWidth(200);
    }
    /**
     * Metodo que carga la informacion con cierto criterio y los coloca en una tabla
     * @param jTableEmpleados
     * @param propiedad 
     */
    public static void cargarValoresPropiedad(JTable jTableEmpleados, String propiedad) {
        ArrayList<Almacena> listaAlmacenamiento = almacenaDAO.obtenerAlmacenamientoBodegas(propiedad);

        String valores[][] = new String[listaAlmacenamiento.size()][5];
        for (int i = 0; i < listaAlmacenamiento.size(); i++) {
            valores[i][0] = "   " + listaAlmacenamiento.get(i).getIdBodega();

            //obtener el nombre de la bodega
            valores[i][1] = bodegaDAO.buscarBodega(listaAlmacenamiento.get(i).getIdBodega());

            valores[i][2] = "   " + listaAlmacenamiento.get(i).getIdProducto();

            valores[i][3] = productoDAO.buscarProducto(listaAlmacenamiento.get(i).getIdProducto());
            //obtener el nombre del producto
            valores[i][4] = "   " + listaAlmacenamiento.get(i).getCantidad();

        }
        //no editar casillas
        jTableEmpleados.setDefaultEditor(Object.class, null);
        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Bodega", "Nombre", "id Producto", "Nombre", "Cantidad"
                }
        ));

        //Dar tamaño a las columnas 
        TableColumnModel columnModel = jTableEmpleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(110);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(3).setPreferredWidth(200);
    }
    /**
     * metodo que permite realizar la eliminación del objeto y retorna si existe y lo carga
     * @param valorBodega
     * @param valorProducto
     * @return 
     */
    public static boolean eliminarRegistro(String valorBodega, String valorProducto) {
        ArrayList<Almacena> listAlmacena = almacenaDAO.obtenerAlmacenamiento();
        boolean existe = false;
        boolean eliminado = false;
        try {
            int idBodega = Integer.parseInt(valorBodega);
            int idProducto = Integer.parseInt(valorProducto);

            for (Almacena a : listAlmacena) {
                if (idBodega == a.getIdBodega() && idProducto == a.getIdProducto()) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                almacenaDAO.eliminarBodega(idBodega, idProducto);
            } else {
                throw new NullPointerException("No se Encontro");
            }
            eliminado = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe elemento (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }
        return eliminado;
    }
    /**
     * Metood que llama el metodo insertar y permite verifcar la existencia del objeto y realiza la carga del resultaod de insetar
     * @param idBodega
     * @param idProducto
     * @param cantidad
     * @return 
     */
    public static boolean insertarRegistro(String idBodega, String idProducto, String cantidad) {
        boolean agregar = false;
        try {
            int idBodegai = Integer.parseInt(idBodega);
            int idProductoi = Integer.parseInt(idProducto);
            int cantidadi = Integer.parseInt(cantidad);

            if (cantidadi == 0 || idBodegai == 0 || idProductoi == 0) {
                throw new NullPointerException("Campos vacios");
            }
            Almacena almcena = new Almacena(idBodegai, idProductoi, cantidadi);
            if (almacenaDAO.insertarAlmacenamiento(almcena)) {
                agregar = true;
            } else {
                throw new java.sql.SQLIntegrityConstraintViolationException("Valores invalidos (Intente de nuevo)");
            }

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos incorrectos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return agregar;
    }
    /**
     * metodo que permite actualizar el objeto y verifica si existe solo permite acutalizar la cantidad
     * @param idBodega
     * @param idProducto
     * @param cantidad
     * @return 
     */
    public static boolean actualizarRegistro(String idBodega, String idProducto, String cantidad) {
        boolean acualizar = false;
        try {
            int idBodegai = Integer.parseInt(idBodega);
            int idProductoi = Integer.parseInt(idProducto);
            int cantidadi = Integer.parseInt(cantidad);

            if (cantidadi == 0 || idBodegai == 0 || idProductoi == 0) {
                throw new NullPointerException("Campos vacios");
            }

            if (bodegaDAO.existeBodega(idBodegai) && productoDAO.existeProducto(idProductoi)) {
                Almacena almacena = new Almacena(idBodegai, idProductoi, cantidadi);
                if (almacenaDAO.actualizarProducto(almacena)) {
                    acualizar = true;
                } else {
                    System.out.println("NO paso ");
                    throw new java.sql.SQLIntegrityConstraintViolationException("Valores invalidos (Intente de nuevo)");
                }
            }else{
                throw new NullPointerException("No existe");
            }

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos incorrectos");
        }catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return acualizar;
    }
}
