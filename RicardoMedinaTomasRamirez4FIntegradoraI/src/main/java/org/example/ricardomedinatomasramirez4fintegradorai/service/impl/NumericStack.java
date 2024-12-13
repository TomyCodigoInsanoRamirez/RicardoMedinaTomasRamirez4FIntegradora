package org.example.ricardomedinatomasramirez4fintegradorai.service.impl;

import org.example.ricardomedinatomasramirez4fintegradorai.model.CarritoProducto;
import java.util.Stack;

public class NumericStack {

    // Pila para manejar los productos eliminados
    static Stack<CarritoProducto> stack = new Stack<>();

    // Método para agregar un producto a la pila
    public static void itemCarritoAlVuelo(CarritoProducto elementoQuitado){
        stack.push(elementoQuitado); // Agrega el producto a la pila estática
    }

    // Método para obtener el último producto eliminado de la pila
    public static CarritoProducto deshacerEliminacion() {
        if (!stack.isEmpty()) {
            return stack.pop(); // Elimina y retorna el último producto de la pila
        }
        return null; // Si la pila está vacía, retorna null
    }
}
