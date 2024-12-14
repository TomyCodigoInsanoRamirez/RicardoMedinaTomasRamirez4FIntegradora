package org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue;

public class Queue<T> {
    int tail; //index de cola
    int head; //index de la cabeza
    int itemsNumber;// numero de elementos actuales en la fila
    int arrLength;//capacidad de la fila
    T[] items;//items



    public Queue(int arrLength){
        this.arrLength = arrLength;
        this.head = 0;
        this.tail = -1;
        this.itemsNumber = 0;
        this.items = (T[]) new Object[arrLength];
    }

    //metodo para meter un elemento a la queue
    public void offer(T item){
        if(isFull()){
            System.out.println("La queue esta llena");
            return;
        }
        tail=(tail+1)%arrLength;//indicador de tail en forma circular(aumenta la cola)
        items[tail] = item;//setea el nuevo item en la queue
        itemsNumber++;//actualiza el numero de items en la queue
    }

    public T poll(){//poll recupera y elimina
        if(isEmpty()){
            System.out.println("La queue esta vacia");
            return null;
        }
        T item = items[head];//seleccionar el item en la cabeza de la queue
        head = (head+1)%arrLength;//actualizar el indicador de forma circular
        itemsNumber--;//actualizar el numero de items en la queue
        return item;//retorna el primer elemento en la queue
    }

    public T peek(){// ver el elemento al frente de la cola sin eliminarlo.
        if(isEmpty()){
            System.out.println("La queue esta vacia");
            return null;
        }
        return items[head];
    }

    public boolean isEmpty(){//verificar que la cola esta vacia
        return (itemsNumber == 0);
    }

    public boolean isFull(){//verificar si la queue esta llena
        return (itemsNumber == arrLength);
    }
}
