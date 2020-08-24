/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import proyecto.model.Cita;
import proyecto.model.Doctor;
import proyecto.model.Paciente;
import proyecto.service.CitaDAO;
import proyecto.service.ExpedienteDAO;

/**
 *
 * @author Jose Pablo
 */
@ManagedBean(name = "controllerCita")
@SessionScoped
public class ControllerCita implements Serializable {

    private static final long serialVersionUID = 1L;

    private Paciente paciente = new Paciente();
    private Doctor doctor = new Doctor();
    private String observacion;
    private Date fecha;

    public String agregarCita() throws EmailException {
        boolean resultado = false;
        String pagina = "";
        if (this.paciente == null || this.doctor == null || this.observacion.equals("") || this.fecha == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Error!", "Por favor ingrese todos los campos requeridos"));
        } else {
            CitaDAO dao = new CitaDAO();
            ExpedienteDAO eDao = new ExpedienteDAO();
            Cita cita = new Cita();
            cita.setDoctor(doctor);
            cita.setPaciente(paciente);
            cita.setObservacion(observacion);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = format.format(new Date());
            java.sql.Date fechaSql = java.sql.Date.valueOf(dateString);
            cita.setFecha(fechaSql);
            resultado = dao.insertarCita(cita);
            eDao.insertarExpediente(cita.getIdCita());
            notificacion(fechaSql, paciente.getCorreo());
            if (resultado) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Notificación!", "La cita fue agendada con éxito!"));
                pagina = "index.xhtml?faces-redirect=true";
            }
        }

        limpiarVariables();
        return pagina;
    }

    public void limpiarVariables() {
        this.paciente = null;
        this.doctor = null;
        this.observacion = null;
        this.fecha = null;
    }

    public String notificacion(Date fecha, String correo) throws EmailException {
        //credentials for email and email account
        Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("proyecto2ulatina@gmail.com", "proyecto_2_ulatina"));
        email.setSSLOnConnect(true);
        email.setFrom("proyecto2ulatina@gmail.com");
        email.setSubject("NOTIFICACIÓN CITA - CCSS");
        email.setMsg("Se agendó una cita para la siguiente fecha " + fecha);
        email.addTo(correo);
        email.send();
        return "NuevaContrasena";
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
