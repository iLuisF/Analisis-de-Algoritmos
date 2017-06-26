
package mx.unam.fciencias.EstDat;

/**
 * Interfaz que define las firmas de los métodos asociados
 * con iteradores.
 *
 * @param <T>
 * @since  2.0
 * @version 2.0 <br>
 */
public interface Iterador<T> {

   /**
    * Posiciona el iterador en el primer elemento de la
    * estructura
    * @throws ExcepcionAccesoIncorrecto si la estructura es
    * vacía
    */
   public void setInicio()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Determina si hay un siguiente elemento.
    * @return <code>true</code> si hay un siguiente
    * elemento, <code>false</code>  en otro caso.
    */
   public boolean haySiguiente();

   /**
    * Avanza el iterador al siguiente elemento disponible en
    * la estructura.
    * @throws ExcepcionAccesoIncorrecto si ya no hay siguiente elemento.
    */
   public void avanza()
      throws ExcepcionAccesoIncorrecto;

   /**
    * Regresa el elemento en la posición actual del
    * iterador.
    * @return el elemento almacenado en la posición actual
    * de la estructura.
    */
   public T elementoActual();

   /**
    * Regresa la posición actual del iterador.
    * @return un enetero no negativo con la posición actual
    * del iterador en la estructura.
    */
   public int posicion();
} // Fin de Iterador.java
