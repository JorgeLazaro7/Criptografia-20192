import java.lang.Math;

/*
* Clase Hill hace el cifrado y descifrado de Hill de un texto en mayúsculas sin espacios con una llave
* La matriz de cifrado se calcula a partir de una palabra llave dada de acuerdo a su longitud:
* Si la longitud de la palabra es uno, la matriz es de 1x1, Si la longitud es entre 2 y 4 la matriz es de 2x2, 
* si la longitud es entre 5 y 9, la matriz es de 3x3, si la longitud es entre 10 y 16, la matriz es de 4x4, etc...
* Sugerencia: Algunas palabras coherentes que nos dan una matriz invertible: {HILL, SOLSTICIO, MATRICIAL, SUBLIME, FORTALEZA, ANTIDERRAPANTE, MICA, MESA, PELICULA, PELICULAS}
*/

public class Hill{

	private static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
	private static int[][] matriz;
	private static int[][] inversa;
	//private static int longitud

	public static String cifrar(String texto, String llave){
		// Primero debemos calcular la matriz de cifrado
		matriz = matrizCifrado(llave);

		System.out.println("\nMatriz de cifrado: \n");

		for (int x=0; x < matriz.length; x++) {
      		System.out.print("|");
      		for (int y=0; y < matriz[x].length; y++) {
        		System.out.print (matriz[x][y]);
        		if (y!=matriz[x].length-1) 
        			System.out.print("\t");
      		}
      		System.out.println("|");
    	}

    	// Vamos a intentar calcular la matriz inversa, si no se puede es por que la matriz no era invertible y cachamos la excepcion
		try{
			ModMatrix m = new ModMatrix(matriz);
			inversa = m.toInt(m.inverse(m));

			// Si no salio la Excepcion, la matriz en invertible, seguimos
			// Los vectores del texto antes y despues de cifrar son vectores columna de tamaño n, n es el numero de filas de la matriz
			int[][] vectorCifrado = new int[matriz.length][1];
			int[][] vectorClaro = new int[matriz.length][1];

			String textoCifrado = "";

			int longitud = texto.length();
			// techo de dividir el texto en los segmentos necesarios: Se calcula el techo por si la longitud no es multiplo de n, 
			int techoLTexto = (int) Math.ceil((double)texto.length()/(double)matriz.length);
			int a=0;
			int entrada;
			for (int i=0; i<techoLTexto; i++){
				for (int j=0; j<matriz.length; j++){
					// Si la longitud del texto no es multiplo de n, se va a completar con A's hasta hacerlo multiplo de n
					a = matriz.length*i + j;
					if(a<texto.length()){
						entrada = alfabetoMayusculas.indexOf(texto.charAt(a));
					}else{
						entrada = 0;
					}
					vectorClaro[j][0]=entrada;
					entrada++;
				}
				// Concretamente este es el cifrado (multiplicacion del vector de indices del segmento del texto en claro con la matriz de cifrado)
				vectorCifrado = multiplicacionModular(matriz, vectorClaro, 27);
				
				// Vamos concatenando los resultados para obtener el texto cifrado
				for (int j=0; j<matriz[0].length; j++){
					textoCifrado += alfabetoMayusculas.charAt(vectorCifrado[j][0]);
				}
			}

			return textoCifrado;

		// Si la llave nos dio una matriz no invertible
		}catch(Exception e){
			System.out.println("\nError matriz no invertible. Intente con otra llave");
			return "Error";
		}
		
	}

	// Método descifrar, recibe un texto cifrado y la palabra clave
	public static String descifrar(String texto, String llave){
		// Primero debemos calcular la matriz de cifrado
		matriz = matrizCifrado(llave);
		
		// Vamos a intentar calcular la matriz inversa, si no se puede es por que la matriz no era invertible y cachamos la excepcion
		// Esa matriz inversa es nuestra matriz de descifrado.
		try{
			ModMatrix m = new ModMatrix(matriz);
			inversa = m.toInt(m.inverse(m));

			System.out.println("\nMatriz de descifrado: \n");
			
			for (int x=0; x < inversa.length; x++) {
      			System.out.print("|");
      			for (int y=0; y < inversa[x].length; y++) {
        			System.out.print (inversa[x][y]);
        			if (y!=inversa[x].length-1) 
        				System.out.print("\t");
      			}
      			System.out.println("|");
    		}

			// Los vectores del texto antes y despues de descifrar son vectores columna de tamaño n, n es el numero de filas de la matriz
			int[][] vectorCifrado = new int[matriz.length][1];
			int[][] vectorClaro = new int[matriz.length][1];

			String textoClaro = "";

			int longitud = texto.length();

			// techo de dividir el texto en los segmentos necesarios: Se calcula el techo por si la longitud no es multiplo de n, 
			int techoLTexto = (int) Math.ceil((double)texto.length()/(double)inversa.length);

			int a=0;
			int entrada;
			for (int i=0; i<techoLTexto; i++){
				for (int j=0; j<inversa.length; j++){
					// se va a completar el texto con A's hasta hacerlo multiplo de n
					a = inversa.length*i + j;
					if(a<texto.length()){
						entrada = alfabetoMayusculas.indexOf(texto.charAt(a));
					}else{
						entrada = 0;
					}
					vectorCifrado[j][0]=entrada;
					entrada++;
				}
				// Concretamente este es el descifrado 
				//(multiplicacion del vector de indices del segmento del texto cifrado con la matriz de descifrado-que es la inversa de la de cifrado-)
				vectorClaro = multiplicacionModular(inversa, vectorCifrado, 27);
				
				// Vamos concatenando los resultados para obtener el texto descifrado
				for (int j=0; j<inversa[0].length; j++){
					textoClaro += alfabetoMayusculas.charAt(vectorClaro[j][0]);
				}
			}
			return textoClaro;

		// Si la llave nos dio una matriz no invertible
		}catch(Exception e){
			System.out.println("\nError matriz no invertible. Intente con otra llave");
			return "Error";
		}
	}


	// Método para calcular matriz de cifrado (Se recibe una llave de cualquier tamaño si no es n² se completa con X's)
	private static int[][] matrizCifrado(String llave){
		// El tamaño de la matriz será el techo de la raiz de la longitud de la palabra
		int tamaño = (int) Math.ceil(Math.sqrt(llave.length()));
		int matriz[][] = new int[tamaño][tamaño];
		int a = 0;
		int entrada;
		// Llenamos la matriz con el índice de la letra en el alfabeto o con el indice de A si hacen falta letras para la matriz cuadrada
		for (int i=0; i<tamaño; i++){
			for (int j=0; j<tamaño; j++){
				if(a<llave.length()){
					entrada = alfabetoMayusculas.indexOf(llave.charAt(a));
					a++;
				}else{
					// Completamos con A
					entrada = 0;
				}
				matriz[i][j] = entrada;
			}
		}
		return matriz;
	}

	// Método que multiplica la matriz 'a' con la matriz 'b' modulo 'modulo'
	public static int[][] multiplicacionModular(int[][] a, int[][] b, int modulo) {
    	int[][] c = new int[a.length][b[0].length];
    	// se comprueba si las matrices se pueden multiplicar
    	if (a[0].length == b.length) {
        	for (int i = 0; i < a.length; i++) {
            	for (int j = 0; j < b[0].length; j++) {
                	for (int k = 0; k < a[0].length; k++) {
                    	// Se obtiene el valor de la entrada i,j
                    	c[i][j] += a[i][k] * b[k][j];
                	}
            	}
        	}
    	}

    	// Se hace la congruencia de las entradas de la matriz resultado modulo 'modulo'
    	for (int i = 0; i < a.length; i++) {
        	for (int j = 0; j < b[0].length; j++) {	
        		c[i][j] = c[i][j]%modulo;
        	}
        }
    	return c;
	}

	public static void main(String args[]){
		System.out.println("Primer Proyecto \n Diana Lucía Guerrero Chávez \n Jorge Alberto Lázaro Arias\n");
		String claro = "PRIMERPROYECTODECRIPTOGRAFIAYSEGURIDADCIFRADODEHILL";
		String llave = "SOLSTICIO";
		System.out.println("Texto en claro: " + claro);
		System.out.println("Llave: " + llave);
		String c = cifrar(claro,llave);
		System.out.println("\nTexto cifrado: " + c);
		String d = descifrar(c,llave);
		System.out.println("\nTexto Descifrado: " + d);
	}


	

}