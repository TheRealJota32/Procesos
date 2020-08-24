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
import proyecto.model.Paciente;
import proyecto.service.PacienteDAO;

/**
 *
 * @author Jose Pablo
 */
@ManagedBean(name = "controllerPaciente")
@SessionScoped
public class ControllerPaciente implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Paciente> pacientes = new ArrayList<Paciente>();

    @PostConstruct
    public void init() {
        listar();
    }

    public void listar() {
        PacienteDAO dao = new PacienteDAO();
        this.pacientes = dao.listarPacientes();
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

}
