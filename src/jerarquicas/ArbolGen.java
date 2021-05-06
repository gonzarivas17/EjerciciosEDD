/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Lista;

/**
 *
 * @author gonzalo
 */
public class ArbolGen {

    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        NodoGen nuevo;
        if (this.raiz == null) {
            //si el arbol esta vacio, el "nuevo" nodo será la raiz
            nuevo = new NodoGen(elemNuevo);
            this.raiz = nuevo;
        } else {
            NodoGen padre = buscarNodo(this.raiz, elemPadre);
            if (padre != null) {
                //si existe el padre, se verifica si tiene algun hijo
                NodoGen auxH = padre.getHijoIzquierdo();
                if (auxH == null) {
                    /*si no tiene hijos hasta el momento, se crea un nodo "nuevo"
                     que será su HIE (hijo izquierdo extremo)*/
                    nuevo = new NodoGen(elemNuevo);
                    padre.setHijoIzquierdo(nuevo);
                } else {
                    /*si "padre" ya tiene algun hijo, se insertara a la derecho de
                     el hijo del extremo derecho de "padre"*/
                    while (auxH.getHermanoDerecho() != null) {
                        auxH = auxH.getHermanoDerecho();
                    }
                    nuevo = new NodoGen(elemNuevo);
                    auxH.setHermanoDerecho(nuevo);
                }
            } //si el "padre" no existe en el arbol, entonces no se puede insertar
            else {
                exito = false;
            }
        }
        return exito;
    }

//verificar con traza(funciona)
    private NodoGen buscarNodo(NodoGen n, Object buscado) {
        NodoGen resul = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resul = n;
            } else {
                resul = buscarNodo(n.getHijoIzquierdo(), buscado);
                //hasta aca busco por los hijos izquierdos
                if (resul == null && n.getHermanoDerecho() != null) {
                    resul = buscarNodo(n.getHermanoDerecho(), buscado);

                }
            }
        }
        return resul;
    }

    public boolean pertenece(Object elem) {
        boolean existe = true;
        //si el elemento existe:
        if (null == buscarNodo(this.raiz, elem)) {
            existe = false;
        }
        return existe;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Object padre(Object buscado) {
        Object p = null;
        if (this.raiz != null && this.raiz.equals(buscado)) {
            p = padreAux(this.raiz, buscado);
        }
        return p;
    }

    private Object padreAux(NodoGen n, Object buscado) {
        Object p = null;
        if (n != null) {
            //el nodo aux apunta al HIE de n
            NodoGen aux = n.getHijoIzquierdo();
            //se realiza la busqueda entre los hijos de n (de izquierda a derecha)
            while (aux != null && p == null) {
                //si el elemento "buscado" esta entre los hijos de n, entonces
                if (aux.getElem().equals(buscado)) {
                    //el padre de "buscado" es el elemento apuntado por "n"
                    p = n.getElem();
                } else//si el el nodo aux no contiene a "buscado", se busca entre sus hermanos
                {
                    aux = aux.getHermanoDerecho();
                }
            }
            if (p == null) {
                //si "buscado" no esta entre los hijos de "n"
                aux = n.getHijoIzquierdo();
                while (aux != null && p == null) {
                    //se realiza la llamada de padreAux sobre cada uno 
                    p = padreAux(aux, buscado);
                    aux = aux.getHermanoDerecho();
                }
            }
        }
        return p;
    }

    public int altura() {
        int alt = -1;
        if (this.raiz != null) {
            alt = alturaAux(this.raiz, 0, 0);
        }
        return alt;
    }

    private int alturaAux(NodoGen n, int mayor, int actual) {
        //si la altura "actual" es mas grande que la "mayor"
        if (actual > mayor) {
            mayor = actual;
        }//si n tiene HIE, entonces, la altura del arbol es una unidad mayor
        if (n.getHijoIzquierdo() != null) {
            NodoGen aux = n.getHijoIzquierdo();
            //llamado de alturaAux para todos los hijos de n
            while (aux != null) {
                mayor = alturaAux(aux, mayor, actual + 1);
                aux = aux.getHermanoDerecho();
            }
        }
        return mayor;
    }

    public int nivel(Object elem) {
        int niv;
        niv = nivelAux(this.raiz, elem, 0);
        return niv;
    }

    private int nivelAux(NodoGen n, Object elem, int nivelActual) {
        /*"nivelActual" es una varible de tipo int, por lo que una llamada
        recursivas no modificaran el valor de "nivelActual" para otra llamada
        (a no ser que se retornara la varible modificada)*/
        int res = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                //si n contiene al elemento buscado,entonces, "res" es el ultimo
                //nivel hasta el momento
                res = nivelActual;
            } else {//si no se encuntra
                //y n tiene al menos el HEI
                if (n.getHijoIzquierdo() != null) {
                    NodoGen aux = n.getHijoIzquierdo();
                    //se realiza la busqueda para todos sus posibles hijos
                    //hasta encontrarlo o que ya no queden nodos por recorrer
                    while (aux != null && res == -1) {
                        res = nivelAux(aux, elem, nivelActual + 1);
                        aux = aux.getHermanoDerecho();
                    }
                }
            }
        }
        return res;
    }
//revisar si elem tambien se inserta
    public Lista ancestros(Object elem) {
        Lista salida = new Lista();
        if (this.raiz != null) {
            if (this.raiz.equals(elem)) {
                salida.insertar(elem, 1);
            } else {
                ancestrosAux(this.raiz, elem, salida);
            }
        }
        return salida;
    }

    private boolean ancestrosAux(NodoGen n, Object elem, Lista l) {
        boolean encontrado = false;
        if (n != null) {
            //si tiene hijos (al menos el HIE)
            if (n.getHijoIzquierdo() != null) {
                NodoGen aux = n.getHijoIzquierdo();
                //se realiza la busqueda de "elem" entre los hijos de n
                while (aux != null && !encontrado) {
                    //si "elem" es encontrado, se detiene la busqueda
                    if (aux.getElem().equals(elem)) {
                        encontrado = true;
                        //y se lo inserta en la lista
                        l.insertar(aux.getElem(), 1);
                    } else {
                        //si aun no se ha encontrado a "elem"
                        //se realiza el llamado de ancestrosAux sobre los hijos
                        //de n (aux), siendo cada hijo la raiz de su correspondinte sub-arbol
                        encontrado = ancestrosAux(aux, elem, l);
                        aux = aux.getHermanoDerecho();
                    }
                }
                if (encontrado) {
                    l.insertar(n.getElem(), 1);
                }
            }
        }
        return encontrado;
    }
    
    public ArbolGen clonar(){
        ArbolGen nuevo=new ArbolGen();
        if(this.raiz!=null){
            NodoGen n=new NodoGen(this.raiz.getElem());
            nuevo.raiz=n;
            clonarAux(this.raiz,nuevo.raiz);
        }
        return nuevo;
    }
    
    private void clonarAux(NodoGen original,NodoGen copia){
        /*si el nodo original tiene hijos, primero se clona el HEI y luego sus
        hermanos*/
        if(original.getHijoIzquierdo()!=null){
            NodoGen nuevo,aux,aux1;
            //aux apunta al HEI del nodo original
            aux=original.getHijoIzquierdo();
            //se crea un nuevo nodo que contiene el elemento de aux
            nuevo=new NodoGen(aux.getElem());
            //se enlaza el nodo "nuevo" al nodo "copia" del arbol nuevo
            copia.setHijoIzquierdo(nuevo);
            //aux apunta al nuevo nodo a copiar
            aux=aux.getHermanoDerecho();
            //aux1 apunta al HEI del arblo nuevo (el nodo anterior al que apunta
            //aux (en el arbol original)
            aux1=copia.getHijoIzquierdo();
            while(aux!=null){
                /*se copian todos los hijos del nodo "original" y cada copia se
                coloca como hijo del nodo copia*/
                nuevo=new NodoGen(aux.getElem());
                //aux1 es el puntero al que es el ultimo hijo insertado del
                //nodo "copia" (el ultimo hasta el momento)
                aux1.setHermanoDerecho(nuevo);
                /*aux apunta al hijo siguiente de "original" y aux1 al siguiente
                hijo de "copia" que es el nodo anterior (en el arbol original)
                al que apunta aux*/
                aux=aux.getHermanoDerecho();
                aux1=aux1.getHermanoDerecho();
            }
            aux=original.getHijoIzquierdo();
            aux1=copia.getHijoIzquierdo();
            //llamada recursiva de clonarAux sobre cada uno de los hijos de "original"
            while(aux!=null){
                clonarAux(aux,aux1);
                aux=aux.getHermanoDerecho();
                aux1=aux1.getHermanoDerecho();
            }
        }
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            //visita del nodo
            s += n.getElem().toString() + "->";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ",";
                hijo = hijo.getHermanoDerecho();
            }
            /*comienza recorrido de los hijos de n llamando recursivamente
             para que cada hijo agregue su subcadena a la general*/
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista l) {
        if (n != null) {
            //visita del nodo
            l.insertar(n.getElem(), l.longitud() + 1);
            //si tiene hijo/s
            if (n.getHijoIzquierdo() != null) {
                //se crea un nodo "hijo" que apunta al HIE
                NodoGen hijo = n.getHijoIzquierdo();
                //se realiza pre-orden del nodo "hijo" y sus posibles hermanos
                while (hijo != null) {
                    listarPreordenAux(hijo, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista l) {
        if (n != null) {
            //llamado recursivo con el primer hijo de n
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), l);
            }
            //visar nodo n
            l.insertar(n.getElem(), l.longitud() + 1);

            //llamados recursivos con los otros hijos
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista l) {
        if (n != null) {
            //evita llamar a los metodos con null
            //si tiene hijo izquierdo
            if (n.getHijoIzquierdo() != null) {
                //se crea un nodo hijo con el HEI de n
                NodoGen hijo = n.getHijoIzquierdo();
                //realizo pos-orden de cada uno de los hijos de n
                while (hijo != null) {
                    listarPosordenAux(hijo, l);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            /*cuando n ya no cuenta con hijos para realizar pos-orden, se
             visita al nodo n*/
            l.insertar(n.getElem(), l.longitud() + 1);
        }
    }
    
    public Lista listarPorNiveles(){
        Lista salida=new Lista();
        if(this.raiz!=null){
            salida.insertar(this.raiz.getElem(), salida.longitud()+1);
            
        }
        return salida;
    }
    
    private void listarPorNivelesAux(NodoGen n,Lista l){
        if(n.getHijoIzquierdo()!=null){
            NodoGen hijo=n.getHijoIzquierdo();
        }
    }
}
