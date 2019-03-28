public class AlfabetosMezclados{
	
	static String alfabetoMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .";

	public static String codificar(String mensaje, String llave){
		String mixAlfabeto="";
		String llaveSR=quitarRepeticiones(llave);
		mixAlfabeto += llaveSR;
		String cadenaCifrada="";
		for (int i = 0; i < alfabetoMayusculas.length(); i++){
			int j = llaveSR.length();
			char actual = alfabetoMayusculas.charAt(i);
			if (mixAlfabeto.indexOf(actual)==-1)
				mixAlfabeto += actual;
		}
		for (int i=0; i<mensaje.length(); i++){
			char caracter = mensaje.charAt(i);
			int indice = alfabetoMayusculas.indexOf(caracter);
			char charCifrado = mixAlfabeto.charAt(indice);
			cadenaCifrada +=  charCifrado;
		}
		return cadenaCifrada;
	}

	public static String decodificar(String mensajeCod, String llave){
		String mixAlfabeto="";
		String llaveSR=quitarRepeticiones(llave);
		mixAlfabeto += llaveSR;
		String cadenaDescifrada="";
		for (int i = 0; i < alfabetoMayusculas.length(); i++){
			int j = llaveSR.length();
			char actual = alfabetoMayusculas.charAt(i);
			if (mixAlfabeto.indexOf(actual)==-1)
				mixAlfabeto += actual;
		}
		for (int i=0; i<mensajeCod.length(); i++){
			char charCifrado = mensajeCod.charAt(i);
			int indice = mixAlfabeto.indexOf(charCifrado);
			char caracter = alfabetoMayusculas.charAt(indice);
			cadenaDescifrada +=  caracter;
		}
		return cadenaDescifrada;
	
	}

	private  static String quitarRepeticiones(String cadena){
		String rtn="";
		for(int i=0; i<cadena.length(); i++){
			char actual = cadena.charAt(i);
			if (rtn.indexOf(actual)==-1){
				rtn += actual;
			}
		}
		return rtn;
	}

	public static void main(String args[]){
		System.out.println("Mensaje: \nCIFRADOALFABETOSMEZCLADOSCRIPTOGRAFIAYSEGURIDAD");
		String c = codificar("CIFRADOALFABETOSMEZCLADOSCRIPTOGRAFIAYSEGURIDAD","OBSERVACION");
		System.out.println("mensaje codificado: \n" + c);
		String d = decodificar(c, "OBSERVACION");
		System.out.println("mensaje decodificado: \n" + d);
	}

}