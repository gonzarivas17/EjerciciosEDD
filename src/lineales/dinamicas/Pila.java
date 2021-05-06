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
public class Pila {

    private Nodo tope;

    public Pila() {
        this.tope = null;
    }

    public boolean apilar(Object e) {
        Nodo nuevo = new Nodo(e, this.tope);
        this.tope = nuevo;
        return true;
    }

    public boolean desapilar() {
        boolean exito;
        if (this.tope == null) {
            exito = false;
        } else {
            //tope=.getEnlace();
            tope = tope.getEnlace();
            exito = true;
        }
        return exito;
    }

    public Object obtenerTope() {
        Object o;
        if (this.tope == null) {
            o = null;
        } else {
            o = this.tope.getElem();
        }
        return o;
    }

    public boolean esVacia() {
        return this.tope == null;
    }

    public void vaciar() {
        //repetitiva cortando enlace hasta llegar al primer nodo?
        this.tope = null;
    }

    //Revisar
    public Pila clone() {
        Pila p1 = new Pila();
        Nodo aux1, aux2, n;
        if (this.tope != null) {
            aux1 = this.tope;
            /*se crea un Nodo con el elemento de la original*/
            aux2 = new Nodo(null, null);
            aux2.setElem(aux1.getElem()); //null no puede crear nodo sin elem?
            p1.tope = aux2;
            while (aux1.getEnlace() != null) {
                n = new Nodo(aux1.getEnlace().getElem(), null);
                aux2.setEnlace(n);
                aux1 = aux1.getEnlace();
                aux2 = aux2.getEnlace();
            }
        }

        return p1;
    }

    public String toString() {
        String s = "|";
        if (this.tope == null) {
            s = "la pila esta vacia";
        } else {
            Nodo n = this.tope;
            s = s + n.getElem().toString() + "|";
            n = n.getEnlace();
            while (n != null) {
                s = s + n.getElem().toString() + "|";
                n = n.getEnlace();
            }
        }
        return s;
    }
}
