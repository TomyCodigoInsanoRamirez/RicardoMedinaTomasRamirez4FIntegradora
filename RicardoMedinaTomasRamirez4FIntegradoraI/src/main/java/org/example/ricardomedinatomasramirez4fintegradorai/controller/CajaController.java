package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue.Queue;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.impl.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.Queue;

@RestController
@RequestMapping("/caja2")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @Autowired
    private IClienteRepository clienteRepository; // Repositorio para acceder a clientes
    private ICarritoRepository carritoRepositoryy;

    Queue<CarritoProducto> queue = new Queue<CarritoProducto>(100);
    Queue<CarritoProducto> queue2 = new Queue<CarritoProducto>(100);
    //Queue<CarritoProducto> queue2 = new LinkedList<>(queue); // Copia de la cola original


    public CajaController(ICarritoRepository carritoRepositoryy) {
        this.carritoRepositoryy = carritoRepositoryy;
    }

    @PostMapping("/agregarUltimoCliente")
    public CarritoProducto agregarUltimoCliente() {
        // Obtener el siguiente cliente que no ha sido agregado a la fila
        //Cliente siguienteCliente = clienteRepository.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola
        CarritoProducto siguienteCliente = carritoRepositoryy.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola

        if (siguienteCliente != null) {
            // Crear el objeto Caja con la información del cliente
            //Caja caja = new Caja(siguienteCliente.getId(), siguienteCliente.getPrimerNombre());
            //cajaService.agregarCliente(caja); // Agregar el cliente a la cola

            // Eliminar el cliente de la base de datos

            queue.offer(siguienteCliente);
            carritoRepositoryy.delete(siguienteCliente); // Eliminar el cliente de la base de datos

            return siguienteCliente;
        } else {
            return null;
        }
    }


    // Endpoint para atender al siguiente cliente
    @GetMapping("/atender")
    public ResponseEntity<CarritoProducto> atenderCliente() {
        //Caja siguienteCliente = cajaService.atenderCliente(); // Obtener cliente
        CarritoProducto siguienteCliente = queue.peek();
        if (siguienteCliente != null) {
            queue.poll();
            return ResponseEntity.ok(siguienteCliente);
        } else {
            return ResponseEntity.status(404).body(null); // Cola vacía
        }
    }

    // Endpoint para obtener toda la fila
    /*@GetMapping("/obtenerFila")
    public ResponseEntity<ArrayList> obtenerFila() {
        queue2 = queue;
        ArrayList<CarritoProducto> res= new ArrayList<CarritoProducto>();// Obtener la cola completa
        while (!queue2.isEmpty()){
            res.add(queue2.poll());
        }
        return ResponseEntity.ok(res);
    }*/
     //cambiossssss

    @GetMapping("/obtenerFila")
    public ResponseEntity<ArrayList<CarritoProducto>> obtenerFila() {
        queue2 = queue;
        ArrayList<CarritoProducto> res= new ArrayList<CarritoProducto>();// Obtener la cola completa
        while (!queue2.isEmpty()){
            res.add(queue2.poll());
        }
        return ResponseEntity.ok(res);
    }



}
