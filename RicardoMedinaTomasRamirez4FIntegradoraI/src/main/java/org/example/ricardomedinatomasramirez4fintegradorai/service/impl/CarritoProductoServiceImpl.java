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

import java.util.Stack;

@Service
public class CarritoProductoServiceImpl implements ICarritoService {

    private static final Logger log = LoggerFactory.getLogger(CarritoProductoServiceImpl.class);
    private static Stack<CarritoProducto> pilaEliminados = new Stack<>();

    @Autowired
    private ICarritoRepository carritoDao;

    @Override
    public ResponseEntity<CarritoProductoResponse> eliminar(Long id) {
        log.info("Buscando en el carrito para eliminar el producto con ID: " + id);
        CarritoProductoResponse response = new CarritoProductoResponse();
        try {
            CarritoProducto producto = carritoDao.findById(id).orElse(null);
            if (producto != null) {
                // Almacenamos el producto eliminado en la pila para deshacer
                pilaEliminados.push(producto); // Usamos la pila estática de eliminados
                carritoDao.deleteById(id); // Eliminamos el producto del carrito
                response.setMetada("Respuesta OK", "00", "Producto eliminado exitosamente");
            } else {
                response.setMetada("Error", "-1", "Producto no encontrado");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al eliminar el producto");
            log.error("Error al eliminar el producto", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CarritoProductoResponse> deshacerEliminacion(Long id) {
        log.info("Deshaciendo la última eliminación");
        CarritoProductoResponse response = new CarritoProductoResponse();
        try {
            // Verificamos si hay elementos en la pila
            if (!pilaEliminados.isEmpty()) {
                // Obtener el último elemento eliminado de la pila
                CarritoProducto ultimoEliminado = pilaEliminados.pop(); // Usamos la pila de eliminados
                carritoDao.save(ultimoEliminado); // Reinsertar el producto en el carrito

                response.setMetada("Respuesta OK", "00", "Última eliminación deshecha exitosamente");
                return new ResponseEntity<>(response, HttpStatus.OK); // Respuesta exitosa
            } else {
                response.setMetada("Error", "-1", "No hay eliminaciones para deshacer");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // No hay eliminaciones previas
            }
        } catch (Exception e) {
            response.setMetada("Error", "-1", "Error al deshacer la eliminación");
            log.error("Error al deshacer la eliminación", e);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Error del servidor
        }
    }
}
