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
public class NodoAVL {
    private Comparable elem;
    private int altura;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    
    public NodoAVL(Comparable elem){
        this.elem=elem;
        this.altura=0;
    }
    
    public Comparable getElem(){
        return this.elem;
    }
    
    public void setElem(Comparable e){
        this.elem=e;
    }
    
    public NodoAVL getIzquierdo(){
        return this.izquierdo;
    }
    
    public void setIzquierdo(NodoAVL izq){
        this.izquierdo=izq;
    }
    
    public NodoAVL getDerecho(){
        return this.derecho;
    }
    
    public void setDerecho(NodoAVL der){
        this.derecho=der;
    }
    
    public int getAltura(){
        return this.altura;
    }
    
    public void recalcularAltura(){
        int altIzq,altDer;
        altIzq=-1;
        altDer=-1;
        if(this.getIzquierdo()!=null)
            altIzq=this.getIzquierdo().getAltura();
        if(this.getDerecho()!=null)
            altDer=this.getDerecho().getAltura();
        if(altIzq<altDer)
            this.altura=altDer+1;
        else
            this.altura=altIzq+1;
    }
}
