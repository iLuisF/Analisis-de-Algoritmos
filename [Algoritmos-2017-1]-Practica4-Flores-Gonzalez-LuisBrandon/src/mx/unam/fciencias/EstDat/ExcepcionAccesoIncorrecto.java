
/**
 * Excepción en un caso no esperado en listas ligadas.
 * @author Flores González Luis Brandon.
 */
package mx.unam.fciencias.EstDat;


public class ExcepcionAccesoIncorrecto extends Exception {

    public ExcepcionAccesoIncorrecto() {
        
    }
    
    public ExcepcionAccesoIncorrecto(String msg) {
        super(msg);
    }
}
