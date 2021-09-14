package controller;

import acces.BodegaDAO;
import acces.EmpleadoDAO;
import exceptions.ExceptionBodega;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import models.Empleado;

public class ControllerEmpleado {

    static EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    /**
     * Metodo que nos permite la conexion entre la vista y el modelo, trae todos
     * los datos de DAO empleado y ademas de colocar los datos en el JTable de
     * la vista
     */
    public static void cargarValores(JTable jTableEmpleados) {
        ArrayList<Empleado> listaEmpleados = empleadoDAO.obtenerEmpleados();

        String valores[][] = new String[listaEmpleados.size()][4];
        for (int i = 0; i < listaEmpleados.size(); i++) {
            valores[i][0] = "   " + listaEmpleados.get(i).getIdEmpleado();
            valores[i][1] = "   " + listaEmpleados.get(i).getIdBodega();
            valores[i][2] = listaEmpleados.get(i).getNombre();
            valores[i][3] = "   " + listaEmpleados.get(i).getEdad();
        }
        //no editar casillas
        jTableEmpleados.setDefaultEditor(Object.class, null);
        jTableEmpleados.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Empleado", "Id Bodega", "Nombre", "Edad"
                }
        ));

        //Dar tamaño a las columnas 
        TableColumnModel columnModel = jTableEmpleados.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(110);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(50);
    }
    /**
     * Metodo que llama al DAO de empleado para eliminar empleado con el id
     * @param valor 
     */

    public static void eliminarRegistro(String valor) {
        ArrayList<Empleado> listaBodegas = empleadoDAO.obtenerEmpleados();
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Empleado empleado1 : listaBodegas) {
                if (id == empleado1.getIdEmpleado()) {
                    existe = true;
                    break;
                }
            }
            if (existe) {
                empleadoDAO.eliminarEmpleado(id);
            } else {
                throw new NullPointerException("No se Encontro");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe el Empleado (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }

    }

    /**
     *
     * Metodo donde convertimos los valores a su respectivo tipo de dato y
     * llamamos la conexion para realizar la inserción
     *
     * @param nombre
     * @param edad
     * @param idBodega
     * @return agregar
     */
    public static boolean insertarRegistro(String nombre, String edad, String idBodega) {
        BodegaDAO bodegaDao = new BodegaDAO();
        boolean agregar = false;
        try {

            int idbodega = Integer.parseInt(idBodega);
            int iedad = Integer.parseInt(edad);

            if (nombre.isEmpty() || iedad == 0) {
                throw new NullPointerException("Campos vacios");
            }
            if (bodegaDao.existeBodega(idbodega)) {
                Empleado empleado = new Empleado(idbodega, nombre, iedad);
                empleadoDAO.insertarEmpleado(empleado);
                agregar = true;
            } else {
                throw new ExceptionBodega("No existe Bodega");

            }
        } catch (ExceptionBodega e) {
            JOptionPane.showMessageDialog(null, "ERROR,La bodega NO EXISTE ", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: No existe bodega");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos Incorrectos(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return agregar;
    }

    /**
     * Motodo que verifica si existe el empleado y lo trae para mostrar en los
     * campos de texto y poder modificar
     *
     * @param valor
     * @return
     */
    public static Empleado existeRegistro(String valor) {
        ArrayList<Empleado> listaEmpleados = empleadoDAO.obtenerEmpleados();
        Empleado empleado = null;
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Empleado empleado1 : listaEmpleados) {
                if (id == empleado1.getIdEmpleado()) {
                    empleado = new Empleado(empleado1.getIdBodega(), empleado1.getNombre(), empleado1.getEdad());
                    existe = true;
                    return empleado;
                }
            }
            if (!existe) {
                throw new NullPointerException("No se Encontro");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe el Empleado (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }
        return null;
    }

    /**
     * Meotod que permite la conexion entre el DAO y la vista , envia info
     *
     * @param empleado
     * @return
     */
    public static boolean actualizarRegistro(Empleado empleado) {
        boolean acualizar = false;
        try {
           if( !new BodegaDAO().existeBodega(empleado.getIdBodega())) throw new ExceptionBodega("No existe bodega");
            empleadoDAO.actualizarEmpleado(empleado);
            acualizar = true;

        }catch (ExceptionBodega e) {
            JOptionPane.showMessageDialog(null, "ERROR,"+e.getMessage(),"Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");

        }
        return acualizar;
    }

}
