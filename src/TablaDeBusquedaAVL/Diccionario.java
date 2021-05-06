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
public class Diccionario {
    private NodoAVLDicc raiz;
    
    public Diccionario(){
        this.raiz=null;
    }
    
    public Object obtenerDato(Comparable clave){
        if(this.raiz!=null){
            
        }
        return null;
    }
    
    private NodoAVLDicc buscarNodo(Comparable clave){
        return null;
    }
    
    public boolean existeClave(Comparable clave){
        boolean existe=false;
        if(this.raiz!=null){
            existe=existeClaveAux(this.raiz,clave);
        }
        return existe;
    }
    
    public boolean existeClaveAux(NodoAVLDicc n, Comparable clave){
        boolean existe=false;
        if(n!=null){
            if(n.getClave().compareTo(clave)==0){
                existe=true;
            }else{
                if(clave.compareTo(n.getClave())<0){
                    existe=existeClaveAux(n.getHijoIzquierdo(),clave);
                }else{
                    existe=existeClaveAux(n.getHijoDerecho(),clave);
                }
            }
        }
        return existe;
    }
}
