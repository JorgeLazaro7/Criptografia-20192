import java.util.Arrays;

public class Hill{
	static String alfabetoMayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String decodificar(String mensaje, int llave[][]){
		int [][] vectorCifrado = {{-1},{-1}};
		int [][] vectorClaro = {{-1},{-1}};
		String textoClaro = "";
		//if (mensaje.length()%2==1)
			//mensaje+="w";
		for (int i=0; i<mensaje.length()/2; i++){
			vectorCifrado[0][0] = alfabetoMayusculas.indexOf(mensaje.charAt(2*i));
			vectorCifrado[1][0] = alfabetoMayusculas.indexOf(mensaje.charAt(2*i + 1));

			vectorClaro = multiplicacionModular(llave, vectorCifrado, 26);

			textoClaro += alfabetoMayusculas.charAt(vectorClaro[0][0]);
			textoClaro += alfabetoMayusculas.charAt(vectorClaro[1][0]);			

		}
		return textoClaro;
	}


	public static int[][] multiplicacionModular(int[][] a, int[][] b, int modulo) {
    	int[][] c = new int[a.length][b[0].length];
    	// se comprueba si las matrices se pueden multiplicar
    	if (a[0].length == b.length) {
        	for (int i = 0; i < a.length; i++) {
            	for (int j = 0; j < b[0].length; j++) {
                	for (int k = 0; k < a[0].length; k++) {
                    	// aquí se multiplica la matriz
                    	c[i][j] += a[i][k] * b[k][j];
                	}
            	}
        	}
    	}

    	for (int i = 0; i < a.length; i++) {
        	for (int j = 0; j < b[0].length; j++) {	
        		c[i][j] = c[i][j]%modulo;
        	}
        }
    	return c;
	}

	public static void main(String[] args) {
    	//int[][] a = { { 1, 19 }, { 6, 7 } };
    	//int[][] b = { {23},{5} };
    	//int[][] c = multiplicacionModular(a, b, 26);
    	//System.out.println(Arrays.deepToString(c));

    	//System.out.println(b[1][0]);

    	String textoCifrado = "ENELSEMESTREANTERIORIMPARTIELCURSODEALGEBRALINEALUNOENLAFACULTADDECIENCIASDELAUNIVERSIDADAUTONOMADEMEXICOEHICEALGUNASOBSERVACIONESACERCADELESPACIODUALDELESPACIOVECTORIALDELOSNUMEROSREALESSOBREELCAMPODELOSNUMEROSRACIONALESELCUALESUNESPACIODEDIMENSIONINFINITAPARAMOSTRARQUEELESPACIOVECTORIALREALSOBREELCAMPODELOSNUMEROSRACIONALESESUNESPACIOVECTORIALDEDIMENSIONINFINITADEBIALLEGARPRIMEROALTEMADEESPACIOSDUALESYASIDARUNADEMOSTRACIONFORMALDETALHECHOCUANDOLLEGAMOSALTEOREMAQUEDICEQUEUNFUNCIONALLINEALTIENEQUESUKERNELESUNHIPERESPACIOFUEMIOPORTUNIDADDEMOSTRARQUEELESPACIODELOSNUMEROSREALESESDEDIMENSIONINFINITASOBREELCAMPODELOSNUMEROSRACIONALESLAIDEAERAMUYSENCILLAMEFIJABAENLAIMAGENDEUNFUNCINALLINEALELCUALERADEFINIDOCOMOELFUNCIONALEVALUADOENRAIZDEDOSIGUALAUNOYCEROSIELNUMEROREALNOESTABAENELGENERADODERAIZDEDOSMOSTRABAQUERAIZDETRESNOESTABAENELGENERADODERAIZDEDOSYDEFINIAOTROFUNCIONALELCUALSEDEFINIACOMOUNOCUANDOSEEVALUAVAENRAIZDETRESYCEROCUANDOELNUMEROREALNOESTABAENELGENERADODERAIZDETRESESTAIDEAPUEDESEGUIRPARACADANUMEROPRIMOYLOSVECTORESGENERADORESDELASIMAGENESDELOSFUNCIONALESSONLINEALMENTEINDEPENDIENTESASIHAYUNACANTIDADINFINITADEVECTORESLINEALMENTEINDEPENDIENTESDELESPACIOVECTORIALDELOSNUMEROSREALESSOBRELOSNUMEROSRACIONALESASILADIMENSIONDELESPACIODELOSNUMEROSREALESSOBRELOSRACIONALESESDEDIMENSIONINFINITAENESEINSTANTEMEPREGUNTEQUEPASARACONELESPACIODELASFUNCIONESCONTINUASSOBREELCONJUNTODELOSNUMEROSREALESYMESURGIOLAIDEADEENCRIPTARENESPACIOSDEDIMENSIONINFINITATOMANDOFUNCIONESQUEGENERENUNSUBESPACIOENUNESPACIODEDIMENSIONINFINITA";
    	int[][] llave = { { 15, 15 }, { 2, 17 } };

    	System.out.println(decodificar(textoCifrado, llave));

	}


}