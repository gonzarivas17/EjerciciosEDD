/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TablaDeBusquedaAVL;

/**
 *
 * @author gonzalo
 */
public class NodoAVLDicc {

    private Comparable clave;
    private Object dato;
    private int altura;
    private NodoAVLDicc hijoIzquierdo;
    private NodoAVLDicc hijoDerecho;

    public NodoAVLDicc(Comparable clave, Object dato) {
        this.clave = clave;
        this.dato = dato;
        this.altura = 0;
    }

    public Comparable getClave() {
        return clave;
    }

    public void setClave(Comparable clave) {
        this.clave = clave;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVLDicc getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoAVLDicc hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoAVLDicc getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoAVLDicc hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

}
