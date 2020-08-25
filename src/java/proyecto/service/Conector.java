/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jose Pablo
 */
public class Conector {

    //db
    private final String DB_URL = "jdbc:mysql://35.225.206.105:3306/clinica?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false";

    //credenciales
    private final String USER = "root";
    private final String PASS = "admin";

    private static Conector conector;

    public static Conector getConector() {
        if (conector == null) {
            conector = new Conector();
        }
        return conector;
    }

    private Conector() {

    }

    public Connection conectar() {

        Connection conn = null;
        try {
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public void cerrarConexion(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
