package org.example.ricardomedinatomasramirez4fintegradorai.service.impl;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.LinkedList;
@Service
public class CajaService {

    private Queue<Caja> filaClientes = new LinkedList<>();

    // Método para agregar un cliente a la fila
    public void agregarCliente(Caja cliente) {
        filaClientes.offer(cliente); // Añadir al final de la cola
    }

    // Método para atender al siguiente cliente
    public Caja atenderCliente() {
        return filaClientes.poll(); // Elimina y retorna el cliente del frente de la cola
    }

    // Método para obtener la cola completa
    public Queue<Caja> obtenerFila() {
        return filaClientes;
    }
}
