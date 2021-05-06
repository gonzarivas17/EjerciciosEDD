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
public class ArbolAVL {

    private NodoAVL raiz;

    public ArbolAVL() {
        this.raiz = null;
    }

    public boolean pertenece(Comparable elem) {
        boolean existe = false;
        if (this.raiz != null) {
            existe = perteneceAux(this.raiz, elem);
        }
        return existe;
    }

    private boolean perteneceAux(NodoAVL n, Comparable buscado) {
        boolean encontrado = false;
        if (n != null) {
            if (n.getElem().compareTo(buscado) == 0) {
                encontrado = true;
            } else {
                if (buscado.compareTo(n.getElem()) < 0) {
                    encontrado = perteneceAux(n.getIzquierdo(), buscado);
                } else {
                    encontrado = perteneceAux(n.getDerecho(), buscado);
                }
            }
        }
        return encontrado;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(elem);
        } else {
            exito = insertarAux(this.raiz, elem);
            //luego de que se realice una insercion con exito(true), se debe
            //controlar que el arbol entero este balanceado (se verifica que el
            //balance de la raiz sea el correcto).La raiz puede cambiar
            if (exito) {
                this.raiz = controlarBalance(this.raiz);
            }
        }
        return exito;
    }

    private boolean insertarAux(NodoAVL n, Comparable elem) {
        /*una vez encontrado el lugar para insertar a "elem", se verifica que
         el subarbol que tiene por raiz a el correspondiente hijo de "n" este
         balanceado.
         Nota: cuando "n" es la raiz con la que se realizo el llamado, se verifica
         el balance con el hijo con el que se llamo recursivamente.
         El balance de la raiz se verifica en el metodo publico*/
        boolean exito = true;
        if (n.getElem().compareTo(elem) == 0) {
            exito = false;
        } else {
            if (elem.compareTo(n.getElem()) < 0) {
                //si "elem" es menor al elemento contenido por "n"
                if (n.getIzquierdo() == null) {
                    //y "n" no tiene hijo izquierdo
                    n.setIzquierdo(new NodoAVL(elem));
                    //se asigna como HI de "n" a un nodo que contiene a "elem"
                    n.recalcularAltura();
                } else {
                    //si "n" no tiene HI
                    exito = insertarAux(n.getIzquierdo(), elem);
                    n.setIzquierdo(controlarBalance(n.getIzquierdo()));
                }
            } else {
                //si "elem" es mayor al elemento contenido por "n"
                if (n.getDerecho() == null) {
                    //y "n" no tiene hijo derecho
                    n.setDerecho(new NodoAVL(elem));
                    //se asigna como HD de "n" a un nodo que contiene a "elem"
                    n.recalcularAltura();
                } else {
                    //si "n" no tiene HD
                    exito = insertarAux(n.getDerecho(), elem);
                    n.setDerecho(controlarBalance(n.getDerecho()));
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Comparable elem) {
        boolean exito = false;
        if (this.raiz != null) {
            if (this.raiz.getElem().compareTo(elem) == 0) {
                if (this.raiz.getIzquierdo() == null && this.raiz.getDerecho() == null) {
                    this.raiz = null;
                } else {
                    if (this.raiz.getIzquierdo() != null && this.raiz.getDerecho() != null) {
                        eliminarCaso3(this.raiz);
                    } else {
                        if (this.raiz.getIzquierdo() != null) {
                            this.raiz = this.raiz.getIzquierdo();
                        } else {
                            this.raiz = this.raiz.getDerecho();
                        }
                    }
                }
                exito = true;
            } else {
                //llamar a aliminarAux con la "raiz" y el hijo correspondiente
                if (elem.compareTo(this.raiz.getElem()) < 0) {
                    exito = eliminarAux(this.raiz, this.raiz.getIzquierdo(), elem);
                } else {
                    exito = eliminarAux(this.raiz, this.raiz.getDerecho(), elem);
                }
            }
            //si la eliminacion fue exitosa, se comprueba que la raiz este bien
            //balanceada
            if (exito) {
                this.raiz = controlarBalance(this.raiz);
            }
        }
        return exito;
    }

    private boolean eliminarAux(NodoAVL padre, NodoAVL hijo, Comparable elem) {
        //es conveniente conservar al nodo padre
        boolean exito = false;
        if (hijo != null) {
            if (hijo.getElem().compareTo(elem) == 0) {
                exito = true;
                eliminarPorCaso(padre, hijo);
                //el calculo de la altura debe hacerce desde el padre para setearlo
                //si no se ha encontrado el nodo a eliminar, continua la busqueda
                //en forma recursiva
            } else {
                //el nodo "hijo" es el padre del siguiente hijo a visitar
                if (elem.compareTo(hijo.getElem()) < 0) {
                    exito = eliminarAux(hijo, hijo.getIzquierdo(), elem);
                    if (exito) {
                        /*si la eliminacion fue exitosa, se controla el balance
                         de el nodo "hijo".
                         en caso de una rotacion, "padre" tendra asignado un nuevo
                         HI*/
                        if (hijo.getElem().compareTo(padre.getElem()) < 0) {
                            padre.setIzquierdo(controlarBalance(hijo));
                        } else {
                            padre.setDerecho(controlarBalance(hijo));
                        }
                    }
                } else {
                    exito = eliminarAux(hijo, hijo.getDerecho(), elem);
                    if (exito) {
                        /*si la eliminacion fue exitosa, se controla el balance
                         de el nodo "hijo".
                         en caso de una rotacion, "padre" tendra asignado un nuevo
                         HD*/
                        if (hijo.getElem().compareTo(padre.getElem()) < 0) {
                            padre.setIzquierdo(controlarBalance(hijo));
                        } else {
                            padre.setDerecho(controlarBalance(hijo));
                        }
                    }
                }

            }
        }
        return exito;
    }

    private void eliminarPorCaso(NodoAVL padre, NodoAVL nodoAEliminar) {

        if (nodoAEliminar.getIzquierdo() != null && nodoAEliminar.getDerecho() != null) {
            /*para el caso 3 el nodo a eliminar no desaparece, solo se cambia el
             valor de su elemento
             como el elemento que pasa a tener el nodoAEliminar es de un nodo que
             pertenece a el subarbol que tiene a nodoAEliminar como raiz, entoces,
             si nodoAEliminar es HI de padre, los hijos de nodoAElminar son menores
             que padre*/

            if (padre.getIzquierdo() != null && padre.getIzquierdo().getElem().compareTo(nodoAEliminar.getElem()) == 0) {
                eliminarCaso3(nodoAEliminar);
                padre.setIzquierdo(controlarBalance(nodoAEliminar));
            } else {
                eliminarCaso3(nodoAEliminar);
                padre.setDerecho(controlarBalance(nodoAEliminar));
            }
        } else {
            if (nodoAEliminar.getIzquierdo() == null && nodoAEliminar.getDerecho() == null) {
                if (padre.getIzquierdo() != null && padre.getIzquierdo().getElem().compareTo(nodoAEliminar.getElem()) == 0) {
                    eliminarCaso1(padre, 'i');
                } else {
                    eliminarCaso1(padre, 'd');
                }
            } else {
                if (nodoAEliminar.getIzquierdo() != null) {
                    eliminarCaso2(padre, nodoAEliminar, 'i');
                } else {
                    eliminarCaso2(padre, nodoAEliminar, 'd');
                }
            }
        }
    }

    private void eliminarCaso1(NodoAVL n, char lugar) {
        /*"n" es el nodo al cual se le va a eliminar uno de sus hijos
         "lugar" indica cual de los hijos de "n" se va a eliminar*/
        if (lugar == 'i') {
            n.setIzquierdo(null);
        } else {
            n.setDerecho(null);
        }
    }

    private void eliminarCaso2(NodoAVL padre, NodoAVL nodoAEliminar, char lugar) {
        /*"padre" es el nodo al cual se le va a eliminar uno de sus hijos
         "nodoAEliminar" es el hijo de "n" que se va a eliminar
         "lugar" indica que hijo de "nodoAElminar" se asignara como nuevo hijo
         de el nodo "padre"*/
        //si "nodoAElminar" solo tiene HI
        if (lugar == 'i') {
            //si el hijo de "padre" que se va a eliminar es HI
            if (padre.getIzquierdo() == nodoAEliminar) {
                padre.setIzquierdo(nodoAEliminar.getIzquierdo());
            } else//sino es el HD de "padre" el cual se elimina
            {
                padre.setDerecho(nodoAEliminar.getIzquierdo());
            }
        } else {//nodo a eliminar solo tiene HD
            if (padre.getIzquierdo() == nodoAEliminar) //se elimina el HI de "padre"
            {
                padre.setIzquierdo(nodoAEliminar.getDerecho());
            } else//se elimina el HD de "padre"
            {
                padre.setDerecho(nodoAEliminar.getDerecho());
            }
        }
    }

    private void eliminarCaso3(NodoAVL nodoAEliminar) {
        NodoAVL padreDeCandidato, candidato;
        padreDeCandidato = nodoAEliminar;
        candidato = padreDeCandidato.getDerecho();
        while (candidato.getIzquierdo() != null) {
            padreDeCandidato = candidato;
            candidato = candidato.getIzquierdo();
        }
        nodoAEliminar.setElem(candidato.getElem());
        //el candidato mas cercano es el nodo derecho de "nodoAEliminar"
        //por lo que es seguro que la busqueda del nodo "candidato" a eliminar
        //debe empezar por el lado derecho de "nodoAEliminar"
        /*El metodo eliminarAux realiza una busqueda recursiva del nodo a eliminar
         por lo que al regreso de la recursion se inspeccionara que los nodos
         esten bien balanceados y se actualizaran sus alturas*/
        eliminarAux(nodoAEliminar, nodoAEliminar.getDerecho(), candidato.getElem());
        /*tras eliminar el al candidato, es necesario verificar que el nodo
         "nodoAEliminar" (que no desaparece) esta balaceado*/
    }

    private NodoAVL controlarBalance(NodoAVL n) {
        int balancePadre;
        //la raiz del subarbol es "n" en un comienzo pues no se ha realizado
        //ninguna rotacion
        NodoAVL raizSubArbol = n;
        if (n != null) {
            balancePadre = calcularBalance(n);
            if (Math.abs(balancePadre) < 2) {
                n.recalcularAltura();
            } else {
                if (balancePadre < -1) {
                    //"n" esta desbalanceado hacia la derecha
                    int balanceHD = calcularBalance(n.getDerecho());
                    if (balancePadre * balanceHD >= 0) {
                        //si "n" y su HD estan desbalancedos ambos hacia la derecha
                        //se realiza una rotacion simple a izquierda
                        raizSubArbol = rotacionSimpleIzq(n);
                        System.out.println("rotacion simple izq con raiz " + raizSubArbol.getElem());
                    } else {
                        //en caso de que "n" y su HD esten desbalanceados en direcciones
                        //opuestas, se realiza una rotacion doble derecha-izquierda
                        raizSubArbol = rotacionDobleDer_Izq(n);
                        System.out.println("rotacion doble der-izq con raiz " + raizSubArbol.getElem());
                    }
                } else {
                    //"n" esta desbalanceado hacia la izquierda
                    int balanceHI = calcularBalance(n.getIzquierdo());
                    if (balancePadre * balanceHI >= 0) {
                        //si "n" y su HI estan desbalanceados ambos hacia la izquierda
                        //se realiza una rotacion simple a derecha
                        raizSubArbol = rotacionSimpleDer(n);
                        System.out.println("rotacion simple der con raiz " + raizSubArbol.getElem());
                    } else {
                        //en caso de que "n" y su HI esten desbalanceados en direcciones
                        //opuestas, se realiza una rotacion doble izquierda-derecha
                        raizSubArbol = rotacionDobleIzq_Der(n);
                        System.out.println("rotacion doble izq-der con raiz " + raizSubArbol.getElem());
                    }
                }
            }
        }
        /*una vez que se ha asegurado que el subarbol que tiene por raiz a 
         "raizSubArbol" esta balanceado, se procede a actualizar la altura de
         "raizSubArbol" y de sus hijos*/
        return raizSubArbol;
    }

    private int calcularBalance(NodoAVL n) {
        int altHI, altHD, balance;
        altHI = -1;
        altHD = -1;
        if (n.getIzquierdo() != null) {
            altHI = n.getIzquierdo().getAltura();
        }
        if (n.getDerecho() != null) {
            altHD = n.getDerecho().getAltura();
        }
        balance = altHI - altHD;
        return balance;
    }

    private NodoAVL rotacionSimpleIzq(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getDerecho();
        temp = h.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
        //se recalculan las alturas de los nodos "r" y "h"
        r.recalcularAltura();
        h.recalcularAltura();
        //eliminar los print
        System.out.println("altura de " + r.getElem() + " " + r.getAltura());
        System.out.println("altura de " + h.getElem() + " " + h.getAltura());
        return h;
    }

    private NodoAVL rotacionSimpleDer(NodoAVL r) {
        NodoAVL h, temp;
        h = r.getIzquierdo();
        temp = h.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
        //se recalculan las alturas de los nodos "r" y "h"
        r.recalcularAltura();
        h.recalcularAltura();
        //eliminar los print
        System.out.println("altura de " + r.getElem() + " " + r.getAltura());
        System.out.println("altura de " + h.getElem() + " " + h.getAltura());
        return h;
    }

    private NodoAVL rotacionDobleIzq_Der(NodoAVL padre) {
        NodoAVL raizSubArbol;
        padre.setIzquierdo(rotacionSimpleIzq(padre.getIzquierdo()));
        raizSubArbol = rotacionSimpleDer(padre);
        return raizSubArbol;
    }

    private NodoAVL rotacionDobleDer_Izq(NodoAVL padre) {
        NodoAVL raizSubArbol;
        padre.setDerecho(rotacionSimpleDer(padre.getDerecho()));
        raizSubArbol = rotacionSimpleIzq(padre);
        return raizSubArbol;
    }

    public boolean vacio() {
        return this.raiz == null;
    }

    public Lista listar() {
        Lista salida = new Lista();
        if (this.raiz != null) {
            listarAux(this.raiz, salida);
        }
        return salida;
    }

    private void listarAux(NodoAVL n, Lista l) {
        //el listado se realiza en in-orden
        if (n != null) {
            if (n.getIzquierdo() != null) {
                listarAux(n.getIzquierdo(), l);
            }
            l.insertar(n.getElem(), l.longitud() + 1);
            if (n.getDerecho() != null) {
                listarAux(n.getDerecho(), l);
            }
        }
    }

    public Lista listarRango(Comparable min, Comparable max) {
        Lista salida = new Lista();
        if (this.raiz != null) {
            listarRangoAux(this.raiz, salida, min, max);
        }
        return salida;
    }

    private void listarRangoAux(NodoAVL n, Lista l, Comparable min, Comparable max) {
        if (n != null) {
            /*Mientras el elemento contenido por "n" este dentro del rango
             el recorrido se realizara en in-orden de manera normanl*/
            if (n.getElem().compareTo(max) <= 0 && n.getElem().compareTo(min) >= 0) {
                listarRangoAux(n.getIzquierdo(), l, min, max);
                l.insertar(n.getElem(), l.longitud() + 1);
                listarRangoAux(n.getDerecho(), l, min, max);
                //si el elemento contenido por "n" no esta dentro del rango
                //es debido a que:
                //es menor a "min"
            } else {
                if (n.getElem().compareTo(min) < 0) {
                    listarRangoAux(n.getDerecho(), l, min, max);
                    //o mayor a "max"
                } else {
                    listarRangoAux(n.getIzquierdo(), l, min, max);
                }
            }
        }
    }
    
    public Comparable minimoElem(){
        Comparable minimo=null;
        if(this.raiz!=null){
            NodoAVL aux=this.raiz;
            while(aux.getIzquierdo()!=null)
                aux=aux.getIzquierdo();
            minimo=aux.getElem();
        }
        return minimo;
    }
    
    public Comparable maximoElem(){
        Comparable maximo=null;
        if(this.raiz!=null){
            NodoAVL aux=this.raiz;
            while(aux.getDerecho()!=null){
                aux=aux.getDerecho();
            }
            maximo=aux.getElem();
        }
        return maximo;
    }

    public String toString() {
        String salida = "";
        if (this.raiz != null) {
            salida = concatenar(salida, this.raiz);
        }
        return salida;
    }

    private String concatenar(String s, NodoAVL n) {
        if (n != null) {
            s = s + n.getElem().toString() + " /" + n.getAltura() + ">";
            if (n.getIzquierdo() != null) {
                s = s + "HI:" + n.getIzquierdo().getElem().toString() + "/ " + n.getIzquierdo().getAltura() + " ";
            }
            if (n.getDerecho() != null) {
                s = s + "HD:" + n.getDerecho().getElem().toString() + "/ " + n.getDerecho().getAltura() + " ";
            }
            s = s + "\n";
            s = concatenar(s, n.getIzquierdo());
            s = concatenar(s, n.getDerecho());
        }
        return s;
    }
}
