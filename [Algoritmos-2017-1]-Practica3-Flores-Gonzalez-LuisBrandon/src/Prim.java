
import mx.unam.fciencias.EstDat.Arista;
import mx.unam.fciencias.EstDat.Vertice;
import mx.unam.fciencias.EstDat.ColaPrioridad;
import mx.unam.fciencias.EstDat.Grafica;
import mx.unam.fciencias.EstDat.ListaLigada;
import mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto;
import java.util.HashMap;
import java.util.Map;

/**
 * Algoritmo de Prim usando colas de prioridad(Min-Heap).
 * 
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Prim {

    /**
     * Algoritmo de prim con colas de prioridas usando heaps.
     *
     * @param grafica Se le aplicara el algoritmo de Prim.
     * @return Árbol de peso minimo.
     * @throws mx.unam.fciencias.EstDat.ExcepcionAccesoIncorrecto
     */
    public ListaLigada<Arista<Integer>> prim(Grafica<Integer> grafica)
            throws ExcepcionAccesoIncorrecto {
        //Min Heap + map.
        ColaPrioridad<Vertice<Integer>> minHeap = new ColaPrioridad<>();
        //Map de vertices a aristas el cual da un peso minimo a su vertice.
        Map<Vertice<Integer>, Arista<Integer>> verticeAarista = new HashMap<>();
        //Almacena el resultado final.
        ListaLigada<Arista<Integer>> resultado = new ListaLigada<>();

        //Inicializa los vertices con valor infinito.
        for (Vertice<Integer> vertice : grafica.getVertices()) {
            minHeap.agregar(Integer.MAX_VALUE, vertice);
        }

        //Empieza desde un vertice al azar.
        Vertice<Integer> verticeInicio = grafica.getVertices().iterator().next();

        //Para el vertice de inicio decrementamos el valor en heap + map a 0.
        minHeap.decrementar(verticeInicio, 0);

        while (!minHeap.esVacio()) {
            //Extrae el vertice de valor minimo desde heap + map.
            Vertice<Integer> actual = minHeap.extraerMinHeap();
            //Consigue la arista correspondiente para este vertice si presenta y
            //lo agrega al resultado final.
            Arista<Integer> aristaArbolExpasion = verticeAarista.get(actual);
            if (aristaArbolExpasion != null) {
                resultado.alFinal(aristaArbolExpasion);
            }
            //Recorre todos los vertices adyacentes.
            for (Arista<Integer> arista : actual.getAristas()) {
                Vertice<Integer> adyacente = getVerticePorArista(actual, arista);
                //Revisa si el vertice adyacente existe en heap + map y el peso
                //asociado con su vertice es mas grande que este peso de la arista.
                if (minHeap.contiene(adyacente) && minHeap.getPeso(adyacente) > 
                        arista.getPeso()) {
                    //Decrementa el valor del vertice adyacente a su peso de la arista.
                    minHeap.decrementar(adyacente, arista.getPeso());
                    verticeAarista.put(adyacente, arista);
                }
            }
        }
        return resultado;
    }

    private Vertice<Integer> getVerticePorArista(Vertice<Integer> vertice,
            Arista<Integer> arista) {
        return arista.getVertice1().equals(vertice) ? arista.getVertice2() : 
                arista.getVertice1();
    }

    /**
     * Prueba el algoritmo de Prim, para esto se necesita crear una gráfica y
     * agregar aristas a tal gráfica donde el primer parametro es el vertice 1,
     * el segundo el vertice 2 y el tercero el peso de la arista del vertice 1
     * al 2.
     *
     * @param args Sin uso.
     * @throws ExcepcionAccesoIncorrecto
     */
    public static void main(String args[]) throws ExcepcionAccesoIncorrecto {
        Grafica<Integer> conexa = new Grafica<>(false);

        //Grafica de ejemplo.        
        conexa.agregarArista(1, 2, 10);
        conexa.agregarArista(1, 3, 2);
        conexa.agregarArista(1, 4, 1);
        conexa.agregarArista(4, 3, 30);
        conexa.agregarArista(4, 2, 3);
        conexa.agregarArista(2, 3, 20);

        Prim prueba = new Prim();
        ListaLigada<Arista<Integer>> aristas = prueba.prim(conexa);

        for (int i = 0; i < aristas.longitud(); i++) {
            System.out.println(aristas.elemento(i));
        }
    }
}
