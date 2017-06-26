package mx.unam.fciencias.EstDat;

public class Arista<T> {

    private boolean esDirigida = false;
    private Vertice<T> vertice1;
    private Vertice<T> vertice2;
    private int peso;

    public Arista(Vertice<T> vertice1, Vertice<T> vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
    }

    public Arista(Vertice<T> vertice1, Vertice<T> vertice2, boolean esDirigida,
            int peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
        this.esDirigida = esDirigida;
    }

    public Arista(Vertice<T> vertice1, Vertice<T> vertice2, boolean esDirigda) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.esDirigida = esDirigda;
    }

    public int getPeso() {
        return peso;
    }

    public Vertice<T> getVertice1() {
        return vertice1;
    }

    public Vertice<T> getVertice2() {
        return vertice2;
    }

    public boolean esDirijida() {
        return esDirigida;
    }

    @Override
    public String toString() {
        return "(" + vertice1 + ", " + vertice2 + ")"
                + "[Es dirigida = " + esDirigida + ", Peso = " + peso + "]";
    }
}
