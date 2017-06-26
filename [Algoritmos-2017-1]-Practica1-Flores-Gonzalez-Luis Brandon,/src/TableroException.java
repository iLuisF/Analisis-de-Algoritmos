
/**
 * Excepción en caso de que se intente generar un tablero diferente de m = 2^k,
 * tal que la región del tablero es de m x m.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class TableroException extends Exception {

    public TableroException(String mensaje) {
        super(mensaje);
    }

}
