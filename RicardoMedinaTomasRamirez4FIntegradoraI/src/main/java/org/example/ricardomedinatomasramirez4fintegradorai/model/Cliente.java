package org.example.supermercado.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<org.example.supermercado.model.CarritoProducto> carritoProductos;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<org.example.supermercado.model.CarritoProducto> getCarritoProductos() {
        return carritoProductos;
    }

    public void setCarritoProductos(List<org.example.supermercado.model.CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
