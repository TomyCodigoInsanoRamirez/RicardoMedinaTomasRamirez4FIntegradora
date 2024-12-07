package org.example.supermercado.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_producto")
public class CarritoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private org.example.supermercado.model.Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private org.example.supermercado.model.Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(org.example.supermercado.model.Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
