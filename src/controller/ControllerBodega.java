package controller;

import acces.BodegaDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import models.Bodega;

public class ControllerBodega {
    static BodegaDAO bodegaDAO = new BodegaDAO();

/**
    * Metodo que nos permite la conexion entre la vista y el modelo, trae todos los datos de DAO Bodega
    */
    public static void cargarValores(JTable jTableBodegas) {
        ArrayList<Bodega> listaBodegas = bodegaDAO.obtenerBodegas();

        String valores[][] = new String[bodegaDAO.obtenerBodegas().size()][3];
        for (int i = 0; i < listaBodegas.size(); i++) {
            valores[i][0] = "   " + listaBodegas.get(i).getIdBodega();
            valores[i][1] = listaBodegas.get(i).getNombre();
            valores[i][2] = listaBodegas.get(i).getDireccion();
        }
        //no editar casillas
        jTableBodegas.setDefaultEditor(Object.class, null);
        jTableBodegas.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Bodega", "Nombre", "Dirección"
                }
        ));
    }

    public static void eliminarRegistro(String valor) {
        ArrayList<Bodega> listaBodegas = bodegaDAO.obtenerBodegas();
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Bodega bodega1 : listaBodegas) {
                if( id == bodega1.getIdBodega()){
                    existe = true;
                    break;
                }
            }
            if (existe) bodegaDAO.eliminarBodega(id);
            else throw new NullPointerException( "No se Encontro" );
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe elemento (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }

    }
    /**
     * Metodo que permite hacer la conexion con el DAO y llamar el metodo de insertar verificar de que existe 
     * y cargar el objeto
     * @param nombre
     * @param direccion
     * @return 
     */
    
    public static boolean insertarRegistro(String nombre, String direccion){
        boolean agregar = false;
        try{
            if(nombre.isEmpty() || direccion.isEmpty()){
                throw new NullPointerException( "Campos vacios" );
            }
            Bodega bodega = new Bodega(nombre, direccion);
            bodegaDAO.insertarBodega(bodega);
            agregar = true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return agregar;
    }
    /**
     * Metodo que premite verificafar la existencia de la bodega por el id retorna verdadero si existe
     * @param valor
     * @return 
     */
    public static Bodega existeRegistro(String valor){
        ArrayList<Bodega> listaBodegas = bodegaDAO.obtenerBodegas();
        Bodega bodega = null;
        boolean existe = false;
        try {
            int id = Integer.parseInt(valor);
            for (Bodega bodega1 : listaBodegas) {
                if( id == bodega1.getIdBodega()){
                   bodega = new Bodega(bodega1.getIdBodega(),bodega1.getNombre(),bodega1.getDireccion());
                    existe = true;
                    return bodega;
                }
            }
            if (!existe) throw new NullPointerException( "No se Encontro" );
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR,NO existe elemento (Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("ERROR VALORES INVALIDOS");
        }
        return null;
    }
    /**
     * Metodo que realiza el llamado hacia el DAO al metodo de actualizar y carga el resultado de dicho llamdo
     * @param bodega
     * @return 
     */
    public static boolean actualizarRegistro(Bodega bodega){
        boolean acualizar = false;
        try{
            if(bodega.getNombre().isEmpty() || bodega.getDireccion().isEmpty()){
                throw new NullPointerException( "Campos vacios" );
            }
            bodegaDAO.actualizarBodega(bodega);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR,Hay campos vacios(Intente de nuevo)", "Estado", JOptionPane.ERROR_MESSAGE);
            System.out.println("LOG: hay campos vacios");
        }
        return acualizar;
    }
    /**
     * Metodo que premite cargar los datos a través de un filtro por nombre o palabras relacionadas al nombre de la bodega
     * @param jTableBodegas
     * @param buscar 
     */
    public static void cargarValoresFiltro(JTable jTableBodegas, String buscar) {
        ArrayList<Bodega> listaBodegas = bodegaDAO.filtroBodegas(buscar);

        String valores[][] = new String[listaBodegas.size()][3];
        for (int i = 0; i < listaBodegas.size(); i++) {
            valores[i][0] = "   " + listaBodegas.get(i).getIdBodega();
            valores[i][1] = listaBodegas.get(i).getNombre();
            valores[i][2] = listaBodegas.get(i).getDireccion();
        }
        //no editar casillas
        jTableBodegas.setDefaultEditor(Object.class, null);
        jTableBodegas.setModel(new javax.swing.table.DefaultTableModel(
                valores, new String[]{
                    "id Bodega", "Nombre", "Dirección"
                }
        ));
    }

}
