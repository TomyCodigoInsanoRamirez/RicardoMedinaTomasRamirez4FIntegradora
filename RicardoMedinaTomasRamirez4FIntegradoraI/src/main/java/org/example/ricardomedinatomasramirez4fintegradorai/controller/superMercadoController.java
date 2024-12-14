package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue.Queue;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.service.impl.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/supermercado")
public class superMercadoController {
    @Autowired
    private CajaService cajaService;

    @Autowired
    private IClienteRepository clienteRepository; // Repositorio para acceder a clientes
    private ICarritoRepository carritoRepositoryy;

    Queue<CarritoProducto> queue = new Queue<CarritoProducto>(100);
    //Queue<CarritoProducto> queue2 = new Queue<CarritoProducto>(100);
    //Queue<CarritoProducto> queue2 = new LinkedList<>(queue); // Copia de la cola original


    public superMercadoController(ICarritoRepository carritoRepositoryy) {
        this.carritoRepositoryy = carritoRepositoryy;
    }

    @PostMapping("/comprar/{id}")
    public double agregarUltimoCliente(@PathVariable Long id) {
        // Obtener el siguiente cliente que no ha sido agregado a la fila
        //Cliente siguienteCliente = clienteRepository.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola
        List<CarritoProducto> siguienteCliente = carritoRepositoryy.findByClienteId(id); // Obtener el cliente con el id más bajo que aún no está en la cola
        //List<CarritoProducto> siguienteCliente = carritoRepositoryy.findAllById(id); // Obtener el cliente con el id más bajo que aún no está en la cola
        CarritoProducto cp =  new CarritoProducto();
        /*cp.setId(siguienteCliente.get(0).getId());
        cp.setCliente(siguienteCliente.get(0).getCliente());
        cp.setProducto(siguienteCliente.get(0).getProducto());
        cp.setCantidad(siguienteCliente.get(0).getCantidad());*/
        double total = 0.0;
        if (siguienteCliente != null) {

            for(int i = 0; i < siguienteCliente.size();i++){
                total = total + siguienteCliente.get(i).getProducto().getPrecio();
            }
            queue.offer(cp);

            for(int i = 0; i < siguienteCliente.size();i++){
                cp.setId(siguienteCliente.get(i).getId());
                cp.setCliente(siguienteCliente.get(i).getCliente());
                cp.setProducto(siguienteCliente.get(i).getProducto());
                cp.setCantidad(siguienteCliente.get(i).getCantidad());
                carritoRepositoryy.delete(cp);
            }


            //carritoRepositoryy.delete(cp); // Eliminar el cliente de la base de datos

            return total;
        } else {
            return 0.0;
        }
    }





}
