/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.dinamicas;

/**
 *
 * @author gonzalo
 */
public class Cola {

    private Nodo frente;
    private Nodo fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean poner(Object e) {
        Nodo n = new Nodo(e, null);
        if (this.frente == null) {
            this.frente = n;
            this.fin = n;
        } else {
            this.fin.setEnlace(n);
            this.fin = n;
        }
        return true;
    }

    public boolean sacar() {
        boolean exito = true;
        if (this.frente == null) {
            exito = false;
        } else {
            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
        }
        return exito;
    }

    public Object obtenerFrente() {
        //si esta vacia retorna null
        return this.frente.getElem();
    }

    public boolean esVacia() {
        boolean vacia = false;
        if (this.frente == null) {
            vacia = true;
        }
        return vacia;
    }

    public void vaciar() {
        this.frente = null;
        this.fin = null;
    }

    public Cola clone() {
        Cola c1 = new Cola();
        Nodo aux, nuevo;
        //la condicion de vacia SIEMPRE tiene que tenerse como caso especial
        if (this.frente != null) {
            aux = this.frente;
            nuevo = new Nodo(aux.getElem(), null);//null
            c1.frente = nuevo;
            c1.fin = nuevo;
            while (aux.getEnlace() != null) {
                nuevo = new Nodo(aux.getEnlace().getElem(), null);
                c1.fin.setEnlace(nuevo);
                c1.fin = nuevo;
                aux = aux.getEnlace();
            }
        }
        return c1;
    }

    public String toString() {
        String s = "";
        Nodo aux;
        aux = this.frente;
        while (aux != null) {
            s = s + aux.getElem().toString() + "|";
            aux = aux.getEnlace();
        }
        return s;
    }
}
