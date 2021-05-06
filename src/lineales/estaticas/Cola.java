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
public class Cola {

    private final int TAM = 7;
    private Object[] arreglo;
    private int frente = 0;
    private int fin = 0;

    public Cola() {
        this.arreglo = new Object[TAM];
    }

    public boolean poner(Object e) {
        boolean exito;
        if (this.frente == ((this.fin + 1) % this.TAM)) {
            exito = false;
        } else {
            this.arreglo[fin] = e;
            this.fin = (this.fin + 1) % TAM;
            exito = true;
        }
        return exito;
    }

    public boolean sacar() {
        boolean exito;
        if (this.frente == this.fin) {
            exito = false;
        } else {
            this.arreglo[frente] = null;
            this.frente = (this.frente + 1) % TAM;
            exito = true;
        }
        return exito;
    }

    public Object obtenerFrente() {
        return this.arreglo[this.frente];
    }

    public boolean esVacia() {
        return this.frente == this.fin;
    }

    public void vaciar() {
        while (this.frente != this.fin) {
            this.arreglo[this.frente] = null;
            this.frente = (this.frente + 1) % TAM;
        }
    }

    public Cola clone() {
        //corregir
        Cola clon = new Cola();
        if(!this.esVacia()){
        int i = this.frente;
        clon.frente=i;
        clon.fin=i;
        while (i != this.fin) {
            clon.arreglo[i] = this.arreglo[i];
            i = (i + 1) % TAM;
            clon.fin = (clon.fin + 1) % TAM;
        }}
        return clon;
    }

    public String toString() {
        String s = "";
        int aux = this.frente;
        while (aux != this.fin) {
            s = s + this.arreglo[aux].toString() + "|";
            aux = (aux + 1) % TAM;
        }
        return s;
    }
}
