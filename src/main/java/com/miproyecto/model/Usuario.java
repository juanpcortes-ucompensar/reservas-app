package com.miproyecto.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String contrasena;

    @Column(nullable = false)
    private String nombre;

    public Usuario() {}

    public Usuario(String correo, String contrasena, String nombre) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.nombre = nombre;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
