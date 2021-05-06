/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author gonzalo
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoArbol(elemNuevo);
        } else {
            //si no es vacio se busca al padre (el nodo padre)
            NodoArbol nodoPadre = obtenerNodo(this.raiz, elemPadre);
            if (nodoPadre != null) {
                //si se encontro al padre se lo inserta a como uno de sus hijos
                //si el lugar es izquierda y esta vacio se inserta
                if (lugar == 'i' && nodoPadre.getIzquierdo() == null) {
                    nodoPadre.setIzquierdo(new NodoArbol(elemNuevo));
                } else {
                    //si el lugar es derecha y esta vacio se inserta
                    if (lugar == 'd' && nodoPadre.getDerecho() == null) {
                        nodoPadre.setDerecho(new NodoArbol(elemNuevo));
                    } /*si no se lo pudo insertar como hijo en el lugar INDICADO
                     simplemente no se puede insertar*/ else {
                        exito = false;
                    }
                }
            } //si el padre no existe (null) no se puede insertar
            else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null;
        if (n != null) {
            //si n tiene al elemento buscado, entonces n es el nodo padre
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                //si n no es el padre, la busqude continua con el hijo izquierdo
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                //si no se encuentra del lado izquierdo,sigue del lado derecho
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public Object padre(Object o) {
        Object pad = null;
        NodoArbol nod;
        if (!this.esVacio()) {
            /*si la raiz contiene al elemento buscado, entonces el padre es null
             pues la raiz no tiene padre*/
            if (this.raiz.getElem().equals(o)) {
                nod = null;
            } else //sino, se busca entre sus hijos
            {
                nod = padreAux(this.raiz, o);
            }
            if (nod != null) {
                pad = nod.getElem();
            }
        }
        return pad;
    }

    private NodoArbol padreAux(NodoArbol n, Object buscado) {
        /*buscado es el elemento sobre el cual hago la busqueda de su padre*/
        NodoArbol result, izq, der;
        result = null;
        if (n != null) {
            izq = n.getIzquierdo();
            der = n.getDerecho();
            //si alguno de los hijos de n tiene el elemento que se busca
            if ((izq != null && izq.getElem().equals(buscado)) || (der != null && der.getElem().equals(buscado))) //si exite alguno de los hijos de "n" que tenga al objeto "buscado"
            {
                result = n;//el padre es n
            } else {
                //sino, busca si el hijo izquierdo tiene por hijo a buscado
                result = padreAux(izq, buscado);
                if (result == null) //si del lado izquierdo no esta el objeto buscado, se lo busca por derecha
                {
                    result = padreAux(der, buscado);
                }
            }
        }
        return result;
    }

    public int altura() {
        int alt;
        alt = alturaAux(this.raiz, -1);
        return alt;
    }

    private int alturaAux(NodoArbol n, int posM) {
        //revisar
        int c1, c2;
        if (n != null) {
            //encuentra un nuevo nodo, por lo tanto la altura es una unidad mayor
            posM++;
            //se analiza la altura siguiendo el camino izquierdo primero
            c1 = alturaAux(n.getIzquierdo(), posM);
            //y luego el derecho
            c2 = alturaAux(n.getDerecho(), posM);
            //posM (posicion mayor) tendra el valor del camino de mayor
            //longitud hasta el momento
            if (c1 > c2) {
                posM = c1;
            } else {
                posM = c2;
            }
        }
        return posM;
    }

    //public int nivel(Object o) tiene que buscar cual es el nivel del objeto
    public int nivel(Object o) {
        int resultado;
        resultado = nivelAux(this.raiz, o, -1);
        return resultado;
    }

    private int nivelAux(NodoArbol n, Object buscado, int pos) {
        int result = -1;
        if (n != null) {
            pos++;
            if (n.getElem().equals(buscado)) {
                result = pos;
            } else {
                result = nivelAux(n.getIzquierdo(), buscado, pos);
                if (result < 0) {
                    result = nivelAux(n.getDerecho(), buscado, pos);
                }

            }
        }
        return result;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public ArbolBin clonar() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            Object elem = this.raiz.getElem();
            NodoArbol raizClon = new NodoArbol(elem);
            clon.raiz = raizClon;
            clonarAux(this.raiz, clon.raiz);
            //llamado al metodo recursivo con la raiz
        }
        return clon;
    }

    private void clonarAux(NodoArbol original, NodoArbol copia) {
        Object e;
        NodoArbol nodo;
        //clona el nodo que es hijo izquierdo del original
        if (original.getIzquierdo() != null) {
            e = original.getIzquierdo().getElem();
            nodo = new NodoArbol(e);
            copia.setIzquierdo(nodo);
            clonarAux(original.getIzquierdo(), copia.getIzquierdo());
        }
        //clona el nodo que es hijo derecho del original
        if (original.getDerecho() != null) {
            e = original.getDerecho().getElem();
            nodo = new NodoArbol(e);
            copia.setDerecho(nodo);
            clonarAux(original.getDerecho(), copia.getDerecho());
        }
    }

    public String toString() {
        String s = "";
        if (this.raiz != null) {
            s = concatenar(s, this.raiz);
        }
        return s;
    }

    private String concatenar(String s, NodoArbol n) {
        /*los caracteres de "s" seran los elementos de los nodos del arbol, el
         cual es recorrido en pre-orden*/
        if (n != null) {
            // nodo:---, hijo izquierdo:---,hijo derecho:---
            s = s + "padre: " + n.getElem().toString();
            if (n.getIzquierdo() != null) {
                s = s + " HI :" + n.getIzquierdo().getElem().toString();
            }
            if (n.getDerecho() != null) {
                s = s + " HD: " + n.getDerecho().getElem().toString();
            }
            s = s + "\n";
            //a "s" se le concatena el elemento del hijo izquierdo de "n"
            s = concatenar(s, n.getIzquierdo());
            /*Una vez que termine con el lado izquierdo de "n", a "s" se le 
             concatenara el elemento del hijo derecho de "n"*/
            s = concatenar(s, n.getDerecho());
        }
        return s;
    }
    /*Otra opcion en lugar de insertar en longitud()+1, puede ser:
     1) que no sea void y devuelva la ultima posicion en que se inserto el ultimo elemento
     2) que lo inserte en la primer pos y luego invertir?*/

    public Lista listarPreorden() {
        Lista list = new Lista();
        if (!this.esVacio()) {
            preordenAux(this.raiz, list);
        }
        return list;
    }

    private void preordenAux(NodoArbol n, Lista l) {
        if (n != null) {
            l.insertar(n.getElem(), l.longitud() + 1);
            preordenAux(n.getIzquierdo(), l);
            preordenAux(n.getDerecho(), l);
        }
    }

    public Lista listarPosorden() {
        Lista list = new Lista();
        if (!this.esVacio()) {
            posordenAux(this.raiz, list);
        }
        return list;
    }

    private void posordenAux(NodoArbol n, Lista l) {
        if (n != null) {
            posordenAux(n.getIzquierdo(), l);
            posordenAux(n.getDerecho(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
        }
    }

    public Lista listarInorden() {
        Lista list = new Lista();
        if (!this.esVacio()) {
            inordenAux(this.raiz, list);
        }
        return list;
    }

    private void inordenAux(NodoArbol n, Lista l) {
        if (n != null) {
            inordenAux(n.getIzquierdo(), l);
            l.insertar(n.getElem(), l.longitud() + 1);
            inordenAux(n.getDerecho(), l);
        }
    }

    //lista por niveles
    public Lista listarPorNiveles() {
        Lista l = new Lista();
        if (this.raiz != null) {
            Cola c = new Cola();
            NodoArbol actual = this.raiz;
            c.poner(actual.getElem());
            while (!c.esVacia()) {
                Object elem = c.obtenerFrente();
                c.sacar();
                l.insertar(elem, l.longitud() + 1);
                if (actual.getIzquierdo() != null) {

                }
            }
        }
        return l;
    }

    //otras operaciones (para parcial)
    public boolean verificarPatron(Lista patron) {
        boolean existeCamino = false;
        if (this.raiz != null) {
            existeCamino = verificarPatronAux(this.raiz, patron, 1);
        }
        return existeCamino;
    }

    private boolean verificarPatronAux(NodoArbol n, Lista l, int pos) {
        boolean verif = false;
        if (n != null && pos <= l.longitud()) {
            if (n.getElem().equals(l.recuperar(pos))) {
                if (n.getIzquierdo() == null && n.getDerecho() == null && pos == l.longitud()) {
                    verif = true;
                } else {
                    verif = verificarPatronAux(n.getIzquierdo(), l, pos+1);
                    if (!verif) {
                        verif = verificarPatronAux(n.getDerecho(), l, pos+1);
                    }
                }
            }
        }
        return verif;
    }
}
