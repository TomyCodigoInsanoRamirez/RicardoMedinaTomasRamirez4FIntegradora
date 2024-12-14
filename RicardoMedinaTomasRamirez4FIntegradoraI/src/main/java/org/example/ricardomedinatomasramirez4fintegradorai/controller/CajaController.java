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
        // Obtener todos los clientes
        List<Cliente> clientes = clienteRepository.findAll(); // Obtener todos los clientes
        if (!clientes.isEmpty()) {
            // Iterar sobre los clientes y agregar cada uno a la fila
            for (Cliente cliente : clientes) {
                Caja caja = new Caja(cliente.getId(), cliente.getPrimerNombre()); // Crear caja para la cola
                cajaService.agregarCliente(caja); // Agregar a la cola
            }
            return ResponseEntity.ok("Clientes agregados a la fila: " );
        } else {
            return ResponseEntity.status(404).body("No hay clientes registrados");
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
