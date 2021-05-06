/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author gonzalo
 */
public class NodoArbol {
    private Comparable elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Comparable elem){
        this.elem=elem;
    }
    
    public Comparable getElem(){
        return this.elem;
    }
    
    public void setElem(Comparable elem){
        this.elem=elem;
    }
    
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    
    public void setIzquierdo(NodoArbol izq){
        this.izquierdo=izq;
    }
    
    public NodoArbol getDerecho(){
        return this.derecho;
    }
    
    public void setDerecho(NodoArbol der){
        this.derecho=der;
    }
}
