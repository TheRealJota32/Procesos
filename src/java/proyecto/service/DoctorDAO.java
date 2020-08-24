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
import proyecto.model.Doctor;

/**
 *
 * @author Jose Pablo
 */
public class DoctorDAO {

    private final Conector conectorJDBC = Conector.getConector();

    public List<Doctor> listarPacientes() {
        Connection connectionDB = conectorJDBC.conectar();
        List<Doctor> doctores = new ArrayList<>();

        try {
            String sql = "SELECT * FROM clinica.doctor;";
            PreparedStatement stmt = connectionDB.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setIdDoctor(rs.getInt("idDoctor"));
                doctor.setNombre(rs.getString("nombre"));
                doctor.setApellido(rs.getString("apellido"));
                doctores.add(doctor);
            }

            rs.close();
            stmt.close();
            conectorJDBC.cerrarConexion(connectionDB, stmt, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctores;
    }

    public Doctor bucarDoctor(int idDoctor) {
        Connection connectionDB = conectorJDBC.conectar();
        Doctor doctor = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connectionDB.prepareStatement("SELECT * FROM clinica.doctor WHERE idDoctor = ?");
            ps.setInt(1, idDoctor);
            rs = ps.executeQuery();

            if (rs.next()) {
                doctor = new Doctor();
                doctor.setIdDoctor(rs.getInt("idDoctor"));
                doctor.setNombre(rs.getString("nombre"));
                doctor.setApellido(rs.getString("apellido"));
            }

            rs.close();
            ps.close();
            conectorJDBC.cerrarConexion(connectionDB, ps, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctor;
    }

}
