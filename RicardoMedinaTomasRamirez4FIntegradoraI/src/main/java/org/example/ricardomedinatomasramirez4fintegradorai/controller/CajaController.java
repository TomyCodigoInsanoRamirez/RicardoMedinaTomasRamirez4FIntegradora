package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.dao.IClienteRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.service.impl.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;

@RestController
@RequestMapping("/caja2")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @Autowired
    private IClienteRepository clienteRepository; // Repositorio para acceder a clientes

    @PostMapping("/agregarUltimoCliente")
    public ResponseEntity<String> agregarUltimoCliente() {
        // Obtener el siguiente cliente que no ha sido agregado a la fila
        Cliente siguienteCliente = clienteRepository.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola

        if (siguienteCliente != null) {
            // Crear el objeto Caja con la información del cliente
            Caja caja = new Caja(siguienteCliente.getId(), siguienteCliente.getPrimerNombre());
            cajaService.agregarCliente(caja); // Agregar el cliente a la cola

            // Eliminar el cliente de la base de datos
            clienteRepository.delete(siguienteCliente); // Eliminar el cliente de la base de datos

            return ResponseEntity.ok("Cliente agregado a la fila: " + siguienteCliente.getPrimerNombre());
        } else {
            return ResponseEntity.status(404).body("No hay más clientes registrados");
        }
    }


    // Endpoint para atender al siguiente cliente
    @GetMapping("/atender")
    public ResponseEntity<Caja> atenderCliente() {
        Caja siguienteCliente = cajaService.atenderCliente(); // Obtener cliente
        if (siguienteCliente != null) {
            return ResponseEntity.ok(siguienteCliente);
        } else {
            return ResponseEntity.status(404).body(null); // Cola vacía
        }
    }

    // Endpoint para obtener toda la fila
    @GetMapping("/obtenerFila")
    public ResponseEntity<Queue<Caja>> obtenerFila() {
        Queue<Caja> fila = cajaService.obtenerFila(); // Obtener la cola completa
        return ResponseEntity.ok(fila);
    }
}
