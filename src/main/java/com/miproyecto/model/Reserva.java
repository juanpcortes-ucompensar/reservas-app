package com.miproyecto.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "reservas")
public class Reserva implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaReserva;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private int duracion;  // Duración en horas

    @Column(nullable = false)
    private String espacio; 

    public Reserva() {}

    public Reserva(Date fechaReserva, Usuario usuario, int duracion, String espacio) {
        this.fechaReserva = fechaReserva;
        this.usuario = usuario;
        this.duracion = duracion;
        this.espacio = espacio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaReserva() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fechaReserva);  // Retorna la fecha formateada
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getEspacio() {
        return espacio;
    }

    public void setEspacio(String espacio) {
        this.espacio = espacio;
    }
}
