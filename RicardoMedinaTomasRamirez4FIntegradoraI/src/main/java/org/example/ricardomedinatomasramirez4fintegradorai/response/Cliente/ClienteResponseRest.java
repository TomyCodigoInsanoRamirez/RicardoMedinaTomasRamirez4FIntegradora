package org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente;

import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;

public class ClienteResponseRest extends ResponseRest {
    private ClienteResponse clienteResponse = new ClienteResponse();

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }
    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }

}