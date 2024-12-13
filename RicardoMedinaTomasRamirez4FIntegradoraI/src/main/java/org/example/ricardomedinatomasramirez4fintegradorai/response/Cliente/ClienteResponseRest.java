package org.example.ricardomedinatomasramirez4fintegradorai.response.Cliente;

import org.example.ricardomedinatomasramirez4fintegradorai.response.ResponseRest;

public class ClienteResponseRest extends ResponseRest {

        private ClienteResponse clienteResponse = new ClienteResponse();

        public ClienteResponse getClientesResponse() {
            return clienteResponse;
        }
        //5:37Clie
        public void setClientesResponse(ClienteResponse clienteResponse) {
            this.clienteResponse = clienteResponse;
        }
}
