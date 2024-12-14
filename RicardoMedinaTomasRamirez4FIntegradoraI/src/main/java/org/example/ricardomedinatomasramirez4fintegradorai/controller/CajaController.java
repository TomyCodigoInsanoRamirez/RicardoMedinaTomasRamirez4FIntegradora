package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue.Queue;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Producto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.impl.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//import java.util.Queue;

@RestController
@RequestMapping("/caja2")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @Autowired
    private IClienteRepository clienteRepository; // Repositorio para acceder a clientes
    private ICarritoRepository carritoRepositoryy;

    Queue<Cliente> queue = new Queue<Cliente>(100);
    Queue<Producto> queueProdcutos = new Queue<Producto>(100);
    //Queue<Cliente> queue2 = new Queue<Cliente>(100);
    //Queue<CarritoProducto> queue2 = new LinkedList<>(queue); // Copia de la cola original


    public CajaController(ICarritoRepository carritoRepositoryy) {
        this.carritoRepositoryy = carritoRepositoryy;
    }

    @PostMapping("/agregar/{id}")
    public Cliente agregarUltimoCliente(@PathVariable Long id) {
        // Obtener el siguiente cliente que no ha sido agregado a la fila
        //Cliente siguienteCliente = clienteRepository.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola
        List<CarritoProducto> siguienteCliente = carritoRepositoryy.findByClienteId(id); // Obtener el cliente con el id más bajo que aún no está en la cola
        Cliente cliente = new Cliente();
        CarritoProducto clientee = new CarritoProducto();
        //cliente.setId(siguienteCliente.get(0).getId());
        //cliente.setCliente(siguienteCliente.get(0).getCliente());
        //cliente.setProducto(siguienteCliente.get(0).getProducto());
        //cliente.setCantidad(siguienteCliente.get(0).getCantidad());
        cliente.setId(siguienteCliente.get(0).getCliente().getId());
        //clientee.setPrimerNombre(siguienteCliente.get(0).getCliente().getPrimerNombre());
        cliente.setApellidoPaterno(siguienteCliente.get(0).getCliente().getApellidoPaterno());
        cliente.setId(siguienteCliente.get(0).getId());
        //clientee.setCliente(siguienteCliente.get(0).getCliente());
        //clientee.setProducto(siguienteCliente.get(0).getProducto());
        queue.offer(siguienteCliente.get(0).getCliente());
        if (siguienteCliente != null) {


            return siguienteCliente.get(0).getCliente();
        } else {
            return null;
        }
    }


    // Endpoint para atender al siguiente cliente
    @GetMapping("/atender")
    public ResponseEntity<Cliente> atenderCliente() {
        //Caja siguienteCliente = cajaService.atenderCliente(); // Obtener cliente
        Cliente siguienteCliente = queue.peek();
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
    public ResponseEntity<ArrayList<Cliente>> obtenerFila() {

        Queue<Cliente> queue2 = queue.copy();
        //queue2 = queue;
        ArrayList<Cliente> res= new ArrayList<Cliente>();// Obtener la cola completa
        while (!queue2.isEmpty()){
            res.add(queue2.poll());
        }
        return ResponseEntity.ok(res);
    }



}
