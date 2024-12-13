package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.service.CajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Queue;

@RestController
@RequestMapping("/caja2")
public class CajaController {

    @Autowired
    private CajaService cajaService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarCliente(@RequestBody Caja caja) {
        cajaService.agregarCliente(caja);
        return ResponseEntity.ok("Cliente agregado a la fila");
    }

    @GetMapping("/atender")
    public ResponseEntity<Caja> atenderCliente() {
        Caja siguienteCliente = cajaService.atenderCliente();
        if (siguienteCliente != null) {
            return ResponseEntity.ok(siguienteCliente);
        } else {
            return ResponseEntity.status(404).body(null); // No hay clientes en la fila
        }
    }

    @GetMapping("/obtenerFila")
    public ResponseEntity<Queue<Caja>> obtenerFila() {
        Queue<Caja> fila = cajaService.obtenerFila();
        return ResponseEntity.ok(fila);
    }
}
