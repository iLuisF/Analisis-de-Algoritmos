
package mx.unam.fciencias.EstDat;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Representación de un gráfica con listas ligadas.
 * @author Flores González Luis Brandon.
 * @param <T> 
 */
public class Grafica<T>{

    private final ListaLigada<Arista<T>> aristas; //Todas las aristas
    private final Map<Long,Vertice<T>> vertices; //Todos los vertices
    boolean esDirigida = false;
    
    public Grafica(boolean esDirigida){
        aristas = new ListaLigada<>();
        vertices = new HashMap<>();
        this.esDirigida = esDirigida;
    }
    
    public void agregarArista(long id1, long id2){
        agregarArista(id1,id2,0);
    }
        
    /**
     * Solo para graficas dirigidas, porque para gráficas no dirigidas podemos
     * terminar agregando aristas en ambos a todos los vertices.
     * @param vertice 
     */
    public void agregarVertice(Vertice<T> vertice){
        if(vertices.containsKey(vertice.getId())){
            return;
        }
        vertices.put(vertice.getId(), vertice);
        for(Arista<T> arista : vertice.getAristas()){
            aristas.alFinal(arista);
        }       
    }
   
    public Vertice<T> getVertice(long id){
        return vertices.get(id);
    }
    
    public void agregarArista(long id1,long id2, int peso){
        Vertice<T> vertice1 = null;
        if(vertices.containsKey(id1)){
            vertice1 = vertices.get(id1);
        }else{
            vertice1 = new Vertice<>(id1);
            vertices.put(id1, vertice1);
        }
        Vertice<T> vertice2 = null;
        if(vertices.containsKey(id2)){
            vertice2 = vertices.get(id2);
        }else{
            vertice2 = new Vertice<>(id2);
            vertices.put(id2, vertice2);
        }

        Arista<T> arista = new Arista<>(vertice1,vertice2,esDirigida,peso);
        aristas.alFinal(arista);
        vertice1.agregarVerticeAdyacente(arista, vertice2);
        if(!esDirigida){
            vertice2.agregarVerticeAdyacente(arista, vertice1);
        }

    }
    
    public ListaLigada<Arista<T>> getAristas(){//Todas las aristas
        return aristas;
    }
    
    public Collection<Vertice<T>> getVertices(){//Todos los vertices
        return vertices.values();
    }
    
    public void setInfVertice(long id, T inf){
        if(vertices.containsKey(id)){
            Vertice<T> vertice = vertices.get(id);
            vertice.setInf(inf);
        }
    }
}

