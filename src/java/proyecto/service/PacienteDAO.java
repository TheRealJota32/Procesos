/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import proyecto.model.Paciente;

/**
 *
 * @author Jose Pablo
 */
public class PacienteDAO {

    private final Conector conectorJDBC = Conector.getConector();

    public List<Paciente> listarPacientes() {
        Connection connectionDB = conectorJDBC.conectar();
        List<Paciente> pacientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM clinica.paciente;";
            PreparedStatement stmt = connectionDB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setResidencia(rs.getString("residencia"));
                paciente.setCorreo(rs.getString("correo"));
                pacientes.add(paciente);
            }
            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public Paciente bucarPaciente(int idPaciente) {
        Connection connectionDB = conectorJDBC.conectar();
        Paciente paciente = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connectionDB.prepareStatement("SELECT * FROM clinica.paciente WHERE idPaciente = ?");
            ps.setInt(1, idPaciente);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                paciente = new Paciente();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setApellido(rs.getString("apellido"));
                paciente.setResidencia(rs.getString("residencia"));
                paciente.setCorreo(rs.getString("correo"));
            }
            
            rs.close();
            ps.close();
            conectorJDBC.cerrarConexion(connectionDB, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return paciente;
    }

}
