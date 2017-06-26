
package mx.unam.fciencias.EstDat;

public class Vertice<T> {
    
    long id;
    private T inf;
    private final ListaLigada<Arista<T>> aristas = new ListaLigada<>();
    private final ListaLigada<Vertice<T>> verticeAdyacente = new ListaLigada<>();
    
    public Vertice(long id){
        this.id = id;
    }
    
    public long getId(){
        return id;
    }
    
    public void setInf(T data){
        this.inf = data;
    }
    
    public T getData(){
        return inf;
    }
    
    public void agregarVerticeAdyacente(Arista<T> a, Vertice<T> v){
        aristas.alFinal(a);
        verticeAdyacente.alFinal(v);
    }
    
    @Override
    public String toString(){
        return String.valueOf(id);
    }
    
    public ListaLigada<Vertice<T>> getVerticesAdyacentes(){
        return verticeAdyacente;
    }
    
    public ListaLigada<Arista<T>> getAristas(){
        return aristas;
    }
    
    public int getGrado(){
        return aristas.longitud();
    }
}
