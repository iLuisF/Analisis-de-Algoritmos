
package mx.unam.fciencias.EstDat;

/**
 * Interfaz que define las firmas de los métodos asociados
 * con iteradores de listas.
 *
 * @param <T>
 * @since  2.0
 * @version 2.0 <br>
 */
public interface IteradorLista<T> extends Iterador<T> {

   /**
    * Reemplaza el elemento en la posición actual con el
    * dado como argumento.
    * @param elem es el elemento que substituye el contenido
    * en la posición actual.
    */
   public void reemplaza(T elem);
} // Fin de IteradorLista.java
