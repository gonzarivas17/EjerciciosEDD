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
public class NodoAdy {

    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private int etiqueta;

    public NodoAdy(NodoVert vertice) {
        //vertice es el nodo al cual apunta el NodoAdy
        this.vertice = vertice;
    }

    public NodoVert getVertice() {
        return this.vertice;
    }

    public void setVertice(NodoVert verticeNuevo) {
        this.vertice = verticeNuevo;
    }

    public NodoAdy getSigAdyacente() {
        return this.sigAdyacente;
    }

    public void setSigAdyacente(NodoAdy adySig) {
        this.sigAdyacente = adySig;
    }

    public int getEtiqueta() {
        return this.etiqueta;
    }

    public void setEtiqueta(int etiqueta) {
        this.etiqueta = etiqueta;
    }
}
