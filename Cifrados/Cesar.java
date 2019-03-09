/**
Cifrado Cesar
*/
public class Cesar{

	private int longAlfabeto;
	private String alfabeto;

	public Cesar(int longAlfabeto){
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

	public String cifrar(String cadena, int desplazamiento){
		String rtnCadena = "";
		// Falta procesar la cadena para ponerla en mayusculas, quitar espacios y simbolos
		int x;
		int dx;
		char cipherChar;
		for (int i=0; i<cadena.length(); i++){
			x = alfabeto.indexOf(cadena.charAt(i));
			dx = (x + desplazamiento) % longAlfabeto;
			cipherChar = alfabeto.charAt(dx);
			rtnCadena += cipherChar;
			
		}
		return rtnCadena;
	}

	public String descifrar(String cadena, int desplazamiento){
		String rtnCadena = "";
		// Falta procesar la cadena para ponerla en mayusculas, quitar espacios y simbolos
		int x;
		int dx;
		char car;
		for (int i=0; i<cadena.length(); i++){
			x = alfabeto.indexOf(cadena.charAt(i));
			int d = (x - desplazamiento);
			if (d<0){
				dx = longAlfabeto + d;
			}else{
				dx = d % longAlfabeto;
			} 
			car = alfabeto.charAt(dx);
			rtnCadena += car;
		}

		return rtnCadena;
	}
	
}