
/**
 * Programa de adoquinamiento en funcionamiento.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Main {

    /**
     * Se muestra el ejemplo con un cuadrado especial en la coordenada (2, 2) en
     * un tablero de 8 x 8.
     *
     * @param args Sin uso.
     */
    public static void main(String[] args) {
        try {
            int tam = 8;
            Coordenada cuadradoEspecial = new Coordenada(2, 2);
            Tablero prueba = new Tablero(tam, cuadradoEspecial);
            prueba.adoquinarTablero();
        } catch (TableroException error) {
            System.out.println(error.getMessage());
        }
    }
}
