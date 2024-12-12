package org.example.ricardomedinatomasramirez4fintegradorai.service.impl;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.ICarritoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;


import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoProductoServiceImpl implements ICarritoService {
    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);

    @Autowired
    private ICarritoRepository carritoDao;



    @Override
    public ResponseEntity<CarritoProductoResponse> buscarTodo() {
        return null;
    }

    @Override
    public ResponseEntity<CarritoProductoResponse> eliminar(Long id) {
        log.info("Buscando en el carrito para eliminar");
        CarritoProductoResponse response = new CarritoProductoResponse();
        //creamos una list
        List<CarritoProducto> list = new ArrayList<>();
        try {
            carritoDao.deleteById(id);
            response.setMetada("Respuesta OK", "00", "Respuesta exitosa");
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar ");
            log.error("Error al eliminar categorias", e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CarritoProductoResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CarritoProductoResponse>(response, HttpStatus.OK);
    }
}
