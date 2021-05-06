/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author gonzalo
 */
public class Pila {

    private static final int TAM = 15;
    private Object[] arreglo;
    private int tope = -1;

    public Pila() {
        this.arreglo = new Object[TAM];
        this.tope = -1;
    }

    public boolean apilar(Object elem) {
        boolean exito;
        if (this.tope + 1 >= this.TAM) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = elem;
            exito = true;
        }
        return exito;
    }

    public boolean desapilar() {
        boolean exito;
        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            tope--;
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object o;
        if (this.tope == -1) {
            o = null;
        } else {
            o = this.arreglo[this.tope];
        }
        return o;
    }

    public boolean esVacia() {
        return this.tope == -1;
    }

    public void vaciar() {
        int i;
        if (this.tope >= 0) {
            for (i = 0; i <= this.tope; i++) {
                this.arreglo[i] = null;
            }
            this.tope = -1;
        }
    }

    public Pila clone() {
        Pila p1 = new Pila();
        int i;
        for (i = 0; i <= this.tope; i++) {
            p1.arreglo[i] = this.arreglo[i];
        }
        p1.tope = this.tope;
        return p1;
    }

    public String toString() {
        String cadena = "";
        int i;
        for (i = 0; i <= this.tope; i++) {
            cadena = cadena + "|" + this.arreglo[i].toString() + "|";
        }
        return cadena;
    }
}
