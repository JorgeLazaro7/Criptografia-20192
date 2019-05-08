#! /usr/bin/env python
#-*- coding: utf-8 -*-

'''
 Integrantes:
 	Hidalgo López Diana Giselle
 	Lázaro Arias Jorge Alberto
'''

from random import randint

'''
 * Genera las claves pública y privada
 * Llave pública = (N, e)
 * Llave privada = (N, d)
'''
def generarllaves():
	#1. Generamos números primos de tamaño 335 bits(para representar números de 100 digitos decimales)
	p = generarPrimo(355)
	q = generarPrimo(355)

	print("------Generamos primos aleatorios p y q-------")
	print("p = ",p)
	print("q = ",q)


	#2. Calculamos n = pq y φ = (p − 1)(q − 1). (See Note 8.5.)
	print("------ n=pq y phi=(p − 1)(q − 1) -----")
	n = p*q
	phi = (p-1)*(q-1)

	print("n = ",n)
	print("phi = ", phi)


	#3. Select a random integer e, 1 < e < φ, such that gcd(e, φ) = 1.
	#4. Use the extended Euclidean algorithm (Algorithm 2.107) to compute 
	#   the unique integer d, 1 < d < φ, such that ed ≡ 1 (mod φ).
	e,d=generarPrimoRelativo(phi)

	print("e = ",e)
	print("d = ", d)

	'''
	5. A’s public key is (n, e); A’s private key is d.
	'''
	llavePublica = [n,e]
	llavePrivada = [n,d]

	print("llave Publica = [n,e] = ", n, ",", e)
	print("lave Privada = [n,d]=",n,",", d)

	return llavePublica,llavePrivada


# Cifrar recibe el mensaje y la llave publica (n,e)
# c = m^e mod n
def cifrar(message, puk):
	cipher = [pow(ord(char), puk[1], puk[0]) for char in message]
	return cipher


# Descifrar recibe el mensaje cifrado y la llave publica (n,e)
# m = c^d mod n
def descifrar(message, prk):
	decipher = [ chr(pow(char, prk[1], prk[0])) for char in message]
	return ''.join(decipher)


'''
* Para generar los primos usamos el test de primalidadde Fermat 
'''
def esPrimo(n, t):
    if n == 1:
        return False
    if t >= n:
        t = n - 1
    for x in range(t):
        val = randint(2, n - 2)
        if pow(val, n-1, n) != 1:
            return False
    return True

'''
* Generamos un número primo de tamaño n bits
'''
def generarPrimo(n):
    primo = False
    while not primo:
        p = randint(2**(n-1), 2**n)
        if esPrimo(p, 1000):
            return p

# Buscamos un número p entre 2 y n-1 que sea primo relativo a n
# Regresamos p el primo relativo y i el número tal que p*1 mod n = 1
def generarPrimoRelativo(n):
	primoR = False
	while not primoR:
		p = randint(2,n-1)
		d,x,i = euclidesExtendido(n,p)
		if (d==1):
			return p,i%n


# recibe dos enteros a y b
    # devuelve d,x,y donde:
    # d = mcd(a,b)
    # x = a^-1 mod b en caso de d=1
    # y = b^-1 mod a en caso de d=1
    # NOTA: x y y pueden ser negativos
def euclidesExtendido(a,b):
    if b == 0:
        return a, 1, 0
    d1,x1,y1 = euclidesExtendido(b, a % b)
    d = d1
    x = y1
    y = x1 - (a//b) * y1
    return d, x, y



if __name__ == '__main__':

    llavePublica, llavePrivada = generarllaves()

    mensaje = "Segundo Proyecto de Criptografía y Seguridad. RSA. Jorge Alberto Lázaro Arias, Diana Giselle Hidalgo López"

    print("\n\n")
    print("mensaje: ", mensaje)

    cifrado = cifrar(mensaje,llavePublica)
    print("\n\n")
    print("cifrado: ", cifrado)

    mensajeR= descifrar(cifrado, llavePrivada)
    print("\n\n")
    print("descifrado: ", mensajeR)

