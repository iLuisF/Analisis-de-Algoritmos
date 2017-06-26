
/**
 * QuickSort, MergeSort y BubbleSort. Además de la implementación de sus respectivos 
 * mejores y peores casos. La explicación de cada uno viene en el reporte con formato pdf.
 * @author Flores González Luis Brandon
 * @version 1.0
 */
public class Practica2{

    /**
     * Ordena un arreglo de enteros crecientemente usando Quick Sort.
     * @return Arreglo ordenado.
     */
    public static int[] quickSort(int[] arr){
	return quickSort(arr, 0, arr.length - 1);
    }

    /**
     * Ordena un arreglo de enteros.
     * @param a - limite inferior.
     * @param b - limite superior.
     */
    private static int[] quickSort(int[] arr, int a, int b){
	int j;
	if(a < b){
	    j = particion(arr, a, b);
	    quickSort(arr, a, j - 1);
	    quickSort(arr, j + 1, b);
	}
	return arr;
    }

    /**
     * Particiona un arreglo, escogiendo como pivote el primer elemento del arreglo.
     * @param a - limite inferior.
     * @param b - limite superior.
     */
    public static int  particion(int[] arr, int a, int b){
	int i, j;
	i = a + 1;
	j = b;

	while(arr[i] < arr[a] && i!=b){
	    i = i + 1;
	}
	while(arr[j] > arr[a] && j!=a){
	    j = j - 1;
	}
	while(i < j){
	    int tmp = arr[i];
	    arr[i] = arr[j];
	    arr[j] = tmp;
	    i = i + 1;
	    j = j - 1;
	    while(arr[i] < arr[a] && i!=b){
		i = i + 1;
	    }
	    while(arr[j] > arr[a] && j!=a){
		j = j - 1;
	    }
	}
	if(a < j){
	    int tmp = arr[a];
	    arr[a] = arr[j];
	    arr[j] = tmp;
	}
	return j;
    }
    
    /**
     * Ordena un arreglo de enteros crecientemente.
     * @return Arreglo ordenado crecientemente.
     */
    public static int[] mergeSort(int[] arr){
	if (arr.length > 1) {
	    mergeSort(arr, 0, arr.length - 1);
	}
	return arr;
    }

    /**
     * Ordena un arreglo de enteros crecientemente.
     * @param arr - Arreglo a ordenar.
     * @param izq - limite inferior.
     * @param der - limite superior.
     * @return Arreglo ordenado.
     */
    private static int[] mergeSort(int[] arr, int izq, int der) {
	if (izq < der) {
	    int enmedio = (izq + der) / 2;
	    mergeSort(arr, izq, enmedio);
	    mergeSort(arr, enmedio + 1, der);
	    merge(arr, izq, enmedio, der);
	}
	return arr;
    }

    /**
     * Mezcla los arreglos de Merge Sort.
     */
    private static void merge(int[] arr, int primero, int enmedio, int ultimo) {
	int i = primero;
	int d = enmedio + 1;
	int r = 0;
	int[] resul = new int[ultimo - primero + 1];
	/* Se compara el i-esimo elemento de la parte izquierda y
	 * el d-esimo de la derecha. T que resulte menor se pasa
	 * al arreglo resultante.
	 */
	while ((i <= enmedio) && (d <= ultimo)) {
	    if (arr[i] <= arr[d]) {
		resul[r++] = arr[i++];
	    } else {
		resul[r++] = arr[d++];
	    }
	}
	/* Se acaba una o ambas mitades.
	 * Si faltaron elementos por copiar, generalmente de una de
	 * las mitades...
	 */
	while (i <= enmedio) {
	    resul[r++] = arr[i++];
	}
	while (d <= ultimo) {
	    resul[r++] = arr[d++];
	}
	// se copia al arreglo arreglo
	for (i = primero, d = 0; i <= ultimo; i++, d++) {
	    arr[i] = resul[d];
	}
    }

    /**
     * Ordena un arreglo crecientemente mendiante Bubble Sort.
     * @param arr - Arreglo a ordenar.
     * @return Arreglo ordenado.
     */
    public static int[] bubbleSort(int[] arr){
	boolean sorted = false; //falso cuando ocurre un intercambio.
	int temp;
	for (int pass = 1; (pass < arr.length) && !sorted; ++pass) {
	    //invariante: A[n+1-pass.. n-1] esta ordenado

	    sorted = true; //Supone que esta ordenado.
	    for (int i = 0; i < arr.length - pass; i++) {
		int sig = i + 1; //Siguiente indice.
		if (arr[i] > arr[sig]) { //hay desorden, intercambia.
		    temp = arr[i];
		    arr[i] = arr[sig];
		    arr[sig] = temp;
		    sorted = false;
		}

	    }
	}
	return arr;
    }   

    /**
     * Si el vector ya esta ordenado, ya sea crecientemente o decrecientemente.
     * Entonces se puede usar el peor caso de Bubble Sort.
     * @param tam - tamaño del peor caso del arreglo de enteros.
     * @return Peor caso de tamaño tam.
     */
    public static int[] peorCasoQuickSort(int tam){
	return peorCasoBubbleSort(tam);
    }

    /**
     * Si ocurre el mayor número de comparaciones entonces esta en el peor caso.
     * @param tam - tamaño del peor caso del arreglo de enteros.
     * @return Arreglo de enteros que provoca el peor caso.
     */
    public static int[] peorCasoMergeSort(int tam){
	return peorCasoMergeSort(mejorCasoBubbleSort(tam));
    }

    /**
     * Genera el peor caso de Merge Sort dado necesariamente
     * un arreglo ordenado.
     * @param arr - Necesariamente un arreglo ordenado.
     * @return Arreglo de enteros que provoca el peor caso.
     */
    public static int[] peorCasoMergeSort(int[] arr){
	if(arr.length <= 1){
	    return arr;
	}
	if(arr.length == 2){
	    int tmp = arr[0];
	    arr[0] = arr[1];
	    arr[1] = tmp;
	    return arr;
	}

	int m = (arr.length + 1) / 2;
	int left[] = new int[m];
	int right[] = new int[arr.length - m];

	for(int i = 0, j = 0; i < arr.length; i = i + 2, j++){
	    left[j] = arr[i];
	}

	for(int i = 1, j = 0; i < arr.length; i = i + 2, j++){
	    right[j] = arr[i];
	}
 
	peorCasoMergeSort(left);
	peorCasoMergeSort(right);
	mergeWorst(arr, left, right);
	return arr;
    }

    /**
     * Auxiliar para generar el peor caso de Merge Sort, es decir, mezcla los
     * subarreglos.
     */
    public static int[] mergeWorst(int[] arr, int[] left, int[] right){
	int i;
	for(i = 0; i < left.length; i++){
	    arr[i] = left[i];
	}
	for(int j = 0; j < right.length; j++, i++){
	    arr[i] = right[j];
	}
	return arr;
    }
    
    /**
     * Si el vector esta ordenado en orden inverso entonces provoca el peor caso.
     * @return Arreglo de enteros que provoca el peor caso.
     */
    public static int[] peorCasoBubbleSort(int tam){
	int[] peorCaso = new int[tam];
	int j =  tam;
	for(int i = 0; i < tam; i++){
	    peorCaso[i] = j--;
	}
	return peorCaso;
    }

    /**
     * Genera el mejor caso para Quick Sort, donde siempre el pivote divide a la
     * mitad del arreglo, hasta que este quede totalmente ordenado.
     * @param tam - Tamaño del peor caso.
     */
    public static int[] mejorCasoQuickSort(int tam){//Corregir
	return mejorCasoQuickSort(mejorCasoBubbleSort(tam));
    }

    //Auxiliares de mejorCasoQuickSort

    /**
     * Quita el primer elemento de un arreglo.
     * @return Mismo arreglo sin el primer elemento.
     */
    public static int[] quitarInicio(int[] arr){
	int[] aux = new int[arr.length];
	int enmedio = arr.length / 2;       
	aux[0] = arr[enmedio];
	int i;
	for(i = 1; i <= enmedio; i++){
	    aux[i] = arr[i-1];
	}
	for(int j = enmedio + 1; j < aux.length; j++, i++){
	    aux[i] = arr[j];
	}
	return aux;
    }

    /**
     * @return Concatenacion de dos arreglos
     */
    public static int[] concatenar(int[] arr1, int[] arr2){
	int[] aux = new int[arr1.length + arr2.length];
	int i;
	for(i = 0; i < arr1.length; i++){
	    aux[i] = arr1[i]; 
	}
	for(int j = 0; j < arr2.length; j++, i++){
	    aux[i] = arr2[j];
	}
	return aux;
    }

    /**
     * @return Concatenacion de un elemento y una arreglo.
     */
    public static int[] concatenar(int head, int arr[]){
	int[] aux = new int[arr.length + 1];
	aux[0] = head;
	for(int i = 1; i < aux.length; i++){
	    aux[i] = arr[i-1];
	}
	return aux;
    }

    /**
     * @return Primer elemento de un arreglo.
     */
    public static int getPrimero(int[] arr){
	return  arr[0];
    }

    /**
     * @return Arreglo sin el primero elemento.
     */
    public static int[] getCola(int[] arr){
	int[] cola = new int[arr.length - 1];
	for(int i = 0; i < cola.length; i++){
	    cola[i] = arr[i + 1];
	}
	return cola;
    }

    /**
     * Genera el mejor caso de Quick Sort.
     * @param Arreglo necesariamente ordenado de manera creciente.
     * @return Mejor caso de Quick Sort.
     */
    public static int[] mejorCasoQuickSort(int[] arr){
	if(arr.length < 3){
	    return arr;
	}else{
	    int[] aux = quitarInicio(arr);
	    int enmedio = arr.length / 2;
	    int[] izq = new int[enmedio];
	    int[] der = new int[arr.length - enmedio];

	    int i;
	    for(i = 0; i < izq.length; i++){
		izq[i] = aux[i];
	
	    }
	    for(int j = 0; j < der.length; j++, i++){
		der[j] = aux[i];		
	    }

	    return concatenar(concatenar(getPrimero(izq), mejorCasoQuickSort(getCola(izq))),
			      concatenar(getPrimero(der), mejorCasoQuickSort(getCola(der))));
	}
    }

    //Acaban auxiliares de mejorCasoQuickSort
    
    /**
     * Si el vector esta ordenado previamente entonces ocurre el mejor caso.
     * @return Arreglo ordenado.
     */
    public static int[] mejorCasoMergeSort(int tam){
	return mejorCasoBubbleSort(tam);	    
    }

    //Si el vector esta ordenado previamente ocurre el mejor caso.
    public static int[] mejorCasoBubbleSort(int tam){
	int[] mejorCaso = new int[tam];
	for(int i = 0; i < tam; i++){
	    mejorCaso[i] = i;
	}
	return mejorCaso;	    
    }
    
    public static void main(String[] args){
	long tiempoInicio, tiempoFin;
	int[] tamanos = {10, 100, 200, 500, 1000, 2000, 5000};
	System.out.println("longitud del arreglo:");
	for (int i = 0; i < tamanos.length; ++i){
	    System.out.print("\t"+(tamanos[i]));
	}
		
	System.out.println("\n\nTiempos para QuickSort:\nPeor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = peorCasoQuickSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = quickSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}		
	System.out.println("\nmejor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = mejorCasoQuickSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = quickSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}
		

	System.out.println("\n\nTiempos para MergeSort:\nPeor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = peorCasoMergeSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = mergeSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}
	System.out.println("\nmejor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = mejorCasoMergeSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = mergeSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}

	System.out.println("\n\nTiempos para BubbleSort:\nPeor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = peorCasoBubbleSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = bubbleSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}
	System.out.println("\nmejor Caso:");
	for (int i = 0; i < tamanos.length; ++i){
	    int[] arr = mejorCasoBubbleSort(tamanos[i]);
	    tiempoInicio = System.currentTimeMillis();
	    arr = bubbleSort(arr);
	    tiempoFin = System.currentTimeMillis();
	    System.out.print("\t"+(tiempoFin - tiempoInicio));
	}
    }
}
