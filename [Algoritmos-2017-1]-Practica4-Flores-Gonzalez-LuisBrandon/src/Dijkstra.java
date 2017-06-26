
import java.util.HashMap;
import java.util.Map;
import mx.unam.fciencias.EstDat.ColaPrioridad;
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;
import mx.unam.fciencias.EstDat.Grafica;
import mx.unam.fciencias.EstDat.Vertice;
import mx.unam.fciencias.EstDat.Arista;

/**
 * 
 * @author Flores González Luis Brandon.
 */
public class Dijkstra {

    /**
     * Dada una gráfica encuentra el camino minimo del vertice fuente a cada uno
     * de los demás vertices. Usando el algoritmo de Dijkstra.
     * @param grafica
     * @param inicio Vertice con el que se comenzara el camino.
     * @return
     * @throws ExcepcionAccesoIncorrecto 
     */
    public Map<Vertice<Integer>, Integer> getCamino(Grafica<Integer> grafica,
            Vertice<Integer> inicio) throws ExcepcionAccesoIncorrecto {
        //heap + map
        ColaPrioridad<Vertice<Integer>> minHeap = new ColaPrioridad<>();        
        //Guarda la distancia más corta desde la raiz a cada vertice.
        Map<Vertice<Integer>, Integer> distancia = new HashMap<>();        
        //Guarda el padre de cada vertice en la distancia máss corta.
        Map<Vertice<Integer>, Vertice<Integer>> padre = new HashMap<>();
        
        for (Vertice<Integer> vertice : grafica.getVertices()) {
            minHeap.agregar(Integer.MAX_VALUE, vertice);
        }
        
        //Coloca la distancia del vertice inicio a 0
        minHeap.decrementar(inicio, 0);        
        distancia.put(inicio, 0);
        //El padre del vertice inicio es vacio(no tiene).
        padre.put(inicio, null);
        
        while (!minHeap.esVacio()) {            
            //Consigue el valor minimo desde el nodo del heap el cual tiene el 
            //vertice y distancia de ese vertice desde el vertice inicio.
            ColaPrioridad<Vertice<Integer>>.Nodo heapNode = minHeap.extraerMin();
            Vertice<Integer> actual = heapNode.getLlave();            
            //Actualiza la distancia más corta del vertice actual desde el
            //vertice inicio.
            distancia.put(actual, heapNode.getPeso());            
            //Recorre todas las aristas del vertice actual.            
            for(int i = 0; i < actual.getAristas().longitud(); i++){
                //Consigue el vertice adyacente.
                Vertice<Integer> adyacente = getVerticePorArista(actual, 
                        actual.getAristas().elemento(i));                
                //Si no contiene entonces el vertice adyacente ya tiene la
                //distancia más cortas desde el vertice inicio.
                if (!minHeap.contiene(adyacente)) {
                    continue;
                }                
                //Agrega distancia del vertice actual al peso de la arista para
                //conseguir distancia del vertice adyacente desde el v. inicio.                                
                int nuevaDistancia = distancia.get(actual) + 
                        actual.getAristas().elemento(i).getPeso();                
                if (minHeap.getPeso(adyacente) > nuevaDistancia) {
                    minHeap.decrementar(adyacente, nuevaDistancia);
                    padre.put(adyacente, actual);
                }
            }
        }
        return distancia;
    }

    private Vertice<Integer> getVerticePorArista(Vertice<Integer> v,
            Arista<Integer> e) {
        return e.getVertice1().equals(v) ? e.getVertice2() : e.getVertice1();
    }
    
    public static void main(String args[]) {
        try {
            //Se crea una digrafica conexa
            Grafica<Integer> conexa = new Grafica<>(false);                        
         
            //Se asignan las aristas a la gráfica.            
            conexa.agregarArista(1, 2, 10);
            conexa.agregarArista(1, 3, 2);
            conexa.agregarArista(1, 4, 1);
            conexa.agregarArista(4, 3, 30);
            conexa.agregarArista(4, 2, 3);
            conexa.agregarArista(2, 3, 20);

            //Se aplica el algoritmo
            Dijkstra test = new Dijkstra();
            Vertice<Integer> inicio = conexa.getVertice(1);
            Map<Vertice<Integer>, Integer> distancia = test.getCamino(conexa, inicio);
            
            //Se muestra el resultado.
            System.out.print(distancia);
            
        } catch (ExcepcionAccesoIncorrecto ex) {
            System.out.println(ex.getMessage());
        }
    }
}
