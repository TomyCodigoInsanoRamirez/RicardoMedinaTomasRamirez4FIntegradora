package org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto;

import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;

import java.util.List;

public class CarritoProductoResponse extends ResponseRest {
    private List<CarritoProducto> carritoProductos;
    public List<CarritoProducto> getCarritoProductoss() {
        return carritoProductos;
    }
    public void setCarritoProductos(List<CarritoProducto> carritoProductos) {
        this.carritoProductos = carritoProductos;
    }
}
