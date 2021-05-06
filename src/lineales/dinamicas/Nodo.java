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
public class Nodo {

    private Object elem;
    private Nodo enlace;

    public Nodo(Object e, Nodo enlace) {
        this.elem = e;
        this.enlace = enlace;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object e) {
        this.elem = e;
    }

    public Nodo getEnlace() {
        return this.enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }
}
