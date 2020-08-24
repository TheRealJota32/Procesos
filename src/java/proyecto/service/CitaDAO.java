/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import proyecto.model.Cita;

/**
 *
 * @author Jose Pablo
 */
public class CitaDAO {

    private final Conector conectorJDBC = Conector.getConector();

    public boolean insertarCita(Cita cita) {
        Connection connectionDB = conectorJDBC.conectar();
        PreparedStatement ps = null;
        boolean resultado = false;

        try {
            String sql = "INSERT INTO clinica.cita (idPaciente, idDoctor, fecha, observacion) VALUES (?,?,?,?);";
            ps = connectionDB.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, cita.getPaciente().getIdPaciente());
            ps.setInt(2, cita.getDoctor().getIdDoctor());
            ps.setDate(3, cita.getFecha());
            ps.setString(4, cita.getObservacion());

            int insert = ps.executeUpdate();

            if (insert == 1) {

                ResultSet generatedKeys = ps.getGeneratedKeys();

                if (generatedKeys.next()) {
                    cita.setIdCita(generatedKeys.getInt(1));
                    resultado = true;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

}
