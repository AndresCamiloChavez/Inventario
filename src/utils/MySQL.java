package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.json.simple.*;
import org.json.simple.parser.*;

public class MySQL {

    public static Connection crearConexion() {
        JSONParser parser = new JSONParser();
        Connection conn = null;

        try {
            String credentials_path = System.getProperty("user.dir") + "/src/utils/db_credentials.json";
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(credentials_path));

            String host = (String) jsonObject.get("db_ip");
            String port = (String) jsonObject.get("db_port");
            String username = (String) jsonObject.get("db_user");
            String password = (String) jsonObject.get("db_pass");
            String dbURL = "jdbc:mysql://" + host + ":" + port + "/bodegas";

            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException | FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
     public static void closeConnection(Connection con) {
        try {
            con.close();
            System.out.println("LOG: Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            System.out.println("LOG:(ERROR)-- Al cerrar la conexion");
        }
    }
}
