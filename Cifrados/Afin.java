/*
* Cifrado Afin
*/

//import Euclides;

public class Afin{

	private int longAlfabeto;
	private String alfabeto;

	public Afin(int longAlfabeto){
		this.longAlfabeto = longAlfabeto;
		switch(longAlfabeto){
			case 27:
				alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
				break;
			default:
				alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				break;
		}
	}

	public Afin(){
		this.longAlfabeto = 26;
		alfabeto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	}

	public String cifrar(String cadena, int decimado, int desplazamiento){
		String rtnCadena = "";
		// Falta procesar la cadena para ponerla en mayusculas, quitar espacios y otros simbolos
		int x;
		int dx;
		char cipherChar;
		for (int i=0; i<cadena.length(); i++){
			x = alfabeto.indexOf(cadena.charAt(i));
			dx = ((x*decimado) + desplazamiento) % longAlfabeto;
			cipherChar = alfabeto.charAt(dx);
			rtnCadena += cipherChar;
			
		}
		return rtnCadena;
	}


	public String descifrar(String cadena, int decimado, int desplazamiento){
		Euclides e = new Euclides(); // En la clase Euclides esta el metodo para encontrar el inverso modulo n que necesitamos
		String rtnCadena = "";
		// Falta procesar la cadena para ponerla en mayusculas, quitar espacios y simbolos
		int x;
		int dx;
		char car;
		int in = e.obtenerInverso(decimado, longAlfabeto); 
		int d;
		for (int i=0; i<cadena.length(); i++){
			
			x = alfabeto.indexOf(cadena.charAt(i));

			d = x-desplazamiento;

			if (d<0){
				dx = ((d + longAlfabeto) * in)%longAlfabeto;
			}else{
				dx = (d * in) % longAlfabeto;
			}

			car = alfabeto.charAt(dx);
			rtnCadena += car;
		}

		return rtnCadena;
	}
	
	/*public static void main(String[] args){
		Afin a = new Afin(26);
		String x = a.cifrar("ESTABALAPAJARAPINTASENTADAENUNVERDELIMON", 3, 1);
		System.out.println(x);
		String c = a.descifrar(x,3,1);
		System.out.println(c);
	}*/
}