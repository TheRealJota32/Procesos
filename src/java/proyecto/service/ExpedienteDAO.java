/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author Jose Pablo
 */
public class ExpedienteDAO {

    private final Conector conectorJDBC = Conector.getConector();

    public boolean insertarExpediente(int idCita) {
        Connection connectionDB = conectorJDBC.conectar();
        PreparedStatement ps = null;
        boolean resultado = false;

        try {
            String sql = "INSERT INTO clinica.expediente (idCita) VALUES (?);";
            ps = connectionDB.prepareStatement(sql);

            ps.setInt(1, idCita);

            int insert = ps.executeUpdate();

            if (insert == 1) {
                resultado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

}
