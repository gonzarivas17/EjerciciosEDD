/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GrafoDinamico;

/**
 *
 * @author gonzalo
 */
public class NodoVert {

    private Object elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    public NodoVert(Object elem, NodoVert verticeSiguiente) {
        this.elem = elem;
        this.sigVertice = verticeSiguiente;
    }

    public Object getElem() {
        return this.elem;
    }

    public void setElem(Object e) {
        this.elem = e;
    }

    public NodoVert getSigVertice() {
        return this.sigVertice;
    }

    public void setSigVertice(NodoVert verticeSig) {
        this.sigVertice = verticeSig;
    }

    public NodoAdy getPrimerAdy() {
        return this.primerAdy;
    }

    public void setPrimerAdy(NodoAdy nuevoAdy) {
        this.primerAdy = nuevoAdy;
    }
}
