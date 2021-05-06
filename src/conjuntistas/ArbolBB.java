/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;
import lineales.dinamicas.Lista;
/**
 *
 * @author gonzalo
 */
public class ArbolBB{

    private NodoArbol raiz;

    public ArbolBB() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        boolean encontrado = false;
        if (this.raiz != null) {
            NodoArbol aux = this.raiz;
            while (aux != null && !encontrado) {
                if (aux.getElem().compareTo(elem) == 0) {
                    encontrado = true;
                } else {
                    if (aux.getElem().compareTo(elem) > 0) {
                        aux = aux.getIzquierdo();
                    } else {
                        aux = aux.getDerecho();
                    }
                }
            }
        }
        return encontrado;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elem);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoArbol n, Comparable elem) {
        //precondicion, n no es null
        boolean exito = true;
        if (n.getElem().compareTo(elem) == 0) {
            //elemento repetido
            exito = false;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                //elemento es menor que el elemento de n
                //si tiene HI baja a la izquierda,sino agrega elemento
                if (n.getIzquierdo() != null) {
                    exito = insertarAux(n.getIzquierdo(), elem);
                } else {
                    n.setIzquierdo(new NodoArbol(elem));
                }
            } else {//elemento mayor que n.getElem()
                //si tiene HD baja a la derecha, sino agrega el elemento
                if (n.getDerecho() != null) {
                    exito = insertarAux(n.getDerecho(), elem);
                } else {
                    n.setDerecho(new NodoArbol(elem));
                }
            }
        }
        return exito;
    }
    
    public boolean eliminar(Comparable elem){
        boolean exito=false;
        if(this.raiz!=null){
            if(this.raiz.getElem().compareTo(elem)==0){
                //modularizar la eliminacin de la raiz
                if(this.raiz.getIzquierdo()==null && this.raiz.getDerecho()==null){
                    this.raiz=null;
                }
                else{
                    if(this.raiz.getIzquierdo()!=null && this.raiz.getDerecho()!=null)
                        eliminarCaso3(this.raiz);
                    else{
                        if(this.raiz.getIzquierdo()!=null){
                            this.raiz=this.raiz.getIzquierdo();
                        }
                        else{
                            this.raiz=this.raiz.getDerecho();
                        }
                    }
                }
                exito=true;
            }
            else{
                if(elem.compareTo(this.raiz.getElem())<0){
                    exito=eliminarAux(this.raiz,this.raiz.getIzquierdo(),elem);
                }
                else{
                    exito=eliminarAux(this.raiz,this.raiz.getDerecho(),elem);
                }
            }
        }
        return exito;
    }
    
    private boolean eliminarAux(NodoArbol padre,NodoArbol hijo, Comparable buscado){
        boolean exito=false;
        if(hijo!=null){
            if(hijo.getElem().compareTo(buscado)==0){
                exito=true;
                eliminarPorCaso(padre,hijo);
            }
            else{
                padre=hijo;
                if(buscado.compareTo(hijo.getElem())<0){
                    exito=eliminarAux(padre,hijo.getIzquierdo(),buscado);
                    //recalcular la altura
                    //al estar volviendo, en el ultimo llamado padre ya sabe la
                    //altura de sus hijos 
                }
                else{
                    exito=eliminarAux(padre,hijo.getDerecho(),buscado);
                    //recalcular la altura
                }
            }
        }
        return exito;
    }
    
    private void eliminarPorCaso(NodoArbol padre,NodoArbol nodoAEliminar){
        if(nodoAEliminar.getIzquierdo()==null && nodoAEliminar.getDerecho()==null){
            eliminarCaso1(padre,nodoAEliminar);
        }
        else{
            if(nodoAEliminar.getIzquierdo()!=null && nodoAEliminar.getDerecho()!=null){
                eliminarCaso3(nodoAEliminar);
            }
            else{//puedo indicar con un char q hijo se va a eliminar
                eliminarCaso2(padre,nodoAEliminar);
            }
        }
    }
       
    private void eliminarCaso1(NodoArbol padre,NodoArbol hijo){
        //padre mayor que hijo
        if(padre.getElem().compareTo(hijo.getElem())>0){
            padre.setIzquierdo(null);
        }
        else{
            padre.setDerecho(null);
        }
    }
    
    private void eliminarCaso2(NodoArbol padre,NodoArbol hijo){
        //"hijo" es HI de "padre"
        if(hijo.getElem().compareTo(padre.getElem())<0){
            if(hijo.getIzquierdo()!=null){
                //si "hijo" tiene HI
                padre.setIzquierdo(hijo.getIzquierdo());
            }
            else{
                padre.setIzquierdo(hijo.getDerecho());
            }
        }else{
            if(hijo.getIzquierdo()!=null){
                padre.setDerecho(hijo.getIzquierdo());
            }
            else{
                padre.setDerecho(hijo.getDerecho());
            }
        }
    }
    
    private void eliminarCaso3(NodoArbol n){
        //precondicion, n no es nulo, pues es el nodo encontrado a eliminar
        NodoArbol candidato,padreDeCand;
        padreDeCand=n;
        //como es caso 3 (tiene ambos hijos) es seguro que el HD no es nulo
        candidato=n.getDerecho();
        //si "candidato" no tiene HI, entonces, "candidato" contiene al menor
        //valor del subarbol derecho
        while(candidato.getIzquierdo()!=null){
            //padre se actualiza, cambiando su referencia al nodo que candidato
            //tenia antes
            padreDeCand=candidato;
            //se busca en el subarbol izquierdo de "candidato" al menor valor
            candidato=candidato.getIzquierdo();
        }
        //"n" pasa a tener el valor de "candidato"
        n.setElem(candidato.getElem());
        eliminarPorCaso(padreDeCand,candidato);
        
    }
    
    public Lista listar(){
        Lista salida=new Lista();
        if(this.raiz!=null){
            listarAux(this.raiz,salida);
        }
        return salida;
    }
    
    private void listarAux(NodoArbol n,Lista l){
        if(n!=null){
            if(n.getIzquierdo()!=null){
                listarAux(n.getIzquierdo(),l);
            }
            l.insertar(n.getElem(), l.longitud()+1);
            if(n.getDerecho()!=null){
                listarAux(n.getDerecho(),l);
            }
        }
    }
    
    public Lista listarRango(Comparable menor,Comparable mayor){
        Lista salida=new Lista();
        if(this.raiz!=null){
            listarRango(this.raiz,menor,mayor,salida);
        }
        return salida;
    }
    //buscar como ordenarlos
    private void listarRango(NodoArbol n,Comparable menor,Comparable mayor,Lista l){
        //listado en pre-orden
        if(n!=null){
            if(n.getElem().compareTo(menor)>=0 && n.getElem().compareTo(mayor)<=0){
                l.insertar(n.getElem(), l.longitud()+1);
            }
            listarRango(n.getIzquierdo(),menor,mayor,l);
            listarRango(n.getDerecho(),menor,mayor,l);
        }
    }
    
    public Comparable minimoElem(){
        Comparable salida=null;
        if(this.raiz!=null){
            NodoArbol aux=this.raiz;
            while(aux.getIzquierdo()!=null){
                aux=aux.getIzquierdo();
            }
            salida=aux.getElem();
        }
        return salida;
    }
    
    public Comparable maximoElem(){
        Comparable salida=null;
        if(this.raiz!=null){
            NodoArbol aux=this.raiz;
            while(aux.getDerecho()!=null){
                aux=aux.getDerecho();
            }
            salida=aux.getElem();
        }
        return salida;
    }
    
    public boolean vacio(){
        return this.raiz==null;
    }
}
