package org.example.ricardomedinatomasramirez4fintegradorai.controller;

import org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue.Queue;
import org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue.Stack;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoProductoRepositoryCustom;
import org.example.ricardomedinatomasramirez4fintegradorai.dao.ICarritoRepository;
import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Cliente;
import org.example.ricardomedinatomasramirez4fintegradorai.model.Producto;
import org.example.ricardomedinatomasramirez4fintegradorai.response.CarritoProducto.CarritoProductoResponse;
import org.example.ricardomedinatomasramirez4fintegradorai.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.Stack;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    //Stack<CarritoProducto> elementosEliminados = new Stack<>();
    Stack<CarritoProducto> elementosEliminados = new Stack<CarritoProducto>(100);
    private ICarritoRepository carritoRepositoryy;
    @Autowired
    private ICarritoRepository carritoRepository;
    private ICarritoProductoRepositoryCustom carritoRepositoryCustom;
    private final ICarritoService carritoService;
    Queue<Producto> queueProdcutos = new Queue<Producto>(100);
    @Autowired
    public CarritoController(ICarritoRepository carritoRepositoryy, ICarritoService carritoService) {
        this.carritoRepositoryy = carritoRepositoryy;
        this.carritoService = carritoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<CarritoProducto> agregarItemCarro(@RequestBody CarritoProducto itemAdd) {
        CarritoProducto carritoProducto = carritoRepository.save(itemAdd);
        return ResponseEntity.ok(carritoProducto);
    }

    /*@Transactional
    @GetMapping("/carrito/{id}")
    public ResponseEntity<CarritoProducto> getItemCliente(@PathVariable Long id) {


        //System.out.println("Id Cliente "+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getCliente().getId());
        //System.out.println("Nombre cliente:"+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getCliente().getPrimerNombre());
        //System.out.println("Id producto: "+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getProducto().getId());
        //System.out.println("Nomrbe producto"+carritoRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()).getBody().getProducto().getNombre());

        return carritoRepository.findByClienteId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }*/

    @Transactional
    @GetMapping("/carrito/{id}")
    public List getItemCliente(@PathVariable Long id) {
        // Obtener el siguiente cliente que no ha sido agregado a la fila
        //Cliente siguienteCliente = clienteRepository.findTopByOrderByIdAsc(); // Obtener el cliente con el id más bajo que aún no está en la cola
        List<CarritoProducto> siguienteCliente = carritoRepositoryy.findByClienteId(id); // Obtener el cliente con el id más bajo que aún no está en la cola
        CarritoProducto cp =  new CarritoProducto();

        List<Producto> p = new ArrayList<>();

        for(int i = 0; i < siguienteCliente.size();i++){
            p.add(siguienteCliente.get(i).getProducto());
        }

        if (siguienteCliente != null) {
            for(int i = 0; i < siguienteCliente.size();i++){
                queueProdcutos.offer(p.get(i));
            }

            return p;
        } else {
            return null;
        }
    }


    @PostMapping("/eliminar/{idProducto}/{idCliente}")
    public ResponseEntity<CarritoProductoResponse> eliminar(@PathVariable Long idProducto, @PathVariable Long idCliente) {

        CarritoProducto elementoEliminado = new CarritoProducto();
        elementoEliminado.setId(carritoRepository.findByProductoIdAndClienteId(idProducto,idCliente).get(0).getId());
        elementoEliminado.setCantidad(carritoRepository.findByProductoIdAndClienteId(idProducto,idCliente).get(0).getCantidad());
        elementoEliminado.setCliente(carritoRepository.findByProductoIdAndClienteId(idProducto,idCliente).get(0).getCliente());
        elementoEliminado.setProducto(carritoRepository.findByProductoIdAndClienteId(idProducto,idCliente).get(0).getProducto());
        elementosEliminados.push(elementoEliminado);

        return carritoService.eliminar(carritoRepository.findByProductoIdAndClienteId(idProducto,idCliente).get(0).getId());
    }

    @PostMapping("/deshacer")
    public ResponseEntity<CarritoProducto> deshacer() {

        CarritoProducto itemReAdd = new CarritoProducto();
        Cliente cliente = new Cliente();
        Producto producto = new Producto();

       itemReAdd = elementosEliminados.peek();
        itemReAdd.setId(Long.valueOf(0));
        //itemReAdd.setCliente(elementosEliminados.peek().getCliente());
        //itemReAdd.setProducto(elementosEliminados.peek().getProducto());
        //itemReAdd.setCantidad(elementosEliminados.peek().getCantidad());


        System.out.println("cliente: " + itemReAdd.getCliente().getPrimerNombre());
        System.out.println("producto: " + itemReAdd.getProducto().getNombre());
        System.out.println("cantidad: " + itemReAdd.getCantidad());

        CarritoProducto carritoProducto = carritoRepository.save(itemReAdd);
        elementosEliminados.pop();
        return ResponseEntity.ok(carritoProducto);
    }
}
