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
public class NodoGen {
    private Object elem;
    private NodoGen hijoIzquierdo;
    private NodoGen hermanoDerecho;
    
    public NodoGen(Object e){
        this.elem=e;
    }
    
    public Object getElem(){
        return this.elem;
    }
    
    public NodoGen getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    
    public NodoGen getHermanoDerecho(){
        return this.hermanoDerecho;
    }
    
    public void setElem(Object e){
        this.elem=e;
    }
    
    public void setHijoIzquierdo(NodoGen hI){
        this.hijoIzquierdo=hI;
    }
    
    public void setHermanoDerecho(NodoGen hD){
        this.hermanoDerecho=hD;
    }
}
