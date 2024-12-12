package org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto;

import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;

public class CarritoResponseRest extends ResponseRest {
    private CarritoProductoResponse carritoResponse = new CarritoProductoResponse();
    public CarritoProductoResponse getCarritoResponse() {
        return carritoResponse;
    }
    public void setCarritoResponse(CarritoProductoResponse carritoResponse) {
        this.carritoResponse = carritoResponse;
    }
}
