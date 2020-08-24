/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import proyecto.model.Doctor;
import proyecto.service.DoctorDAO;

/**
 *
 * @author Jose Pablo
 */
@ManagedBean(name = "controllerDoctor")
@SessionScoped
public class ControllerDoctor implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Doctor> doctores = new ArrayList<Doctor>();

    @PostConstruct
    public void init() {
        listar();
    }

    public void listar() {
        DoctorDAO dao = new DoctorDAO();
        this.doctores = dao.listarPacientes();
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }

}
