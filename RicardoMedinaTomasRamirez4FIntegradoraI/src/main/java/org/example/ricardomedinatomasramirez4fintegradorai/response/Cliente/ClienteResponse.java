package org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente;

import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;

import java.util.List;

public class ClienteResponse {
    private List<Cliente> clientes;
    public List<Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}