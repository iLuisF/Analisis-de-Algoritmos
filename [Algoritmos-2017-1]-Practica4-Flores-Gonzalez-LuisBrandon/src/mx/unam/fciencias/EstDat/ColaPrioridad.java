package mx.unam.fciencias.EstDat;

import java.util.HashMap;
import java.util.Map;

/**
 * Combinación de heap binario y map.
 *
 * @author Flores González Luis Brandon.
 * @param <T>
 */
public class ColaPrioridad<T> {

    private final ListaLigada<Nodo> nodos = new ListaLigada<>();//Todos los nodos.
    private final Map<T, Integer> posicionNodo = new HashMap<>();

    public class Nodo {

        private int peso;
        private  T llave;
        
        public int getPeso(){
            return peso;
        }
        
        public T getLlave(){
            return llave;        
        }
    }

    /**
     * Revisa si la llave esta en el heap o no.
     *
     * @param llave
     * @return
     */
    public boolean contiene(T llave) {
        return posicionNodo.containsKey(llave);
    }
    
    /**
     * Agrega una llave y su peso al heap.
     *
     * @param peso
     * @param llave
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public void agregar(int peso, T llave) throws ExcepcionAccesoIncorrecto {
        Nodo nodo = new Nodo();
        nodo.peso = peso;
        nodo.llave = llave;
        nodos.alFinal(nodo);
        int tamanio = nodos.longitud();
        int actual = tamanio - 1;
        int indicePadre = (actual - 1) / 2;
        posicionNodo.put(nodo.llave, actual);

        while (indicePadre >= 0) {
            Nodo nodoPadre = nodos.elemento(indicePadre);
            Nodo nodoActual = nodos.elemento(actual);
            if (nodoPadre.peso > nodoActual.peso) {
                swap(nodoPadre, nodoActual);
                actualizarPosicionMap(nodoPadre.llave, nodoActual.llave, 
                        indicePadre, actual);
                actual = indicePadre;
                indicePadre = (indicePadre - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Regresa el minimo sin extraerlo.
     *
     * @return Minimo sin extraer del heap.
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public T min() throws ExcepcionAccesoIncorrecto {
        return nodos.elemento(0).llave;
    }

    /**
     * Revisa si la cola es vacia o no.
     *
     * @return
     */
    public boolean esVacio() {
        return nodos.longitud() == 0;
    }

    /**
     * Decrementa el peso de una llave dada.
     *
     * @param llave
     * @param peso
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public void decrementar(T llave, int peso) throws ExcepcionAccesoIncorrecto {
        Integer posicion = posicionNodo.get(llave);
        nodos.elemento(posicion).peso = peso;
        int padre = (posicion - 1) / 2;
        while (padre >= 0) {
            if (nodos.elemento(padre).peso > nodos.elemento(posicion).peso) {
                swap(nodos.elemento(padre), nodos.elemento(posicion));
                actualizarPosicionMap(nodos.elemento(padre).llave, 
                        nodos.elemento(posicion).llave, padre, posicion);
                posicion = padre;
                padre = (padre - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * Regresa el peso de una llave dada.
     *
     * @param llave
     * @return
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public Integer getPeso(T llave) throws ExcepcionAccesoIncorrecto {
        Integer posicion = posicionNodo.get(llave);
        if (posicion == null) {
            return null;
        } else {
            return nodos.elemento(posicion).peso;
        }
    }

    /**
     * Regresa y elimina el minimo del heap.
     *
     * @return
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public Nodo extraerMin() throws ExcepcionAccesoIncorrecto {
        int tamanio = nodos.longitud() - 1;
        Nodo minNodo = new Nodo();
        minNodo.llave = nodos.elemento(0).llave;
        minNodo.peso = nodos.elemento(0).peso;

        int pesoUltimoNodo = nodos.elemento(tamanio).peso;
        nodos.elemento(0).peso = pesoUltimoNodo;
        nodos.elemento(0).llave = nodos.elemento(tamanio).llave;
        posicionNodo.remove(minNodo.llave);
        posicionNodo.remove(nodos.elemento(0));
        posicionNodo.put(nodos.elemento(0).llave, 0);
        nodos.elimina(tamanio);

        int indiceActual = 0;
        tamanio--;
        while (true) {
            int izq = 2 * indiceActual + 1;
            int der = 2 * indiceActual + 2;
            if (izq > tamanio) {
                break;
            }
            if (der > tamanio) {
                der = izq;
            }
            int indiceMasPeque = nodos.elemento(izq).peso 
                    <= nodos.elemento(der).peso ? izq : der;
            if (nodos.elemento(indiceActual).peso 
                    > nodos.elemento(indiceMasPeque).peso) {
                swap(nodos.elemento(indiceActual), nodos.elemento(indiceMasPeque));
                actualizarPosicionMap(nodos.elemento(indiceActual).llave, 
                        nodos.elemento(indiceMasPeque).llave, indiceActual, 
                        indiceMasPeque);
                indiceActual = indiceMasPeque;
            } else {
                break;
            }
        }
        return minNodo;
    }

    /**
     * Extrae el minimo del heap.
     *
     * @return
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public T extraerMinHeap() throws ExcepcionAccesoIncorrecto {
        Nodo nodo = extraerMin();
        return nodo.llave;
    }

    private void swap(Nodo nodo1, Nodo nodo2) {
        int peso = nodo1.peso;
        T inf = nodo1.llave;
        nodo1.llave = nodo2.llave;
        nodo1.peso = nodo2.peso;
        nodo2.llave = inf;
        nodo2.peso = peso;
    }

    private void actualizarPosicionMap(T inf1, T inf2, int pos1, int pos2) {
        posicionNodo.remove(inf1);
        posicionNodo.remove(inf2);
        posicionNodo.put(inf1, pos1);
        posicionNodo.put(inf2, pos2);
    }

}
