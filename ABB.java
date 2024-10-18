package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto
    private Nodo raiz;
    private int cardinal;
    private T maximo;
    private T minimo;

    private class Nodo {
        // Agregar atributos privados del Nodo
        Nodo padre;
        Nodo hijomenor;
        Nodo hijomayor;
        T valor;

        // Crear Constructor del nodo
        Nodo(T v){
            this.valor = v;
            this.padre = null;
            this.hijomenor = null;
            this.hijomayor = null;
        }
    }

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
        this.minimo = null;
        this.maximo = null;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        return this.minimo;
    }

    public T maximo(){
        return this.maximo;
    }

    public void insertar(T elem){
        Nodo actual = this.raiz;
        if (this.cardinal == 0){
            this.raiz = new Nodo(elem);
            this.raiz.izquierda = null;
            this.raiz.derecha = null;
            this.maximo = elem;
            this.maximo = elem;
        while(elem.compareTo(actual.valor)<0){
            actual
        }



        
    }

    public boolean pertenece(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
