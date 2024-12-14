package org.example.ricardomedinatomasramirez4fintegradorai.StackAndQueue;

public class Stack <T> {
    int capacidad;
    int tope;
    T[] items;

    public Stack(int capacidad) {
        this.capacidad = capacidad;
        tope = -1;
        this.items = (T[]) new Object[capacidad];
    }

    public void push (T item){
        if(isFull()){
            System.out.println("El stack ya esta lleno, no puedes agregar mas");
            return;
        }
        items[++tope]=item;
    }

    public T pop(){
        if(isEmpty()){
            System.out.println("El stack esta vacia");
            return null;
        }
        return items [tope--];
    }

    public T peek (){
        if(isEmpty()){
            System.out.println("El stack esta vacia");
            return null;
        }
        return items [tope];
    }

    public boolean isEmpty(){
        return (tope==-1);
    }

    public boolean isFull(){
        return(tope==capacidad-1);

    }
}
