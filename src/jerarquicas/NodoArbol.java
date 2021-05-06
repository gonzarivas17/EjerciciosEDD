/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

/**
 *
 * @author gonzalo
 */
public class NodoArbol {
  
    private Object elem;
    private NodoArbol izquierdo;
    private NodoArbol derecho;
    
    public NodoArbol(Object e){
        this.elem=e;
        this.izquierdo=null;
        this.derecho=null;
    }
    
    public NodoArbol(Object e, NodoArbol izq,NodoArbol der){
        this.elem=e;
        this.izquierdo=izq;
        this.derecho=der;
    }
    
    public Object getElem(){
        return this.elem;
    }
    
    public NodoArbol getIzquierdo(){
        return this.izquierdo;
    }
    
    public NodoArbol getDerecho(){
        return this.derecho;
    }
    
    public void setElem(Object e){
        this.elem=e;
    }
    
    public void setIzquierdo(NodoArbol izq){
        this.izquierdo=izq;
    }
    
    public void setDerecho(NodoArbol der){
        this.derecho=der;
    }
}
