package org.example.ricardomedinatomasramirez4fintegradorai.service;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Caja;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Queue;
import java.util.LinkedList;

@Service
public class CajaService {

    @Autowired
    private ICajaRepository cajaRepository;

    // Cola para manejar los clientes
    private Queue<Caja> cola = new LinkedList<>();

    // Método para agregar un cliente a la cola
    public void agregarCliente(Caja caja) {
        cola.add(caja);
        cajaRepository.save(caja); // Guarda la entidad en la base de datos si es necesario
    }

    // Método para atender al siguiente cliente de la cola
    public Caja atenderCliente() {
        return cola.poll(); // Devuelve y elimina el primer cliente de la cola
    }

    // Método para obtener la fila actual de clientes
    public Queue<Caja> obtenerFila() {
        return cola;
    }
}
