package com.prueba.demo.core.model;


public class Persona {

     private Integer idPersona;
     private String dni;
     private String apellidoPaterno;
     private String apellidoMaterno;
     private String direccion;
     private String nombre;    
 
 
    public Integer getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Persona() {
    }
    @Override
    public String toString() {
        return "Persona [apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno + ", direccion="
                + direccion + ", dni=" + dni + ", idPersona=" + idPersona + ", nombre=" + nombre + "]";
    }
    public Persona(Integer idPersona, String dni, String apellidoPaterno, String apellidoMaterno, String direccion,
            String nombre, String resultado) {
        this.idPersona = idPersona;
        this.dni = dni;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.direccion = direccion;
        this.nombre = nombre;
    }
 

    


    
}
