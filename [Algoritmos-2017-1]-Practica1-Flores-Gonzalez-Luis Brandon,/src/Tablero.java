
/**
 * Representación de un tablero de adoquines tal que existe un cuadrado especial.
 * Como identificador de cada adoquín se toma un número diferente para
 * representarlo. El 0 representa al cuadrado especial.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Tablero {

    private final int[][] tablero;
    private int adoquin;
    private final Coordenada cuadrado;

    /**
     * Crea un tablero de (m x m) tal que m = 2^(n-1) para algun n que pertenece
     * a los números naturales.
     *
     * @param tam Tamaño del tablero.
     * @param especial Cuadrado especial.
     * @throws TableroException En caso de ser diferente a 2^(n-1).
     */
    public Tablero(int tam, Coordenada especial) throws TableroException {
        if (!esValido(tam)) {
            throw new TableroException("El tamaño del tablero debe ser igual "
                    + "a 2^(n-1) para algun n natural.");
        } else {
            this.tablero = new int[tam][tam];
            this.adoquin = 1;
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    this.tablero[i][j] = -1;
                }
            }
            this.tablero[especial.getX()][especial.getY()] = 0;
            this.cuadrado = especial;
        }
    }

    /**
     * Verifica si el tamaño de un tablero es valido.
     *
     * @param tam Región del área del tablero.
     * @return True si es valido false en otro caso.
     */
    private Boolean esValido(int tam) {
        int i = 1;
        Boolean tamanioValido;
        while (i < tam) {
            i = i * 2;
        }
        tamanioValido = (i == tam);
        return tamanioValido;
    }

    /**
     * Identifica el cuadrante apartir de una coordenada. Primer cuadrante:
     * Cuadrante superior izquierdo. Segundo cuadrante: Cuadrante superior
     * derecho. Tercer cuadrante: Cuadrante inferior izquierdo. Cuarto
     * cuadrante: Cuadrante inferior derecho.
     *
     * @param tam tamaño del tablero.
     * @param id Coordenada del cuadrado especial.
     * @return El cuadrante de la coordenada dada.
     */
    private int numeroCuadrante(int tam, Coordenada id) { //Paso inductivo.   
        int numeroCuadrante;
        if (this.cuadrado.getX() < id.getX() + tam / 2
                && this.cuadrado.getY() < id.getY() + tam / 2) {
            numeroCuadrante = 1;
        } else if (this.cuadrado.getX() < id.getX() + tam / 2
                && this.cuadrado.getY() >= id.getY() + tam / 2) {
            numeroCuadrante = 2;
        } else if (this.cuadrado.getX() >= id.getX() + tam / 2
                && this.cuadrado.getY() < id.getY() + tam / 2) {
            numeroCuadrante = 3;
        } else {
            numeroCuadrante = 4;
        }
        return numeroCuadrante;
    }

    /**
     * Adoquina la región de m x m con el adoquín dado, cubriendo uno cada
     * cuadrado exactamente una vez, con excepción del cuadrado especial, el
     * cual no sera cubierto por ningún adoquín.
     *
     * @param tam Tamaño del tablero o subtablero.
     * @param id
     */
    private void adoquinar(int tam, Coordenada id) {
        if (tam == 1) {
            if (this.tablero[id.getX()][id.getY()] == -1) {
                this.tablero[id.getX()][id.getY()] = adoquin;
            }
        }
        if (tam == 2) {
            adoquinar(tam / 2, new Coordenada(id.getX() + 1, id.getY()));
            adoquinar(tam / 2, new Coordenada(id.getX(), id.getY() + 1));
            adoquinar(tam / 2, new Coordenada(id.getX() + 1, id.getY() + 1));
            adoquinar(tam / 2, new Coordenada(id.getX(), id.getY()));
            adoquin++;
        }
        if (tam > 2) {
            switch (numeroCuadrante(tam, id)) {
                case 1:
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY()));
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2 - 1] = adoquin;
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2] = adoquin;
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2] = adoquin;
                    adoquin++;
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY() + tam / 2));
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY()));
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY() + tam / 2));
                    break;
                case 2:
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY() + tam / 2));
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2 - 1] = adoquin;
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2] = adoquin;
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2 - 1] = adoquin;
                    adoquin++;
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY()));
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY()));
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY() + tam / 2));
                    break;
                case 3:
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY()));
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2] = adoquin;
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2] = adoquin;
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2 - 1] = adoquin;
                    adoquin++;
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY()));
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY() + tam / 2));
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY() + tam / 2));
                    break;
                case 4:
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY() + tam / 2));
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2] = adoquin;
                    this.tablero[id.getX() + tam / 2][id.getY() + tam / 2 - 1] = adoquin;
                    this.tablero[id.getX() + tam / 2 - 1][id.getY() + tam / 2 - 1] = adoquin;
                    adoquin++;
                    adoquinar(tam / 2, new Coordenada(id.getX() + tam / 2, id.getY()));
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY() + tam / 2));
                    adoquinar(tam / 2, new Coordenada(id.getX(), id.getY()));
                    break;
                default:
                    System.out.println("Cuadrante no encontrado.");
                    break;
            }
        }
    }

    private void imprimirTablero() {
        for (int[] casilla : this.tablero) {
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.print(casilla[j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Adoquina el tablero y lo imprime en la terminal.
     */
    public void adoquinarTablero() {
        this.adoquinar(this.tablero.length, new Coordenada(0, 0));
        this.imprimirTablero();
    }

}
