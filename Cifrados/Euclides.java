
import java.util.*;

public class Euclides{
	
	public int mcd(int dividendo, int divisor){
		if (!(dividendo >= divisor || divisor > 0) ){
			System.out.printf("dividendo debe ser mayor a cero o divisor debe ser mayor a cero");
			return -1;
		}

		int	residuo = dividendo % divisor; //modulo

		//actualizamos nuestras variables
		while(residuo > 0){
			dividendo = divisor;
			divisor = residuo;
			residuo = dividendo % divisor;
		}

		return divisor;
	}


	/*Hacemos un recorrido desde 1 hasta n para
	encontrar a los primos relativos.

	Si el mcd de (n,i) = 1 entonces el valor de i
	es una unidad
	*/
	public List<Integer> encuentraUnidades(int n){
		List<Integer> unidades = new ArrayList<Integer>();
		int num;
		int contador=0; //indica cuántas unidades encontró
		for(int i=1; i <= n; i++){
			num = mcd(n,i);
	
			if(num == 1){
				unidades.add(i);
				//System.out.printf("\ni= "+i);
				contador += 1;
			}
		}
		System.out.printf("\n" +n +" tiene "+contador +" unidades\n");
		return unidades;
	}



    public int obtenerInverso(int n, int m){
    	int r = m % n; // primer residuo;
    	int c;
    	int x=n;
    	int y=r;
    	int c1 = 1;
    	int c2 = -(m/n);
    	int t1 = 0;
    	int t2 = 1;
    	while(r!=0){
    		c = x/y;
    		r = x%y;
    		//guardamos valores temporales de los coeficientes
	        //multiplicamos los coeficiente por -1*cociente de la division
    	    c1*=-c;
        	c2*=-c;
	        //sumamos la corrida anterior
    	    c1+=t1;
        	c2+=t2;
	        //actualizamos corrida anterior
    	    t1=-(c1-t1)/c;
        	t2=-(c2-t2)/c;
	        x=y;
    	    y=r;
    	}

    	if (x==1){
    		return (t2 < 0)? t2 + m : t2;
    	}
    	else{
    		System.out.println("Error, no existe el inverso");
    		return -1;
    	} 
    }


	/*public static void main (String args[]){
		List<Integer> u = encuentraUnidades(1024);
		Iterator<Integer> i = u.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		//System.out.println("El inverso de 11 modulo 26 es: ");
		//int x = obtenerInverso(11, 26);
		//System.out.println(x);

	}*/
}	