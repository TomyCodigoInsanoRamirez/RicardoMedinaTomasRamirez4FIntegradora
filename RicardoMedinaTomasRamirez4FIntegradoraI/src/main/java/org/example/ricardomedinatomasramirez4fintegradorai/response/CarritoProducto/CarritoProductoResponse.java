package org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto;

import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;

import java.util.List;

public class CarritoProductoResponse {
    private List<CarritoProducto> carritoProductos;
    public List<CarritoProducto> getCarritoProductoss() {
        return carritoProductos;
    }
    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
